package frameTablero;

import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

//Panel que muestra el menú de pausa, victoria o derrota con sus componentes visuales.
public class Frm__T__02__02__Layered__Menu extends JLayeredPane {
	private static final long serialVersionUID = 1L;
		
	private static Frm__T__02__02__Layered__Menu miLayeredPane;
	
	private Frm__T__03__02__01__Panel__MenuTitulo menuTitulo;
	private Frm__T__03__02__00__Panel__MenuFondo menuFondo;
	private Frm__T__03__02__02__Panel__MenuBotones menuBotones;
	ArrayList<JPanel> paneles;
    
    public static Frm__T__02__02__Layered__Menu getPane() 
    {
        if (miLayeredPane == null) miLayeredPane = new Frm__T__02__02__Layered__Menu();
        return miLayeredPane;
    }
	private Frm__T__02__02__Layered__Menu() {
		paneles = new ArrayList<JPanel>();
		
    	setOpaque(false);
		setLayout(null);
		
		menuFondo = new Frm__T__03__02__00__Panel__MenuFondo();
		menuFondo.setBounds( 0, 0, T_CFG.ANCHO_MENU, T_CFG.ALTO_MENU);
		paneles.add(menuFondo);
		
		menuTitulo = new Frm__T__03__02__01__Panel__MenuTitulo();
		menuTitulo.setBounds( 0, 0, T_CFG.ANCHO_TITULO_MENU, T_CFG.ALTO_TITULO_MENU);
		paneles.add(menuTitulo);
		
		menuBotones = new Frm__T__03__02__02__Panel__MenuBotones();
		menuBotones.setBounds( T_CFG.X_BTNS_MENU, T_CFG.Y_BTNS_MENU, T_CFG.ANCHO_BTNS_MENU, T_CFG.ALTO_BTNS_MENU);
		paneles.add(menuBotones);
		
		for(int i = 0; i < paneles.size(); i++) {
			add(paneles.get(i), Integer.valueOf(i));
		}
		
    	setVisible(false);
	}
	
	// Cambia el contenido del menú y lo muestra según el tipo de evento (pause, win, lose...).
	public void mostrarMenu(String pMenu, boolean visible) {
		menuTitulo.cambiarTitulo(pMenu);
		menuBotones.cambiarBotones(pMenu);
		setVisible(visible);
	}
	
	public void ocultarMenu() 
	{
		setVisible(false);
	}
	


}
