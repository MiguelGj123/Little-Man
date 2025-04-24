package frameTablero;
import java.awt.*;

import javax.swing.*;

import escenario.Escenario;
import sonido.SoundManager;

import java.awt.event.*;
import java.util.*;



public class FrameTablero extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	
    private Frm__Pane pane;
    private Frm__CONFIG config;
    private Frm__HUD HUDPanel; 

    private final int PIXELS_POR_LADO_CELDA = 45;


    
    
    
    //SE INICIA AQUI!!!

    public FrameTablero(String[] params, int[] dims) {  // params son playerTipo, pantalla, dificultad, sonido

    	
    	// Inicializar Config con valores para todas las clases que lo necesiten
    	
    	Frm__CONFIG.iniciarConfig(dims[0], dims[1], PIXELS_POR_LADO_CELDA);
    	config = Frm__CONFIG.getConfig();
    	
    	
    	// Crear Frame
    	
    	setTitle("Bomberman");										// Ventana Titulo
    	setSize(config.getANCHO(), config.getALTO());					// Ventana Dimensiones
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					// Ventana Operacion Cerrar
    	setLocationRelativeTo(null);
    	
    	//System.out.println("Frame Tablero ___ Dimensiones Tablero" + config.getANCHO() + " " + config.getALTO());
    	
    	
    	// Inicializar el LayeredPane en el que se guardará el contenido
    	
    	pane = Frm__Pane.getPane();
    	pane.setPreferredSize(new Dimension(config.getANCHO(), config.getALTO()));
		pane = pane.iniciarPane(params[1]);
		
		
        Escenario.getEscenario().addObserver(this);
        
        configurarVentana();
        inicializarSonidos(params[3]);
        aniadirObserverMouseKeyListener();
        
        pack();

    }
    
    
    private void configurarVentana() {
    	//pack();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pane, BorderLayout.CENTER);
    	setResizable(false); 
    	setLocationRelativeTo(null);
    	setVisible(true);
    	hudConfig();
    }
    
    private void aniadirObserverMouseKeyListener() {
    	addKeyListener(new Controller());								// añadimos key   listener
    }

    
    private void inicializarSonidos(String volumen) {
    	SoundManager.getSoundManager().close();
    	SoundManager.getSoundManager().soundsToLoadEscenario(volumen);
    }
    private void hudConfig() {
    	HUDPanel = Frm__HUD.getHUD();
        getContentPane().add(HUDPanel.iniciarHUD(), BorderLayout.NORTH);
    }
    
    
    

    @Override
    public void update(Observable o, Object obj) {
        if (o instanceof Escenario && obj instanceof int[][][]) {
            gestionarMatrizCodigosImagenes((int[][][]) obj);
        } else
        
        if (o instanceof Escenario && obj instanceof String[]) {
            gestionarMatrizCodigosSonidos((String[]) obj);
        } else 
    	if (o instanceof Escenario && obj instanceof Integer) {
            HUDPanel.gestionarTemporizador((Integer) obj);
        }
        if (o instanceof Escenario && obj instanceof String) {
        	HUDPanel.gestionarVida((String) obj);
        }
        if (o instanceof Escenario && obj instanceof Double) {
        	HUDPanel.gestionarPuntuacion((Double) obj);
        }
    	
        
    }
    
    
    
    private void gestionarMatrizCodigosImagenes(int[][][] res) {
    	pane.actualizarTablero(res);
    }
    
    private void gestionarMatrizCodigosSonidos(String[] res) {

    	for (String sonido: res) {
            int index = sonido.indexOf('*'); // Encontrar la posición del asterisco

            if (index != -1) { // Verificar que exista el asterisco
                String comandoSonido = sonido.substring(0, index);  // Desde el inicio hasta el asterisco (sin incluirlo)
                String nombreSonido = sonido.substring(index + 1); // Desde después del asterisco hasta el final

                if (comandoSonido.equals("PARAR_SONIDO")) {
                	SoundManager.getSoundManager().stopSound(nombreSonido);
                } else if (comandoSonido.equals("SONAR_SONIDO")) {
                	if (nombreSonido.equals("MENU")||nombreSonido.equals("MUSIC")) {
                		SoundManager.getSoundManager().playSoundMusic(nombreSonido);
                	} else {
                		SoundManager.getSoundManager().playSound(nombreSonido);
                	}
                }
            }
    	}
    	
    }
    
	
    	
    
    @Override public boolean isOpaque() { 
        return false; 
    }
    
   
    
    	
	class Controller implements KeyListener {
	
		public Controller() {
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
	    	switch (keyCode) {
	        	case KeyEvent.VK_SPACE: Escenario.getEscenario().pressBomba();
	        		break;
		        case KeyEvent.VK_A: Escenario.getEscenario().pressLeft();
		            break;
		        case KeyEvent.VK_W: Escenario.getEscenario().pressUp();
		            break;
		        case KeyEvent.VK_D: Escenario.getEscenario().pressRight();
		            break;
		        case KeyEvent.VK_S: Escenario.getEscenario().pressDown();
	                break;
		        case KeyEvent.VK_ENTER: Escenario.getEscenario().pressEnter();
	        		break;
		        default: ;
	       			break;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
	    	switch (keyCode) {
	        	case KeyEvent.VK_SPACE: Escenario.getEscenario().releaseBomba();
	    			break;
		        case KeyEvent.VK_A: Escenario.getEscenario().releaseLeft();
	                break;
		        case KeyEvent.VK_W: Escenario.getEscenario().releaseUp();
	                break;
		        case KeyEvent.VK_D: Escenario.getEscenario().releaseRight();
	                break;
		        case KeyEvent.VK_S: Escenario.getEscenario().releaseDown();
	                break;
		        case KeyEvent.VK_ENTER: Escenario.getEscenario().releaseEnter();
					break;
		        default: ;
		        	break;
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
	}
}



