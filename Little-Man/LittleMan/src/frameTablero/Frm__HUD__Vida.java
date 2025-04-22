package frameTablero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class Frm__HUD__Vida extends JLayeredPane{
	private static final long serialVersionUID = 1L;

    private JPanel vidaPanel;

    
    private boolean iniciarVidas=true;

	private static Frm__HUD__Vida vida;
    private Frm__CONFIG config;
	
	private Frm__HUD__Vida() {
		config = Frm__CONFIG.getConfig();
		setLayout(null);
	}
	
	public static Frm__HUD__Vida getHUDVida(){
		if (vida == null) vida = new Frm__HUD__Vida();
		return vida;
	}
		

	public JPanel vidaEnPantalla(JPanel HUD) {
        vidaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); // sin espacio entre corazones
        vidaPanel.setBackground(Color.BLACK);
        vidaPanel.setPreferredSize(new Dimension(config.getANCHO(), 45));
        HUD.add(vidaPanel, BorderLayout.WEST);
        
        return HUD;
    }
	
	
    
    public void gestionarVida(String vidaStr) {
        int vidas = Integer.parseInt(vidaStr);

        if (iniciarVidas) {
            vidaPanel.removeAll();
            for (int i = 0; i < vidas; i++) {
                JLabel vidaLabel = crearCorazonLabel(true, i);
                vidaPanel.add(vidaLabel);
            }
            iniciarVidas = false;
            vidaPanel.revalidate();
            vidaPanel.repaint();
        } else {
            int total = vidaPanel.getComponentCount();
            for (int i = 0; i < total; i++) {
                Component comp = vidaPanel.getComponent(i);
                if (comp instanceof JLabel) {
                    JLabel label = (JLabel) comp;
                    boolean lleno = i < vidas;
                    cambiarIconoCorazon(label, lleno);
                }
            }
        }
    }
    private JLabel crearCorazonLabel(boolean lleno, int index) {
        Image img = new ImageIcon("Pixels/" + (lleno ? "heart_full.png" : "heart_empty.png"))
                .getImage()
                .getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setName("vida" + index);
        return label;
    }

    private void cambiarIconoCorazon(JLabel label, boolean lleno) {
        Image img = new ImageIcon("Pixels/" + (lleno ? "heart_full.png" : "heart_empty.png"))
                .getImage()
                .getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(img));
    }
	
}
