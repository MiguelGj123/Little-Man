package escenario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;



public class EscenarioFichero {
		
		private static EscenarioFichero miFichero;
		
		private EscenarioFichero() {
			String dirActual= System.getProperty("user.dir");
			Path rutaArchivo = Paths.get(dirActual, "Estadisticas.txt");
				if (!Files.exists(rutaArchivo)) {
					try {
						Files.createFile(rutaArchivo);
					} catch (IOException e) {
					}
				}
		}
		
		public static EscenarioFichero getFichero() {
			if (miFichero == null) miFichero = new EscenarioFichero();
			return miFichero;
		}
		private static void escribirEnFichero(String pLinea) {
			String dirActual= System.getProperty("user.dir");
			String dirFicheroCompleto = dirActual + File.separator + "Estadisticas.txt";
			try(FileWriter fw = new FileWriter(dirFicheroCompleto, true);
					PrintWriter out= new PrintWriter(fw))
			{
				out.println();
				out.print(pLinea);
			} catch (IOException e) {
				
			}
				
			
		}
		public static void guardarEstadisticas(String pNombre, boolean pVictoria, int pPuntuacion, String pDificultad, String pPantalla, String pBomberman, int pTiempo, int pVidas) {
			Calendar c= new GregorianCalendar();
			int[] fechaHora= {	c.get(Calendar.DATE), 
								c.get(Calendar.MONTH)+1, 
								c.get(Calendar.YEAR), 
								c.get(Calendar.HOUR_OF_DAY), 
								c.get(Calendar.MINUTE), 
								c.get(Calendar.SECOND)};
			String[] strFechaHora= {String.format("%02d",fechaHora[0]), 
									String.format("%02d",fechaHora[1]), 
									String.format("%04d",fechaHora[2]), 
									String.format("%02d",fechaHora[3]), 
									String.format("%02d",fechaHora[4]), 
									String.format("%02d",fechaHora[5])};
			
			String fechaHoraCompleta= 	strFechaHora[0] + "/" +
										strFechaHora[1] + "/" + 
										strFechaHora[2] + "  " + 
										strFechaHora[3] + ":" + 
										strFechaHora[4] + ":" + 
										strFechaHora[5];
			String lineaEstadistica = " -  " + String.format("%-15.14s", pNombre);
			if (pVictoria) {
				lineaEstadistica += " ha ganado  | Puntuación:  " + pPuntuacion + 
									"	| Bomberman: " + pBomberman + 
									"	| Tiempo: " + pTiempo + 
									"	| Vidas: " + pVidas + 
									"	| Pantalla: " + pPantalla + 
									"	| Dificultad: "+ pDificultad + 
									"	| Fecha: "+ fechaHoraCompleta;
			} else {
				lineaEstadistica += " ha perdido | Puntuación:  " + pPuntuacion + 
									"	| Bomberman: " + pBomberman + 
									"	| Tiempo: " + pTiempo + 
									"	| Vidas: " + pVidas + 
									"	| Pantalla: " + pPantalla + 
									"	| Dificultad: "+ pDificultad + 
									"	| Fecha: "+ fechaHoraCompleta;
			}
			escribirEnFichero(lineaEstadistica);
		}
		
		public static String recuperarEstadisticas() {
			String dirActual= System.getProperty("user.dir");
			String dirFicheroCompleto = dirActual + File.separator + "Estadisticas.txt";
			InputStream fichero;
			String estadisticas="";
			try {
					fichero= new FileInputStream(dirFicheroCompleto);
					Scanner sc = new Scanner(fichero);
					while(sc.hasNext()) {
						String linea = sc.nextLine();
						if (!linea.equals(""))estadisticas +="\n"+linea;
					}
					sc.close();
					
			
				
			} catch (FileNotFoundException e) {
					
			}
			return estadisticas;
		}

}
