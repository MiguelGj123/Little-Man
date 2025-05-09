package entidad;

//Clase abstracta que representa una bomba colocada en el escenario.
//Controla su tiempo hasta explosión, radio de daño y su representación gráfica.
public abstract class EntidadInamovibleBomba extends EntidadInamovible {
	
	private int ticks;                // Tiempo restante para explotar
	private int radioBomba;           // Alcance de la explosión
	private final int codigoBomba;    // Código visual de la bomba

	public EntidadInamovibleBomba (int radio, int ticksHastaExplotar, int codigoBomba, int posX, int posY) {
		super(posX, posY);
		this.radioBomba = radio;
		ticks = ticksHastaExplotar;
		this.codigoBomba = codigoBomba;
	}

	// Decrementa el contador interno. Devuelve true si debe explotar.
	public boolean tick() {
		ticks--;
		return ticks < 0;
	}

	// Aumenta o reduce el alcance de la explosión.
	public void cambioRadio(int cambioRadio) {
		radioBomba = radioBomba + cambioRadio;
	}

	// Ajusta el tiempo restante antes de explotar.
	public void cambioTick(int cambioTicks) {
		ticks = ticks + cambioTicks;
	}

	public int getTickBomba() { return ticks; }

	public int getRadioBomba() { return radioBomba; }

	public int getCodigoBomba() { return codigoBomba; }

	public int getPosX() { return super.getPosX(); }

	public int getPosY() { return super.getPosY(); }
}
