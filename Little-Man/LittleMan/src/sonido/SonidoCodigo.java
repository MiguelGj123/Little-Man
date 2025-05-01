package sonido;

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

    public String sonar() {
        return "SONAR_SONIDO*" + id;
    }

    public String parar() {
        return "PARAR_SONIDO*" + id;
    }
}
