
public class EntidadMovibleJugadorFactory {
	
	private static EntidadMovibleJugadorFactory myEntidadMovibleJugadorFactory;
	
	private EntidadMovibleJugadorFactory() {}
	
	public static EntidadMovibleJugadorFactory getEntidadMovibleJugadorFactory()
	{
		if (myEntidadMovibleJugadorFactory == null)
		{
			myEntidadMovibleJugadorFactory = new EntidadMovibleJugadorFactory();
		}
		return myEntidadMovibleJugadorFactory;
	}
	
	public EntidadMovibleJugador generate (String pJugador, int posX, int posY)
	{
		EntidadMovibleJugador jugador;
		
		switch (pJugador)
		{
			case "BLANCO":
				jugador = new EntidadMovibleJugadorBomberman_blanco(posX, posY);
				break;
			case "NEGRO":
				jugador = new EntidadMovibleJugadorBomberman_negro(posX, posY);
				break;
			default:
				jugador = new EntidadMovibleJugadorBomberman_blanco(posX, posY);
				break;
		}
		return jugador;
	}
}
