package Package;

public class BloqueFactory {
	private static BloqueFactory myBloqueFactory;
	
	private BloqueFactory() {}
	
	public static BloqueFactory getBloqueFactory()
	{
		if (myBloqueFactory == null)
		{
			myBloqueFactory = new BloqueFactory();
		}
		return myBloqueFactory;
	}
	
	public BloqueState generate (String pState)
	{
		BloqueState state;
		
		switch (pState)
		{
			case "DURO":
				state = new BloqueStateDuro();
				break;
			case "BLANDO":
				state = new BloqueStateBlando();
				break;
			case "FUEGO":
				state = new BloqueStateFuego();
				break;
			case "VACIO":
				state = new BloqueStateVacio();
				break;
			default:
				state = new BloqueStateVacio();
				break;
		}
		return state;
	}
}
