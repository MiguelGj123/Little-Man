package frameTablero;

import javax.swing.JPanel;


public class Frm__T__03__02__02__Panel__MenuBotones extends JPanel {

    private static final long serialVersionUID = 1L;
	private Frm__T__03__02__02c__Panel__FilaBotonesVoD filaBotonesVoD;
	private Frm__T__03__02__02b__Panel__FilaBotonesPausa filaBotonesPausa;


	public Frm__T__03__02__02__Panel__MenuBotones() {
	    setOpaque(false);
	    setLayout(null);
	   
	    filaBotonesVoD = new Frm__T__03__02__02c__Panel__FilaBotonesVoD();
	    filaBotonesVoD.setBounds(0, 0, T_CFG.ANCHO_BTNS_MENU, T_CFG.ALTO_BTNS_MENU);
	    add(filaBotonesVoD);
	    
	    filaBotonesPausa = new Frm__T__03__02__02b__Panel__FilaBotonesPausa();
	    filaBotonesPausa.setBounds(0, 0, T_CFG.ANCHO_BTNS_MENU, T_CFG.ALTO_BTNS_MENU);
	    add(filaBotonesPausa);
	}

	public void cambiarBotones( String comandos) 
	{
		
        if (comandos.equals("PAUSE")) {
        	filaBotonesPausa.setVisible(true);
        	filaBotonesVoD.setVisible(false);
        } else {
        	filaBotonesPausa.setVisible(false);
        	filaBotonesVoD.setVisible(true);
        }
	}
	
}
