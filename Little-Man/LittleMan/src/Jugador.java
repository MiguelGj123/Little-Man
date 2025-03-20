
public abstract class Jugador extends Movible 
{
	
	
	public abstract boolean gestionarVida();

	public abstract boolean menosXBombas();
	
	public abstract void sumarVida();
	
	public abstract void ponerBomba(); 
	
	public abstract void bombaExplotada();
	
	public abstract int radioBomba();

	public abstract int duracionBomba();
	

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
