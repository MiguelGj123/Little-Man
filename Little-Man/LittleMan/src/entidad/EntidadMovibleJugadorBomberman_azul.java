package entidad;

// Jugador de tipo AZUL. Tiene 3 vidas, 3 bombas máximas, y codificaciones gráficas propias.
public class EntidadMovibleJugadorBomberman_azul extends EntidadMovibleJugador {

	private static final int maxBombas = 3;
	private static final int vidas = 3;
	private static final String tipoBomba = "AZUL";
	private static final int[] codigosJugador = {201, 211, 221, 231}; // normal, con bomba, muerto, victoria
	private static final String tipoJugador = "AZUL";
	private static final int invencibilidad = 40;

	public EntidadMovibleJugadorBomberman_azul(int posX, int posY) {
		super(maxBombas, vidas, tipoBomba, codigosJugador, tipoJugador, posX, posY, invencibilidad);
	}
}
