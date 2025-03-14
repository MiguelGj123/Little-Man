import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.swing.GroupLayout.Alignment;

public class FrameTablero extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Escenario escenario;
	private JLabel[][] celdas;
	private boolean tableroInicializado = false; //Booleano para verificar que las celdas ya están inicializadas
	private static final ImageIcon JUGADOR_ICONO = new ImageIcon("Pixels/whitedown1.png");
	private static final ImageIcon BLOQUE_DURO_ICONO = new ImageIcon("Pixels/hard1.png");
	private static final ImageIcon BLOQUE_BLANDO_ICONO = new ImageIcon("Pixels/soft3.png");


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTablero frame = new FrameTablero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameTablero() {
		escenario = Escenario.getEscenario();
		escenario.addObserver(this);
		
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(false);
		
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Image img = new ImageIcon(("Pixels/stageBack1.png")).getImage();
	            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	        }
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		celdas = new JLabel[13][17];
		contentPane.setLayout(new GridLayout(13,17));
		
		inicializarTableroVisual();
		tableroInicializado = true;
		
		
		
		
		addKeyListener(new Controller());
	}
	
	private void inicializarTableroVisual() {
       

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 17; j++) {
                celdas[i][j] = new JLabel();
                celdas[i][j].setOpaque(false);
                celdas[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                celdas[i][j].setVerticalAlignment(SwingConstants.CENTER);


                contentPane.add(celdas[i][j]);
            }
        }
        contentPane.revalidate();
        contentPane.repaint();
    }
	
	private void actualizarCelda(int i, int j,Entidad ent) {
        if (ent instanceof Bomberman_blanco) {
            celdas[i][j].setIcon(JUGADOR_ICONO); // Imagen del jugador
        } else if (ent instanceof Bloque) {
            Bloque bloque = (Bloque) ent;
            switch (bloque.getTipo()) {
                case DURO:
                    celdas[i][j].setIcon(BLOQUE_DURO_ICONO);
                    break;
                case BLANDO:
                    celdas[i][j].setIcon(BLOQUE_BLANDO_ICONO);
                    break;
                case VACIO:
                	celdas[i][j].setIcon(null);
            }
        } else {
            celdas[i][j].setIcon(null);
        }
    }
	
	@Override
	public void update(Observable o, Object arg) {
		if(!tableroInicializado) {
			return;
		}
		
		if(o instanceof Escenario) {
			if(arg instanceof Entidad[][]) {
				Entidad[][] res = (Entidad[][])arg;
				 for (int i = 0; i < 13; i++) {
			            for (int j = 0; j < 17; j++) {
			                actualizarCelda(i,j,res[i][j]);
			            }
			     }
				 repaint();
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}

class Controller implements KeyListener {
	private Escenario escenario;
	int anterior=-1;
	
	public Controller() {
		this.escenario = Escenario.getEscenario();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode!=anterior) {
            anterior=keyCode;
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
		        default: ;
		       			break;
        	}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
			if (anterior==keyCode) {
	        	anterior=-1;
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
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
}



