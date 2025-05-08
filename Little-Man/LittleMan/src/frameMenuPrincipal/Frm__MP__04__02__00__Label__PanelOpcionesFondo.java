package frameMenuPrincipal;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Frm__MP__04__02__00__Label__PanelOpcionesFondo extends JLabel{
	
	private static final long serialVersionUID = 1L;
	
	private final String[] iconsFondo = new String[] {"Pixels/Menu_opciones_vacio.png", "Pixels/Menu_opciones_vacio_x.png"};
	
	private final int ancho = 600;
	private final int alto = 576;

	
	public Frm__MP__04__02__00__Label__PanelOpcionesFondo() {
    	setOpaque(false);
		setLayout(null);
        setName("menuOpciones");
        
        OpcionesFondoController controller = new OpcionesFondoController();
        addMouseMotionListener(controller);
        addMouseListener(controller);
        
		establecerIcono(0);
    	setBounds(0, 0, ancho, alto);
	}
	
	public void iluminar() {
		establecerIcono(1);
	}
	
	public void desIluminar() {
		establecerIcono(0);
	}
	
	public void establecerIcono(int icono) {
		ImageIcon fondoSinEscalarIcon = new ImageIcon(iconsFondo[icono]);
        Image fondoEscalado = fondoSinEscalarIcon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon fondoEscaladoIcon = new ImageIcon(fondoEscalado);
        
    	setIcon(fondoEscaladoIcon);		
	}
	
	
	
	
	
	
	class OpcionesFondoController implements MouseMotionListener, MouseListener{
	    private final int AREA_X = 533; // 600 (ancho) - 67 = 533
	    private final int AREA_Y = 0;
	    private final int AREA_ANCHO = 67;
	    private final int AREA_ALTO = 61;
	    private boolean dentroDelArea = false;

	    @Override
	    public void mouseEntered(MouseEvent e) {
	        checkArea(e); // Verificar posición al entrar al componente
	    }

	    @Override
	    public void mouseMoved(MouseEvent e) {
	        checkArea(e); // Verificar posición al moverse
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	        // Al salir completamente del componente
	        if (dentroDelArea) {
	            establecerIcono(0);
	            dentroDelArea = false;
	        }
	    }

	    private void checkArea(MouseEvent e) {
	        int x = e.getX();
	        int y = e.getY();
	        
	        boolean ahoraEnArea = (x >= AREA_X && x <= AREA_X + AREA_ANCHO && 
	                             y >= AREA_Y && y <= AREA_Y + AREA_ALTO);
	        
	        if (ahoraEnArea != dentroDelArea) {
	            dentroDelArea = ahoraEnArea;
	            establecerIcono(dentroDelArea ? 1 : 0);
	        }
	    }

	    @Override public void mouseClicked(MouseEvent e) {
	    	if (dentroDelArea) Frm__00__Frame_Principal.getMenuPrincipal().gestionarBotonOpciones();
			Frm__00__Frame_Principal.getMenuPrincipal().hacerSonido("SELECT_MENU"+Frm__00__Frame_Principal.getMenuPrincipal().getOpcionVolumen());

	    }
	    
	    // Métodos no usados pero requeridos
	    @Override public void mouseDragged(MouseEvent e) {}
	    @Override public void mousePressed(MouseEvent e) {}
	    @Override public void mouseReleased(MouseEvent e) {}
}

}
