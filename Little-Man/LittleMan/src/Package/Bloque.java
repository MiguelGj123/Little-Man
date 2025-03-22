package Package;

public class Bloque extends Inamovible 
{
	private Tipo tipo; // Tipo de bloque (VACIO, BLANDO, DURO, FUEGO)
	private int ticks; // Contador para la transición de estado
	
	public Bloque (Tipo tipo) 
	{
		this.tipo = tipo;
	}
	
	// Rompe el bloque si no es de tipo DURO
	public void romperbloque()
	{
		if (tipo != Tipo.DURO) 
		{
			this.tipo = Tipo.FUEGO;
			
			ticks = 40;
		}
	}
	
	public boolean tick()
	{
		ticks--;

		if(ticks==0) 
		{
			tipo = tipo.VACIO; // El bloque se marca como VACIO tras el tiempo
		}
		
		return ticks < 0;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	// Métodos delegados a la superclase
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
