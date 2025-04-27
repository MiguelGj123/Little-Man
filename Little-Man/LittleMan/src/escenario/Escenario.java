package escenario;
import java.util.*;


import frameTablero.FrameTablero;



public class Escenario extends Observable{
	
	private static Escenario miEscenario;
	private EscenarioFacade miEscenarioFacade;
	
	private static final int FILAS = 11,COLUMNAS = 17;
	
	private boolean left=false, right=false, up=false, down=false, bomb=false;
	private int cont=1;
	private Timer timer=null;
	private int tiempo,temporizador;
	private String dificultad, pantalla, playerTipo;
	private Double puntos=0.;
	private Double puntosTotales=0.;
		

	private Escenario() {
    }
	
    private Escenario(String playerTipo, String pantalla, String dificultad, String volumen) {
    	inicializarTablero(playerTipo, pantalla, dificultad, volumen);
    }

    public static Escenario getEscenario() {
    	if (miEscenario == null) miEscenario = new Escenario();
        return miEscenario;
    }
    
    public static Escenario getEscenario(String pPlayerTipo, String pantalla, String dificultad, String volumen) {
        if (miEscenario == null) miEscenario = new Escenario(pPlayerTipo, pantalla, dificultad, volumen);
        return miEscenario;
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
		iniciarJuegoFrame(params);
		timerStep();
	}
	private void iniciarJuegoFrame(String[] params) {
	    FrameTablero nuevoframe = new FrameTablero(params, new int[] {17, 11});
		nuevoframe.setVisible(true);
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
					tiempo--;
				}
				actualizarEscenario();
			}
		};
		timer.scheduleAtFixedRate(task, 0, 50);
	}
	
	private void actualizarEscenario() {
		if (miEscenarioFacade.sumarTiempo()) {
			temporizador=temporizador+10;
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
	
	
	
	

	
	
	

	
	

	
	
	public void pressLeft() 	{ miEscenarioFacade.pressLeft(); }
    public void pressRight() 	{ miEscenarioFacade.pressRight(); }
    public void pressUp() 		{ miEscenarioFacade.pressUp(); }
    public void pressDown() 	{ miEscenarioFacade.pressDown(); }
    public void pressBomba() 	{ bomb 	= true; left 	= right = up 	= down = false; }
    
    public void pressEnter() {
    	puntos=-1.;
    	if (miEscenarioFacade.getMuerto() || miEscenarioFacade.getWin()) {
    		EscenarioFichero.guardarEstadisticas("Preueba", miEscenarioFacade.getWin(), (int) (double) puntosTotales, dificultad, pantalla, playerTipo, temporizador, miEscenarioFacade.getVidas());
    		if ("pacifico".equals(dificultad)) {tiempo=4000;}
    		if ("facil".equals(dificultad)) {tiempo=4000;}
    		if ("normal".equals(dificultad)) {tiempo=3000;}
    		if ("dificil".equals(dificultad)) {tiempo=2000;}
    		temporizador=tiempo/20;
    		System.out.println(EscenarioFichero.recuperarEstadisticas());
    	}
    	miEscenarioFacade.gestionarEnter();
    	
    }
    
    
    public void releaseBomba()	{ bomb	= false;	}
    public void releaseLeft()	{ miEscenarioFacade.releaseLeft(); }
    public void releaseRight()	{ miEscenarioFacade.releaseRight(); }
    public void releaseUp()		{ miEscenarioFacade.releaseUp(); }
    public void releaseDown()	{ miEscenarioFacade.releaseDown(); }
    public void releaseEnter()	{ }
	
    public int getCOLUMNAS () { return COLUMNAS; }
    public int getFILAS () { return FILAS; }
    
    
    
    
    
		                
}
		        