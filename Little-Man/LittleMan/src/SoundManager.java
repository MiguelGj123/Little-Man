import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static final SoundManager miSoundManager = new SoundManager();
    private final Map<String, Clip> soundClips = new HashMap<>();

    // Constructor privado para evitar instancias adicionales
    private SoundManager() {}

    // Método para obtener la única instancia del Singleton
    public static SoundManager getSoundManager() {
        return miSoundManager;
    }

    // Precargar sonidos en memoria
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

    // Reproducir un sonido precargado
    public void playSound(String name) {
        Clip clip = soundClips.get(name);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop(); // Detiene el sonido si ya está reproduciéndose
            }
            clip.setFramePosition(0); // Reinicia desde el inicio
            clip.start();
        }
    }
    public void stopSound(String name) {
        Clip clip = soundClips.get(name);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop(); // Detiene el sonido si ya está reproduciéndose
            }
        }
    }
    private void loadAsync(String nombre, String path) {
    	new Thread(() -> loadSound(nombre,path)).start();
    }
    public void soundsToLoad() {
    	loadAsync("bombExplode", "sfx/bombExplode.wav");
    	loadAsync("die", "sfx/die.wav");
    	loadAsync("enemyDies", "sfx/enemyDies.wav");
    	loadAsync("itemGet", "sfx/itemGet.wav");
    	loadAsync("placeBomb", "sfx/placeBomb.wav");
    	loadAsync("walk", "sfx/walk.wav");
    	loadAsync("win", "sfx/5-Track-5.wav");
    	loadSound("music", "sfx/3-Track-3.wav");
    }
    

    // Liberar memoria cuando el juego termine
    public void close() {
        for (Clip clip : soundClips.values()) {
            clip.close();
        }
        soundClips.clear();
    }
}
