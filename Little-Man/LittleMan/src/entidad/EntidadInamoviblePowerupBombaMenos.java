package entidad;

// Power-up que  reduce el número de bombas disponibles. Código 602.
public class EntidadInamoviblePowerupBombaMenos extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 602;

	public EntidadInamoviblePowerupBombaMenos(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}