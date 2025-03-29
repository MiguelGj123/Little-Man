
public class Main {
    public static void main(String[] args)
    {
    	SoundManager.getSoundManager().loadSound("bombExplode", "sfx/bombExplode.wav");
    	SoundManager.getSoundManager().loadSound("die", "sfx/die.wav");
    	SoundManager.getSoundManager().loadSound("enemyDies", "sfx/enemyDies.wav");
    	SoundManager.getSoundManager().loadSound("itemGet", "sfx/itemGet.wav");
    	SoundManager.getSoundManager().loadSound("placeBomb", "sfx/placeBomb.wav");
    	SoundManager.getSoundManager().loadSound("walk", "sfx/walk.wav");
    	SoundManager.getSoundManager().loadSound("3", "sfx/3-Track-3.wav");

        FrameTablero frame = new FrameTablero("BLANCO");
        frame.setVisible(true);

    }


}
