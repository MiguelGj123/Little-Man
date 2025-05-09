package frameTablero;


import javax.swing.JPanel;


public class Frm__T__03__02__01__Panel__MenuTitulo extends JPanel {

    private static final long serialVersionUID = 1L;
	private Frm__T__04__02__01__Label__MenuTitulo labelTitulo;

	public Frm__T__03__02__01__Panel__MenuTitulo() {
		
		setOpaque(false);
		setLayout(null);
		labelTitulo = new Frm__T__04__02__01__Label__MenuTitulo();
		labelTitulo.setBounds(T_CFG.X_TITULO_MENU, T_CFG.Y_TITULO_MENU, T_CFG.ANCHO_TITULO_MENU, T_CFG.ALTO_TITULO_MENU);
		add(labelTitulo);
		setVisible(true);
	}

	public void cambiarTitulo(String titulo) {
		labelTitulo.cambiarTitulo(titulo);
	}
}
