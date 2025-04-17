package escenario;
import java.util.*;

import javax.swing.ImageIcon;

import frameTablero.FrameTablero;



public class Escenario extends Observable{
	
	private static Escenario miEscenario;
	private EscenarioFacade miEscenarioFacade;
	
	private static final int FILAS = 11,COLUMNAS = 17;
	
	private boolean left=false, right=false, up=false, down=false, bomb=false;
	private int cont=1;
	private Timer timer=null;
	private String pantalla;
		

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
	
	public void inicializarTablero(String playerTipo,String pantalla,String dificultad,String volumen)
	{
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
		miEscenarioFacade.inicializarTablero(playerTipo, COLUMNAS, FILAS, pantalla, dificultad);
		String[] params= {playerTipo, pantalla, dificultad, volumen};
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
				actualizarEscenario();
			}
		};
		timer.scheduleAtFixedRate(task, 0, 50);
	}
	
	private void actualizarEscenario() {		
		miEscenarioFacade.actualizarEscenario(cont, bomb, left, right, up, down);
		
		int[][][] matrizImagenes = miEscenarioFacade.generarMatrizImagenes();
		String[] vectorSonidos = miEscenarioFacade.generarVectorSonidos();
		
		setChanged();
		notifyObservers(matrizImagenes);
		setChanged();
		notifyObservers(vectorSonidos);
	}
	
	
	
	

	
	
	

	
	

	
	
	public void pressLeft() 	{ miEscenarioFacade.pressLeft(); }
    public void pressRight() 	{ miEscenarioFacade.pressRight(); }
    public void pressUp() 		{ miEscenarioFacade.pressUp(); }
    public void pressDown() 	{ miEscenarioFacade.pressDown(); }
    public void pressBomba() 	{ bomb 	= true; left 	= right = up 	= down = false; }
    
    public void pressEnter() {

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
		        