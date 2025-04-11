package entidad;

public class EntidadMovibleJugadorBomberman_negro extends EntidadMovibleJugador 
{
	
	private static final int	maxBombas				= 1;
	private static final int	vidas					= 1;
	private static final String tipoBomba				= "NEGRO";
	private static final int[]	codigosJugador			= {25, 26, 27, 28};
	private static final String	tipoJugador				= "NEGRO";
	
	public EntidadMovibleJugadorBomberman_negro(int posX, int posY) {
		super(maxBombas, vidas, tipoBomba, codigosJugador, tipoJugador, posX, posY);
	}

}