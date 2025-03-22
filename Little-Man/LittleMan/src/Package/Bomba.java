package Package;

public class Bomba extends Inamovible {
	private int ticks; // Duración de la bomba antes de explotar
	
	public Bomba(int pDuracionBomba, int pPosX, int pPosY)
	{
		ticks = pDuracionBomba;
		super.setPosX(pPosX);
		super.setPosY(pPosY);
	}
	
	public boolean tick() 
	{
		ticks--;

		return ticks<0; // Devuelve true cuando la bomba debe explotar
		
	}
	// Métodos que solo delegan en la superclase
	public void setPosX(int pPosX)
	{
		super.setPosX(pPosX);
	}
	
	public void setPosY(int pPosY)
	{
		super.setPosY(pPosY);
	}
	
	public int getPosX()
	{
		return super.getPosX();
	}
	
	public int getPosY()
	{
		return super.getPosY();
	}


}
