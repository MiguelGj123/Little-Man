package entidad;

public class EntidadMovibleJugadorBomberman_rojo extends EntidadMovibleJugador 
{
	
	private static final int	maxBombas				= 2;
	private static final int	vidas					= 2;
	private static final String tipoBomba				= "ROJO";
	private static final int[]	codigosJugador			= {251, 261, 271, 281};
	private static final String	tipoJugador				= "ROJO";
	private static final int 	invencibilidad			= 40;

	
	public EntidadMovibleJugadorBomberman_rojo(int posX, int posY) {
		super(maxBombas, vidas, tipoBomba, codigosJugador, tipoJugador, posX, posY, invencibilidad);
	}

}
