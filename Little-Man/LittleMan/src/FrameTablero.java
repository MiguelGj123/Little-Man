import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.util.*;


public class FrameTablero extends JFrame implements Observer {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int tableroColumnas, tableroFilas;
    private boolean tableroInicializado = false;
    private BufferedImage tableroBuffer;
    private Image fondoTablero = new ImageIcon("Pixels/stageBack1.png").getImage();


    private static final ImageIcon[] ICONOS = {
        new ImageIcon("Pixels/hard1.png"), // Bloque duro (10)
        new ImageIcon("Pixels/soft3.png"), // Bloque blando (11)
        new ImageIcon("Pixels/miniBlast1.gif"), // Explosión (13)
        new ImageIcon("Pixels/whitedown1.png"), // Jugador blanco (20)
        new ImageIcon("Pixels/whitewithbomb1.png"), // Jugador blanco con bomba (21)
        new ImageIcon("Pixels/onFire2.png"), // Jugador blanco muerto (22)
        new ImageIcon("Pixels/whitehappy1.png"), //w Jugador blanco victoria (23)
        new ImageIcon("Pixels/blackdown1.png"), // Jugador negro (25)
        new ImageIcon("Pixels/blackwithbomb2.png"), // Jugador negro con bomba (26)
        new ImageIcon("Pixels/onFire4.png"), // Jugador negro muerto (27)
        new ImageIcon("Pixels/blackhappy1.png"), // Jugador negro victoria (28)
        new ImageIcon("Pixels/bomb1.png"), // Bomba (30)
        new ImageIcon("Pixels/bomb2.png"), // Bomba ultra (35)
        new ImageIcon("Pixels/baloon1.png") // Enemigo (40)
    };
    private static final int[] CODIGOS_ICONOS = {10, 11, 13, 20, 21, 22, 23, 25, 26, 27, 28, 30, 35, 40};

    public FrameTablero(String playerTipo) { 
        Escenario.getEscenario(playerTipo).addObserver(this);
    }

    private void inicializarTableroVisual() {
        setTitle("Bomberman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoTablero, 0, 0, getWidth(), getHeight(), this);
                if (tableroBuffer != null) {
                    g.drawImage(tableroBuffer, 0, 0, this);
                }
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        tableroBuffer = new BufferedImage(tableroColumnas * 45, tableroFilas * 45, BufferedImage.TYPE_INT_ARGB);
        contentPane.setPreferredSize(new Dimension(tableroColumnas * 45, tableroFilas * 45));
        pack();
        
        tableroInicializado = true;
        addKeyListener(new Controller());
    }

    private ImageIcon obtenerIcono(int codigo) {
        for (int i = 0; i < CODIGOS_ICONOS.length; i++) {
            if (CODIGOS_ICONOS[i] == codigo) return ICONOS[i];
        }
        return null;
    }

    @Override
    public void update(Observable o, Object obj) {
        if (o instanceof Escenario && obj instanceof int[][][]) {
            int[][][] res = (int[][][]) obj;

            if (!tableroInicializado) {
                tableroColumnas = res.length;
                tableroFilas = res[0].length;
                inicializarTableroVisual();
            }

            Graphics g = tableroBuffer.getGraphics();
            g.drawImage(fondoTablero, 0, 0, tableroBuffer.getWidth(), tableroBuffer.getHeight(), null);

            for (int columna = 0; columna < res.length; columna++) {
                for (int fila = 0; fila < res[columna].length; fila++) {
                    ArrayList<ImageIcon> imagenesSuperpuestas = new ArrayList<>();
                    for (int nivel = 0; nivel < res[columna][fila].length; nivel++) {
                        ImageIcon icono = obtenerIcono(res[columna][fila][nivel]);
                        if (icono != null) {imagenesSuperpuestas.add(icono);}
                    }

                    if (!imagenesSuperpuestas.isEmpty()) {
                        ImageIcon nuevaImagen = combinarMultiplesImagenes(imagenesSuperpuestas.toArray(new ImageIcon[0]));
                        g.drawImage(nuevaImagen.getImage(), (columna ) * 45 + (46 - nuevaImagen.getIconWidth()) / 2 ,  (fila ) * 45 + (46 - nuevaImagen.getIconHeight()) / 2 , null);
 
                    }
                }
            }
            g.dispose();
            repaint();
        }
    }

    private ImageIcon combinarMultiplesImagenes(ImageIcon[] imagenes) {
        if (imagenes.length == 0) return null;

        int width = imagenes[0].getIconWidth();
        int height = imagenes[0].getIconHeight();
        
        
        for (ImageIcon icono : imagenes) {
        	if (icono.getIconHeight() > height) height = icono.getIconHeight(); 
        	if (icono.getIconWidth() > width) width= icono.getIconWidth();
 
        }
 
        BufferedImage resultado = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = resultado.getGraphics();
        
        for (ImageIcon icono : imagenes) {
            g.drawImage(icono.getImage(), (width - icono.getIconWidth()) / 2  , (height - icono.getIconHeight()) / 2, null);
//            g.drawImage(icono.getImage(), 0 , 0 , null);
        }
        g.dispose();
        
        return new ImageIcon(resultado);
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



