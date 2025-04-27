package entidad;

public abstract class EntidadInamoviblePowerup extends EntidadInamovible{
	
	private int codigoPowerup;
	
	public EntidadInamoviblePowerup(int posX, int posY, int pCodigoPowerup) {
		
		super(posX, posY);
		this.codigoPowerup = pCodigoPowerup;
		
	}
	
	public int getCodigoPowerup() { return codigoPowerup; }
	
	public int getPosX() { return super.getPosX(); }
	
	public int getPosY() { return super.getPosY(); }
	
}
