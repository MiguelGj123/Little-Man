
public class Bloque extends Inamovible 
{
	boolean completado = false;
	Tipo tipo;
	
	public Bloque (Tipo tipo) 
	{
		this.tipo = tipo;
	}
	
	public boolean romperbloque()
	{
		if (tipo != Tipo.DURO || tipo != Tipo.FUEGO) 
		{
			this.tipo = tipo.FUEGO;
			completado = true;
		}
		else 
		{
			completado = false;
		}
		
		return completado;					
	}
	
	public void quitarFuego() 
	{
		this.tipo = tipo.VACIO;
		
	}
	
	
	
	
}
