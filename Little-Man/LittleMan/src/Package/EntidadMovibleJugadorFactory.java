package Package;

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
	
	public EntidadMovibleJugador generate (String pJugador)
	{
		EntidadMovibleJugador jugador;
		
		switch (pJugador)
		{
			case "BLANCO":
				jugador = new EntidadMovibleJugadorBomberman_blanco();
				break;
			case "NEGRO":
				jugador = new EntidadMovibleJugadorBomberman_negro();
				break;
			default:
				jugador = new EntidadMovibleJugadorBomberman_blanco();
				break;
		}
		return jugador;
	}
}
