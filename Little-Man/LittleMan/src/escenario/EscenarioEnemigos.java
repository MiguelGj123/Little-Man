package escenario;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import entidad.EntidadMovibleEnemigo;
import entidad.EntidadMovibleEnemigoFactory;
import sonido.SonidoCodigos;
import sonido.SonidoCodigosEnum;



public class EscenarioEnemigos {
	
	Random random = new Random();

	private int COLUMNAS, FILAS;
	private static EscenarioEnemigos misEnemigos;
	private EscenarioFacade miEscenarioFacade;
    private ArrayList<EntidadMovibleEnemigo> listaEnemigos = new ArrayList<EntidadMovibleEnemigo>();
    private int contadorSpawnear;
    private boolean visible;
    private boolean win=false;
    private ArrayList<String> listaSonidos = new ArrayList<String>();
    private double dificultad;

    
    private EscenarioEnemigos() {}
    
    public static EscenarioEnemigos getEnemigos(){
    	if (misEnemigos == null) {
    		misEnemigos = new EscenarioEnemigos();
    	}
    	return misEnemigos;
    }
    
    
    public void darValoresColumnasFilas (int COLUMNAS, int FILAS) {
    	this.COLUMNAS = COLUMNAS;
    	this.FILAS = FILAS;
    }
	
	public void inicializarEnemigos(ArrayList<int[]> posicionesVacias, int COLUMNAS, int FILAS, String pDificultad) {
		win=false;
		this.COLUMNAS = COLUMNAS;
		this.FILAS = FILAS;
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
		contadorSpawnear = 60;
		visible = false;
		switch(pDificultad){
			case "pacifico":
				dificultad=0;
				break;
			case "facil":
				dificultad=0.075;
				break;
			case "normal":
				dificultad=0.15;
				break;
			case "dificil":
				dificultad=0.25;
				break;
			default:
				dificultad=0.075;
				break;
		}
		
		listaEnemigos.clear();
		Iterator<int[]> posicionesVaciasIterator = posicionesVacias.iterator();
		EntidadMovibleEnemigo enemigo;
		
		while (posicionesVaciasIterator.hasNext())
		{
			int[] posV = posicionesVaciasIterator.next();
			enemigo = null;
			
			if (posV[0] + posV[1] > 2 && random.nextDouble() < dificultad) {
				 enemigo = EntidadMovibleEnemigoFactory.getEntidadMovibleEnemigoFactory().generate("BALOON", posV[0], posV[1]);
				 listaEnemigos.add(enemigo);
			}
		}
	}
	
	public void actualizarTicksEnemigos() {
		if (contadorSpawnear > 0) {
			contadorSpawnear--;
			if (contadorSpawnear%5 == 0) visible = !visible;
			if (contadorSpawnear == 0) visible = true;
		} else {
			for ( int i=0; i<listaEnemigos.size(); i++) {
				EntidadMovibleEnemigo pEnemigo = listaEnemigos.get(i);								// Obtenemos el Enemigo que queremos actualizar
				
				if (pEnemigo.tick()) {																// Comprobamos si al actualizar termina su cuenta atras del enemigo
					actualizarPosicionEnemigo(i);													// Gestiona el movimiento del enemigo
					pEnemigo.resetTick();
					i--;																			// El elemento i+1 de la lista pasa a ser el elemento i, por lo que para actualizarlo es necesario retroceder un elemento en la lista
				}
			}
			for ( int i = 0; i < listaEnemigos.size(); i++) {
				miEscenarioFacade.gestionarEnemigo(listaEnemigos.get(i).getPosX(),listaEnemigos.get(i).getPosY());						// El enemigo se encarga de notificar a los jugadores que en esa posicion hay un enemigo
			}
		}
	}
	
	
	private void actualizarPosicionEnemigo(int i) {			
		int posEX=listaEnemigos.get(i).getPosX();												// posicion X actual del Enemigo	[COLUMNA]
		int posEY=listaEnemigos.get(i).getPosY();												// posicion Y actual del Enemigo	[FILA]
		int posEXnew = posEX, posEYnew = posEY;													// posiciones XY nuevas despues del movimiento
		boolean movimientoEnemigoChocaConParedOBomba = false;
		
		int mov = random.nextInt(4) + 1 ;
		
		posEXnew = (mov == 1  && posEX != 0			  ) ? posEX - 1 : posEXnew;
		posEXnew = (mov == 2  && posEX != COLUMNAS - 1) ? posEX + 1 : posEXnew;
		posEYnew = (mov == 3  && posEY != 0			  ) ? posEY - 1 : posEYnew;
		posEYnew = (mov == 4  && posEY != FILAS - 1   ) ? posEY + 1 : posEYnew;
		
		movimientoEnemigoChocaConParedOBomba = 													// Enemigo choca contra pared o bomba si
				(  ( mov == 1  && posEX == 0			)										// Choca contra exterior
				|| ( mov == 2  && posEX == COLUMNAS - 1	)
				|| ( mov == 3  && posEY == 0			)
				|| ( mov == 4  && posEY == FILAS - 1	)
				|| miEscenarioFacade.chocaConPos(posEXnew, posEYnew));					// o si hay una bomba o bloque en la nueva posicion
		
		for ( int j=0; j<listaEnemigos.size(); j++) {											// Choca contra otro Enemigo?
			movimientoEnemigoChocaConParedOBomba =
				  (  posEXnew == listaEnemigos.get(j).getPosX()
				  && posEYnew == listaEnemigos.get(j).getPosY()) ?
							true : movimientoEnemigoChocaConParedOBomba;
		}
		
		if (!movimientoEnemigoChocaConParedOBomba) {
			listaEnemigos.get(i).setPosX(posEXnew);
			listaEnemigos.get(i).setPosY(posEYnew);
		}

	}
	
	
	
	public void gestionarFuego(int posFX, int posFY) {
		for (int i = 0; i < listaEnemigos.size(); i++) {
			if (listaEnemigos.get(i).getPosX() == posFX && listaEnemigos.get(i).getPosY() == posFY) {
				listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoPararSonido(SonidoCodigosEnum.ENEMY_DEATH));
				listaSonidos.add(SonidoCodigos.getSonidoCodigos().getCodigoSonarSonido(SonidoCodigosEnum.ENEMY_DEATH));
				listaEnemigos.remove(i);
				i--;
			}
			if (listaEnemigos.isEmpty()){
				win=true;
			}
		}
	}
	
	
	public int[][][] generarMatrizAniadirBloques(int[][][] matrizEditar){
		if (visible) {
			if (!win) {
				for (EntidadMovibleEnemigo pEnemigo : listaEnemigos) {
					matrizEditar[pEnemigo.getPosX()][pEnemigo.getPosY()][2] = pEnemigo.getCodigoEnemigo();
				}
			} else {
				matrizEditar[0][0][2]=1;
			}
		}
		return matrizEditar;
	}
	
	public String[] getListaSonidos() {
	    return listaSonidos.toArray(new String[0]);
	}
	
	public void listaSonidosClear() {
		listaSonidos.clear();
	}


	
	
	public void resetWin() {win=false;}
    
}
