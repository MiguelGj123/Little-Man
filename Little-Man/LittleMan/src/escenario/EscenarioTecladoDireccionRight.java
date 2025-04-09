package escenario;

public class EscenarioTecladoDireccionRight extends EscenarioTecladoDireccion{
	
	private static final String direction = "R";
	private static EscenarioTecladoDireccionRight miRight;
	
	private EscenarioTecladoDireccionRight() {
		super(direction);
	}
	
	public static EscenarioTecladoDireccionRight getRight() {
		if (miRight == null) miRight = new EscenarioTecladoDireccionRight();
		return miRight;
	}
	

	


}
