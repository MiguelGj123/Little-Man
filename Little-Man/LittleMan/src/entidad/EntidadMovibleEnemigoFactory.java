package entidad;

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
	
	public EntidadMovibleEnemigo generate (String pEnemigo, int posX, int posY)
	{
		EntidadMovibleEnemigo enemigo;
		
		switch (pEnemigo)
		{
			case "BALOON":
				enemigo = new EntidadMovibleEnemigoBaloon(posX, posY);
				break;
			case "DORIA":
				enemigo = new EntidadMovibleEnemigoDoria(posX, posY);
				break;
			default:
				enemigo = new EntidadMovibleEnemigoBaloon(posX, posY);
				break;
		}
		return enemigo;
	}
}
