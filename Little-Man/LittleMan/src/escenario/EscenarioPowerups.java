package escenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import entidad.EntidadInamoviblePowerup;
import entidad.EntidadInamoviblePowerupFactory;
import sonido.SonidoCodigo;



public class EscenarioPowerups {

	private Escenario_CONFIG esCfg = new Escenario_CONFIG();
	
	private static EscenarioPowerups misPowerups;
	private EscenarioFacade miEscenarioFacade;
	private Escenario miEscenario;
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
    	miEscenario = Escenario.getEscenario();
    }
    
    public EntidadInamoviblePowerup getPowerupEnPosicionXY (int pPosX, int pPosY)										// devuelve la bomba que hay en una posicion XY
	{																												// devuelve null si no hay bomba en esa posicion
    	return listaPowerups.stream()
    		    .filter(p -> p.getPosX() == pPosX && p.getPosY() == pPosY)
    		    .findFirst()
    		    .orElse(null);
	}
    
    public boolean hayPowerupEnPosicionXY (int pPosX, int pPosY)										// devuelve la bomba que hay en una posicion XY
	{																												// devuelve null si no hay bomba en esa posicion
    	return listaPowerups.stream()
    		    .anyMatch(p -> p.getPosX() == pPosX && p.getPosY() == pPosY);

	}
    
    public boolean generarPowerup( int posX, int posY){

		
		List<String> tipos = List.of(
			    "VidaRec", "VidaMas", "BombaMas", "FuegoMas", "TiempoMas", "Invencible",
			    "BombaPatada", "BombaPincho", "Puntos", "BombaMenos", "FuegoMenos",
			    "TickBombaMas", "TickBombaMenos", "Aleatorio"
			);

			String tipo = tipos.get(random.nextInt(tipos.size()));
			listaPowerups.add(EntidadInamoviblePowerupFactory.getPowerupFactory().generate(tipo, posX, posY));
			return true;

	}
    
    public int actualizarPowerups(int posJX, int posJY) {
    	int puntos = 0;
    	for ( int i=0; i<listaPowerups.size(); i++) {
    		EntidadInamoviblePowerup pPowerup = listaPowerups.get(i);													// Obtenemos el bloque que queremos actualizar
			
			if (pPowerup.getPosX()==posJX && pPowerup.getPosY()==posJY) {
				listaSonidos.add(SonidoCodigo.ITEM_GET.sonar());
				powerupEffect(pPowerup.getCodigoPowerup());
				listaPowerups.remove(i);		
				puntos=100;
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
			miEscenarioFacade.sumarPuntos(500*(1<<puntos));
			break;
		case 609: //Reduce en 5 ticks el tiempo para explotar una bomba
			if(miEscenarioFacade.getTicksBomba()>5) {miEscenarioFacade.restarTicks();}
			break;
		case 610: //Aumenta en 5 ticks el tiempo para explotar una bomba
			miEscenarioFacade.sumarTicks();
			break;
		case 611: 
			miEscenario.sumarTiempo(10);
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
    
    public int[][] generarMatrizAniadirPowerup(){
		int[][] matrizGenerada = new int[esCfg.col][esCfg.fil];

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

}
