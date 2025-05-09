package entidad;

// Clase abstracta base para cualquier entidad del juego, con posición X e Y
public abstract class Entidad {
	
	private int posX, posY;  // Coordenadas de la entidad
	
	public Entidad(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void setPosX(int pPosX) { posX = pPosX; }

	public void setPosY(int pPosY) { posY = pPosY; }

	public int getPosX() { return posX; }
	
	public int getPosY() { return posY; }
}
