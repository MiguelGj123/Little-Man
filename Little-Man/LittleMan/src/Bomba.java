
public class Bomba extends Inamovible {
	private int ticks;
	
	public Bomba(int pDuracionBomba, int pPosX, int pPosY)
	{
		ticks = pDuracionBomba;
		super.setPosX(pPosX);
		super.setPosY(pPosY);
	}
	
	public boolean tick() 
	{
		ticks--;

		return ticks==0;
		
	}
	
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
