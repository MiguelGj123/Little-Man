package main;

import escenario.Escenario;
import frameMenuPrincipal.FrameMenuPrincipal;

public class Main {
    public static void main(String[] args)
    {

        FrameMenuPrincipal frameInicio = FrameMenuPrincipal.getFrameMenuPrincipal().inicializarFrameMenuPrincipal();
        frameInicio.setVisible(true);
    	
    	//Escenario.getEscenario().inicializarTablero("ROJO", "NORMAL", "dificil", "BAJO");

        

    }


}
