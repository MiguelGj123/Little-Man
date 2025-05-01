package escenario;

import java.util.ArrayList;
import java.util.Random;
import entidad.EntidadInamoviblePowerup;
import entidad.EntidadInamoviblePowerupFactory;
import sonido.SonidoCodigo;


public class EscenarioPowerups {

	private static EscenarioPowerups misPowerups;
	private EscenarioFacade miEscenarioFacade;
	private ArrayList<EntidadInamoviblePowerup> listaPowerups = new ArrayList<EntidadInamoviblePowerup>();
    private ArrayList<String> listaSonidos = new ArrayList<String>();
    private Random random = new Random();
	
	private EscenarioPowerups() {}
	    
    public static EscenarioPowerups getPowerups(){
    	if (misPowerups == null) {
    		misPowerups = new EscenarioPowerups();
    	}
    	return misPowerups;
    }
    
    public void inicializarPowerups() {
    	listaPowerups.clear();
    	miEscenarioFacade = EscenarioFacade.getEscenarioFacade();
    	listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("Invencible", 0, 1));
    	/*listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("VidaMas", 0, 2));
    	listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("VidaMas", 0, 3));
    	listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("VidaMas", 0, 4));*/
    }
    
    public EntidadInamoviblePowerup getPowerupEnPosicionXY (int pPosX, int pPosY)										// devuelve la bomba que hay en una posicion XY
	{																												// devuelve null si no hay bomba en esa posicion
		for (EntidadInamoviblePowerup pPowerup: listaPowerups) {
			if (pPowerup.getPosX()==pPosX && pPowerup.getPosY()==pPosY) { return pPowerup; }
		}
		return null;
	}
    
    public boolean hayPowerupEnPosicionXY (int pPosX, int pPosY)										// devuelve la bomba que hay en una posicion XY
	{																												// devuelve null si no hay bomba en esa posicion
		for (EntidadInamoviblePowerup pPowerup: listaPowerups) {
			if (pPowerup.getPosX()==pPosX && pPowerup.getPosY()==pPosY) { return true; }
		}
		return false;
	}
    
    public boolean generarPowerup( int posX, int posY){
		boolean powerupPuesto = false;
		
		int numero = random.nextInt(13);
		switch(numero) {
		case 0:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("VidaRec", posX, posY));
			break;
		case 1:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("VidaMas", posX, posY));
			break;
		case 2:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("BombaMas", posX, posY));
			break;
		case 3:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("FuegoMas", posX, posY));
			break;
		case 4:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("TiempoMas", posX, posY));
			break;
		case 5:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("Invencible", posX, posY));
			break;
		case 6:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("BombaPatada", posX, posY));
			break;
		case 7:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("BombaPincho", posX, posY));
			break;
		case 8:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("Puntos", posX, posY));
			break;
		case 9:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("BombaMenos", posX, posY));
			break;
		case 10:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("FuegoMenos", posX, posY));
			break;
		case 11:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("TickBombaMas", posX, posY));
			break;
		case 12:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("TickBombaMenos", posX, posY));
			break;
		case 13:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("Aleatorio", posX, posY));
			break;
		default:
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate("Aleatorio", posX, posY));
			break;
		}
		return powerupPuesto;
	}
    
    public Double actualizarPowerups(int posJX, int posJY) {
    	Double puntos=0.;
    	for ( int i=0; i<listaPowerups.size(); i++) {
    		EntidadInamoviblePowerup pPowerup = listaPowerups.get(i);													// Obtenemos el bloque que queremos actualizar
			
			if (pPowerup.getPosX()==posJX && pPowerup.getPosY()==posJY) {
				listaSonidos.add(SonidoCodigo.ITEM_GET.sonar());
				powerupEffect(pPowerup.getCodigoPowerup());
				listaPowerups.remove(i);		
				puntos=100.;
			}
		}
		return puntos;
	}
    
    private void powerupEffect(int pPowerup) {
    	switch(pPowerup) {
		case 600: 
			int numero = random.nextInt(601,613);
			powerupEffect(numero);
			break;
		case 601: //aumenta en 1 la cantidad de bombas maximas
				miEscenarioFacade.sumarBomba();
			break;
		case 602: //reduce en 1 la cantidad de bombas maximas
			if(miEscenarioFacade.getMaxBombas()>1) {miEscenarioFacade.restarBomba();}
			break;
		case 603: 
			break;
		case 604: 
			break;
		case 605: //aumenta en 1 el radio de las bombas
			miEscenarioFacade.sumarRadio();
			break;
		case 606: //reduce en 1 el radio de las bombas
			if(miEscenarioFacade.getRadioBomba()>1) {miEscenarioFacade.restarRadio();}
			break;
		case 607: 
			miEscenarioFacade.invencibilidad();
			listaSonidos.add(SonidoCodigo.INVENCIBILIDAD.sonar());
			break;
		case 608: 
			int puntos = random.nextInt(5);
			System.out.println(500.*(1<<puntos)+"  " + puntos);
			miEscenarioFacade.sumarPuntos(500.*(1<<puntos));
			break;
		case 609: //Reduce en 5 ticks el tiempo para explotar una bomba
			if(miEscenarioFacade.getTicksBomba()>5) {miEscenarioFacade.restarTicks();}
			break;
		case 610: //Aumenta en 5 ticks el tiempo para explotar una bomba
			miEscenarioFacade.sumarTicks();
			break;
		case 611: 
			miEscenarioFacade.setTiempo();
			break;
		case 612: //Aumenta en 1 la vida maxima
			miEscenarioFacade.sumarVida();
			break;
		case 613: //Recupera 1 de vida perdida sin añadir al maximo
			miEscenarioFacade.curarVida();
			break;
		default:
			break;
		}
    }
    
    public int[][] generarMatrizAniadirPowerup(int COLUMNAS, int FILAS){
		int[][] matrizGenerada = new int[COLUMNAS][FILAS];

		for (EntidadInamoviblePowerup pPowerup : listaPowerups) {
			matrizGenerada[pPowerup.getPosX()][pPowerup.getPosY()] = pPowerup.getCodigoPowerup();
		}
		return matrizGenerada;
	}
    
    public String[] getListaSonidos() {
	    return listaSonidos.toArray(new String[0]);
	}
	
	public void listaSonidosClear() {
		listaSonidos.clear();
	}

	public void resetPowerups() {
		listaPowerups.clear();
    	miEscenarioFacade = null;
    	listaSonidos.clear();
		
	}

}
