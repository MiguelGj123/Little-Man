package escenario;
import java.util.ArrayList;

public class EscenarioFacade {
	
	private static EscenarioFacade miEscenarioFacade;

	private int COLUMNAS, FILAS;
	private EscenarioTablero tablero = EscenarioTablero.getTablero();
	private EscenarioEnemigos enemigos = EscenarioEnemigos.getEnemigos();
	private EscenarioBombas bombas = EscenarioBombas.getBombas();
	private EscenarioJugador jugador = EscenarioJugador.getJugador();
	private EscenarioTeclado teclado = EscenarioTeclado.getEscenarioTeclado();
	private String tipoPantalla;
	private String dificultad;
	
	
	
	
	private EscenarioFacade() {
    }
	
    public static EscenarioFacade getEscenarioFacade() {
    	if (miEscenarioFacade == null) {
    		miEscenarioFacade = new EscenarioFacade();
    	}
    	return miEscenarioFacade;
    }

    
	public void inicializarTablero(String playerTipo, int COLUMNAS, int FILAS, String pantalla, String pDificultad)
	{
		jugador.inicializarJugador(playerTipo);
		this.COLUMNAS = COLUMNAS;
		this.FILAS = FILAS;
		this.tipoPantalla = pantalla;                   //Guardar el tipoPantalla para el reinicio
		this.dificultad=pDificultad;
	    tablero.inicializarTablero(pantalla);
	    tablero.inicializarTablero(pantalla);
		enemigos.inicializarEnemigos(tablero.getPosicionesVacias(), COLUMNAS, FILAS, dificultad);
		bombas.inicializarBombas();

	}
	
	public void actualizarEscenario(int cont, boolean bomb, boolean left, boolean right, boolean up, boolean down) {
		enemigos.actualizarTicksEnemigos();
		if (cont%2 == 0 && !jugador.getEstaMuerto() && !jugador.getWin()) {
			if (bomb && jugador.getPuedePonerBomba()) {
				if (bombas.ponerBomba(jugador.getTipoBomba(), jugador.getPosX(), jugador.getPosY())) {
					jugador.gestionarPonerBomba();
				}
			}
			
			String[] direccion = teclado.tick();
			
			jugador.actualizarPosicionJugador(direccion, COLUMNAS, FILAS);
		}
		
		bombas.actualizarTicksBombas();
		tablero.actualizarTicksFuego();

	}
	
	public int[][][] generarMatrizImagenes(){
		
		int[][][] matrizDevolver;
		matrizDevolver = tablero.generarMatrizAniadirBloques();
		matrizDevolver = bombas.generarMatrizAniadirBloques(matrizDevolver);
		matrizDevolver = jugador.generarMatrizAniadirBloques(matrizDevolver);
		int[][][] matrizDevolverWin = enemigos.generarMatrizAniadirBloques(matrizDevolver);
		if (matrizDevolverWin[0][0][2]==1) {
			for (int columna = 0; columna < matrizDevolver.length; columna++) {
                for (int fila = 0; fila < matrizDevolver[columna].length; fila++) {
                	if (	matrizDevolver[columna][fila][4]==201 || 
                			matrizDevolver[columna][fila][4]==202 || 
                			matrizDevolver[columna][fila][4]==203 || 
                			matrizDevolver[columna][fila][4]==204) {
                		matrizDevolver[columna][fila][4]=23;
                		jugador.setWin();
                	} else if(	matrizDevolver[columna][fila][4]==251  || 
                				matrizDevolver[columna][fila][4]==252  || 
                				matrizDevolver[columna][fila][4]==253  || 
                				matrizDevolver[columna][fila][4]==254){
                		matrizDevolver[columna][fila][4]=28;
                		jugador.setWin();
                	}
                }
			}
			
		} else {
			matrizDevolver = enemigos.generarMatrizAniadirBloques(matrizDevolver);
		}
		
		return matrizDevolver;
	}
	
	public String[] generarVectorSonidos() {
		ArrayList<String> codigosSonido = new ArrayList<String>();

		for (String sonido : tablero.getListaSonidos())		{	codigosSonido.add(sonido);	}
		tablero.listaSonidosClear();
		for (String sonido : enemigos.getListaSonidos())	{	codigosSonido.add(sonido);	}
		enemigos.listaSonidosClear();
		for (String sonido : bombas.getListaSonidos())		{	codigosSonido.add(sonido);	}
		bombas.listaSonidosClear();
		for (String sonido : jugador.getListaSonidos())		{	codigosSonido.add(sonido);	}
		
		jugador.listaSonidosClear();
		
		
		
		
	    return codigosSonido.toArray(new String[0]);
	}

    public void gestionarFuego(int posX, int posY) {
    	EscenarioJugador.getJugador().gestionarFuego(posX, posY);
    	EscenarioEnemigos.getEnemigos().gestionarFuego(posX, posY);
    }
    
    public boolean chocaConPos(int posX, int posY) {
    	return hayBombaEn(posX, posY) || hayBloqueChocableEn(posX, posY);
    }
    
    public void gestionarEnemigo(int posX, int posY) {
    	EscenarioJugador.getJugador().gestionarEnemigo(posX, posY);
    }
    
    public void gestionarExplosion(int posX, int posY, int radioBomba) {
    	EscenarioJugador.getJugador().gestionarExplosion();
    	EscenarioTablero.getTablero().gestionarExplosion(posX, posY, radioBomba);
    }
    
    public boolean hayBombaEn (int posX, int posY) {
    	return EscenarioBombas.getBombas().hayBombaEnPosicionXY(posX, posY);
    }
    
    private boolean hayBloqueChocableEn (int posX, int posY) {
    	return EscenarioTablero.getTablero().getChocaContraCelda(posX, posY);
    }

    public boolean getMuerto () {
    	return jugador.getEstaMuerto();
    }
    
    public void gestionarEnter () {
    	if (jugador.getEstaMuerto() || jugador.getWin()) {
    		inicializarTablero(jugador.getTipoJugador(), COLUMNAS, FILAS, this.tipoPantalla, dificultad);
    	}
    	
    }
    
    public void pressLeft()  { teclado.pressedLeft(); }
    public void pressRight() { teclado.pressedRight(); }
    public void pressUp()    { teclado.pressedUp(); }
    public void pressDown()  { teclado.pressedDown(); }
    public void releaseLeft()  { teclado.releasedLeft(); }
    public void releaseRight() { teclado.releasedRight(); }
    public void releaseUp()    { teclado.releasedUp(); }
    public void releaseDown()  { teclado.releasedDown(); }
	

	
	
	
	
	

}
