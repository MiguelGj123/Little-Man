
public class EscenarioJugador {
	
	
	private static EscenarioJugador miJugador;
    private EntidadMovibleJugador jug;
    
    private EscenarioJugador() {}
    
    public static EscenarioJugador getJugador(){
    	if (miJugador == null) {
    		miJugador = new EscenarioJugador();
    	}
    	return miJugador;
    }
	
	
	public void inicializarJugador(String playerTipo){
		jug = EntidadMovibleJugadorFactory.getEntidadMovibleJugadorFactory().generate(playerTipo, 0, 0);
	}
	
	
	

	public void actualizarPosicionJugador(String mov, int COLUMNAS, int FILAS)
	{
		int posJX = jug.getPosX();																				// posicion X actual del jugador	[COLUMNA]
		int posJY = jug.getPosY();																				// posicion Y actual del jugador	[FILA]
		int posJXnew = posJX, posJYnew = posJY;																	// posiciones XY nuevas despues del movimiento
		boolean movimientoJugadorChocaConParedOBomba = false;													// flag si choca contra pared en coordenadas nuevas
		
		if (!jug.getEstaMuerto()) {
			posJXnew = (mov.equals("L") && posJX != 0			) ? posJX - 1 : posJXnew;
			posJXnew = (mov.equals("R") && posJX != COLUMNAS - 1) ? posJX + 1 : posJXnew;
			posJYnew = (mov.equals("U") && posJY != 0			) ? posJY - 1 : posJYnew;
			posJYnew = (mov.equals("D") && posJY != FILAS - 1	) ? posJY + 1 : posJYnew;
			
			movimientoJugadorChocaConParedOBomba = 																// Jugador choca contra pared o bomba si
					(  ( mov.equals("L") && posJX == 0				)											// Choca contra exterior
					|| ( mov.equals("R") && posJX == COLUMNAS - 1	)
					|| ( mov.equals("U") && posJY == 0				)
					|| ( mov.equals("D") && posJY == FILAS - 1		)
					|| Escenario.getEscenario().chocaConPos(posJXnew, posJYnew));					// o si hay una bomba o bloque en la nueva posicion
			
			if (!movimientoJugadorChocaConParedOBomba) {
				jug.setPosX(posJXnew);
				jug.setPosY(posJYnew);
			}
		}
	}
	
	public void gestionarFuego(int posFX, int posFY) {
		if (getPosX() == posFX && getPosY() == posFY) { gestionarVida(); }
	}
	
	public void gestionarEnemigo(int posFX, int posFY) {
		if (getPosX() == posFX && getPosY() == posFY) { gestionarVida(); }
	}
	
	
	
	public int[][][] generarMatrizAniadirBloques(int[][][] matrizEditar){
		if (jug.getEstaMuerto()) {
			matrizEditar[getPosX()][getPosY()][4] = jug.getCodigoJugadorMuerto();
		} else if (Escenario.getEscenario().hayBombaEn(getPosX(), getPosY())) {
			matrizEditar[getPosX()][getPosY()][4] = jug.getCodigoJugadorConBomba();
		} else {
			matrizEditar[getPosX()][getPosY()][4] = jug.getCodigoJugador();
		}
		return matrizEditar;
	}
	
	
	
	

	
	
	public int getPosX() { return jug.getPosX(); }
	public int getPosY() { return jug.getPosY(); }
	public boolean getPuedePonerBomba() { return jug.puedePonerBombas(); }
	public boolean getEstaMuerto() { return jug.getEstaMuerto(); }
	public void gestionarVida() { jug.gestionarVida(); }
	public void gestionarExplosion() { jug.bombaExplotada(); }
	public void gestionarPonerBomba() { jug.ponerBomba(); }
	public String getTipoBomba() { return jug.getTipoBomba(); }
	public String getTipoJugador() { return jug.getTipoJugador(); }
	
	
}
