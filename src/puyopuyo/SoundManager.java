package puyopuyo;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class SoundManager {
    
    private static final URL[] songList = new URL[20];
    private static final URL[] sfxList = new URL[22];
    
    private static Clip song = null;
    private static boolean playing;
    
    static {
        System.out.println("Initializing SoundManager...");
        // TODO: make error message
        songList[0] = SoundManager.class.getResource("/puyopuyo/sound/BGM_Menu.wav");
        songList[1] = SoundManager.class.getResource("/puyopuyo/sound/BGM_Pinch.wav");

        sfxList[0] = SoundManager.class.getResource("/puyopuyo/sound/Chain0.wav");
        sfxList[1] = SoundManager.class.getResource("/puyopuyo/sound/Chain1.wav");
        sfxList[2] = SoundManager.class.getResource("/puyopuyo/sound/Chain2.wav");
        sfxList[3] = SoundManager.class.getResource("/puyopuyo/sound/Chain3.wav");
        sfxList[4] = SoundManager.class.getResource("/puyopuyo/sound/Chain4.wav");
        sfxList[5] = SoundManager.class.getResource("/puyopuyo/sound/Chain5.wav");
        sfxList[6] = SoundManager.class.getResource("/puyopuyo/sound/ChainHit1.wav");
        sfxList[7] = SoundManager.class.getResource("/puyopuyo/sound/ChainHit2.wav");
        sfxList[8] = SoundManager.class.getResource("/puyopuyo/sound/ChainHit3.wav");
        sfxList[9] = SoundManager.class.getResource("/puyopuyo/sound/ChainHit4.wav");
        sfxList[10] = SoundManager.class.getResource("/puyopuyo/sound/Cursor.wav");
        sfxList[11] = SoundManager.class.getResource("/puyopuyo/sound/Junk.wav");
        sfxList[12] = SoundManager.class.getResource("/puyopuyo/sound/Land.wav");
        sfxList[13] = SoundManager.class.getResource("/puyopuyo/sound/Lose.wav");
        sfxList[14] = SoundManager.class.getResource("/puyopuyo/sound/Move.wav");
        sfxList[15] = SoundManager.class.getResource("/puyopuyo/sound/Rotate.wav");
        sfxList[16] = SoundManager.class.getResource("/puyopuyo/sound/ScoreCount.wav");
        sfxList[17] = SoundManager.class.getResource("/puyopuyo/sound/Select.wav");
        sfxList[18] = SoundManager.class.getResource("/puyopuyo/sound/Start.wav");
        sfxList[19] = SoundManager.class.getResource("/puyopuyo/sound/Win.wav");
        sfxList[20] = SoundManager.class.getResource("/puyopuyo/sound/Win2.wav");
        sfxList[21] = SoundManager.class.getResource("/puyopuyo/sound/Win3.wav");
    }
    
    public static void playSFX(int o) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(sfxList[o]);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException | NullPointerException ex) {
            System.err.println("Error: Missing or invalid SFX file: " + o);
        }
    }
    
    public static void playSong(int o) {
        if(song != null)
            song.stop();
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(songList[o]);
            song = AudioSystem.getClip();
            song.open(audioIn);
            playing = true;
            song.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException | NullPointerException ex) {
            System.err.println("Error: Missing or invalid SONG file " + o);
        }
    }
    
    public static void pause() {
        if(playing) {
            song.stop();
            playing = false;
        }
        else {
            song.start();
            playing = true;
        }
    }
}
