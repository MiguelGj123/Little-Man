package main;

import escenario.Escenario;
import frameMenuPrincipal.FrameMenuPrincipal;

public class Main {
    public static void main(String[] args)
    {

        //FrameMenuPrincipal frameInicio = new FrameMenuPrincipal();
        //frameInicio.setVisible(true);
    	
    	Escenario.getEscenario().inicializarTablero("AZUL", "NORMAL", "dificil", "BAJO");

        

    }


}
