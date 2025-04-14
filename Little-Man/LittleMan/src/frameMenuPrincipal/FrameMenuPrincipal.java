package frameMenuPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import frameTablero.FrameTablero;
import sonido.SoundManager;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import escenario.Escenario;

public class FrameMenuPrincipal extends JFrame {
	
	private MenuPrincipal menu = MenuPrincipal.getMenuPrincipal();

	private final int ANCHO = 1000;
	private final int LARGO = 606;
	private static final long serialVersionUID = 1L;
	private static final ImageIcon ICONO_TITULO = new ImageIcon("Pixels/title.png");
	private static final ImageIcon ICONO_IMAGEN = new ImageIcon("Pixels/back.png");
	private static final ImageIcon ICONO_EXPLOSION = new ImageIcon("Pixels/explotion-explode.gif");
	private static final ImageIcon ICONO_EXP_PERSONAJE = new ImageIcon("Pixels/blast.gif");
	private static final ImageIcon ICONO_OPCIONES = new ImageIcon("Pixels/Icono_opciones.png");
	private boolean opcionesAbiertas = false;
	
	private int seleccionFila0 = 1;
	private int seleccionFila1 = 1;
	private int seleccionFila2 = 1;
    private int valorFinalFila2= 1;
	private JLabel imagenCombinada;
	
	private Timer explosionTimer;
	private Controller controller = null;
	private Random random = new Random();
	private JLayeredPane layeredPane;
	private JPanel personajes;
	private JPanel explosiones_personaje;
	private JLabel explosion;
	private JLabel fondo;
	private JLabel titulo;
	private JLabel fondoBoss;	
	private JLabel iconoOpciones;
	private JPanel panelOpciones;
	private JPanel imagenesOpciones;
															
	
	// Se guardan las siguientes imagenes en un vector ICONOS_PERSONAJES
	private static final ImageIcon[] ICONOS_PERSONAJES = {
			
        new ImageIcon("Pixels/bomber1.png"),		// [0] Imagen Bomberman Blanco:		color
        new ImageIcon("Pixels/bomber2.png"),		// [1] Imagen Bomberman Negro:		color
        new ImageIcon("Pixels/bomber3.png"),		// [2] Imagen Bomberman Azul:		color
        new ImageIcon("Pixels/bomber4.png"),		// [3] Imagen Bomberman Rosa:		color
        
        new ImageIcon("Pixels/bomber1_gray.png"),	// [4] Imagen Bomberman Blanco:		gris
        new ImageIcon("Pixels/bomber2_gray.png"),	// [5] Imagen Bomberman Negro:		gris
        new ImageIcon("Pixels/bomber3_gray.png"),	// [6] Imagen Bomberman Azul:		gris
        new ImageIcon("Pixels/bomber4_gray.png"),	// [7] Imagen Bomberman Rosa:		gris
    };

	// Se guardan las siguientes imagenes en un vector ICONOS_ENEMIGOS
    private static final ImageIcon[] ICONOS_ENEMIGOS = {
        new ImageIcon("Pixels/boss2.png"),			// [0] Imagen Boss Medio
        new ImageIcon("Pixels/boss3.png"), 			// [1] Imagen Boss Derecha
        new ImageIcon("Pixels/boss4.png"), 			// [2] Imagen Boss Izquierda
        new ImageIcon("Pixels/baloon1.png"),		// [3] Imagen Enemigo Globo
        new ImageIcon("Pixels/doria2.png"),			// [4] Imagen Enemigo Azul
        new ImageIcon("Pixels/pass1.png"),			// [5] Imagen Enemigo Purpura
        new ImageIcon("Pixels/bomb1.png"),	        // [6] Imagen Bomba
    };

    private static final ImageIcon[] BOTONES_OPCIONES = {
    		new ImageIcon("Pixels/Selector_nombres.png"),
	        new ImageIcon("Pixels/Selector_dificultad_0_off.png"),
	        new ImageIcon("Pixels/Selector_dificultad_0_on.png"),
	        new ImageIcon("Pixels/Selector_dificultad_1_off.png"),
	        new ImageIcon("Pixels/Selector_dificultad_1_on.png"),
	        new ImageIcon("Pixels/Selector_dificultad_2_off.png"),
	        new ImageIcon("Pixels/Selector_dificultad_2_on.png"),
	        new ImageIcon("Pixels/Selector_dificultad_3_off.png"),
	        new ImageIcon("Pixels/Selector_dificultad_3_on.png"),
	        
	        
	    };
    
