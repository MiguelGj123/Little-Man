package entidad;

public class EntidadInamoviblePowerupFactory {
	
	private static EntidadInamoviblePowerupFactory myPowerupFactory;
	
	private EntidadInamoviblePowerupFactory() {}
	
	public static EntidadInamoviblePowerupFactory getPowerupFactory()
	{
		if (myPowerupFactory == null)
		{
			myPowerupFactory = new EntidadInamoviblePowerupFactory();
		}
		return myPowerupFactory;
	}
	
	public EntidadInamoviblePowerup generate (String pTipoPowerup, int posX, int posY)
	{
		EntidadInamoviblePowerup tipoPowerup;
		
		switch (pTipoPowerup)
		{
			case "VidaRec":
				tipoPowerup = new EntidadInamoviblePowerupVidaRec(posX, posY);
				break;
			case "VidaMas":
				tipoPowerup = new EntidadInamoviblePowerupVidaMas(posX, posY);
				break;
			case "BombaMas":
				tipoPowerup = new EntidadInamoviblePowerupBombaMas(posX, posY);
				break;
			case "FuegoMas":
				tipoPowerup = new EntidadInamoviblePowerupFuegoMas(posX, posY);
				break;
			case "TiempoMas":
				tipoPowerup = new EntidadInamoviblePowerupTiempoMas(posX, posY);
				break;
			case "Invencible":
				tipoPowerup = new EntidadInamoviblePowerupInvencible(posX, posY);
				break;
			case "BombaPatada":
				tipoPowerup = new EntidadInamoviblePowerupBombaPatada(posX, posY);
				break;
			case "BombaPincho":
				tipoPowerup = new EntidadInamoviblePowerupBombaPincho(posX, posY);
				break;
			case "Puntos":
				tipoPowerup = new EntidadInamoviblePowerupPuntos(posX, posY);
				break;
			case "BombaMenos":
				tipoPowerup = new EntidadInamoviblePowerupBombaMenos(posX, posY);
				break;
			case "FuegoMenos":
				tipoPowerup = new EntidadInamoviblePowerupFuegoMenos(posX, posY);
				break;
			case "TickBombaMas":
				tipoPowerup = new EntidadInamoviblePowerupTickBombaMas(posX, posY);
				break;
			case "TickBombaMenos":
				tipoPowerup = new EntidadInamoviblePowerupTickBombaMenos(posX, posY);
				break;
			case "Aleatorio":
				tipoPowerup = new EntidadInamoviblePowerupAleatorio(posX, posY);
				break;
			default:
				tipoPowerup = new EntidadInamoviblePowerupAleatorio(posX, posY);
				break;
		}
		return tipoPowerup;
	}
	
}
