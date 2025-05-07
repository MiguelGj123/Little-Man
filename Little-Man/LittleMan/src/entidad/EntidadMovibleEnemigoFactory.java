package entidad;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class EntidadMovibleEnemigoFactory {

    private static EntidadMovibleEnemigoFactory myEntidadMovibleEnemigoFactory =
            new EntidadMovibleEnemigoFactory(); 

    private Map<String, BiFunction<Integer, Integer, EntidadMovibleEnemigo>> enemigoMap;

    private EntidadMovibleEnemigoFactory() {
        enemigoMap = new HashMap<>();
        enemigoMap.put("BALOON", EntidadMovibleEnemigoBaloon::new);
        enemigoMap.put("DORIA", EntidadMovibleEnemigoDoria::new);
    }

    public static EntidadMovibleEnemigoFactory getEntidadMovibleEnemigoFactory() {
        return myEntidadMovibleEnemigoFactory;
    }

    public EntidadMovibleEnemigo generate(String pEnemigo, int posX, int posY) {
        return enemigoMap
                .getOrDefault(pEnemigo, EntidadMovibleEnemigoBaloon::new)
                .apply(posX, posY);
    }
}
