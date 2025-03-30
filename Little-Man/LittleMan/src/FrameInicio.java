import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class FrameInicio extends JFrame implements Observer {

	private final int ANCHO = 1000;
	private final int LARGO = 606;
	private static final long serialVersionUID = 1L;
	private static final ImageIcon ICONO_TITULO = new ImageIcon("Pixels/title.png");
	private static final ImageIcon ICONO_IMAGEN = new ImageIcon("Pixels/back.png");
	private static final ImageIcon ICONO_EXPLOSION = new ImageIcon("Pixels/explotion-explode.gif");
																	
	private static final ImageIcon[] ICONOS_PERSONAJES = {
	        new ImageIcon("Pixels/bomber1.png"),
	        new ImageIcon("Pixels/bomber2.png"),
	        new ImageIcon("Pixels/bomber3.png"),
	        new ImageIcon("Pixels/bomber4.png"),
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
        
        JLabel explosion = crearFondoExplosion(600,600);
        JLabel fondo = crearFondo(ANCHO, LARGO);
        JLabel titulo = crearTitulo(600,200);
        JLabel fondoBoss = crearFondoBoss(280,310);
        JPanel personajes = crearPanelPersonajes();
        layeredPane.add(fondo, Integer.valueOf(0));
        layeredPane.add(titulo, Integer.valueOf(1));
        layeredPane.add(explosion, Integer.valueOf(2));
        layeredPane.add(fondoBoss, Integer.valueOf(3));
        layeredPane.add(personajes, Integer.valueOf(4));
        add(layeredPane);
        
        pack();
        setResizable(false); 
        setLocationRelativeTo(null);
        setVisible(true);
        MenuPrincipal.getMenuPrincipal().addObserver(this);
        addMouseListener(new Controller());
        addKeyListener(new Controller2());
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
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[1], 210, 380, 150, 150, true, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[2], 620, 380, 150, 150, false, 1.5));
        panelCentral.add(crearLabelConImagen(ICONOS_PERSONAJES[3], 730, 280, 150, 150, false, 1.5));
        
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
        return label;
    }
    
    @Override
    public void update(Observable o, Object obj) {
        if(o instanceof MenuPrincipal && MenuPrincipal.getMenuPrincipal().isReady()) {
        	String personaje = (String) obj;
        	FrameTablero nuevoframe = new FrameTablero(personaje);
        	nuevoframe.setVisible(true);
        	this.setVisible(false);
        	dispose();
        }
    }
    
    class Controller implements  MouseListener {
    	
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
    
    }
 class Controller2 implements  KeyListener {
    	
    	public Controller2() {
    	}

		@Override
		public void keyTyped(KeyEvent e) {
			
			
		}
		
		
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if(keyCode==KeyEvent.VK_ENTER) {
				MenuPrincipal.getMenuPrincipal().iniciarJuego();
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			
		}

		
    
    }
}
