package sonido;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static SoundManager miSoundManager;
    private final Map<String, Clip> soundClips = new HashMap<>();
    private String music=null;

    // Constructor privado para evitar instancias adicionales
    private SoundManager() {}

    // Método para obtener la única instancia del Singleton
    public static SoundManager getSoundManager() {
    	if (miSoundManager==null) miSoundManager= new SoundManager();
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
    public void playSoundMusic(String name) {
        Clip clip = soundClips.get(name);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop(); // Detiene el sonido si ya está reproduciéndose
            }
            if (music==null) {
            clip.setFramePosition(0); // Reinicia desde el inicio
            } else {
                clip.setFramePosition(getFrameMusic(music));
            }
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.setLoopPoints(0, 7938047);
            clip.start();
            music=name;
            
        }
    }
    private int getFrameMusic(String name) {
    	int frame=0;
    	Clip clip = soundClips.get(name);
    	if (clip != null) {
    		frame = clip.getFramePosition();
    	}
    	return frame;
    }
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
        	if (name=="MENU"||name=="MUSIC") {
        		music = null;
        	}
            if (clip.isRunning()) {
                clip.stop(); // Detiene el sonido si ya está reproduciéndose
            }
        }
    }
    private void loadAsync(String name, String path) {
    	new Thread(() -> loadSound(name,path)).start();
    }
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
        	loadSound("MUSIC_MENU2", "sfx/musicMenu2.wav");
        	loadSound("MUSIC_MENU3", "sfx/musicMenu3.wav");
    }
    public void soundsToLoadEscenario(String vol) {
    	switch(vol) {
    	case "APAGADO":
    		loadAsync("BOMB_EXPLODE", "sfx/void.wav");
        	loadAsync("DEATH", "sfx/void.wav");
        	loadAsync("ENEMY_DEATH", "sfx/void.wav");
        	loadAsync("ITEM_GET", "sfx/void.wav");
        	loadAsync("PLACE_BOMB", "sfx/void.wav");
        	loadAsync("WALK", "sfx/void.wav");
        	loadAsync("WIN", "sfx/void.wav");
        	loadSound("MUSIC", "sfx/void.wav");
    		break;
    	case "BAJO":
        	loadAsync("BOMB_EXPLODE", "sfx/bombExplode1.wav");
        	loadAsync("DEATH", "sfx/die1.wav");
        	loadAsync("ENEMY_DEATH", "sfx/enemyDies1.wav");
        	loadAsync("ITEM_GET", "sfx/itemGet1.wav");
        	loadAsync("PLACE_BOMB", "sfx/placeBomb1.wav");
        	loadAsync("WALK", "sfx/walk1.wav");
        	loadAsync("WIN", "sfx/win1.wav");
        	loadSound("MUSIC", "sfx/musicScene1.wav");
    		break;
    	case "MEDIO":
        	loadAsync("BOMB_EXPLODE", "sfx/bombExplode2.wav");
        	loadAsync("DEATH", "sfx/die2.wav");
        	loadAsync("ENEMY_DEATH", "sfx/enemyDies2.wav");
        	loadAsync("ITEM_GET", "sfx/itemGet2.wav");
        	loadAsync("PLACE_BOMB", "sfx/placeBomb2.wav");
        	loadAsync("WALK", "sfx/walk2.wav");
        	loadAsync("WIN", "sfx/win2.wav");
        	loadSound("MUSIC", "sfx/musicScene2.wav");
    		break;
    	case "ALTO":
        	loadAsync("BOMB_EXPLODE", "sfx/bombExplode3.wav");
        	loadAsync("DEATH", "sfx/die3.wav");
        	loadAsync("ENEMY_DEATH", "sfx/enemyDies3.wav");
        	loadAsync("ITEM_GET", "sfx/itemGet3.wav");
        	loadAsync("PLACE_BOMB", "sfx/placeBomb3.wav");
        	loadAsync("WALK", "sfx/walk3.wav");
        	loadAsync("WIN", "sfx/win3.wav");
        	loadSound("MUSIC", "sfx/musicScene3.wav");
    		break;
		default:
        	loadAsync("BOMB_EXPLODE", "sfx/bombExplode2.wav");
        	loadAsync("DEATH", "sfx/die2.wav");
        	loadAsync("ENEMY_DEATH", "sfx/enemyDies2.wav");
        	loadAsync("ITEM_GET", "sfx/itemGet2.wav");
        	loadAsync("PLACE_BOMB", "sfx/placeBomb2.wav");
        	loadAsync("WALK", "sfx/walk2.wav");
        	loadAsync("WIN", "sfx/win2.wav");
        	loadSound("MUSIC", "sfx/musicScene2.wav");
			break;

    	}
    }
    

    // Liberar memoria cuando el juego termine
    public void close() {
        for (Clip clip : soundClips.values()) {
            clip.close();
        }
        music = null;
        soundClips.clear();
    }
}
