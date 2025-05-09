package main;

import frameMenuPrincipal.Frm__00__Frame_Principal;

//Clase principal que lanza la aplicación. Inicia el menú principal del juego.
public class Main {

	// Inicia el menú principal cargando su panel y mostrándolo en pantalla.
	public static void main(String[] args)
    {

        Frm__00__Frame_Principal frameInicio = Frm__00__Frame_Principal.getMenuPrincipal();
        frameInicio.inicializarPanel();
        frameInicio.setVisible(true);
     
    }

}