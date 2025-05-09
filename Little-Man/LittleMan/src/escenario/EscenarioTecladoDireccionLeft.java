package escenario;

public class EscenarioTecladoDireccionLeft extends EscenarioTecladoDireccion {
	
	private static final String direction = "L";
	private static EscenarioTecladoDireccionLeft miLeft;
	
	private EscenarioTecladoDireccionLeft() {
		super(direction);
	}
	
	public static EscenarioTecladoDireccionLeft getLeft() {
		if (miLeft == null) miLeft = new EscenarioTecladoDireccionLeft();
		return miLeft;
	}
	
}
