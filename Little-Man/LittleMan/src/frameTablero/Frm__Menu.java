package frameTablero;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import escenario.Escenario;
import frameMenuPrincipal.FrameMenuPrincipal;
import sonido.SoundManager;


public class Frm__Menu extends JLayeredPane{
	private static final long serialVersionUID = 1872619048608368151L;
	private static Frm__Menu menu;
	private Frm__CONFIG config;
	private JPanel panelGeneral;
	private JLabel tituloLabel;
	private JPanel panelBotones;
	private boolean enMenu=false;
	
	private Frm__MenuState estadoActual;
	
	private Frm__Menu() {
		setLayout(null);
	}
	
	public static Frm__Menu getMenu(){
		if (menu == null) menu = new Frm__Menu();
		return menu;
	}
	
	public Frm__Menu iniciarMenu() {
		config = Frm__CONFIG.getConfig();
	    int anchoPanel = 600;
	    int altoPanel = 576;

	    panelGeneral = new JPanel(null); 
	    panelGeneral.setBounds((config.getANCHO() - anchoPanel) / 2, 15, anchoPanel, altoPanel);
	    panelGeneral.setOpaque(false);

	    tituloLabel = new JLabel("", JLabel.CENTER);
	    tituloLabel.setBounds(0, 30, anchoPanel, 50);
	    tituloLabel.setFont(new Font("Arial", Font.BOLD, 28));
	    panelGeneral.add(tituloLabel);

	    panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
	    panelBotones.setBounds(100, 300, 400, 60);
	    panelBotones.setOpaque(false);
	    panelGeneral.add(panelBotones);
	    
	    menu.setEnabled(true);
		menu.setVisible(true);
	    panelGeneral.setVisible(false);
	    this.add(panelGeneral);
	    return menu;
	}
	
	
	public void mostrarMenu(String titulo, String[] textos, String[] comandos) {
	    this.removeAll();

	    int anchoPanel = 500;
	    int altoPanel = 250;

	    JPanel panelGeneral = new JPanel(null) {



			private static final long serialVersionUID = 1872619048608368151L;

			@Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2d = (Graphics2D) g.create();
	            g2d.setColor(new Color(0, 0, 0, 150));
	            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
	            g2d.dispose();
	        }
	    };
	    panelGeneral.setBounds(
	        (config.getANCHO() - anchoPanel) / 2,
	        (config.getALTO() - altoPanel) / 2,
	        anchoPanel,
	        altoPanel
	    );
	    panelGeneral.setOpaque(false);

	    JLabel tituloLabel = new JLabel(titulo, JLabel.CENTER);
	    tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
	    tituloLabel.setForeground(Color.WHITE);
	    tituloLabel.setBounds(0, 20, anchoPanel, 40);
	    panelGeneral.add(tituloLabel);

	    JPanel filaBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
	    filaBotones.setOpaque(false);
	    filaBotones.setBounds(0, 110, anchoPanel, 50);

	    for (int i = 0; i < textos.length; i++) {
	        JToggleButton boton = new JToggleButton(textos[i]);
	        boton.setPreferredSize(new Dimension(180, 40));
	        boton.setFont(new Font("Arial", Font.BOLD, 14));
	        boton.setMargin(new Insets(5, 10, 5, 10));
	        boton.setBorderPainted(false);
	        boton.setContentAreaFilled(false);
	        boton.setFocusPainted(false);
	        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        boton.setForeground(Color.WHITE);

	        boton.setActionCommand(comandos[i]);
	        boton.addActionListener(e -> manejarAccion(e.getActionCommand()));

	        filaBotones.add(boton);
	    }

	    panelGeneral.add(filaBotones);
	    this.add(panelGeneral);
	    this.revalidate();
	    this.repaint();
	    this.setVisible(true);
	}

	private void manejarAccion(String comando) {
	    switch (comando) {
	        case "REANUDAR":
	            this.setVisible(false);
	            SwingUtilities.getWindowAncestor(this).requestFocusInWindow();
	            Escenario.getEscenario().pressEscape();
	            break;
	        case "VOLVER_JUGAR":
	        case "REINTENTAR":
	            this.setVisible(false);
	            SwingUtilities.getWindowAncestor(this).requestFocusInWindow();
	            Escenario.getEscenario().pressEnter();
	            break;
	        case "VOLVER_MENU":
	            cerrarYVolverAMenuPrincipal(false);
	            break;
	        case "GUARDAR_Y_MENU":
	            cerrarYVolverAMenuPrincipal(true);
	            break;
	    }

	}
	private void cerrarYVolverAMenuPrincipal(boolean guardar) {
	    ocultarMenu();
	    volverAlMenuPrincipal(guardar);
	    limpiarRecursos();
	}
	private void ocultarMenu() {
	    this.setVisible(false);
	    SwingUtilities.getWindowAncestor(this).requestFocusInWindow();
	}

	private void volverAlMenuPrincipal(boolean guardar) {
	    Escenario.getEscenario().vueltaAMenuPrincipal();
	    if (guardar) {
	        Escenario.getEscenario().escribirEnFichero();
	    }
	    SwingUtilities.getWindowAncestor(this).setVisible(false);
	    FrameMenuPrincipal.getFrameMenuPrincipal().resetFrameMenuPrincipal();
	    FrameMenuPrincipal frameInicio = FrameMenuPrincipal.getFrameMenuPrincipal().inicializarFrameMenuPrincipal();
        frameInicio.setVisible(true);
	}

	private void limpiarRecursos() {
	    Escenario.getEscenario().deleteObserver(FrameTablero.getFrameTablero());
	    FrameTablero.getFrameTablero().resetFrameTablero();
	    FrameTablero.getFrameTablero().discard();
	    Escenario.getEscenario().resetEscenario();
	}


	public void actualizarMenu(Boolean[] res) {
		if (res == null || res.length < 3) return;
		
		if (res[0] && !enMenu) {
		    setEstado(new Frm__MenuPausa());
		} else if (res[1] && !enMenu) {
		    setEstado(new Frm__MenuVictoria());
		} else if (res[2] && !enMenu) {
		    setEstado(new Frm__MenuDerrota());
		} else if (!res[0] && !res[1] && !res[2]) {
		    setEstado(null);
		}
	}
	    
	public void setEstado(Frm__MenuState estado) {
	    this.estadoActual = estado;
	    if (estado != null) {
	        estado.mostrar(this);
	        enMenu = true;
	    } else {
	        this.setVisible(false);
	        enMenu = false;
	    }
	}

	public void resetPausa() {
		panelGeneral=null;
		tituloLabel=null;
		panelBotones=null;
		enMenu=false;
		menu.removeAll();
		menu.setEnabled(false);
		menu.setVisible(false);
	}

}

