package escenario;

public class EscenarioTecladoDireccionUp extends EscenarioTecladoDireccion{
	
	private static final String direction = "U";
	private static EscenarioTecladoDireccionUp miUp;
	
	private EscenarioTecladoDireccionUp() {
		super(direction);
	}
	
	public static EscenarioTecladoDireccionUp getUp() {
		if (miUp == null) miUp = new EscenarioTecladoDireccionUp();
		return miUp;
	}


}
