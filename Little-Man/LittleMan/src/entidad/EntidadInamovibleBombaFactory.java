package entidad;

public class EntidadInamovibleBombaFactory {
	
	private static EntidadInamovibleBombaFactory myBombaFactory;
	
	private EntidadInamovibleBombaFactory() {}
	
	public static EntidadInamovibleBombaFactory getBombaFactory()
	{
		if (myBombaFactory == null)
		{
			myBombaFactory = new EntidadInamovibleBombaFactory();
		}
		return myBombaFactory;
	}
	
	public EntidadInamovibleBomba generate (String pTipoBomba, int posX, int posY)
	{
		EntidadInamovibleBomba tipoBomba;
		
		switch (pTipoBomba)
		{
			case "BLANCO":
				tipoBomba = new EntidadInamovibleBombaSuper(posX, posY);
				break;
			case "NEGRO":
				tipoBomba = new EntidadInamovibleBombaUltra(posX, posY);
				break;
			default:
				tipoBomba = null;
				break;
		}
		return tipoBomba;
	}

	
}
