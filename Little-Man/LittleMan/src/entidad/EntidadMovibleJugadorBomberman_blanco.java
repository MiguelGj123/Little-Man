package entidad;

public class EntidadMovibleJugadorBomberman_blanco extends EntidadMovibleJugador 
{
	
	private static final int 	maxBombas				= 10;
	private static final int 	vidas					= 1;
	private static final String tipoBomba				= "BLANCO";
	private static final int[]	codigosJugador			= {20, 21, 22};
	private static final String	tipoJugador				= "BLANCO";
	
	public EntidadMovibleJugadorBomberman_blanco(int posX, int posY) {
		super(maxBombas, vidas, tipoBomba, codigosJugador, tipoJugador, posX, posY);
	}
}
