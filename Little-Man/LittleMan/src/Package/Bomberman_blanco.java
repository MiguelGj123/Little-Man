package Package;
public class Bomberman_blanco extends Jugador 
{
	private int contadorBombas = 0;
	private int vidas=1;
	private final int radioBomba = 1;
	private final int duracionBomba = 60;
	
	public Bomberman_blanco() {
		super.setPosX(0);
		super.setPosY(0);
	}
	
	// Reduce una vida si aún tiene
	public void gestionarVida()
	{
		if (vidas > 0)
		{
			vidas--;
		}
	}
	
	// Devuelve true si el jugador no tiene vidas
	public boolean getEstaMuerto()
	{
		return vidas == 0;
	}
	
	// Restaura la vida del jugador
	public void sumarVida()
	{
		vidas=1;
		return;
	}
	
	// Verifica si puede colocar más bombas
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