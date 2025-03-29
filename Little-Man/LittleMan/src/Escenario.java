import java.util.*;

import javax.swing.ImageIcon;



public class Escenario extends Observable{
	
	private static Escenario miEscenario;
	
	private static final int FILAS = 11,COLUMNAS = 17;
	
	private boolean left=false, right=false, up=false, down=false, bomb=false;
	private int cont=1;
	private Timer timer=null;
	
	EscenarioTablero tablero = EscenarioTablero.getTablero();
	EscenarioEnemigos enemigos = EscenarioEnemigos.getEnemigos();
	EscenarioBombas bombas = EscenarioBombas.getBombas();
	EscenarioJugador jugador = EscenarioJugador.getJugador();
	

	private Escenario() {
    }
	
    private Escenario(String playerTipo) {
    	inicializarTablero(playerTipo);
    }

    public static Escenario getEscenario() {
    	if (miEscenario == null) {
        	miEscenario = new Escenario();
        }
        return miEscenario;
    }
    
    public static Escenario getEscenario(String playerTipo) {
        if (miEscenario == null) {
        	miEscenario = new Escenario(playerTipo);
        }
        return miEscenario;
    }
	
	private void inicializarTablero(String playerTipo)
	{
		SoundManager.getSoundManager().soundsToLoad();
		SoundManager.getSoundManager().playSound("music");
		tablero.inicializarTablero();
		enemigos.inicializarEnemigos(tablero.getPosicionesVacias(), COLUMNAS, FILAS);
		bombas.inicializarBombas();
		jugador.inicializarJugador(playerTipo);
		
		timerStep();
	}

	private void timerStep()
	{
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cont++;
				if (cont==121) {
					cont=1;
				}
				actualizarEscenario();
				
			}
		};
		timer.scheduleAtFixedRate(task, 0, 50);
	}
	
	private void actualizarEscenario() {	
		enemigos.actualizarTicksEnemigos();
		if (cont%2 == 0 && !jugador.getEstaMuerto() && !jugador.getWin()) {
			if (bomb && jugador.getPuedePonerBomba()) {
				if (bombas.ponerBomba(jugador.getTipoBomba(), jugador.getPosX(), jugador.getPosY())) {
					jugador.gestionarPonerBomba();
				}
			}
			
			String direccion = "A";
			if (left) {
				direccion = "L";
			} else if (right) {
				direccion = "R";
			} else if (up) {
				direccion = "U";
			} else if (down) {
				direccion = "D";
			}
			
			jugador.actualizarPosicionJugador(direccion, getCOLUMNAS(), getFILAS());
		}
		
		bombas.actualizarTicksBombas();
		tablero.actualizarTicksFuego();
		
		setChanged();
		notifyObservers(generarMatriz());
	}

	private int[][][] generarMatriz(){
		
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

	public void pressLeft() 	{ left 	= true; right 	= up 	= down 	= bomb = false; }
    public void pressRight() 	{ right = true; left 	= up 	= down 	= bomb = false; }
    public void pressUp() 		{ up 	= true; left 	= right = down 	= bomb = false; }
    public void pressDown() 	{ down 	= true; left 	= right = up 	= bomb = false; }
    public void pressBomba() 	{ bomb 	= true; left 	= right = up 	= down = false; }
    
    public void pressEnter() {
    	if (jugador.getEstaMuerto() || jugador.getWin()) {
    		jugador.resetWin();
    		enemigos.resetWin();
    		timer.purge();
    		timer.cancel();
    		inicializarTablero(jugador.getTipoJugador());
    	}
    }
    
    
    public void releaseBomba()	{ bomb	= false;	}
    public void releaseLeft()	{ left	= false;	}
    public void releaseUp()		{ up	= false;	}
    public void releaseRight()	{ right	= false;	}
    public void releaseDown()	{ down	= false;	}
    public void releaseEnter()	{ }
	
    public int getCOLUMNAS () { return COLUMNAS; }
    public int getFILAS () { return FILAS; }
    
    
    public boolean chocaConPos(int posX, int posY) {
    	return hayBombaEn(posX, posY) || hayBloqueChocableEn(posX, posY);
    }
    
    public void gestionarFuego(int posX, int posY) {
    	EscenarioJugador.getJugador().gestionarFuego(posX, posY);
    	EscenarioEnemigos.getEnemigos().gestionarFuego(posX, posY);
    }
    
    public void gestionarEnemigo(int posX, int posY) {EscenarioJugador.getJugador().gestionarEnemigo(posX, posY);}
    
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
    
    
		                
}
		        