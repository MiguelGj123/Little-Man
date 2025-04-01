
public class MenuPrincipalPersonajeFactory {
	
	private static MenuPrincipalPersonajeFactory myPersonajeFactory;
	
	private MenuPrincipalPersonajeFactory() {}
	
	public static MenuPrincipalPersonajeFactory getPersonajeFactory()
	{
		if (myPersonajeFactory == null)
		{
			myPersonajeFactory = new MenuPrincipalPersonajeFactory();
		}
		return myPersonajeFactory;
	}
	
	public MenuPrincipalPersonajeState generate (String pState)
	{
		MenuPrincipalPersonajeState state;
		
		switch (pState)
		{
			case "BLANCO":
				state = new MenuPrincipalPersonajeBlanco();
				break;
			case "NEGRO":
				state = new MenuPrincipalPersonajeNegro();
				break;
			default:
				state = new MenuPrincipalPersonajeBlanco();
		}
		return state;
	}
}
