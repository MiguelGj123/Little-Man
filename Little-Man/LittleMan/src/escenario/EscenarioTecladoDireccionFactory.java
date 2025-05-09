package escenario;

//Fábrica Singleton que devuelve instancias concretas de direcciones de teclado según un identificador (L, R, U, D).
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
	
	// Devuelve una dirección concreta (Left, Right, Up, Down) en función del string recibido.
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
