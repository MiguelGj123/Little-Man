package escenario;
import java.util.*;





public class Escenario extends Observable{
	
	private int cont;
	private boolean pausa, gameEnded;
	
	private Timer timer;
	private int tiempo;
	private String playerTipo, pantalla, dificultad;
	private int puntosTotales;
		
	private EscenarioTeclado myTeclado;
	private EscenarioFacade miEscenarioFacade;
	private static Escenario miEscenario;

	private Escenario() {}

    public static Escenario getEscenario() {
    	if (miEscenario == null) miEscenario = new Escenario();
        return miEscenario;
    }

	public void iniciarVentana(String pPlayerTipo,String pPantalla,String pDificultad) {
		inicializarTablero(pPlayerTipo, pPantalla, pDificultad);
		
		setChanged();
		notifyObservers(new Object[]{"INICIAR_TABLERO", null});
		
		setChanged();
		notifyObservers(new Object[]{"INICIAR_FONDO", pantalla});
		
	}
	
	
	public void inicializarTablero(String pPlayerTipo,String pPantalla,String pDificultad) {	
		cont = 0;
		pausa = false;
		gameEnded = false;
		
		if (timer != null) timer.cancel();
		timer = null;
		gameEnded = false;
		pausa = false;
		
		dificultad=pDificultad;
		
		Map<String, Integer> dificultadMap = new HashMap<>();
		dificultadMap.put("PACIFICO", 200);
		dificultadMap.put("FACIL",    200);
		dificultadMap.put("NORMAL",   150);
		dificultadMap.put("DIFICIL",  100);

		tiempo = dificultadMap.getOrDefault(dificultad, 150); 	// Tiempo en segundos
		
		playerTipo = pPlayerTipo;
		
		Random random = new Random();
		String[] posiblesPantallas = new String[] {"NORMAL", "NO_DURO", "VACIO"};
		pantalla = (pPantalla.equals("ALEATORIO")) ? posiblesPantallas[random.nextInt(3)] : pPantalla;
		
		puntosTotales = 0;
		
		myTeclado = EscenarioTeclado.getEscenarioTeclado();
		miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
		miEscenarioFacade.inicializarTablero(playerTipo, pantalla, dificultad);
		
		setChanged();
		notifyObservers(new Object[]{"INICIALIZAR_VIDA", miEscenarioFacade.getVidas()});
		
		setChanged();
		notifyObservers(new Object[]{"PAUSE", false});
		
		setChanged();
		notifyObservers(new Object[]{"GESTIONAR_TEMPORIZADOR", tiempo});
	}
	
	public void iniciarPartida() {
		timerStep();
		setChanged();
		notifyObservers(new Object[]{"INICIAR_PARTIDA", null});
	}
	
	private void timerStep() {
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override public void run() {
				if (!pausa && !gameEnded) {
					if (cont == 20) {
						cont = 0;
						tiempo --;
						
						setChanged();
						notifyObservers(new Object[]{"GESTIONAR_TEMPORIZADOR", tiempo});
					}
					cont++;
					
					if		(miEscenarioFacade.getMuerto())		gestionarGameEnded("YOU DIED");
					else if	(miEscenarioFacade.getWin())		gestionarGameEnded("WINNER");
					else if	(tiempo == 0)						gestionarGameEnded("TIME ENDED");
					else										actualizarEscenario();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 50);
	}
	
	protected void gestionarGameEnded(String param) {
		gameEnded = true;
		String[] fin= {"PARAR_SONIDO*MUSIC","PARAR_SONIDO*INVENCIBILIDAD","SONAR_SONIDO*DEATH"};
		setChanged();
		notifyObservers(new Object[]{"GESTIONAR_CODIGOS_SONIDOS", fin});
    	setChanged();
    	notifyObservers(new Object[]{param, gameEnded});
	}

	private void actualizarEscenario() {
		
		int puntosTick = miEscenarioFacade.actualizarEscenario();
		if (puntosTick!=-1) puntosTotales += puntosTick;
		
		List<Object[]> notificaciones = Arrays.asList(
			    new Object[]{"GESTIONAR_CODIGOS_IMAGENES",	miEscenarioFacade.generarMatrizImagenes(pantalla)},
			    new Object[]{"GESTIONAR_CODIGOS_SONIDOS",	miEscenarioFacade.generarVectorSonidos()},
			    new Object[]{"GESTIONAR_VIDAS",				miEscenarioFacade.getVidas()},
			    new Object[]{"GESTIONAR_PUNTUACION",		puntosMultiplicadosPorDificulad(puntosTotales)}
			);

			notificaciones.forEach(obj -> {
			    setChanged();
			    notifyObservers(obj);
			});
		
		
		
	}
	
	
	
	private int puntosMultiplicadosPorDificulad(int puntosTick) {
		if (puntosTick == -1) return puntosTick;

	    Map<String, Integer> multiplicadorMap = new HashMap<>();
	    multiplicadorMap.put("NORMAL", 2);
	    multiplicadorMap.put("DIFICIL", 4);

	    return puntosTick * multiplicadorMap.getOrDefault(dificultad, 1);
	}

	public void escribirEnFichero() {
		EscenarioFichero.guardarEstadisticas("Prueba", miEscenarioFacade.getWin(), (int) (double) puntosTotales, dificultad, pantalla, playerTipo, tiempo, miEscenarioFacade.getVidas());
	}
	
	
	public void pressLeft() 	{ myTeclado.pressedLeft(); }
    public void pressRight() 	{ myTeclado.pressedRight(); }
    public void pressUp() 		{ myTeclado.pressedUp(); }
    public void pressDown() 	{ myTeclado.pressedDown(); }
    public void pressBomba() 	{ myTeclado.pressedBomb();}
    public void pressEscape()	{
    	if (!gameEnded) {
			pausa = !pausa;
			setChanged();
			notifyObservers(new Object[]{"PAUSE", pausa});
    	}
    }

	public void releaseBomba()	{ myTeclado.releasedBomb();	}
    public void releaseLeft()	{ myTeclado.releasedLeft(); }
    public void releaseRight()	{ myTeclado.releasedRight(); }
    public void releaseUp()		{ myTeclado.releasedUp(); }
    public void releaseDown()	{ myTeclado.releasedDown(); }
    
    
    
    
	public void sumarTiempo(int i) { tiempo += 10; }

	public void finalizarPartida(boolean fichero) {
		if (fichero) {
			escribirEnFichero(); 
		}
		gameEnded = true;
		timer.cancel();
		setChanged();
		notifyObservers(new Object[]{"FINALIZAR_PARTIDA", null});
	}

	public void reiniciarPartida() {
		timer.cancel();
		inicializarTablero(playerTipo, pantalla, dificultad);
		timerStep();
	}
	
	

	
    
    
    
    
    
		                
}