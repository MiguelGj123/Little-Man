package Package;

public class EntidadMovibleEnemigoFactory {
	
	private static EntidadMovibleEnemigoFactory myEntidadMovibleEnemigoFactory;
	
	private EntidadMovibleEnemigoFactory() {}
	
	public static EntidadMovibleEnemigoFactory getEntidadMovibleEnemigoFactory()
	{
		if (myEntidadMovibleEnemigoFactory == null)
		{
			myEntidadMovibleEnemigoFactory = new EntidadMovibleEnemigoFactory();
		}
		return myEntidadMovibleEnemigoFactory;
	}
	
	public EntidadMovibleEnemigo generate (String pEnemigo)
	{
		EntidadMovibleEnemigo enemigo;
		
		switch (pEnemigo)
		{
			case "BALOON":
				enemigo = new EntidadMovibleEnemigoBaloon();
				break;
			default:
				enemigo = new EntidadMovibleEnemigoBaloon();
				break;
		}
		return enemigo;
	}
}
