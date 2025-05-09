package escenario;


import java.util.*;
import entidad.EntidadMovibleEnemigo;
import entidad.EntidadMovibleEnemigoFactory;
import sonido.SonidoCodigo;
//Clase Singleton que gestiona todos los enemigos del juego.
//Controla su aparición aleatoria según dificultad, su movimiento, 
//su interacción con el fuego y su representación visual en el escenario.
public class EscenarioEnemigos {

    private Random random = new Random();
    private Escenario_CONFIG esCfg = new Escenario_CONFIG();

    private static EscenarioEnemigos misEnemigos;
    private EscenarioFacade miEscenarioFacade;
    private ArrayList<EntidadMovibleEnemigo> listaEnemigos = new ArrayList<>();
    private int contadorSpawnear;
    private boolean visible;
    private boolean win = false;
    private ArrayList<String> listaSonidos = new ArrayList<>();
    private double dificultad;

    // Constructor privado para Singleton
    private EscenarioEnemigos() {}

    // Devuelve la única instancia (Singleton)
    public static EscenarioEnemigos getEnemigos() {
        if (misEnemigos == null) {
            misEnemigos = new EscenarioEnemigos();
        }
        return misEnemigos;
    }

    
    public void darValoresColumnasFilas () {}

    // Inicializa enemigos en posiciones aleatorias según la dificultad
    public void inicializarEnemigos(ArrayList<int[]> posicionesVacias, String pDificultad) {
        win = false;
        miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
        contadorSpawnear = 60;
        visible = false;
        switch(pDificultad){
            case "PACIFICO": dificultad = 0; break;
            case "FACIL":    dificultad = 0.075; break;
            case "NORMAL":   dificultad = 0.15; break;
            case "DIFICIL":  dificultad = 0.25; break;
            default:         dificultad = 0.075; break;
        }

        listaEnemigos.clear();
        for (int[] posV : posicionesVacias) {
            if (posV[0] + posV[1] > 2 && random.nextDouble() < dificultad) {
                EntidadMovibleEnemigo enemigo = (random.nextDouble() < dificultad * 2) ?
                        EntidadMovibleEnemigoFactory.getEntidadMovibleEnemigoFactory().generate("DORIA", posV[0], posV[1]) :
                        EntidadMovibleEnemigoFactory.getEntidadMovibleEnemigoFactory().generate("BALOON", posV[0], posV[1]);
                listaEnemigos.add(enemigo);
            }
        }
    }

    // Actualiza el estado de los enemigos, notifica al jugador si hay colisiones
    public int actualizarTicksEnemigos() {
        int puntos = 0;
        if (contadorSpawnear > 0) {
            contadorSpawnear--;
            if (contadorSpawnear % 5 == 0) visible = !visible;
            if (contadorSpawnear == 0) visible = true;
        } else {
            for (int i = 0; i < listaEnemigos.size(); i++) {
                EntidadMovibleEnemigo pEnemigo = listaEnemigos.get(i);
                if (pEnemigo.tick()) {
                    actualizarPosicionEnemigo(i);
                    pEnemigo.resetTick();
                    i--;
                }
            }
            for (EntidadMovibleEnemigo enemigo : listaEnemigos) {
                puntos += miEscenarioFacade.gestionarEnemigo(enemigo.getPosX(), enemigo.getPosY());
            }
        }
        return puntos;
    }

