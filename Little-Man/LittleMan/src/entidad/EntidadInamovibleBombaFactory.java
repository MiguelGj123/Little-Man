package entidad;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

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

    public EntidadInamovibleBomba generate(String pTipoBomba, int posX, int posY, int radio) {
        if ("AZUL".equals(pTipoBomba)) {
            return new EntidadInamovibleBombaHyper(posX, posY, radio);
        }
        return bombaMap
                .getOrDefault(pTipoBomba, (x, y) -> null)
                .apply(posX, posY);
    }
}
