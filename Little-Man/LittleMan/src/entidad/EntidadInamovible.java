package entidad;

// Subclase abstracta para entidades que no pueden moverse (bloques, obstáculos, etc.)
public abstract class EntidadInamovible extends Entidad {
	
	public EntidadInamovible(int posX, int posY) { super(posX, posY); }

	public int getPosX() { return super.getPosX(); }

	public int getPosY() { return super.getPosY(); }

}