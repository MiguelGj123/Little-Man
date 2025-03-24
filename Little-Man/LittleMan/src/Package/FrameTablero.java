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

import Package.Escenario;



public class FrameTablero extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel[][] celdas;// Celdas del tablero visual
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
					FrameTablero frame = new FrameTablero(); // Inicializa el JFrame
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
		Escenario.getEscenario().addObserver(this); // Registra el JFrame como observador de Escenario
		
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(false);
		
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Image img = new ImageIcon(("Pixels/stageBack1.png")).getImage(); // Fondo de pantalla
	            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	        }
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		celdas = new JLabel[13][17]; // 13 filas y 17 columnas para las celdas
		contentPane.setLayout(new GridLayout(13,17));
		
		inicializarTableroVisual(); // Inicializa el tablero visual con celdas
		tableroInicializado = true;
		
		
		
		
		addKeyListener(new Controller()); // Añade el controlador de teclado
	}
	
	// Inicializa las celdas visuales en la GUI
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
	
	// Actualiza el icono de una celda específica según el valor
	private void actualizarCelda(int i, int j,Integer pos) {
        switch(pos) {
	        case 30:
	        	celdas[i][j].setIcon(BOMBA_ICONO);// Bomba
	        	break;
	        case 20:
	        	celdas[i][j].setIcon(JUGADOR_ICONO); // Jugador
	        	break;
	        case 21:
	        	celdas[i][j].setIcon(JUGADOR_BOMBA_ICONO); // Jugador con bomba
	        	break;
	        case 22:
	        	celdas[i][j].setIcon(JUGADOR_MUERTO_ICONO); // Jugador muerto
	        	break;
	        case 10:
	        	celdas[i][j].setIcon(BLOQUE_DURO_ICONO); // Bloque duro
	            break;
	        case 11:
	            celdas[i][j].setIcon(BLOQUE_BLANDO_ICONO); // Bloque blando
	            break;
	        case 12:
	        	celdas[i][j].setIcon(null); // Espacio vacío
	        	break;
	        case 13:
	        	celdas[i][j].setIcon(BLOQUE_FUEGO_ICONO); // Fuego
	        	break;
	    }
	} 
	
	// Método que se llama cuando el escenario cambia (se actualiza)
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
			                actualizarCelda(i,j,res[i][j]); // Actualiza la celda
			                cont ++;
			            }
			     }
				 repaint(); // Vuelve a dibujar el tablero
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); // Redibuja la ventana
	}


}
//Controlador para las teclas presionadas
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
            	case KeyEvent.VK_SPACE: Escenario.getEscenario().pressBomba(); // Espacio
            			break;
		        case KeyEvent.VK_A: Escenario.getEscenario().pressLeft(); // Izquierda
		                break;
		        case KeyEvent.VK_W: Escenario.getEscenario().pressUp(); // Arriba
		                break;
		        case KeyEvent.VK_D: Escenario.getEscenario().pressRight(); // Derecha
		                break;
		        case KeyEvent.VK_S: Escenario.getEscenario().pressDown(); // Abajo
		                break;
		        case KeyEvent.VK_ENTER: Escenario.getEscenario().pressEnter(); // Enter
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
	            	case KeyEvent.VK_SPACE: Escenario.getEscenario().releaseBomba(); // Espacio
	            			break;
			        case KeyEvent.VK_A: Escenario.getEscenario().releaseLeft(); // Izquierda
			                break;
			        case KeyEvent.VK_W: Escenario.getEscenario().releaseUp(); // Arriba
			                break;
			        case KeyEvent.VK_D: Escenario.getEscenario().releaseRight(); // Derecha
			                break;
			        case KeyEvent.VK_S: Escenario.getEscenario().releaseDown(); // Abajo
			                break;
			        case KeyEvent.VK_ENTER: Escenario.getEscenario().releaseEnter(); // Enter
	        				break;
			        default: ;
			       			break;
	        	}
			}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
}



