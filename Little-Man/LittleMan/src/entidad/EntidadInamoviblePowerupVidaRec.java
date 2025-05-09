package entidad;

// Power-up que recupera vida (sin añadir una nueva). Código 613.
public class EntidadInamoviblePowerupVidaRec extends EntidadInamoviblePowerup {

	private static final int codigoPowerup = 613;

	public EntidadInamoviblePowerupVidaRec(int posX, int posY) {
		super(posX, posY, codigoPowerup);
	}
}