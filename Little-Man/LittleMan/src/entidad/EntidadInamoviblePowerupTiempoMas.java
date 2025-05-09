package entidad;

// Power-up que otorga tiempo adicional al jugador. Código 611.
public class EntidadInamoviblePowerupTiempoMas extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 611;

	public EntidadInamoviblePowerupTiempoMas(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}