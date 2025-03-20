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
	private static final ImageIcon BLOQUE_FUEGO_ICONO = new ImageIcon("Pixels/miniBlast1.gif");
	private static final ImageIcon JUGADOR_BOMBA_ICONO = new ImageIcon("Pixels/whitewithbomb1.png");
	private static final ImageIcon BOMBA_ICONO = new ImageIcon("Pixels/bomb1.png");
	private static final ImageIcon JUGADOR_MUERTO_ICONO = new ImageIcon("Pixels/onFire2.png");

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
	
	private void actualizarCelda(int i, int j,Integer pos) {
        switch(pos) {
        case 30:
        	celdas[i][j].setIcon(BOMBA_ICONO);
        	break;
        case 20:
        	celdas[i][j].setIcon(JUGADOR_ICONO); // Imagen del jugador
        	break;
        case 21:
        	celdas[i][j].setIcon(JUGADOR_BOMBA_ICONO);
        	break;
        case 22:
        	celdas[i][j].setIcon(JUGADOR_MUERTO_ICONO);
        	break;
        case 10:
        	celdas[i][j].setIcon(BLOQUE_DURO_ICONO);
            break;
        case 11:
            celdas[i][j].setIcon(BLOQUE_BLANDO_ICONO);
            break;
        case 12:
        	celdas[i][j].setIcon(null);
        	break;
        case 13:
        	celdas[i][j].setIcon(BLOQUE_FUEGO_ICONO);
        	break;
    }
        } 
	
	public void update(Observable o, Object obj) {
		if(!tableroInicializado) {
			return;
		}
		if(o instanceof Escenario) {
			if(obj instanceof int[][]) {
				int[][] res = (int[][]) obj;
				Integer cont =0;
				 for (int i = 0; i < 13; i++) {
			            for (int j = 0; j < 17; j++) {
			                actualizarCelda(i,j,res[i][j]);
			                cont ++;
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

	int anterior=-1;
	
	public Controller() {
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
		        case KeyEvent.VK_ENTER: Escenario.getEscenario().pressEnter();
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
			        case KeyEvent.VK_ENTER: Escenario.getEscenario().releaseEnter();
	        				break;
			        default: ;
			       			break;
	        	}
			}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
}



