package entidad;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

//Fábrica que usa el patrón Factory para generar power-ups según su tipo (por string).
public class EntidadInamoviblePowerupFactory {

	private static EntidadInamoviblePowerupFactory myPowerupFactory =
		new EntidadInamoviblePowerupFactory(); 

	private Map<String, BiFunction<Integer, Integer, EntidadInamoviblePowerup>> powerupMap;

	private EntidadInamoviblePowerupFactory() {
		powerupMap = new HashMap<>();
		powerupMap.put("VidaRec", EntidadInamoviblePowerupVidaRec::new);
		powerupMap.put("VidaMas", EntidadInamoviblePowerupVidaMas::new);
		powerupMap.put("BombaMas", EntidadInamoviblePowerupBombaMas::new);
		powerupMap.put("FuegoMas", EntidadInamoviblePowerupFuegoMas::new);
		powerupMap.put("TiempoMas", EntidadInamoviblePowerupTiempoMas::new);
		powerupMap.put("Invencible", EntidadInamoviblePowerupInvencible::new);
		powerupMap.put("Puntos", EntidadInamoviblePowerupPuntos::new);
		powerupMap.put("BombaMenos", EntidadInamoviblePowerupBombaMenos::new);
		powerupMap.put("FuegoMenos", EntidadInamoviblePowerupFuegoMenos::new);
		powerupMap.put("TickBombaMas", EntidadInamoviblePowerupTickBombaMas::new);
		powerupMap.put("TickBombaMenos", EntidadInamoviblePowerupTickBombaMenos::new);
		powerupMap.put("Aleatorio", EntidadInamoviblePowerupAleatorio::new);
	}

	public static EntidadInamoviblePowerupFactory getPowerupFactory() {
		return myPowerupFactory;
	}

	public EntidadInamoviblePowerup generate(String pTipoPowerup, int posX, int posY) {
		return powerupMap
			.getOrDefault(pTipoPowerup, EntidadInamoviblePowerupAleatorio::new)
			.apply(posX, posY);
	}
}