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
	private String dificultad;
		

	private Escenario() {
    }
	
    private Escenario(String playerTipo, String pantalla, String dificultad, String volumen) {
    	inicializarTablero(playerTipo, pantalla, dificultad, volumen);
    }

    public static Escenario getEscenario() {
    	if (miEscenario == null) miEscenario = new Escenario();
        return miEscenario;
    }
    
    public static Escenario getEscenario(String playerTipo, String pantalla, String dificultad, String volumen) {
        if (miEscenario == null) miEscenario = new Escenario(playerTipo, pantalla, dificultad, volumen);
        return miEscenario;
    }
	
	public void inicializarTablero(String playerTipo,String pantalla,String pDificultad,String volumen)
	{
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
		miEscenarioFacade.inicializarTablero(playerTipo, COLUMNAS, FILAS, pantalla, pDificultad);
		String[] params= {playerTipo, pantalla, pDificultad, volumen};
		dificultad=pDificultad;
		if (dificultad=="pacifico") {tiempo=4000;}
		if (dificultad=="facil") {tiempo=4000;}
		if (dificultad=="normal") {tiempo=3000;}
		if (dificultad=="dificil") {tiempo=2000;}
		temporizador=tiempo/20;
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
		miEscenarioFacade.actualizarEscenario(cont, bomb, left, right, up, down);
		if (tiempo%20==0 && tiempo>=0) {
			temporizador=tiempo/20;
		}
		int[][][] matrizImagenes = miEscenarioFacade.generarMatrizImagenes();
		String[] vectorSonidos = miEscenarioFacade.generarVectorSonidos();
		if (temporizador<=0) {
			miEscenarioFacade.gestionarGolpe();
		}
		setChanged();
		notifyObservers(matrizImagenes);
		setChanged();
		notifyObservers(vectorSonidos);
		setChanged();
		notifyObservers(temporizador);
		setChanged();
		notifyObservers(miEscenarioFacade.getVidas());
		
	}
	
	
	
	

	
	
	

	
	

	
	
	public void pressLeft() 	{ miEscenarioFacade.pressLeft(); }
    public void pressRight() 	{ miEscenarioFacade.pressRight(); }
    public void pressUp() 		{ miEscenarioFacade.pressUp(); }
    public void pressDown() 	{ miEscenarioFacade.pressDown(); }
    public void pressBomba() 	{ bomb 	= true; left 	= right = up 	= down = false; }
    
    public void pressEnter() {
    	if (miEscenarioFacade.getMuerto() || miEscenarioFacade.getWin()) {
    		if (dificultad=="pacifico") {tiempo=4000;}
    		if (dificultad=="facil") {tiempo=4000;}
    		if (dificultad=="normal") {tiempo=3000;}
    		if (dificultad=="dificil") {tiempo=2000;}
    		temporizador=tiempo/20;
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
		        