package entidad;

// Power-up que genera un power-up aleatorio. Representado con código 600.
public class EntidadInamoviblePowerupAleatorio extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 600;

	public EntidadInamoviblePowerupAleatorio(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}
