package frameMenuPrincipal;


public class MenuPrincipal {
	private static MenuPrincipal miMenuPrincipal;
	private boolean listo;
	private MenuPrincipalPersonaje personaje;
	private String escenario;
	private String dificultad;
	private String volumen;
	
	private MenuPrincipal() {
		personaje = MenuPrincipalPersonaje.getMyMenuPrincipalPersonaje();
	}
	
	public static MenuPrincipal getMenuPrincipal() {
		if(miMenuPrincipal==null) miMenuPrincipal=new MenuPrincipal();
		return miMenuPrincipal;
	}
	
	//Metodo que establece el jugador dependiendo delo recibido por el controlador
	public String seleccionPersonaje(String pPersonaje) {
		if (!listo) personaje.changeState(MenuPrincipalPersonajeFactory.getPersonajeFactory().generate(pPersonaje));
		return personaje.getTipo(); //Notifica que el personaje ha cambiado
	}
	public void seleccionarOpciones(int pDificultad, int pVolumen, int pEscenario) {
		switch (pDificultad) {
		case 0:
			this.dificultad="pacifico";
			break;
		case 1:
			this.dificultad="facil";
			break;
		case 2:
			this.dificultad="normal";
			break;
		case 3:
			this.dificultad="dificil";
			break;
		default:
			this.dificultad="facil";
			break;
		}
		switch (pVolumen) {
		case 0:
			this.volumen="APAGADO";
			break;
		case 1:
			this.volumen="BAJO";
			break;
		case 2:
			this.volumen="MEDIO";
			break;
		case 3:
			this.volumen="ALTO";
			break;
		default:
			this.volumen="BAJO";
			break;
		}
		switch (pEscenario) {
		case 1:
			this.escenario="NORMAL";
			break;
		case 2:
			this.escenario="NO_DURO";
			break;
		case 3:
			this.escenario="VACIO";
			break;
		default:
			this.escenario="NORMAL";
			break;
		}
		
	}
	
	
	public String[] iniciarJuego() {
		if (!listo) {
			listo=true;
			return new String[] {personaje.getTipo(), escenario, dificultad, volumen};
		}
		return null;
	}
	
	public boolean isReady() {
		return listo;
	}
	
	public String pressA() {
		if (!listo) personaje.rotarIzquierda();
		return personaje.getTipo();
	}
	public String pressD() {
		if (!listo) personaje.rotarDerecha();
		return personaje.getTipo();
	}
	
	public String getPersonajeSelecionado() 
	{
		return personaje.getTipo();
	}	
	
	
}
