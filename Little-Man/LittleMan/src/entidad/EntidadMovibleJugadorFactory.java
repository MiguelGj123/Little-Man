package entidad;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

//Fábrica que genera instancias de jugadores según su color.
//Implementa patrón Factory.
public class EntidadMovibleJugadorFactory {

	private static EntidadMovibleJugadorFactory myEntidadMovibleJugadorFactory =
		new EntidadMovibleJugadorFactory();

	private Map<String, BiFunction<Integer, Integer, EntidadMovibleJugador>> jugadorMap;

	private EntidadMovibleJugadorFactory() {
		jugadorMap = new HashMap<>();
		jugadorMap.put("BLANCO", EntidadMovibleJugadorBomberman_blanco::new);
		jugadorMap.put("NEGRO", EntidadMovibleJugadorBomberman_negro::new);
		jugadorMap.put("AZUL", EntidadMovibleJugadorBomberman_azul::new);
		jugadorMap.put("ROJO", EntidadMovibleJugadorBomberman_rojo::new);
	}

	public static EntidadMovibleJugadorFactory getEntidadMovibleJugadorFactory() {
		return myEntidadMovibleJugadorFactory;
	}

	public EntidadMovibleJugador generate(String pJugador, int posX, int posY) {
		return jugadorMap.getOrDefault(pJugador, EntidadMovibleJugadorBomberman_blanco::new)
		                 .apply(posX, posY);
	}
}
