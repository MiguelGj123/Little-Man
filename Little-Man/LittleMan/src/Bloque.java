
public class Bloque extends Inamovible 
{
	private boolean completado = false;
	private Tipo tipo;
	private int ticks;
	
	public Bloque (Tipo tipo) 
	{
		this.tipo = tipo;
	}
	
	public boolean romperbloque()
	{
		if (tipo != Tipo.DURO || tipo != Tipo.FUEGO) 
		{
			this.tipo = tipo.FUEGO;
			ticks = 40;
			completado = true;
		}
		else 
		{
			completado = false;
		}
		
		return completado;					
	}
	
	public boolean tick()
	{
		ticks--;
		
		if(ticks==0) 
		{
			tipo = tipo.VACIO;
		}
		
		return ticks == 0;
	}
	
	public Tipo getTipo() {
		return tipo;
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
