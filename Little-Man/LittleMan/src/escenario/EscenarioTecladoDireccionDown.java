package escenario;

public class EscenarioTecladoDireccionDown extends EscenarioTecladoDireccion{
	
	private static final String direction = "D";
	private static EscenarioTecladoDireccionDown miDown;
	
	private EscenarioTecladoDireccionDown() {
		super(direction);
	}
	
	public static EscenarioTecladoDireccionDown getDown() {
		if (miDown == null) miDown = new EscenarioTecladoDireccionDown();
		return miDown;
	}

}
