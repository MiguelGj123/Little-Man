package sonido;
//Enum que representa los distintos tipos de sonidos utilizados en el juego.
//Incluye métodos para generar los comandos de reproducción o parada.
public enum SonidoCodigo {
    BOMB_EXPLODE("BOMB_EXPLODE"),
    DEATH("DEATH"),
    ENEMY_DEATH("ENEMY_DEATH"),
    ITEM_GET("ITEM_GET"),
    PLACE_BOMB("PLACE_BOMB"),
    WALK("WALK"),
    WIN("WIN"),
    MUSIC("MUSIC"),
	INVENCIBILIDAD("INVENCIBILIDAD");

    private final String id;

    SonidoCodigo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
 // Devuelve el comando en formato texto para indicar que debe reproducirse este sonido.
    public String sonar() {
        return "SONAR_SONIDO*" + id;
    }

 // Devuelve el comando en formato texto para indicar que debe detenerse este sonido.
    public String parar() {
        return "PARAR_SONIDO*" + id;
    }
}