    // Calcula y aplica el nuevo movimiento del enemigo, si no hay colisión
    private void actualizarPosicionEnemigo(int i) {
        int posEX = listaEnemigos.get(i).getPosX();
        int posEY = listaEnemigos.get(i).getPosY();
        int posEXnew = posEX, posEYnew = posEY;
        boolean movimientoChoca = false;
        boolean persigueJugador = false;
        int mov = random.nextInt(4) + 1;

        // Movimiento dirigido si es enemigo tipo DORIA y el jugador está cerca
        if (listaEnemigos.get(i).getCodigoEnemigo() == 41 &&
            (Math.abs(miEscenarioFacade.getPosXJ() - posEX) <= 4 ||
             Math.abs(miEscenarioFacade.getPosYJ() - posEY) <= 4) &&
            mov <= 3) {

            if (Math.abs(miEscenarioFacade.getPosXJ() - posEX) >= Math.abs(miEscenarioFacade.getPosYJ() - posEY)) {
                posEXnew = (miEscenarioFacade.getPosXJ() - posEX < 0 && posEX != 0) ? posEX - 1 : posEXnew;
                posEXnew = (miEscenarioFacade.getPosXJ() - posEX > 0 && posEX != esCfg.col - 1) ? posEX + 1 : posEXnew;
            } else {
                posEYnew = (miEscenarioFacade.getPosYJ() - posEY < 0 && posEY != 0) ? posEY - 1 : posEYnew;
                posEYnew = (miEscenarioFacade.getPosYJ() - posEY > 0 && posEY != esCfg.fil - 1) ? posEY + 1 : posEYnew;
            }

            persigueJugador = true;
        }

        // Movimiento aleatorio si es BALOON o no hay persecución
        if (listaEnemigos.get(i).getCodigoEnemigo() == 40 || !persigueJugador) {
            posEXnew = (mov == 1 && posEX != 0) ? posEX - 1 : posEXnew;
            posEXnew = (mov == 2 && posEX != esCfg.col - 1) ? posEX + 1 : posEXnew;
            posEYnew = (mov == 3 && posEY != 0) ? posEY - 1 : posEYnew;
            posEYnew = (mov == 4 && posEY != esCfg.fil - 1) ? posEY + 1 : posEYnew;
        }

        movimientoChoca = comprobarMovimiento(mov, posEX, posEY, posEXnew, posEYnew);

        // Intenta esquivar si hay persecución y colisión
        if (movimientoChoca && persigueJugador) {
            if (posEXnew != posEX) {
                posEXnew = posEX;
                posEYnew = (miEscenarioFacade.getPosYJ() - posEY < 0 && posEY != 0) ? posEY - 1 : posEYnew;
                posEYnew = (miEscenarioFacade.getPosYJ() - posEY > 0 && posEY != esCfg.fil - 1) ? posEY + 1 : posEYnew;
                movimientoChoca = comprobarMovimiento(mov, posEX, posEY, posEXnew, posEYnew);
            } else if (posEYnew != posEY) {
                posEYnew = posEY;
                posEXnew = (miEscenarioFacade.getPosXJ() - posEX < 0 && posEX != 0) ? posEX - 1 : posEXnew;
                posEXnew = (miEscenarioFacade.getPosXJ() - posEX > 0 && posEX != esCfg.col - 1) ? posEX + 1 : posEXnew;
                movimientoChoca = comprobarMovimiento(mov, posEX, posEY, posEXnew, posEYnew);
            }
        }

        if (!movimientoChoca) {
            listaEnemigos.get(i).setPosX(posEXnew);
            listaEnemigos.get(i).setPosY(posEYnew);
        }
    }

    // Comprueba si el nuevo movimiento choca con límites, bombas, bloques o enemigos
    private boolean comprobarMovimiento(int mov, int posEX, int posEY, int posEXnew, int posEYnew) {
        boolean choca = ((mov == 1 && posEX == 0) ||
                         (mov == 2 && posEX == esCfg.col - 1) ||
                         (mov == 3 && posEY == 0) ||
                         (mov == 4 && posEY == esCfg.fil - 1) ||
                         miEscenarioFacade.chocaConPos(posEXnew, posEYnew));
        for (EntidadMovibleEnemigo enemigo : listaEnemigos) {
            if (posEXnew == enemigo.getPosX() && posEYnew == enemigo.getPosY()) {
                choca = true;
            }
        }
        return choca;
    }

    // Gestiona el efecto del fuego: elimina enemigos en la posición y suma puntos
    public int gestionarFuego(int posFX, int posFY) {
        int puntos = 0;
        for (int i = 0; i < listaEnemigos.size(); i++) {
            if (listaEnemigos.get(i).getPosX() == posFX && listaEnemigos.get(i).getPosY() == posFY) {
                listaSonidos.add(SonidoCodigo.ENEMY_DEATH.parar());
                listaSonidos.add(SonidoCodigo.ENEMY_DEATH.sonar());
                puntos += (listaEnemigos.get(i).getCodigoEnemigo() == 40) ? 100 : 120;
                listaEnemigos.remove(i);
                i--;
            }
            if (listaEnemigos.isEmpty()) {
                win = true;
                miEscenarioFacade.gestionarPlayerWin();
            }
        }
        return puntos;
    }

    // Genera matriz con las posiciones de los enemigos visibles
    public int[][] generarMatrizAniadirEnemigos() {
        int[][] matriz = new int[esCfg.col][esCfg.fil];
        if (visible && !win) {
            for (EntidadMovibleEnemigo e : listaEnemigos) {
                matriz[e.getPosX()][e.getPosY()] = e.getCodigoEnemigo();
            }
        }
        return matriz;
    }

    // Devuelve los sonidos generados por los enemigos
    public String[] getListaSonidos() {
        return listaSonidos.toArray(new String[0]);
    }

    // Limpia la lista de sonidos generados
    public void listaSonidosClear() {
        listaSonidos.clear();
    }

    // Reinicia el estado de victoria
    public void resetWin() {
        win = false;
    }
}
