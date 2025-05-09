package escenario;

public class EscenarioTecladoDireccionFactory {
	
	private static EscenarioTecladoDireccionFactory myDirectionFactory;
	
	private EscenarioTecladoDireccionFactory() {}
	
	public static EscenarioTecladoDireccionFactory getDirectionFactory()
	{
		if (myDirectionFactory == null)
		{
			myDirectionFactory = new EscenarioTecladoDireccionFactory();
		}
		return myDirectionFactory;
	}
	
	public EscenarioTecladoDireccion generate (String pDireccion)
	{
		EscenarioTecladoDireccion direccion;
		
		switch (pDireccion)
		{
			case "L":
				direccion = EscenarioTecladoDireccionLeft.getLeft();

				break;
			case "R":
				direccion = EscenarioTecladoDireccionRight.getRight();

				break;
			case "U":
				direccion = EscenarioTecladoDireccionUp.getUp();

				break;
			case "D":
				direccion = EscenarioTecladoDireccionDown.getDown();

				break;
			default:
				direccion = null;
				break;
		}
		
		return direccion;
	}
}
