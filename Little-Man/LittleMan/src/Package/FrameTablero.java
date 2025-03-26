package Package;
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
	private JLabel[][] celdas;
	private int tableroColumnas, tableroFilas;
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
		Escenario.getEscenario().addObserver(this);
	}
	
	private void inicializarTableroVisual() {
		try {
			setTitle("Bomberman");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, tableroColumnas * 45, tableroFilas * 45);
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
			
			celdas = new JLabel[tableroColumnas][tableroFilas];
			contentPane.setLayout(new GridLayout(tableroFilas, tableroColumnas));
			
			tableroInicializado = true;
			
			addKeyListener(new Controller());
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		for (int fila = 0; fila < tableroFilas; fila++) {
			for (int columna = 0; columna < tableroColumnas; columna++) {				// aniadir labels a matriz celdas
                celdas[columna][fila] = new JLabel();
                celdas[columna][fila].setOpaque(false);
                celdas[columna][fila].setHorizontalAlignment(SwingConstants.CENTER);
                celdas[columna][fila].setVerticalAlignment(SwingConstants.CENTER);

                contentPane.add(celdas[columna][fila]);
            }
        }
        
        contentPane.revalidate();
        contentPane.repaint();
    }
	
	private void actualizarCelda(int columna, int fila,Integer pos) {
        switch(pos) {
	        case 30:
	        	celdas[columna][fila].setIcon(BOMBA_ICONO);
	        	break;
	        case 20:
	        	celdas[columna][fila].setIcon(JUGADOR_ICONO); // Imagen del jugador
	        	break;
	        case 21:
	        	celdas[columna][fila].setIcon(JUGADOR_BOMBA_ICONO);
	        	break;
	        case 22:
	        	celdas[columna][fila].setIcon(JUGADOR_MUERTO_ICONO);
	        	break;
	        case 10:
	        	celdas[columna][fila].setIcon(BLOQUE_DURO_ICONO);
	            break;
	        case 11:
	            celdas[columna][fila].setIcon(BLOQUE_BLANDO_ICONO);
	            break;
	        case 12:
	        	celdas[columna][fila].setIcon(null);
	        	break;
	        case 13:
	        	celdas[columna][fila].setIcon(BLOQUE_FUEGO_ICONO);
	        	break;
		}
    } 
	
	public void update(Observable o, Object obj) {
		if (o instanceof Escenario) {
			if (obj instanceof int[][]) {
				int[][] res = (int[][]) obj;										// [COLUMNAS][FILAS]
				
				for (int columna = 0; columna < res.length; columna++) {			// PARA CADA COLUMNA
		            for (int fila = 0; fila < res[columna].length; fila++) {		// PARA CADA FILA
		            	if(!tableroInicializado) {
			            	tableroColumnas = res.length;
			            	tableroFilas    = res[columna].length;
			            	inicializarTableroVisual();
		            	}
		                actualizarCelda (columna, fila, res[columna][fila]);
		            }
				}
			}
		}
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



