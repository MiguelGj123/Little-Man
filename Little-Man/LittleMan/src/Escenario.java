import java.util.*;

import javax.swing.ImageIcon;



public class Escenario extends Observable{
	
	private static Escenario miEscenario;
	private static EscenarioFacade miEscenarioFacade;
	
	private static final int FILAS = 11,COLUMNAS = 17;
	
	private boolean left=false, right=false, up=false, down=false, bomb=false;
	private int cont=1;
	private Timer timer=null;
		

	private Escenario() {
    }
	
    private Escenario(String playerTipo) {
    	inicializarTablero(playerTipo);
    }

    public static Escenario getEscenario() {
    	if (miEscenario == null) miEscenario = new Escenario();
        return miEscenario;
    }
    
    public static Escenario getEscenario(String playerTipo) {
        if (miEscenario == null) miEscenario = new Escenario(playerTipo);
        return miEscenario;
    }
	
	private void inicializarTablero(String playerTipo)
	{
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
		miEscenarioFacade.inicializarTablero(playerTipo, COLUMNAS, FILAS);
		timerStep();
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
	
	
	
	

	
	
	

	
	
	
	
	
	
	public void pressLeft() 	{ left 	= true; right 	= up 	= down 	= bomb = false; }
    public void pressRight() 	{ right = true; left 	= up 	= down 	= bomb = false; }
    public void pressUp() 		{ up 	= true; left 	= right = down 	= bomb = false; }
    public void pressDown() 	{ down 	= true; left 	= right = up 	= bomb = false; }
    public void pressBomba() 	{ bomb 	= true; left 	= right = up 	= down = false; }
    
    public void pressEnter() {

    	miEscenarioFacade.gestionarEnter();
    	
    }
    
    
    public void releaseBomba()	{ bomb	= false;	}
    public void releaseLeft()	{ left	= false;	}
    public void releaseUp()		{ up	= false;	}
    public void releaseRight()	{ right	= false;	}
    public void releaseDown()	{ down	= false;	}
    public void releaseEnter()	{ }
	
    public int getCOLUMNAS () { return COLUMNAS; }
    public int getFILAS () { return FILAS; }
    
    
    
    
		                
}
		        