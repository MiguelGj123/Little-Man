package frameTablero;

public class Frm__CONFIG {
	
	private static Frm__CONFIG miConfig;
	
	private final int FILAS;
	private final int COLUMNAS;
	private final int PXL_LADO;
	
	private Frm__CONFIG(int pCOLUMNAS, int pFILAS, int pPXL_LADO) {
		FILAS = pFILAS;
		COLUMNAS = pCOLUMNAS;
		PXL_LADO = pPXL_LADO;
		System.out.println(FILAS);
		System.out.println(COLUMNAS);


	}
	
	public static void iniciarConfig(int COLUMNAS, int FILAS, int PXL_LADO) {
		//System.out.println("AAAA FrameTableroValoresConfig Iniciar Config");
		if (miConfig == null) miConfig = new Frm__CONFIG(COLUMNAS, FILAS, PXL_LADO);
	}
	
	public static Frm__CONFIG getConfig() {
		return miConfig;
	}
	
	public int getFILAS() {
		return FILAS;
	}
	
	public int getCOLUMNAS() {
		return COLUMNAS;
	}
	
	public int getPXL_LADO() {
		return PXL_LADO;
	}
	
	public int getANCHO() {
		return COLUMNAS * PXL_LADO;
	}
	
	public int getALTO() {
		return FILAS * PXL_LADO;
	}
}
