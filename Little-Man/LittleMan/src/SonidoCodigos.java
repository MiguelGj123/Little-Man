
public class SonidoCodigos {
	
	/*
	 * 0 -> Esa celda no emite sonido
	 * 1-8 -> Esa celda empieza a emitir ese sonido
	 * 99 -> Se para el sonido
	 * 
	 */
	
	private final String[] idSonidos = new String[] { "BOMB_EXPLODE", "DEATH", "ENEMY_DEATH", "ITEM_GET", "PLACE_BOMB", "WALK", "WIN", "MUSIC"};
	private final String[] idComandosSonidos = new String[] { "SONAR_SONIDO", "PARAR_SONIDO" };
	
	private static SonidoCodigos miSonidoCodigos;
	
	private SonidoCodigos() {};
	
	public static SonidoCodigos getSonidoCodigos() {
		if (miSonidoCodigos == null) {
			miSonidoCodigos = new SonidoCodigos();
		}
		return miSonidoCodigos;
	}
	
	private String getCodigoSonido (SonidoCodigosEnum sonidoCodigo) {
		String codigo;
		
		switch (sonidoCodigo){
			case BOMB_EXPLODE:
				codigo = idSonidos[0];
				break;
			case DEATH:
				codigo = idSonidos[1];
				break;
			case ENEMY_DEATH:
				codigo = idSonidos[2];
				break;
			case ITEM_GET:
				codigo = idSonidos[3];
				break;
			case PLACE_BOMB:
				codigo = idSonidos[4];
				break;
			case WALK:
				codigo = idSonidos[5];
				break;
			case WIN:
				codigo = idSonidos[6];
				break;
			case MUSIC:
				codigo = idSonidos[7];
				break;
			default:
				codigo = "SONIDO_VACIO";
		}
		
		return codigo;
	}
	
	public String getCodigoSonarSonido(SonidoCodigosEnum sonidoCodigo) {
		return idComandosSonidos[0] + "*" + getCodigoSonido(sonidoCodigo);					// El texto "*" separa el comando y 
	}
	
	
	public String getCodigoPararSonido(SonidoCodigosEnum sonidoCodigo) {
		return idComandosSonidos[1] + "*" + getCodigoSonido(sonidoCodigo);					// El texto "*" separa el comando y 
	}
	

	
	
}
