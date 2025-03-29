
public class EntidadInamovibleBloqueFactory {
	
	private static EntidadInamovibleBloqueFactory myBloqueFactory;
	
	private EntidadInamovibleBloqueFactory() {}
	
	public static EntidadInamovibleBloqueFactory getBloqueFactory()
	{
		if (myBloqueFactory == null)
		{
			myBloqueFactory = new EntidadInamovibleBloqueFactory();
		}
		return myBloqueFactory;
	}
	
	public EntidadInamovibleBloqueState generate (String pState)
	{
		EntidadInamovibleBloqueState state;
		
		switch (pState)
		{
			case "DURO":
				state = new EntidadInamovibleBloqueStateDuro();
				break;
			case "BLANDO":
				state = new EntidadInamovibleBloqueStateBlando();
				break;
			case "FUEGO":
				state = new EntidadInamovibleBloqueStateFuego();
				break;
			case "VACIO":
				state = new EntidadInamovibleBloqueStateVacio();
				break;
			default:
				state = new EntidadInamovibleBloqueStateVacio();
				break;
		}
		return state;
	}
}
