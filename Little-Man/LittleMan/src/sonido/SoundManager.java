package sonido;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Clase Singleton que gestiona la carga, reproducción, parada y cierre de sonidos y música.
//Se encarga de manejar clips de audio en memoria y diferenciarlos por nombre.
public class SoundManager {
    private static SoundManager instance;
    private final Map<String, Clip> soundClips = new HashMap<>();
    private String currentMusic = null;

    private SoundManager() {}

    public static SoundManager getSoundManager() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

 // Carga un archivo de sonido desde el disco y lo almacena como Clip con el nombre indicado.
    private void loadSound(String name, String path) {
        try {
            File soundFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            soundClips.put(name, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

 // Lanza un hilo para cargar el sonido de forma asíncrona sin bloquear la ejecución.
    private void loadAsync(String name, String path) {
        new Thread(() -> loadSound(name, path)).start();
    }

 // Reproduce un efecto de sonido una vez, reiniciando el Clip si ya estaba en reproducción.
    public void playSound(String name) {
        Clip clip = soundClips.get(name);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

 // Reproduce una pista musical en bucle continuo. Guarda la referencia como música actual.
    public void playSoundMusic(String name) {
        Clip clip = soundClips.get(name);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(currentMusic == null ? 0 : getFramePosition(currentMusic));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.setLoopPoints(0, 7938047); // configurable
            clip.start();
            currentMusic = name;

        }
    }

 // Detiene el sonido indicado. Si es música, también borra la referencia como actual.
    public void stopSound(String name) {
        Clip clip = soundClips.get(name);
        if (clip != null) {
            if (name.equals("MUSIC")) {
                currentMusic = null;
            }
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }

 // Devuelve la posición actual del Clip en frames, útil para reanudar desde donde se pausó.
    private int getFramePosition(String name) {
        Clip clip = soundClips.get(name);
        return (clip != null) ? clip.getFramePosition() : 0;
    }

 // Detiene y libera todos los sonidos cargados, vaciando la caché.
    public void close() {
        for (Clip clip : soundClips.values()) {
        	if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
        }
        soundClips.clear();
        currentMusic = null;
    }

 // Detiene y libera todos los sonidos cargados, vaciando la caché.
    public void soundsToLoadMenu() {
    	loadAsync("BOMB_EXPLODE1", "sfx/bombExplode1.wav");
    	loadAsync("BOMB_EXPLODE2", "sfx/bombExplode2.wav");
    	loadAsync("BOMB_EXPLODE3", "sfx/bombExplode3.wav");
    	loadAsync("MINI_EXPLODE1", "sfx/miniExplode1.wav");
    	loadAsync("MINI_EXPLODE2", "sfx/miniExplode2.wav");
    	loadAsync("MINI_EXPLODE3", "sfx/miniExplode3.wav");
    	loadAsync("SELECT_MENU1", "sfx/selectMenu1.wav");
    	loadAsync("SELECT_MENU2", "sfx/selectMenu2.wav");
    	loadAsync("SELECT_MENU3", "sfx/selectMenu3.wav");
    	loadSound("MUSIC_MENU1", "sfx/musicMenu1.wav");
    	loadAsync("MUSIC_MENU2", "sfx/musicMenu2.wav");
    	loadAsync("MUSIC_MENU3", "sfx/musicMenu3.wav");
    }

 // Carga los sonidos específicos del juego según el nivel de volumen configurado.
    public void soundsToLoadEscenario(String volumen) {
        String sufijo = switch (volumen.toUpperCase()) {
            case "BAJO" -> "1";
            case "MEDIO" -> "2";
            case "ALTO" -> "3";
            case "APAGADO" -> "void";
            default -> "1";
        };

        if (sufijo != "void") {
	        loadAsync("BOMB_EXPLODE", "sfx/bombExplode" + sufijo + ".wav");
	        loadAsync("DEATH", "sfx/die" + sufijo + ".wav");
	        loadAsync("ENEMY_DEATH", "sfx/enemyDies" + sufijo + ".wav");
	        loadAsync("ITEM_GET", "sfx/itemGet" + sufijo + ".wav");
	        loadAsync("PLACE_BOMB", "sfx/placeBomb" + sufijo + ".wav");
	        loadAsync("WALK", "sfx/walk" + sufijo + ".wav");
	        loadAsync("WIN", "sfx/win" + sufijo + ".wav");
	        loadAsync("INVENCIBILIDAD", "sfx/invencibilidad" + sufijo + ".wav");
	        loadSound("MUSIC", "sfx/musicScene" + sufijo + ".wav");
        }
    }
}
