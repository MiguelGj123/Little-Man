package frameMenuPrincipal;

import java.awt.Image;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frm__MP__04__02__03__Label__ImagenMapa extends JLabel{

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Random random;
	private int fila;
	private int btnRotar;
	
	public Frm__MP__04__02__03__Label__ImagenMapa() {
    	setOpaque(false);
		setLayout(null);
		setVisible(true);
		random = new Random();
	}
	
	
	public void ponerImagen(int fila, int boton) {
		this.fila = fila;
		if (timer != null) timer.cancel();
		if (boton == 0) {
			cambiarImagen(fila, btnRotar);
			timerStep();
		} else {
			cambiarImagen(fila, boton);
		}
	}
	
	public void cambiarImagen(int fila, int boton) {
		String rutaImagen = "Pixels/Combinacion_" + fila + "_" + boton + ".png";
		ImageIcon iconRoot = new ImageIcon(rutaImagen);
		Image TituloEscalado = iconRoot.getImage().getScaledInstance(190, 190, Image.SCALE_SMOOTH);
		ImageIcon TituloEscaladoIcon = new ImageIcon(TituloEscalado);

		setIcon(TituloEscaladoIcon);
	}
	
	private void timerStep() {
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override public void run() {
				btnRotar++;
				if (btnRotar >= 4) btnRotar = 1;
				cambiarImagen(fila, btnRotar);
			}
		};
		timer.scheduleAtFixedRate(task, 0, 100);
	}

}
