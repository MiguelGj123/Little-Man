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
				//System.out.println("Left");
				break;
			case "R":
				direccion = EscenarioTecladoDireccionRight.getRight();
				//System.out.println("Right");
				break;
			case "U":
				direccion = EscenarioTecladoDireccionUp.getUp();
				//System.out.println("Up");
				break;
			case "D":
				direccion = EscenarioTecladoDireccionDown.getDown();
				//System.out.println("Down");
				break;
			default:
				direccion = null;
				break;
		}
		
		return direccion;
	}
}
