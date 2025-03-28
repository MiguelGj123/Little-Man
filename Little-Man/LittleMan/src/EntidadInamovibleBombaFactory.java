
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
				tipoBomba = new EntidadInamovibleBombaBlanca(posX, posY);
				break;
			case "NEGRO":
				tipoBomba = new EntidadInamovibleBombaNegra(posX, posY);
				break;
			default:
				tipoBomba = null;
				break;
		}
		return tipoBomba;
	}

	
}
