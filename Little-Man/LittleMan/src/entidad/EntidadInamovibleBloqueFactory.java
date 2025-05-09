package entidad;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

//Fábrica de bloques que implementa el patrón Factory.
//Devuelve instancias de estados concretos según una clave String ("DURO", "BLANDO", etc.)
public class EntidadInamovibleBloqueFactory {

	private static EntidadInamovibleBloqueFactory myBloqueFactory = new EntidadInamovibleBloqueFactory();

	private Map<String, Supplier<EntidadInamovibleBloqueState>> stateMap;

	private EntidadInamovibleBloqueFactory() {
		stateMap = new HashMap<>();
		stateMap.put("DURO", EntidadInamovibleBloqueStateDuro::new);
		stateMap.put("BLANDO", EntidadInamovibleBloqueStateBlando::new);
		stateMap.put("FUEGO", EntidadInamovibleBloqueStateFuego::new);
		stateMap.put("VACIO", EntidadInamovibleBloqueStateVacio::new);
	}

	public static EntidadInamovibleBloqueFactory getBloqueFactory() {
		return myBloqueFactory;
	}

	// Devuelve una instancia del estado correspondiente al tipo de bloque recibido.
	public EntidadInamovibleBloqueState generate(String pState) {
		return stateMap.getOrDefault(pState, EntidadInamovibleBloqueStateVacio::new).get();
	}
}
