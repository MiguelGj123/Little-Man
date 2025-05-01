package escenario;
import java.util.*;

import frameMenuPrincipal.FrameMenuPrincipal;
import frameTablero.FrameTablero;



public class Escenario extends Observable{
	
	private static Escenario miEscenario;
	private EscenarioFacade miEscenarioFacade;
	
	private static final int FILAS = 11,COLUMNAS = 17;
	
	private boolean left=false, right=false, up=false, down=false, bomb=false;
	private boolean unaVez=false;
	private Boolean[] pausa= {false, false, false}; //[0]=Escape, [1]=Victoria, [2]=Derrota
	private int cont=1;
	private Timer timer=null;
	private int tiempo,temporizador;
	private String dificultad, pantalla, playerTipo;
	private Double puntos=0.;
	private Double puntosTotales=0.;
		

	private Escenario() {
    }
	


    public static Escenario getEscenario() {
    	if (miEscenario == null) miEscenario = new Escenario();
        return miEscenario;
    }
    

    public void resetEscenario() {

    	left=false;
    	right=false; 
    	up=false;
    	down=false; 
    	bomb=false;
    	
    	unaVez=false;
    	pausa[0]= false;
    	pausa[1]= false;
    	pausa[2]= false;
    	
    	cont=1;
    	timer.cancel();
    	timer=null;
    	tiempo=0;
    	temporizador=0;
    	
    	playerTipo="";
		dificultad="";
		pantalla="";
		
    	puntos=0.;
    	puntosTotales=0.;
    	miEscenarioFacade.resetEscenarioFacade();
    	miEscenarioFacade=null;
    }
	
	public void inicializarTablero(String pPlayerTipo,String pPantalla,String pDificultad,String volumen)
	{	
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
		miEscenarioFacade.inicializarTablero(pPlayerTipo, COLUMNAS, FILAS, pPantalla, pDificultad);
		String[] params= {pPlayerTipo, pPantalla, pDificultad, volumen};
		playerTipo=pPlayerTipo;
		dificultad=pDificultad;
		pantalla=pPantalla;
		if ("pacifico".equals(dificultad)) {tiempo=4000;}
		if ("facil".equals(dificultad)) {tiempo=4000;}
		if ("normal".equals(dificultad)) {tiempo=3000;}
		if ("dificil".equals(dificultad)) {tiempo=2000;}
		temporizador=tiempo/20;
		puntos=0.;
		puntosTotales=0.;
		if(!unaVez) {
		iniciarJuegoFrame(params);
		
		}
		timerStep();
	}
	private void iniciarJuegoFrame(String[] params) {
		setChanged();
		notifyObservers(params);
		unaVez=true;
		
    }

	private void timerStep()
	{
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cont++;
				if (cont==121) cont=1;
				if (!miEscenarioFacade.getMuerto() && !miEscenarioFacade.getWin()) {
					if (!pausa[0] || !pausa[1] || !pausa[2]) {
					tiempo--;
					}
				}
				actualizarEscenario();
			}
		};
		timer.scheduleAtFixedRate(task, 0, 50);
	}
	
	private void actualizarEscenario() {
		if (miEscenarioFacade.getMuerto()) {
			pausa[2]= true;
		}
		if(miEscenarioFacade.getWin()) {
			pausa[1]= true;
		}
		if (!pausa[0] && !pausa[1] && !pausa[2]) {
		if (miEscenarioFacade.sumarTiempo()) {
			tiempo=tiempo+200;
			miEscenarioFacade.quitarTiempo();
		}
		if (puntos!=-1) {
		puntos = miEscenarioFacade.actualizarEscenario(cont, bomb, left, right, up, down);
		}
		if (tiempo%20==0 && tiempo>=0) {
			temporizador=tiempo/20;
		}
		int[][][] matrizImagenes = miEscenarioFacade.generarMatrizImagenes();
		String[] vectorSonidos = miEscenarioFacade.generarVectorSonidos();
		if (temporizador<=0) {
			miEscenarioFacade.gestionarTiempo();
		}
		setChanged();
		notifyObservers(matrizImagenes);
		setChanged();
		notifyObservers(vectorSonidos);
		setChanged();
		notifyObservers(temporizador);
		if(!miEscenarioFacade.getMuerto() &&!miEscenarioFacade.getWin()) {
		setChanged();
		notifyObservers(miEscenarioFacade.getVidas());
		if ("normal".equals(dificultad) && puntos != -1.) puntos *= 2;
		if ("dificil".equals(dificultad) && puntos != -1.) puntos *= 4;
		setChanged();
		notifyObservers(puntos);
		if (puntos!=-1) {
		puntosTotales=puntos+puntosTotales;
		}
		puntos=0.;
		}

		} 
		setChanged();
		notifyObservers(pausa);


	}
	
	
	
	

	
	
	

	
	
	public void escribirEnFichero() {
		EscenarioFichero.guardarEstadisticas("Preueba", miEscenarioFacade.getWin(), (int) (double) puntosTotales, dificultad, pantalla, playerTipo, temporizador, miEscenarioFacade.getVidas());
	}
	
	
	public void pressLeft() 	{ miEscenarioFacade.pressLeft(); }
    public void pressRight() 	{ miEscenarioFacade.pressRight(); }
    public void pressUp() 		{ miEscenarioFacade.pressUp(); }
    public void pressDown() 	{ miEscenarioFacade.pressDown(); }
    public void pressBomba() 	{ bomb 	= true; left 	= right = up 	= down = false; }
    
    public void pressEnter() {
    	puntos=-1.;
    	if (miEscenarioFacade.getMuerto() || miEscenarioFacade.getWin()) {
    		escribirEnFichero();
    		if ("pacifico".equals(dificultad)) {tiempo=4000;}
    		if ("facil".equals(dificultad)) {tiempo=4000;}
    		if ("normal".equals(dificultad)) {tiempo=3000;}
    		if ("dificil".equals(dificultad)) {tiempo=2000;}
    		temporizador=tiempo/20;
    		pausa[1]=false;
    		pausa[2]=false;
    	}
    	miEscenarioFacade.gestionarEnter();
    	setChanged();
		notifyObservers("-1");
    	
    }
    public void pressEscape() {
    	if (pausa[0]==true) {
    		pausa[0]=false;
    	} else if (pausa[0]==false){
    		pausa[0]=true;
    	}
    }
    
    
    public void releaseBomba()	{ bomb	= false;	}
    public void releaseLeft()	{ miEscenarioFacade.releaseLeft(); }
    public void releaseRight()	{ miEscenarioFacade.releaseRight(); }
    public void releaseUp()		{ miEscenarioFacade.releaseUp(); }
    public void releaseDown()	{ miEscenarioFacade.releaseDown(); }
    public void releaseEnter()	{ }
    public void releaseEscape() { }
	
    public int getCOLUMNAS () { return COLUMNAS; }
    public int getFILAS () { return FILAS; }

	public void vueltaAMenuPrincipal() {
		unaVez=false;
		
	}

	

	
    
    
    
    
    
		                
}
		        