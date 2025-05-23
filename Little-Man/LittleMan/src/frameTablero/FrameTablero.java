package frameTablero;
import java.awt.*;

import javax.swing.*;

import escenario.Escenario;
import frameMenuPrincipal.Frm__00__Frame_Principal;
import sonido.SoundManager;

import java.awt.event.*;
import java.util.*;


//Ventana principal del juego que contiene el tablero y recibe las notificaciones del modelo.
//Maneja el ciclo visual del juego y redirige las acciones del teclado al controlador del escenario.
public class FrameTablero extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	
	private static FrameTablero frmTablero;
	private Frm__T__01__Layered__LayeredPrincipal layeredPrincipal;
    
    private Controller controller;
    
    private FrameTablero() 
    {
    	
    }


 // Devuelve la instancia Singleton de la ventana de juego.
    public static FrameTablero getFrameTablero() {
    	if (frmTablero==null) {
    		frmTablero= new FrameTablero();
    	}
    	return frmTablero;
    }

    
    


 // Inicializa el frame con tamaño, capas, sonidos y listeners.

    public void inicializarFrameTablero(String pVolumen) {
        // Crear Frame
        setTitle("Bomberman");                                        // Ventana Titulo
        setSize(T_CFG.ANCHO_VENTANA, T_CFG.ALTO_VENTANA);                 // Ventana Dimensiones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               // Ventana Operacion Cerrar
        setLocationRelativeTo(null);
        
        layeredPrincipal = Frm__T__01__Layered__LayeredPrincipal.getPane();
        layeredPrincipal.iniciarPaneles();
        layeredPrincipal.setPreferredSize(new Dimension(T_CFG.ANCHO_VENTANA, T_CFG.ALTO_VENTANA)); // layeredPane toma las dimensiones de la ventana
        add(layeredPrincipal);
                        
        Escenario.getEscenario().addObserver(this);        

        inicializarSonidos(pVolumen);
        aniadirObserverMouseKeyListener();
        
        pack();
        setResizable(false);
        setEnabled(true);
        setVisible(false);
        
        layeredPrincipal.mostrarMenu("", false);
    }
    
 // Añade el controlador de teclado personalizado a la ventana.
    private void aniadirObserverMouseKeyListener() {
		controller = new Controller();
		addKeyListener(controller);								// añadimos key   listener
    }

 // Cierra cualquier sonido anterior y carga los sonidos del escenario con el volumen dado.
    private void inicializarSonidos(String volumen) {
    	SoundManager.getSoundManager().close();
    	SoundManager.getSoundManager().soundsToLoadEscenario(volumen);
    }
    
 // Hace visible la ventana del juego cuando se inicia la partida.
    public void iniciarPartida() {
    	setVisible(true);
    }
    
    

 // Procesa eventos enviados desde el modelo del juego y actualiza la vista en consecuencia.
    @Override
    public void update(Observable o, Object obj) {
        try 
        {
            if (obj instanceof Object[]) 
            {
                Object[] arrayObjetos = (Object[]) obj;

                if (arrayObjetos.length == 2 && arrayObjetos[0] instanceof String) 
                {
                    String texto = (String) arrayObjetos[0];

                    switch (texto) 
                    {
                        case "GESTIONAR_CODIGOS_IMAGENES":
                        	layeredPrincipal.gestionarMatrizCodigosImagenes((int[][][]) arrayObjetos[1]);
                            break;

                        case "GESTIONAR_CODIGOS_SONIDOS":
                            gestionarMatrizCodigosSonidos((String[]) arrayObjetos[1]);
                            break;

                        case "GESTIONAR_TEMPORIZADOR":
                        	layeredPrincipal.gestionarTemporizador((Integer) arrayObjetos[1]);
                            break;

                        case "PAUSE":
                        case "YOU DIED":
                        case "WINNER":
                        case "TIME ENDED":
                            layeredPrincipal.mostrarMenu((String) arrayObjetos[0], (Boolean) arrayObjetos[1] );
                            break;
                            
                        case "INICIAR_FONDO":
                        	layeredPrincipal.iniciarFondo((String) arrayObjetos[1]);
                        	break;

                        case "GESTIONAR_PUNTUACION":
                        	layeredPrincipal.gestionarPuntuacion((int) arrayObjetos[1]);
                            break;
                            
                        case "GESTIONAR_VIDAS":
                        	layeredPrincipal.gestionarVidas((int) arrayObjetos[1]);
                        	break;
                        case "INICIALIZAR_VIDA":
                        	layeredPrincipal.inicializarVidas((int) arrayObjetos[1]);
                        	break;
                        case "FINALIZAR_PARTIDA":
                        	finalizarPartida();;
                        	break;
                        case "REINICIAR_PARTIDA":
                        	reiniciarPartida();;
                        	break;

                        default:
                            break;
                    }
                }
            }
        } 
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
  
    
 // Interpreta los códigos de sonido recibidos y los reproduce o detiene según el comando.
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
		        case KeyEvent.VK_ESCAPE: Escenario.getEscenario().pressEscape();
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
		        default: ;
		        	break;
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
	}
	

	// Elimina el listener de teclado, reinicia el menú principal y cierra la ventana del juego.
	public void finalizarPartida() {
		removeKeyListener(controller);
		Frm__00__Frame_Principal.getMenuPrincipal().inicializarPanel();
		dispose();
	}
	
	// Elimina el listener de teclado para preparar una nueva partida.
	public void reiniciarPartida() {
		removeKeyListener(controller);
	}
}



