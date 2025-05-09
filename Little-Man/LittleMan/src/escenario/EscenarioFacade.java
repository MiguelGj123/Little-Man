package escenario;
import java.util.ArrayList;

//Clase central que implementa el patrón Facade para ocultar la complejidad del subsistema de juego.
//Coordina la interacción entre jugador, enemigos, bombas, tablero, power-ups y entradas de teclado.
//Proporciona métodos clave para inicializar, actualizar y consultar el estado del juego.
public class EscenarioFacade {
	
	private static EscenarioFacade miEscenarioFacade;
	
	private Escenario_CONFIG esCfg = new Escenario_CONFIG();

	private EscenarioTablero tablero;
	private EscenarioEnemigos enemigos;
	private EscenarioBombas bombas;
	private EscenarioJugador jugador;
	private EscenarioPowerups powerups;
	private EscenarioTeclado teclado;
	
	private boolean bombasExplotadas = true;
	private boolean sumarTiempo = false;
	private int puntosExtra = 0;
	
	
	
	
	private EscenarioFacade() {
    }
	
    public static EscenarioFacade getEscenarioFacade() {
    	if (miEscenarioFacade == null) miEscenarioFacade = new EscenarioFacade();
    	return miEscenarioFacade;
    }

 // Inicializa todos los subsistemas del juego: jugador, tablero, enemigos, bombas y powerups.
	public void inicializarTablero(String playerTipo, String pantalla, String dificultad) {

		tablero = EscenarioTablero.getTablero();
		enemigos = EscenarioEnemigos.getEnemigos();
		bombas = EscenarioBombas.getBombas();
		jugador = EscenarioJugador.getJugador();
		powerups = EscenarioPowerups.getPowerups();
		teclado = EscenarioTeclado.getEscenarioTeclado();
		
		jugador.inicializarJugador(playerTipo);
	    tablero.inicializarTablero(pantalla);
		enemigos.inicializarEnemigos(tablero.getPosicionesVacias(), dificultad);
		bombas.inicializarBombas();
		powerups.inicializarPowerups();

	}
	
	// Actualiza el estado del juego en cada tick: enemigos, jugador, bombas, fuego y puntuación.
	public int actualizarEscenario() {
		
		int puntos = 0;
		
		puntos = enemigos.actualizarTicksEnemigos() + puntos;
		
		if (teclado.isBomb()) {			// si bomba pulsada
			if (jugador.getPuedePonerBomba()) {
				if ((jugador.getTipoJugador().equals("ROJO") && bombasExplotadas) || !jugador.getTipoJugador().equals("ROJO")) {
					if (bombas.ponerBomba(jugador.getTipoBomba(), jugador.getPosX(), jugador.getPosY(), jugador.getNumBombas())) {
						jugador.gestionarPonerBomba();
					}
				}
			} else if (jugador.getTipoJugador().equals("ROJO")) {
				puntos = bombas.actualizarTicksBombas() + puntos;
			}
		}
		
		String[] direccion = teclado.tick();
		
		jugador.actualizarPosicionJugador(direccion);
		jugador.actualizarTicksJugador();
		
		puntos = powerups.actualizarPowerups(jugador.getPosX(),jugador.getPosY())+ puntos;
		if (!jugador.getTipoJugador().equals("ROJO")){
			puntos=bombas.actualizarTicksBombas()+puntos;
		}
		if (jugador.getTipoJugador().equals("ROJO")){
				bombasExplotadas = !tablero.hayFuego();
		}
		puntos=tablero.actualizarTicksFuego()+puntos;
		puntos = getPuntos() + puntos;
		return puntos;
	}
	
	// Genera la matriz 3D de códigos de imagen que representa el estado visual del escenario.
	public int[][][] generarMatrizImagenes(String pantalla){
		
		int[][][] matrizDevolver = new int[5][esCfg.col][esCfg.fil];
		
		for (int panel = 0; panel < matrizDevolver.length; panel ++) {
			for (int columna = 0; columna < matrizDevolver[panel].length; columna ++) {
				for (int fila = 0; fila < matrizDevolver[panel][columna].length; fila ++) {
					matrizDevolver[panel][columna][fila] = 0;
				}
			}
		}

		
		matrizDevolver[0] = tablero.generarMatrizAniadirBloques(pantalla);
		matrizDevolver[1] = bombas.generarMatrizAniadirBombas();
		matrizDevolver[2] = enemigos.generarMatrizAniadirEnemigos();
		matrizDevolver[3] = jugador.generarMatrizAniadirjugador(bombas.hayBombaEnPosicionXY(jugador.getPosX(), jugador.getPosY()));
		matrizDevolver[4] = powerups.generarMatrizAniadirPowerup();

		
		return matrizDevolver;
	}
	
