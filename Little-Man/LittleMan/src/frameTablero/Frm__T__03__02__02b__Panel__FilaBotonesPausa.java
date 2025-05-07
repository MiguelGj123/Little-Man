package frameTablero;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import frameMenuPrincipal.CONFIG__MENU_OPCIONES;
import frameMenuPrincipal.Frm__MP__04__02__02__ToggleButton__Botones;

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
