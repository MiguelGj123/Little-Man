
public class Bomberman_blanco extends Jugador 
{
	int contadorBombas = 0;
	boolean bombaPuesta = false;
	
	@Override
	public boolean ponerBomba() 
	{
		if (contadorBombas<10)
	
		{
			bombaPuesta = true;
			contadorBombas++;
		}
		
		else
		{
			bombaPuesta = false;
		}
		
		return bombaPuesta;
		
	}
	
	@Override
	public void bombaExplotada() 
	{
		contadorBombas --;
		
	}
	
	@Override
	public int radioBomba() 
	{
		return 1;
	}
	
	public Tipo getTipo() {
		return Tipo.JUGADOR;
	}
}
