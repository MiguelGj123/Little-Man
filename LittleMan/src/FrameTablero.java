import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class FrameTablero extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Escenario escenario;


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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		addKeyListener(new Controller());
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}

class Controller implements KeyListener {
	private Escenario escenario;
	
	public Controller() {
		this.escenario = Escenario.getEscenario();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
}



