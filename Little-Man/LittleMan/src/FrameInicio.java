import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class FrameInicio extends JFrame implements Observer {

	private final int ANCHO = 1000;
	private final int LARGO = 606;
	private static final long serialVersionUID = 1L;
	private static final ImageIcon ICONO_TITULO = new ImageIcon("Pixels/title.png");
	private static final ImageIcon ICONO_IMAGEN = new ImageIcon("Pixels/back.png");
	private static final ImageIcon ICONO_EXPLOSION = new ImageIcon("Pixels/explotion-explode.gif");
	private static final ImageIcon ICONO_EXP_PERSONAJE = new ImageIcon("Pixels/blast.gif");
	
	private JLabel explosion;
	private JPanel personajes;
	private JPanel explosiones_personaje;
	private int numPersonaje = 0;
																	
	private static final ImageIcon[] ICONOS_PERSONAJES = {
	        new ImageIcon("Pixels/bomber1.png"),
	        new ImageIcon("Pixels/bomber2.png"),
	        new ImageIcon("Pixels/bomber3.png"),
	        new ImageIcon("Pixels/bomber4.png"),
	        new ImageIcon("Pixels/bomber1_gray.png"),
	        new ImageIcon("Pixels/bomber2_gray.png"),
	        new ImageIcon("Pixels/bomber3_gray.png"),
	        new ImageIcon("Pixels/bomber4_gray.png"),
	    };

	    private static final ImageIcon[] ICONOS_ENEMIGOS = {
	        new ImageIcon("Pixels/boss2.png"),
	        new ImageIcon("Pixels/boss3.png"), 
	        new ImageIcon("Pixels/boss4.png"), 
	        new ImageIcon("Pixels/baloon1.png"),
	        new ImageIcon("Pixels/doria2.png"),
	        new ImageIcon("Pixels/pass1.png"),
	        new ImageIcon("Pixels/bomb1.png"),	        
	    };
	    
	/**
	 * Create the frame.
	 */
    public FrameInicio() 
    {
    	setTitle("Bomberman Menu");
        setSize(ANCHO, LARGO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(ANCHO, LARGO)); 
        layeredPane.setLayout(null);
        
        explosion = crearFondoExplosion(600,600);
        JLabel fondo = crearFondo(ANCHO, LARGO);
        JLabel titulo = crearTitulo(600,200);
        JLabel fondoBoss = crearFondoBoss(280,310);
        personajes = crearPanelPersonajes();
        explosiones_personaje = crearPanelExplosiones();
        layeredPane.add(fondo, Integer.valueOf(0));
        layeredPane.add(titulo, Integer.valueOf(1));
        layeredPane.add(explosion, Integer.valueOf(2));
        layeredPane.add(fondoBoss, Integer.valueOf(3));
        layeredPane.add(explosiones_personaje, Integer.valueOf(4));
        layeredPane.add(personajes, Integer.valueOf(5));
        add(layeredPane);
        explosion.setVisible(false);
        
        pack();
        setResizable(false); 
        setLocationRelativeTo(null);
        setVisible(true);
        MenuPrincipal.getMenuPrincipal().addObserver(this);
        addMouseListener(new Controller());
        addKeyListener(new Controller());
    }
    
    
    private JLabel crearFondo(int anchoDisponible, int altoDisponible) {
                
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, anchoDisponible, altoDisponible);
        fondo.setIcon(new ImageIcon(ICONO_IMAGEN
        		.getImage().getScaledInstance(anchoDisponible,altoDisponible, Image.SCALE_SMOOTH )));
        return fondo;
    }
    
    private JLabel crearTitulo(int pAncho, int pAlto) 
    {
    	
        ImageIcon iconoOriginal = ICONO_TITULO;
        Image img = iconoOriginal.getImage().getScaledInstance(pAncho, pAlto, Image.SCALE_SMOOTH); // ancho x alto
        ImageIcon iconoEscalado = new ImageIcon(img);

        JLabel tituloLabel = new JLabel(iconoEscalado);
        int xPos = ((ANCHO - pAncho)/2);
        int yPos = 15;
        
        tituloLabel.setBounds(xPos, yPos, pAncho, pAlto);
        
        
        return tituloLabel;   
    }
    
    
    private JLabel crearFondoBoss(int pAncho, int pAlto)
    {
    	
    	 ImageIcon iconoOriginal = ICONOS_ENEMIGOS[0];
         Image img = iconoOriginal.getImage().getScaledInstance(pAncho, pAlto, Image.SCALE_SMOOTH); // ancho x alto
         ImageIcon iconoEscalado = new ImageIcon(img);

         JLabel bossLabel = new JLabel(iconoEscalado);
         int xPos = ((ANCHO - pAncho)/2);
         int yPos = 200;
         
         bossLabel.setBounds(xPos, yPos, pAncho, pAlto);
         
         
         return bossLabel;   
    	
    }
    
    private JLabel crearFondoExplosion(int pAncho, int pAlto)
    {
    	
    	 ImageIcon gifIcon = ICONO_EXPLOSION;
    	 Image gifEscalado =  gifIcon.getImage()
    			 .getScaledInstance(pAncho, pAlto, Image.SCALE_DEFAULT);
       
         JLabel explosionLabel = new JLabel(new ImageIcon(gifEscalado));
         int xPos = ((ANCHO - pAncho)/2);
         int yPos = 100;
         
         explosionLabel.setBounds(xPos, yPos, pAncho, pAlto);
         
         
         return explosionLabel;   
    	
    }
      
    
    private JPanel crearPanelPersonajes() {
        JPanel panelCentral = new JPanel();
        panelCentral.setOpaque(false);
        panelCentral.setLayout(null); // Usamos layout nulo para posicionamiento absoluto
        panelCentral.setBounds(0, 0, ANCHO, LARGO);

        // Posiciones personalizadas para cada imagen (x, y, ancho, alto)
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[0], 100, 280, 150, 150, true, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[1], 210, 380, 150, 150, false, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[2], 620, 380, 150, 150, false, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[3], 730, 280, 150, 150, false, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[4], 100, 280, 150, 150, false, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[5], 210, 380, 150, 150, true, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[6], 620, 380, 150, 150, true, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[7], 730, 280, 150, 150, true, 1.5));
        
        panelCentral.add(crearLabelConImagen(ICONOS_ENEMIGOS[1], 900, 180, 150, 380, true, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_ENEMIGOS[2], -43, 450, 150, 250, true, 1.3));
        panelCentral.add(crearLabelConImagen(ICONOS_ENEMIGOS[3], 150, 500, 50, 50, true, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_ENEMIGOS[4], 800, 450, 50, 50, true, 1.3));
        panelCentral.add(crearLabelConImagen(ICONOS_ENEMIGOS[5], 880, 490, 50, 50, true, 1.3));
        panelCentral.add(crearLabelConImagen(ICONOS_ENEMIGOS[6], 410, 480, 30, 30, true, 1.5));
        
        return panelCentral;
    }
 
    private JLabel crearLabelConImagen(ImageIcon imagen, int x, int y, int width, int height, boolean isVisible, double multTamaño) 
    {
        ImageIcon iconoOriginal = imagen;
        Image imagenOriginal = iconoOriginal.getImage();

        int ancho = (int)(iconoOriginal.getIconWidth() * multTamaño);
        int alto = (int)(iconoOriginal.getIconHeight() * multTamaño);

        Image imagenRedimensionada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

        JLabel label = new JLabel(iconoRedimensionado);
        label.setBounds(x, y, width, height);
        label.setVisible(isVisible);
        label.setName(imagen.getDescription());
        return label;
    }
    
    private JLabel crearLabelConGif(ImageIcon imagen, int x, int y, int width, int height, boolean isVisible, double multTamaño, String Nombre) 
    {
    	 ImageIcon gifIcon = ICONO_EXPLOSION;
    	 Image gifEscalado =  gifIcon.getImage()
    			 .getScaledInstance(width, height, Image.SCALE_DEFAULT);
       
         JLabel explosionLabel = new JLabel(new ImageIcon(gifEscalado));
         int xPos = ((ANCHO - width)/2);
         int yPos = 100;
         
         explosionLabel.setBounds(x, y, width, height);
         explosionLabel.setName(Nombre);
         explosionLabel.setVisible(isVisible);
         
         return explosionLabel;
    }
    
    private JPanel crearPanelExplosiones()
    {
        JPanel panelCentral = new JPanel();
        panelCentral.setOpaque(false);
        panelCentral.setLayout(null); // Usamos layout nulo para posicionamiento absoluto
        panelCentral.setBounds(0, 0, ANCHO, LARGO);

        // Posiciones personalizadas para cada imagen (x, y, ancho, alto)
        panelCentral.add(crearLabelConGif(ICONO_EXP_PERSONAJE, 100, 280, 150, 150, true, 1.5, "0"));
        panelCentral.add(crearLabelConGif(ICONO_EXP_PERSONAJE, 210, 380, 150, 150, false, 1.5, "1"));
        panelCentral.add(crearLabelConGif(ICONO_EXP_PERSONAJE, 620, 380, 150, 150, false, 1.5, "2"));
        panelCentral.add(crearLabelConGif(ICONO_EXP_PERSONAJE, 730, 280, 150, 150, false, 1.5, "3"));
        
        return panelCentral;
    	
    }
    
    private void quitarGris(String quitarGris) 
    {
    	int personaje = -1;
    	
    	switch (quitarGris) {
    		case "BLANCO":
    			personaje = 0;
    			break;
    		case "NEGRO":
    			personaje = 1;
    			break;
    		case "OTRO_1":
    			personaje = 2;
    			break;
    		case "OTRO_2":
    			personaje = 3;
    			break;
    	}
    	
    	//Principal
    	for (int i = 0; i < 4; i++) obtenerLabelPorNombre(personajes, ICONOS_PERSONAJES[i].getDescription()).setVisible(false);
    	for (int i = 4; i < 8; i++) obtenerLabelPorNombre(personajes, ICONOS_PERSONAJES[i].getDescription()).setVisible(true);
    	
    	obtenerLabelPorNombre(personajes, ICONOS_PERSONAJES[personaje + 4].getDescription()).setVisible(false);
    	obtenerLabelPorNombre(personajes, ICONOS_PERSONAJES[personaje].getDescription()).setVisible(true);
    	
    }
    
    private void quitarExplosion(int numAnterior) 
    {
    	//Principal
    	obtenerLabelPorNombre(explosiones_personaje, String.valueOf(numPersonaje)).setVisible(true);

    	
    	if(numAnterior > -1 && numAnterior < 4) 
    	{
    		obtenerLabelPorNombre(explosiones_personaje, String.valueOf(numAnterior)).setVisible(false);
    	}
    	
    }
    
    public JLabel obtenerLabelPorNombre(JPanel panel, String nombre) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel && nombre.equals(comp.getName())) {
                return (JLabel) comp;
            }
        }
        return null; // No se encontró
    }
    
    @Override
    public void update(Observable o, Object obj) {
    	if (o instanceof MenuPrincipal ) {
    		if ( obj instanceof String) {
    			quitarGris((String) obj);
    		} else if( MenuPrincipal.getMenuPrincipal().isReady()) {
	        	explosion.setVisible(true);
	        	new SwingWorker<Void,Void>(){
	        		@Override
	        		protected Void doInBackground() throws Exception {
	        			//Espera 2 segundos
	        			Thread.sleep(1100);
	        			return null;
	        		}
	        		@Override
	        		protected void done() {
	        			String personaje = (String) obj;
	                	String pantalla = (String) obj;
						FrameTablero nuevoframe = new FrameTablero(personaje,pantalla );
	                	nuevoframe.setVisible(true);
	                	dispose();
	        		}
	        	}.execute();
	        }
    	}
    }
    
    
    
    
    class Controller implements  MouseListener,KeyListener {
    	
    	public Controller() {
    	}

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			String jugadorSeleccionado = null;
			if(x>140 && x<226) {
				if(y>323 && y<443) {
					jugadorSeleccionado = "BLANCO";
				}
			}
			else if(x<325 && x>259) {
				if(y>408 && y<559) {
					jugadorSeleccionado = "NEGRO";
				}
			}
			MenuPrincipal.getMenuPrincipal().seleccionPersonaje(jugadorSeleccionado);
			MenuPrincipal.getMenuPrincipal().iniciarJuego();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) 
		{
			int keyCode = e.getKeyCode();
	        	switch (keyCode) {
	            	case KeyEvent.VK_D:
	            		MenuPrincipal.getMenuPrincipal().pressD();
	            			break;
	            	case KeyEvent.VK_A:
	            		MenuPrincipal.getMenuPrincipal().pressA();
	            			break;
	            	case KeyEvent.VK_ENTER:
	            		/*if(numPersonaje == 0) 
	            		{
	            			MenuPrincipal.getMenuPrincipal().seleccionPersonaje("BLANCO");
	            			MenuPrincipal.getMenuPrincipal().iniciarJuego();
	            		}
	            		if (numPersonaje ==1)
	            		{
	            			MenuPrincipal.getMenuPrincipal().seleccionPersonaje("NEGRO");
	            			MenuPrincipal.getMenuPrincipal().iniciarJuego();
	            		}
	            		if (numPersonaje == 2)
	            		{
	            			JOptionPane.showMessageDialog(null, "Personaje en desarollo, gracias por su paciencia.", "Info", JOptionPane.INFORMATION_MESSAGE);
	            		}
	            		if (numPersonaje == 3)
	            		{
	            			JOptionPane.showMessageDialog(null, "Personaje en desarollo, gracias por su paciencia.", "Info", JOptionPane.INFORMATION_MESSAGE);
	            		}
	            		*/
	            		
	            		MenuPrincipal.getMenuPrincipal().pressEnter();
	            		
	            			break;
	            		

			        default: ;
			       			break;
	        	}
	    }
			
		

		@Override
		public void keyReleased(KeyEvent e) {
			
			
		}
    
    }
    
}