



public class EscenarioJugador {
	
	
	private static EscenarioJugador miJugador;
    private EntidadMovibleJugador jug;
    private boolean sfx=true, win=false;
    
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

				
			
			if (!movimientoJugadorChocaConParedOBomba && (posJX != posJXnew || posJY != posJYnew)) {
				SoundManager.getSoundManager().playSound("walk");
				if (posJXnew==posJX-1) {
					jug.setCodigoMov(2);
				}
				if (posJXnew==posJX+1) {
					jug.setCodigoMov(4);
				}
				if (posJYnew==posJY-1) {
					jug.setCodigoMov(3);
				}
				if (posJYnew==posJY+1) {
					jug.setCodigoMov(1);
				}
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
		if (win) {
			if (sfx) {
				sfx=false;
				SoundManager.getSoundManager().stopSound("music");
				SoundManager.getSoundManager().playSound("win");
			}
			if (jug.getCodigoJugador()==20) {
				matrizEditar[getPosX()][getPosY()][4] = 23;
			} else if (jug.getCodigoJugador()==25) {
				matrizEditar[getPosX()][getPosY()][4] = 28;
				
			}
			
		} else if (jug.getEstaMuerto())  {
			
			if (sfx) {
				sfx=false;
				SoundManager.getSoundManager().stopSound("music");
				SoundManager.getSoundManager().playSound("die");
			}
			matrizEditar[getPosX()][getPosY()][4] = jug.getCodigoJugadorMuerto();
		} else if (Escenario.getEscenario().hayBombaEn(getPosX(), getPosY())) {
			matrizEditar[getPosX()][getPosY()][4] = jug.getCodigoJugadorConBomba();
		} else {
			sfx=true;
			int codigoFinal = Integer.parseInt(jug.getCodigoJugador() + "" + jug.getCodigoMov());
			matrizEditar[getPosX()][getPosY()][4] = codigoFinal;
		}
		
		return matrizEditar;
	}
	
	
	
	

	public void setWin() {win=true;}
	public void resetWin() {win=false;}
	public boolean getWin() {return win;}
	public int getPosX() { return jug.getPosX(); }
	public int getPosY() { return jug.getPosY(); }
	public int getCodigoMov() { return jug.getCodigoMov(); }
	public boolean getPuedePonerBomba() { return jug.puedePonerBombas(); }
	public boolean getEstaMuerto() { return jug.getEstaMuerto(); }
	public void gestionarVida() { jug.gestionarVida(); }
	public void gestionarExplosion() { jug.bombaExplotada(); }
	public void gestionarPonerBomba() { jug.ponerBomba(); }
	public String getTipoBomba() { return jug.getTipoBomba(); }
	public String getTipoJugador() { return jug.getTipoJugador(); }
	
	
}
