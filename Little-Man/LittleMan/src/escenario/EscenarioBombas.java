package escenario;


import java.util.ArrayList;
import entidad.EntidadInamovibleBomba;
import entidad.EntidadInamovibleBombaFactory;
import sonido.SonidoCodigo;
//Clase Singleton que gestiona todas las bombas del juego.
//Controla su colocación, temporización, explosión y representación visual.
//Usa una fábrica para generar bombas personalizadas por tipo.
public class EscenarioBombas {

	private static EscenarioBombas misBombas;
	private Escenario_CONFIG esCfg = new Escenario_CONFIG();

	private int cambioRadio = 0;
	private int cambioTick = 0;
	private String tipoDeBomba = "";
	private EscenarioFacade miEscenarioFacade;
	private ArrayList<EntidadInamovibleBomba> listaBombas = new ArrayList<>();
	private ArrayList<String> listaSonidos = new ArrayList<>();

	private EscenarioBombas() {}

	public static EscenarioBombas getBombas() {
		if (misBombas == null) {
			misBombas = new EscenarioBombas();
		}
		return misBombas;
	}

	public void inicializarBombas() {
		listaBombas.clear();
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
		cambioRadio = 0;
		cambioTick = 0;
	}

	// Devuelve la bomba colocada en una posición específica, o null si no hay ninguna
	public EntidadInamovibleBomba getBombaEnPosicionXY(int pPosX, int pPosY) {
		return listaBombas.stream()
			.filter(b -> b.getPosX() == pPosX && b.getPosY() == pPosY)
			.findFirst()
			.orElse(null);
	}

	// Devuelve true si hay una bomba colocada en la posición indicada
	public boolean hayBombaEnPosicionXY(int pPosX, int pPosY) {
		return listaBombas.stream()
			.anyMatch(b -> b.getPosX() == pPosX && b.getPosY() == pPosY);
	}

	// Actualiza el estado de cada bomba y gestiona su explosión si su temporizador llega a cero
	public int actualizarTicksBombas() {
		int puntos = 0;

		for (int i = 0; i < listaBombas.size(); i++) {
			EntidadInamovibleBomba pBomba = listaBombas.get(i);

			if (pBomba.tick()) {
				puntos += miEscenarioFacade.gestionarExplosion(pBomba.getPosX(), pBomba.getPosY(), pBomba.getRadioBomba());
				listaBombas.remove(i);
				i--;
			}
		}
		return puntos;
	}

	// Intenta colocar una bomba en una posición, solo si es distinta a la última colocada
	public boolean ponerBomba(String tipoBomba, int posJX, int posJY, int radio) {
		boolean bombaPuesta = false;
		tipoDeBomba = tipoBomba;

		if (listaBombas.isEmpty() ||
			!(listaBombas.getLast().getPosX() == posJX && listaBombas.getLast().getPosY() == posJY)) {

			listaSonidos.add(SonidoCodigo.PLACE_BOMB.sonar());
			EntidadInamovibleBomba nuevaBomba =
				EntidadInamovibleBombaFactory.getBombaFactory().generate(tipoBomba, posJX, posJY, radio);
			nuevaBomba.cambioRadio(cambioRadio);
			nuevaBomba.cambioTick(cambioTick);
			listaBombas.add(nuevaBomba);
			bombaPuesta = true;
		}
		return bombaPuesta;
	}

	// Genera la matriz visual del escenario con las bombas colocadas
	public int[][] generarMatrizAniadirBombas() {
		int[][] matrizGenerada = new int[esCfg.col][esCfg.fil];
		for (EntidadInamovibleBomba pBomba : listaBombas) {
			matrizGenerada[pBomba.getPosX()][pBomba.getPosY()] = pBomba.getCodigoBomba();
		}
		return matrizGenerada;
	}


	public String[] getListaSonidos() { return listaSonidos.toArray(new String[0]); }
	public void listaSonidosClear() { listaSonidos.clear(); }
	public int getTicks() { return EntidadInamovibleBombaFactory.getBombaFactory().generate(tipoDeBomba, 0, 0, 1).getTickBomba(); }
	public int getRadio() { return EntidadInamovibleBombaFactory.getBombaFactory().generate(tipoDeBomba, 0, 0, 1).getRadioBomba(); }
	public void sumarRadio() { cambioRadio++; }
	public void restarRadio() { cambioRadio--; }
	public void sumarTicks() { cambioTick += 5; }
	public void restarTicks() { cambioTick -= 5; }
}
