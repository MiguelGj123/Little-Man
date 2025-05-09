package frameMenuPrincipal;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import sonido.SoundManager;
import escenario.Escenario;
import frameTablero.FrameTablero;

//Clase principal del menú. Inicializa la ventana, coordina la transición al juego.
public class Frm__00__Frame_Principal extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	private static Frm__00__Frame_Principal frameMenuPrincipal;

	private Frm__MP__01__LayeredPane_Principal layeredPanePrincipal;
	
	private FrameController miController;
	
	private String anterior="1";

	
	public static Frm__00__Frame_Principal getMenuPrincipal() {
		if (frameMenuPrincipal == null) frameMenuPrincipal = new Frm__00__Frame_Principal();
		return frameMenuPrincipal;
	}
    
    private Frm__00__Frame_Principal() {}
    
 // Inicializa la ventana del menú, sonidos, listeners, paneles e inscribe al observer del juego.
    public void inicializarPanel() {
    	inicializarSonidos();
    	setTitle("Bomberman Menu");										// Ventana Titulo
    	setSize(CONFIG__MP.getAncho(), CONFIG__MP.getAlto());											// Ventana Dimensiones
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					// Ventana Operacion Cerrar
    	setLocationRelativeTo(null);

    	layeredPanePrincipal = Frm__MP__01__LayeredPane_Principal.getPane();
    	layeredPanePrincipal.setPreferredSize(new Dimension(CONFIG__MP.getAncho(), CONFIG__MP.getAlto()));
    	add(layeredPanePrincipal);
    	
    	
    	miController = new FrameController();
    	addKeyListener(miController);								// añadimos key   listener
    	
    	setResizable(false); 
    	setVisible(true);
    	pack();
    	
    	layeredPanePrincipal.ponerImagenCombinadaMenuOpciones(layeredPanePrincipal.getNumOpcionBotonDificultad(), layeredPanePrincipal.getNumOpcionBotonMapa());
    	
    	
    	Escenario.getEscenario().addObserver(this);
    	
    	
    	
    }
    private void inicializarSonidos() {
    	SoundManager.getSoundManager().close();
    	SoundManager.getSoundManager().soundsToLoadMenu();
    	hacerMusica("MUSIC_MENU"+"1");
    }
    
    public void ponerImagenCombinadaMenuOpciones() {
    	layeredPanePrincipal.ponerImagenCombinadaMenuOpciones(layeredPanePrincipal.getNumOpcionBotonDificultad(), layeredPanePrincipal.getNumOpcionBotonMapa());
    }
     
    public void actualizarJugadorColor(int jugador) {												// actualizarJugadorColor se encarga de actualizar que jugadores se ven en blanco y negro, y cuales de color en el menu principal
    	layeredPanePrincipal.seleccionar(jugador);
    }
    
    public void gestionarBotonOpciones() {
    	layeredPanePrincipal.gestionarBotonOpciones();
    }
    
    public void hacerSonido(String sonido) {
    	SoundManager.getSoundManager().playSound(sonido);
    }
    public void pararSonido(String sonido) {
    	SoundManager.getSoundManager().stopSound(sonido);
    }
    public void hacerMusica(String sonido) {
    	SoundManager.getSoundManager().playSoundMusic(sonido);
    }
    

	public void explotar() {
		layeredPanePrincipal.explotar();
	}

	public void desExplotar() {
		layeredPanePrincipal.desExplotar();;
	}

	public String[] getOpcionesSeleccionadas() {
		return layeredPanePrincipal.getOpcionesSeleccionadas();
	}
	public String getAnteriorVolumen() {
		return anterior;
	}
	public String getOpcionVolumen() {
		String vol=layeredPanePrincipal.getOpcionVolumen();
		String devolver="1";
		switch (vol) {
		case "APAGADO":
			devolver="0";
			break;
		case "BAJO":
			devolver="1";
			break;
		case "MEDIO":
			devolver="2";
			break;
		case "ALTO":
			devolver="3";
			break;
		default:
			devolver="1";
			break;
		}
		anterior=devolver;
		return devolver;
	}
	
	// Recibe notificaciones del juego y responde con acciones: iniciar partida, cerrar menú, etc.
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
		                    case "INICIAR_TABLERO":
		            			FrameTablero.getFrameTablero().inicializarFrameTablero(layeredPanePrincipal.getOpcionesSeleccionadas()[1]);
		            			Frm__00__Timer__IniciarJuego.startTimer();
		                    	break;
	                        case "INICIAR_PARTIDA":
	                        	Frm__00__Frame_Principal.getMenuPrincipal().desExplotar();
	                        	FrameTablero.getFrameTablero().iniciarPartida();
	                        	removeKeyListener(miController);
	                        	dispose();
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

   
    
    
    class FrameController implements KeyListener
    {
    	public FrameController() {}
    	
    	// Llama al método que lanza el juego con las opciones seleccionadas.
    	private void iniciarJuego() {
    		String[] opcionesSeleccionadas= layeredPanePrincipal.getOpcionesSeleccionadas();
    		Frm__00__Timer__IniciarJuego.iniciarPartida(layeredPanePrincipal.getJugadorSeleccionado(), opcionesSeleccionadas[0], opcionesSeleccionadas[1], opcionesSeleccionadas[2]);
        }
		
    	// Gestiona teclas presionadas: rotación de personaje y confirmación de opciones en el menú.
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			if (!layeredPanePrincipal.opcionesAbiertas()) {
	        	switch (keyCode) {
	            	case KeyEvent.VK_D:
	            		layeredPanePrincipal.rotarjugadorDerecha();
	            		break;
	            		
	            	case KeyEvent.VK_A:
	            		layeredPanePrincipal.rotarJugadorIzquierda();
	            		break;
	            		
	            	case KeyEvent.VK_ENTER:
	            		iniciarJuego();
			        default: ;
			       		break;
	        	}
	        } 
			
			if(keyCode == KeyEvent.VK_O || keyCode == KeyEvent.VK_ESCAPE) {
				gestionarBotonOpciones();
				
	        }
	    }
			
		

		@Override public void keyReleased(KeyEvent e) {}
		@Override public void keyTyped(KeyEvent e) {}
    
    }

}