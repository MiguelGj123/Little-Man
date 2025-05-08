package frameTablero;


import javax.swing.JPanel;


public class Frm__T__03__02__02b__Panel__FilaBotonesPausa extends JPanel {

    
    private static final long serialVersionUID = 1L;
   	private Frm__T__04__02__02__Label__Continuar labelContinuar;
   	private Frm__T__04__02__03__Label__VolverMenu labelVolverMenu;	


   	public Frm__T__03__02__02b__Panel__FilaBotonesPausa() 
   	{
   		setOpaque(false);
   		setLayout(null);  // Aquí puedes ajustar los márgenes entre los botones
   		
   		labelContinuar = new Frm__T__04__02__02__Label__Continuar();
   		labelContinuar.setBounds(T_CFG.X_BTN1_MENU, T_CFG.Y_BTN1_MENU, T_CFG.PXL_BTN_MENU, T_CFG.PXL_BTN_MENU);
		add(labelContinuar);

   		labelVolverMenu = new Frm__T__04__02__03__Label__VolverMenu();
   		labelVolverMenu.setBounds(T_CFG.X_BTN2_MENU, T_CFG.Y_BTN2_MENU, T_CFG.PXL_BTN_MENU, T_CFG.PXL_BTN_MENU);   		
   		add(labelVolverMenu);
   		
   		setVisible(true);
   		      
   	}
	



	
}