    public FrameMenuPrincipal() 
    {
    	inicializarSonidos();
    	crearVentana();
    	crearJPanel();
        generarJLabelsFrame();
        aniadirJLabels();
        aniadirJPanel();
        configurarVentana();
        aniadirObserverMouseKeyListener();
        
		actualizarJugadorColor("BLANCO");
    }
    private void inicializarSonidos() {
    	SoundManager.getSoundManager().close();
    	SoundManager.getSoundManager().soundsToLoadMenu();
    	SoundManager.getSoundManager().playSoundMusic("MUSIC_MENU"+seleccionFila1);
    }
    
    private void crearVentana() {										// Se crea la ventana "Bomberman Menu" con las dimensiones definidas ANCHO y LARGO
    	setTitle("Bomberman Menu");										// Ventana Titulo
        setSize(ANCHO, LARGO);											// Ventana Dimensiones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					// Ventana Operacion Cerrar
    }
    
    private void crearJPanel() {										// Se crea y configura en la ventana un panel que guardara los JLabel que compondran el menu inicio 
        layeredPane = new JLayeredPane();								// layeredPane es un JLayeredPane 
        layeredPane.setPreferredSize(new Dimension(ANCHO, LARGO)); 		// layeredPane toma las dimensiones de la ventana
        layeredPane.setLayout(null);
    }
    
    private void generarJLabelsFrame() {								       	// Se crean los JLabel que aparecen en el menu principal
    	fondo				  = crearJLabel(ICONO_IMAGEN       , 0 , 0   , ANCHO , LARGO , true , false , false , "ImagenFondo"		, false);		// fondo	  es un JLABEL  que contiene la imagen  de fondo 
    	titulo				  = crearJLabel(ICONO_TITULO       , 0 , 15  , 600   , 200   , true , true  , false , "Titulo"			, false);		// titulo	  es un JLABEL  que contiene la imagen  del titulo
        explosion			  = crearJLabel(ICONO_EXPLOSION    , 0 , 100 , 600   , 600   , false, true  , true , "ExplosionCentral"	, false);		// explosion  es un JLABEL  que contiene el GIF		de la explosion de fondo
        fondoBoss			  = crearJLabel(ICONOS_ENEMIGOS[0] , 0 , 200 , 280   , 310   , true , true  , false , "FondoBoss"		, false);		// fondoBoss  es un JLABEL  que contiene la imagen  del boss principal central
        explosiones_personaje = crearPanelExplosiones();																			// explosiones_personaje es un JPANEL  que contiene los JLabel de las explosiones detras de los persojaes
        personajes			  = crearPanelPersonajes();																				// personajes			 es un JPANEL  que contiene los Jlabel de los personajes
        iconoOpciones = crearIconoOpciones();
        panelOpciones = crearPanelOpciones();
        imagenCombinada = new JLabel();
    	imagenCombinada.setBounds(600, 400, 154, 154);
    } 
    
    private void aniadirJLabels() {										// Se añaden al JFrame inicial los JLABEL y JPANEL que hemos inicializado
        layeredPane.add(fondo                 , Integer.valueOf(0));	// añadimos fondo				 en la capa 0 del layeredPane
        layeredPane.add(titulo                , Integer.valueOf(1));	// añadimos titulo				 en la capa 1 del layeredPane
        layeredPane.add(explosion             , Integer.valueOf(2));	// añadimos explosion			 en la capa 2 del layeredPane
        layeredPane.add(fondoBoss             , Integer.valueOf(3));	// añadimos fondoBoss 			 en la capa 3 del layeredPane
        layeredPane.add(explosiones_personaje , Integer.valueOf(4));	// añadimos explosionesPersonaje en la capa 4 del layeredPane
        layeredPane.add(personajes            , Integer.valueOf(5));	// añadimos personajes			 en la capa 5 del layeredPane
        layeredPane.add(iconoOpciones		  , Integer.valueOf(6));
        layeredPane.add(panelOpciones		  , Integer.valueOf(7));
        layeredPane.add(imagenCombinada		  , Integer.valueOf(8));
    }
    
