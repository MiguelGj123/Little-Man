package Package;
public enum Tipo {
    // Tipos de celdas con sus propiedades
    VACIO(true, true), BLANDO(false, true), DURO(false, false), FUEGO(true, true);

    private boolean puedePosicionarseJugadorEncima;
    private boolean puedeSerExplotado;

    // Constructor que asigna propiedades a cada tipo
    private Tipo(boolean puedePosicionarseJugadorEncima, boolean puedeSerExplotado) {
        this.puedePosicionarseJugadorEncima = puedePosicionarseJugadorEncima;
        this.puedeSerExplotado = puedeSerExplotado;
    }

    // Indica si la celda puede ser destruida
    public boolean getPuedeSerExplotado() {
        return puedeSerExplotado;
    }

    // Indica si el jugador puede pararse sobre la celda
    public boolean getPuedePosicionarseJugadorEncima() {
        return puedePosicionarseJugadorEncima;
    }

    // Indica si el jugador choca contra la celda
    public boolean getJugadorChocaContraCelda() {
        return !getPuedePosicionarseJugadorEncima();
    }
}
