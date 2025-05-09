package entidad;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

//Fábrica que genera bombas según el tipo de jugador (color): blanco, negro, rojo, azul.
//Devuelve instancias concretas con sus radios y tiempos definidos.
public class EntidadInamovibleBombaFactory {

	private static EntidadInamovibleBombaFactory myBombaFactory = new EntidadInamovibleBombaFactory();

	private Map<String, BiFunction<Integer, Integer, EntidadInamovibleBomba>> bombaMap;

	private EntidadInamovibleBombaFactory() {
		bombaMap = new HashMap<>();
		bombaMap.put("BLANCO", EntidadInamovibleBombaSuper::new);
		bombaMap.put("NEGRO", EntidadInamovibleBombaUltra::new);
		bombaMap.put("ROJO",  EntidadInamovibleBombaMega::new);
	}

	public static EntidadInamovibleBombaFactory getBombaFactory() {
		return myBombaFactory;
	}

	// Devuelve una instancia concreta de bomba según el tipo de jugador. Azul es caso especial.
	public EntidadInamovibleBomba generate(String pTipoBomba, int posX, int posY, int radio) {
		if ("AZUL".equals(pTipoBomba)) {
			return new EntidadInamovibleBombaHyper(posX, posY, radio);
		}
		return bombaMap.getOrDefault(pTipoBomba, (x, y) -> null).apply(posX, posY);
	}
}
