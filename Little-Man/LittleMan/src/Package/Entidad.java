package Package;

public abstract class Entidad {
	
	private int posX, posY; // Coordenadas de la entidad

	public Tipo getTipo() {
		return null; // Método a sobrescribir en subclases
	}
	
	public void setPosX(int pPosX)
	{
		posX = pPosX;
	}

	public void setPosY(int pPosY)
	{
		posY = pPosY;
	}

	public int getPosX()
	{
		return posX;
	}
	
	public int getPosY()
	{
		return posY;
	}


}
