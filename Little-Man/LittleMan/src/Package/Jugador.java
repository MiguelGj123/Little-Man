package Package;
public abstract class Jugador extends Movible 
{
	
	// Métodos abstractos que deben ser implementados por las subclases
	public abstract void gestionarVida();
	
	public abstract boolean getEstaMuerto();

	public abstract boolean menosXBombas();
	
	public abstract void sumarVida();
	
	public abstract void ponerBomba(); 
	
	public abstract void bombaExplotada();
	
	public abstract int radioBomba();

	public abstract int duracionBomba();
	
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