    private void aniadirJPanel() { 										// Añadimos layeredPane a FrameMenuPrincipal
    	add(layeredPane);
    }
    
    private void configurarVentana() {
    	pack();
    	setResizable(false); 
    	setLocationRelativeTo(null);
    	setVisible(true);
    }
    
    private void aniadirObserverMouseKeyListener() {
    	addMouseListener(new Controller());								// añadimos mouse listener
    	addKeyListener(new Controller());								// añadimos key   listener
    }
    
    private JLabel crearIconoOpciones() {
    	
    	return crearJLabel(ICONO_OPCIONES, 20, 20, 58, 58, true, false, false, "icono", true);

    }
    
    private JPanel crearPanelOpciones() {
        int anchoPanel = 600;
        int altoPanel = 576; // Esto es solo para el fondo
        int anchoFila = 400;
        int altoFila = 56;

        JPanel panelGeneral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(0, 0, 0, 128));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };


        panelGeneral.setLayout(null);
        panelGeneral.setBounds((ANCHO - anchoPanel) / 2, 15, anchoPanel, altoPanel);
        panelGeneral.setOpaque(false);


        JPanel fila1 = crearFilaBotones(0);
        JPanel fila2 = crearFilaBotones(1);
        JPanel fila3 = crearFilaBotones(2);
        JLabel label1 = crearLabelOpciones(0);
        JLabel label2 = crearLabelOpciones(1);
        JLabel label3 = crearLabelOpciones(2);

        fila1.setBounds((anchoPanel - anchoFila) / 2, 160, anchoFila, altoFila);
        fila2.setBounds((anchoPanel - anchoFila) / 2, 208, anchoFila, altoFila);
        fila3.setBounds((anchoPanel - anchoFila) / 2, 256, anchoFila, altoFila);

        panelGeneral.add(fila1);
        panelGeneral.add(fila2);
        panelGeneral.add(fila3);
        panelGeneral.add(label1);
        panelGeneral.add(label2);
        panelGeneral.add(label3);
        
        panelGeneral.setVisible(false);
        return panelGeneral;
    }
       
    private JLabel crearLabelOpciones(int filaIndex) 
    {	

    	
    	Image image= null;
    	image = BOTONES_OPCIONES[0].getImage().getScaledInstance(414, 150, Image.SCALE_SMOOTH);        // img es un GIF,     y contiene el iconoOriginal del titulo redimensionado a dimensiones x e y
        ImageIcon iconoEscalado = new ImageIcon(image);                                                        // iconoEscalado es un ImageIcon de la Image img que creamos anteriormente

        JLabel returnLabel = new JLabel(iconoEscalado);  
        returnLabel.setBounds(150, 160, 414, 150);
    	    	
    	return returnLabel;
    }
    
    private JPanel crearFilaBotones(int filaIndex) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER, -9, 0));
        fila.setOpaque(false);

        ButtonGroup grupo = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
        	Image image=null;
        	ImageIcon iconoEscalado=null;
            JToggleButton boton = new JToggleButton("" + i);
            boton.setPreferredSize(new Dimension(70, 51)); // Ancho x Alto
            boton.setFont(new Font("Arial", Font.BOLD, 16)); // Tamaño y estilo de letra
            if (filaIndex==0) {
            	image= BOTONES_OPCIONES[i*2+1].getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH); 
            	iconoEscalado = new ImageIcon(image); 
	            boton.setIcon(iconoEscalado);
	            image= BOTONES_OPCIONES[i*2+2].getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH); 
            	iconoEscalado = new ImageIcon(image); 
	            boton.setSelectedIcon(iconoEscalado);
	            if (i==1) {
	            	boton.doClick();
	            }
            } else if (filaIndex==1) {
            	image= BOTONES_OPCIONES[i*2+1].getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH); 
            	iconoEscalado = new ImageIcon(image); 
	            boton.setIcon(iconoEscalado);
	            image= BOTONES_OPCIONES[i*2+2].getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH); 
            	iconoEscalado = new ImageIcon(image); 
	            boton.setSelectedIcon(iconoEscalado);
            } else if (filaIndex==2) {
            	image= BOTONES_OPCIONES[i*2+1].getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH); 
            	iconoEscalado = new ImageIcon(image); 
	            boton.setIcon(iconoEscalado);
	            image= BOTONES_OPCIONES[i*2+2].getImage().getScaledInstance(63, 51, Image.SCALE_SMOOTH); 
            	iconoEscalado = new ImageIcon(image); 
	            boton.setSelectedIcon(iconoEscalado);
	            if (i==1) {
	            	boton.doClick();
	            }
            }
            boton.setMargin(new Insets(5, 60, 5, 30)); // top, left, bottom, right
            boton.setBorderPainted(false);
            boton.setContentAreaFilled(false);
            boton.setFocusPainted(false);
            grupo.add(boton);
            fila.add(boton);
            final int botonIndex = i;

            boton.addActionListener(e -> {
            	
                if (filaIndex == 0) {
                    seleccionFila0 = botonIndex;
                } else if (filaIndex == 1) {
                	SoundManager.getSoundManager().stopSound("MUSIC_MENU"+seleccionFila1);
                    seleccionFila1 = botonIndex;
                    SoundManager.getSoundManager().playSoundMusic("MUSIC_MENU"+seleccionFila1);
                }else if (filaIndex == 2) {
                    seleccionFila2 = botonIndex;
                }
                SoundManager.getSoundManager().playSound("SELECT_MENU"+seleccionFila1);
                actualizarImagenCombinada(); // método que actualiza la imagen
            });
        }
        return fila;
    }
    
    private void actualizarImagenCombinada() {
        if (seleccionFila0 != -1 && seleccionFila2 != -1) {


            if (seleccionFila2 == 0) {
                valorFinalFila2 = random.nextInt(3) + 1; // aleatorio entre 1 y 3
            } else {
                valorFinalFila2 = seleccionFila2;
            }

            String ruta = "Pixels/Combinacion_" + seleccionFila0 + "_" + valorFinalFila2 + ".png";

            try {
                ImageIcon icono = new ImageIcon(ruta);
                Image imagen = icono.getImage().getScaledInstance(154, 154, Image.SCALE_SMOOTH);
                imagenCombinada.setIcon(new ImageIcon(imagen));
                imagenCombinada.setVisible(true);
            } catch (Exception ex) {
                System.out.println("No se pudo cargar la imagen: " + ruta);
                imagenCombinada.setVisible(false);
            }
        }
    }


    


    private JLabel crearJLabel(ImageIcon imageIcon, int posX, int poxY, int pAncho, int pAlto, boolean isVisible, boolean centrado, boolean esGIF, String nombre, boolean isClickable) {
        Image image;
        if(esGIF) image = imageIcon.getImage().getScaledInstance(pAncho, pAlto, Image.SCALE_DEFAULT);    // img es una Image, y contiene el iconoOriginal del titulo redimensionado a dimensiones x e y 
        else image = imageIcon.getImage().getScaledInstance(pAncho, pAlto, Image.SCALE_SMOOTH);        // img es un GIF,     y contiene el iconoOriginal del titulo redimensionado a dimensiones x e y
        ImageIcon iconoEscalado = new ImageIcon(image);                                                        // iconoEscalado es un ImageIcon de la Image img que creamos anteriormente

        JLabel returnLabel = new JLabel(iconoEscalado);                                                        // tituloLabel es un nuevo JLabel que contiene iconoEscalado

        if (centrado)  returnLabel.setBounds(((ANCHO - pAncho)/2), poxY, pAncho, pAlto);                    // crear label a devolver centrado
        if (!centrado) returnLabel.setBounds(posX, poxY, pAncho, pAlto);                                    // crear label a devolver en posiciones

        nombre = (nombre == null) ? imageIcon.getDescription() : nombre;
        returnLabel.setName(nombre);
        returnLabel.setVisible(isVisible);
        
        if(isClickable) 
        {
        	returnLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	returnLabel.addMouseListener(getController());
        	
        }

        return returnLabel ;
    }
    
	public String descripcionToNombrePersonaje(String descripcion) 
	{
		switch (descripcion) 
		{
			case "Pixels/bomber1.png":
				return "BLANCO";
			case "Pixels/bomber2.png":
				return "NEGRO";
			case "Pixels/bomber3.png":
				return "";
			case "Pixels/bomber4.png":
				return "";
			case "Pixels/bomber1_gray.png":
				return "BLANCO";
			case "Pixels/bomber2_gray.png":
				return "NEGRO";
			case "Pixels/bomber3_gray.png":
				return "";
			case "Pixels/bomber4_gray.png":
				return "";
			case "icono":
				return "icono";
			default:
				return "";
		}
	
	}
    
    private JPanel crearPanelPersonajes() {																	// crearPanelPersonajes genera un JPanel con los JLabel de los personajes que aparecerán en el fondo del menu principal
        JPanel panelCentral = new JPanel();																	// panelCentral es un nuevo JPanel
        panelCentral.setOpaque(false);																		// panelCentral es opaca
        panelCentral.setLayout(null);																		// panelCentral usa un layout nulo para posicionamiento absoluto
        panelCentral.setBounds(0, 0, ANCHO, LARGO);															// panelCentral: posicion [0,0] tamaño [ANCHO,LARGO]

        /*
         * A continuacion se añaden al panel JLabels nuevos creados con el metodo crearLabelConImagen, el cual se implementa a continuacion. Este metodo crea un nuevo
         * JLabel con la imagen y lo posiciona en la posicion x e y especificada, con el tamaño especificado, y con la visibilidad true o false que se le indique
         */
        
        panelCentral.add(crearJLabel(ICONOS_PERSONAJES[0], 110, 290, 90, 132, false, false, false , null	, true ));		// añadir a panelCentral:	Bomberman	(color) BLANCO	posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_PERSONAJES[1], 240, 380, 75, 139, false, false, false , null	, true ));		// añadir a panelCentral:	Bomberman	(color) NEGRO	posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_PERSONAJES[2], 695, 380, 65, 128, false, false, false , null	, false));		// añadir a panelCentral:	Bomberman	(color) AZUL	posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_PERSONAJES[3], 750, 290, 171, 150, false, false, false , null	, false));		// añadir a panelCentral:	Bomberman	(color) ROSA	posx, posy, dimX, dimY, visible, multTamaño
        
        panelCentral.add(crearJLabel(ICONOS_PERSONAJES[4], 110, 290, 90, 132, true , false, false , null	, true ));		// añadir a panelCentral:	Bomberman	(gris)  BLANCO	posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_PERSONAJES[5], 240, 380, 75, 139, true , false, false , null	, true ));		// añadir a panelCentral:	Bomberman	(gris)  NEGRO	posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_PERSONAJES[6], 695, 380, 65, 128, true , false, false , null	, false));		// añadir a panelCentral:	Bomberman	(gris)  AZUL	posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_PERSONAJES[7], 750, 290, 171, 150, true , false, false , null	, false));		// añadir a panelCentral:	Bomberman	(gris)  ROSA	posx, posy, dimX, dimY, visible, multTamaño
        
        panelCentral.add(crearJLabel(ICONOS_ENEMIGOS[1]  , 910, 180, 177, 357, true , false, false , null	, false));		// añadir a panelCentral:	Enemigo		DERECHA		posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_ENEMIGOS[2]  , -17, 470, 100, 216, true , false, false , null	, false));		// añadir a panelCentral:	Enemigo		IZQUIERDA	posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_ENEMIGOS[3]  , 150, 500, 55, 55, true , false, false , null		, false));		// añadir a panelCentral:	Enemigo		GLOBO		posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_ENEMIGOS[4]  , 800, 480, 51, 60, true , false, false , null		, false));		// añadir a panelCentral:	Enemigo		AZUL		posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_ENEMIGOS[5]  , 880, 520, 63, 57, true , false, false , null		, false));		// añadir a panelCentral:	Enemigo		PURPURA		posx, posy, dimX, dimY, visible, multTamaño
        panelCentral.add(crearJLabel(ICONOS_ENEMIGOS[6]  , 410, 480, 48,48, true , false, false , null		, false));		// añadir a panelCentral:	Enemigo		BOMBA		posx, posy, dimX, dimY, visible, multTamaño
        
        return panelCentral;
    }
    
    private JPanel crearPanelExplosiones() {																// crearPanelPersonajes genera un JPanel con los JLabel de los personajes que aparecerán en el fondo del menu principal
    	JPanel panelCentral = new JPanel();																	// panelCentral es un nuevo JPanel
    	panelCentral.setOpaque(false);																		// panelCentral es opaca
    	panelCentral.setLayout(null);																		// panelCentral usa un layout nulo para posicionamiento absoluto
    	panelCentral.setBounds(0, 0, ANCHO, LARGO);															// panelCentral: posicion [0,0] tamaño [ANCHO,LARGO]
    	
    	 /*
         * A continuacion se añaden al panel JLabels nuevos creados con el metodo crearLabelConImagen, el cual se implementa a continuacion. Este metodo
         * crea un nuevo JLabel con la imagen y lo posiciona en la posicion x e y especificada, con el tamaño especificado, y con la visibilidad true o
         * false que se le indique
         */
    	
    	panelCentral.add(crearJLabel(ICONO_EXP_PERSONAJE, 85, 295, 150, 150, true , false, true , "0"	, false));		// añadir a panelCentral:	GIF	  Explosion_0	posx, posy, dimX, dimY, visible, multTamaño, nombre
    	panelCentral.add(crearJLabel(ICONO_EXP_PERSONAJE, 205, 385, 150, 150, false, false, true , "1"	, false));		// añadir a panelCentral:	GIF	  Explosion_1	posx, posy, dimX, dimY, visible, multTamaño, nombre
    	panelCentral.add(crearJLabel(ICONO_EXP_PERSONAJE, 653, 380, 150, 150, false, false, true , "2"	, false));		// añadir a panelCentral:	GIF	  Explosion_2	posx, posy, dimX, dimY, visible, multTamaño, nombre
    	panelCentral.add(crearJLabel(ICONO_EXP_PERSONAJE, 765, 298, 150, 150, false, false, true , "3"	, false));		// añadir a panelCentral:	GIF	  Explosion_3	posx, posy, dimX, dimY, visible, multTamaño, nombre
    	
    	return panelCentral;
    	
    }
    
    private void actualizarJugadorColor(String quitarGris) {												// actualizarJugadorColor se encarga de actualizar que jugadores se ven en blanco y negro, y cuales de color en el menu principal
    	int personaje = -1;																					// se inicializa el personaje seleccionado a un valor que no es posible
    	SoundManager.getSoundManager().stopSound("MINI_EXPLODE"+seleccionFila1);
        SoundManager.getSoundManager().playSound("MINI_EXPLODE"+seleccionFila1);
    	switch (quitarGris) {																				// personaje toma el valor de la posicion de ICONOS_PERSONAJES que corresponde al personaje seleccionado
    		case "BLANCO":																					
    			personaje = 0;
    			break;
    		case "NEGRO":
    			personaje = 1;
    			break;
    		case "OTRO_1":
    			personaje = 2;
    			break;
    		case "OTRO_2":
    			personaje = 3;
    			break;
    	}
    	
    	if (personaje >= 0 && personaje < ICONOS_PERSONAJES.length/2) {																			// Si es un personaje valido:
    		for (int i = 0; i < 4; i++) ((JLabel)personajes.getComponent(i)).setVisible(false);				// Se ocultan todos los jugadores en color
    		for (int i = 4; i < 8; i++) ((JLabel)personajes.getComponent(i)).setVisible(true);				// Se muestran todos los jugadores blanco y negro
    		
    		for (int i = 0; i < 4; i++) ((JLabel)explosiones_personaje.getComponent(i)).setVisible(false);	// Se ocultan todas las explosiones
    		
    		((JLabel)personajes.getComponent(personaje)).setVisible(false);									// Se oculta el personaje en blanco y negro seleccionado
    		((JLabel)personajes.getComponent(personaje)).setVisible(true);									// Se muestra el personaje que esta seleccionado
    		
    		((JLabel)explosiones_personaje.getComponent(personaje)).setVisible(true);						// Se muestra el personaje que esta seleccionado
    		
    		  
            if (explosionTimer  != null && explosionTimer.isRunning()) {										// Cancelar cualquier timer previo antes de iniciar uno nuevo
                explosionTimer.stop();
            }

            explosionTimer = new Timer(900, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < 4; i++) {
                        obtenerLabelPorNombre(explosiones_personaje, String.valueOf(i)).setVisible(false);
                    }
                    explosionTimer.stop(); // Detener el timer después de ejecutarse
                }
            });
            
            explosionTimer.setRepeats(false); // Solo se ejecuta una vez
            explosionTimer.start();
        }
    }

	

    public JLabel obtenerLabelPorNombre(JPanel panel, String nombre) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel && nombre.equals(comp.getName())) {
                return (JLabel) comp;
            }
        }
        return null; // No se encontró
    }
    private void iniciarJuegoMenuPrincipal(String[] params) {
	    FrameTablero nuevoframe = new FrameTablero(new String[] {params[0], params[1], params[2], params[3]}, new int[] {17, 11});
		nuevoframe.setVisible(true);
		dispose();
    }
    private void explosionVisible() {
    	SoundManager.getSoundManager().playSound("BOMB_EXPLODE"+seleccionFila1);
    	explosion.setVisible(true);
    }
    private int getOpcion(int fila) {
    	int filaOpcion=0;
    	if (fila==0) {
    		filaOpcion=this.seleccionFila0;
    	} else if (fila==1) {
    		filaOpcion=this.seleccionFila1;
    	} else if (fila==2) {
    		filaOpcion=this.valorFinalFila2;
    	}
    	return filaOpcion;
    }
    
    
    private Controller getController() 
    {
    	if (controller == null)
    	{
    		controller = new Controller();
    	}
    	
    	return controller;
    }
    
    class Controller implements  MouseListener,KeyListener
    {
    	private Timer  startGameTimer;

    	
    	public Controller() 
    	{
    		
    	}
    	private void iniciarJuego(String[] params) {
    		explosionVisible();
    		
            startGameTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	iniciarJuegoMenuPrincipal(params);
                	Escenario.getEscenario().inicializarTablero(params[0], params[1], params[2]);
                    startGameTimer.stop(); // Detener el timer después de ejecutarse
                }
            });

            startGameTimer.setRepeats(false); // Solo se ejecuta una vez
            startGameTimer.start();
        }
    	

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			/*int x = e.getX();
			int y = e.getY();
			System.out.println(x + " " + y);
			*/
			
			 if (e.getComponent() instanceof JLabel labelClickado) 
			 {
			        String nombre = descripcionToNombrePersonaje(labelClickado.getName());
			        if (!opcionesAbiertas) 
			        {
					  if (nombre == "icono")
					  {
						  opcionesAbiertas = true;
						  panelOpciones.setVisible(true);
						  imagenCombinada.setVisible(true);
						  
					  }
					  else if (!menu.isReady()){
						  actualizarJugadorColor(menu.seleccionPersonaje(nombre));
						  menu.seleccionarOpciones(getOpcion(0), getOpcion(1), getOpcion(2));
						  iniciarJuego(menu.iniciarJuego());
					 }
			        }
			        else 
			        {
			        	if(nombre == "icono")
			        	{
			        		opcionesAbiertas = false;
							panelOpciones.setVisible(false);
							imagenCombinada.setVisible(false);
			        	}
			        }
			 }
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) 
		{
			  if (e.getComponent() instanceof JLabel labelClickado) {
			        String labelNombre = descripcionToNombrePersonaje(labelClickado.getName());
			        if (!opcionesAbiertas) actualizarJugadorColor(menu.seleccionPersonaje(labelNombre));
			    }
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
		@Override
		public void keyTyped(KeyEvent e) 
		{
			
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) 
		{
			int keyCode = e.getKeyCode();
			if (!opcionesAbiertas) {
	        	switch (keyCode) {
	            	case KeyEvent.VK_D:
	            		actualizarJugadorColor(menu.pressD());
	            		break;
	            		
	            	case KeyEvent.VK_A:
	            		actualizarJugadorColor(menu.pressA());
	            		break;
	            		
	            	case KeyEvent.VK_ENTER:
	            		if (!menu.isReady()) {
	            			menu.seleccionarOpciones(getOpcion(0), getOpcion(1), getOpcion(2));
	            			iniciarJuego(menu.iniciarJuego());
	            		}
            			break;
            			
	            	case KeyEvent.VK_O:
	            		panelOpciones.setVisible(true);
            			break;
            			
			        default: ;
			       		break;
	        	}
	        }
	    }
			
		

		@Override
		public void keyReleased(KeyEvent e) {
			
			
		}
    
    }
    
}