	// Recoge todos los sonidos pendientes de los subsistemas y los devuelve en un array.
	public String[] generarVectorSonidos() {
		ArrayList<String> codigosSonido = new ArrayList<String>();

		for (String sonido : tablero.getListaSonidos())		{	codigosSonido.add(sonido);	}
		tablero.listaSonidosClear();
		for (String sonido : enemigos.getListaSonidos())	{	codigosSonido.add(sonido);	}
		enemigos.listaSonidosClear();
		for (String sonido : bombas.getListaSonidos())		{	codigosSonido.add(sonido);	}
		bombas.listaSonidosClear();
		for (String sonido : jugador.getListaSonidos())		{	codigosSonido.add(sonido);	}
		jugador.listaSonidosClear();
		for (String sonido : powerups.getListaSonidos())	{	codigosSonido.add(sonido);	}
		powerups.listaSonidosClear();
		
		
		
	    return codigosSonido.toArray(new String[0]);
	}
	public void generarPowerup(int posX, int posY) { powerups.generarPowerup(posX, posY);}

	// Gestiona el efecto del fuego en una celda: daño a jugador y enemigos, suma puntos si aplica.
    public int gestionarFuego(int posX, int posY) {
    	int puntos = 0;
    	puntos = jugador.gestionarFuego(posX, posY)+puntos;
    	puntos = enemigos.gestionarFuego(posX, posY)+puntos;
    	return puntos;
    }
    
 // Devuelve true si hay una colisión en la celda (por bomba o bloque chocable).
    public boolean chocaConPos(int posX, int posY) { return hayBombaEn(posX, posY) || hayBloqueChocableEn(posX, posY);}
    
 // Gestiona la interacción del jugador con un enemigo en la posición indicada.
    public int gestionarEnemigo(int posX, int posY) {
    	int puntos = 0;

    	puntos=jugador.gestionarEnemigo(posX, posY);

		return puntos;
    }
    
 // Gestiona las consecuencias de una explosión: daño al jugador y fuego en el tablero.
    public int gestionarExplosion(int posX, int posY, int radioBomba) {
    	int puntos=0;
    	jugador.gestionarExplosion();
    	puntos=tablero.gestionarExplosion(posX, posY, radioBomba);
    	return puntos;
    }
    public void invencibilidad()		{ jugador.gestionarInvencibilidad();}
    public void gestionarGolpe() 		{ jugador.gestionarVida();}
    public void gestionarTiempo() 		{ jugador.gestionarTiempo();}
    
    public boolean hayBombaEn (int posX, int posY) 				{return bombas.hayBombaEnPosicionXY(posX, posY);}
    
    private boolean hayBloqueChocableEn (int posX, int posY) 	{return tablero.getChocaContraCelda(posX, posY);}

    public boolean getMuerto () 		{return jugador.getEstaMuerto();}
    public boolean getWin() 			{return jugador.getWin();}
    public int getVidas() 				{return jugador.getVidas();}
    
    
    public void	sumarPuntos(int puntos) 	{ puntosExtra = puntos;}
    public int getPuntos()				{ int puntos = puntosExtra; puntosExtra=0; return puntos;}
    public boolean sumarTiempo()			{ return sumarTiempo;}
    public void setTiempo()					{ sumarTiempo=true;}
	public void sumarBomba()				{ jugador.sumarBomba();}
	public void restarBomba()				{ jugador.restarBomba();}
	public void sumarVida()					{ jugador.sumarVida();}
	public void curarVida()					{ jugador.curarVida();}
	public void sumarRadio() 				{ bombas.sumarRadio();}
	public void restarRadio() 				{ bombas.restarRadio();}
	public void sumarTicks() 				{ bombas.sumarTicks();}
	public void restarTicks()		 		{ bombas.restarTicks();}
	
	public int getRadioBomba()			{return bombas.getRadio();}
	public int getTicksBomba()			{return bombas.getTicks();}
	public int getMaxBombas()			{return jugador.getMaxBombas();}
    public int getPosXJ() 				{ int posXJ=this.jugador.getPosX(); return posXJ;}
    public int getPosYJ() 				{ int posYJ=this.jugador.getPosY(); return posYJ;}
    
    public void gestionarPlayerWin() 	{ jugador.setWin(); }

    
	

	
	
	
	
	

}
