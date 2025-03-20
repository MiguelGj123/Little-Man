
public class Bomberman_blanco extends Jugador 
{
	private int contadorBombas = 0;
	private boolean bombaPuesta = false;
	private int vidas=1;
	private final int radioBomba = 1;
	private final int duracionBomba = 60;
	
	public Bomberman_blanco() {
		super.setPosX(0);
		super.setPosY(0);
	}
	
	public boolean gestionarVida()
	{
		vidas--;
		return vidas <= 0;
	}
	
	public boolean menosXBombas()
	{
		return contadorBombas < 10;
	}
	
	@Override
	public void ponerBomba() 
	{
		contadorBombas++;
	}
	
	
	
	@Override
	public void bombaExplotada()
	{
		contadorBombas--;
	}
	
	@Override
	public int radioBomba() 
	{
		return radioBomba;
	}
	
	public int duracionBomba() 
	{
		return duracionBomba;
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
