package main;

import frameMenuPrincipal.Frm__00__Frame_Principal;

public class Main {

	public static void main(String[] args)
    {

        Frm__00__Frame_Principal frameInicio = Frm__00__Frame_Principal.getMenuPrincipal();
        frameInicio.inicializarPanel();
        frameInicio.setVisible(true);
        
    	//Escenario.getEscenario().inicializarTablero("ROJO", "NORMAL", "dificil", "BAJO");
    }

}