package frameTablero;

import javax.swing.JPanel;

public class Frm__T__03__02__02c__Panel__FilaBotonesVoD extends JPanel {

    private static final long serialVersionUID = 1L;
	private Frm__T__04__02__04__Label__Reiniciar labelReiniciar;
	private Frm__T__04__02__05__Label__VolverMenuGuardar labelVolverMenu;	


	public Frm__T__03__02__02c__Panel__FilaBotonesVoD() 
	{
		setOpaque(false);
		setLayout(null);  // Aquí puedes ajustar los márgenes entre los botones
		
		labelReiniciar = new Frm__T__04__02__04__Label__Reiniciar();
		labelReiniciar.setBounds(T_CFG.X_BTN1_MENU, T_CFG.Y_BTN1_MENU, T_CFG.PXL_BTN_MENU, T_CFG.PXL_BTN_MENU);
		add(labelReiniciar);
		
		labelVolverMenu = new Frm__T__04__02__05__Label__VolverMenuGuardar();
		labelVolverMenu.setBounds(T_CFG.X_BTN2_MENU, T_CFG.Y_BTN2_MENU, T_CFG.PXL_BTN_MENU, T_CFG.PXL_BTN_MENU);
		add(labelVolverMenu);
		
		setVisible(true);
		      
	}
        
}
