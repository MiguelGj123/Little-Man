package entidad;

// Clase abstracta para entidades que pueden moverse, como jugadores y enemigos.
// Proporciona getters y setters para la posición.
public abstract class EntidadMovible extends Entidad {

	public EntidadMovible(int posX, int posY) { super(posX, posY); }

	public void setPosX(int pPosX) { super.setPosX(pPosX); }

	public void setPosY(int pPosY) { super.setPosY(pPosY); }

	public int getPosX() { return super.getPosX(); }

	public int getPosY() { return super.getPosY(); }
}
