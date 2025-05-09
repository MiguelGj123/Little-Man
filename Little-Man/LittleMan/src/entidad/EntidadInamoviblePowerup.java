package entidad;

// Clase base abstracta para todos los power-ups. Cada uno tiene un código que lo identifica.
public abstract class EntidadInamoviblePowerup extends EntidadInamovible {

	private int codigoPowerup;

	public EntidadInamoviblePowerup(int posX, int posY, int pCodigoPowerup) {
		super(posX, posY);
		this.codigoPowerup = pCodigoPowerup;
	}

	public int getCodigoPowerup() { return codigoPowerup; }

	public int getPosX() { return super.getPosX(); }

	public int getPosY() { return super.getPosY(); }
}
