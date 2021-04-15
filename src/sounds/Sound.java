package sounds;


import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    private final static String BACKGROUND_MUSIC = "rec/sounds/music.wav";
    private final static String SELECTUNIT_MUSIC = "rec/sounds/select_unit.wav";

    public static synchronized void playSound(String path, boolean repeat) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new File(path));
                    clip.open(inputStream);
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    double gain = 0.03;
                    float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
                    gainControl.setValue(dB);
                    if (repeat)
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public static synchronized void playBackgroundMusic () {
        playSound(BACKGROUND_MUSIC, true);
    }
    public static synchronized void playSelectUnitMusic () { playSound(SELECTUNIT_MUSIC, false); }
}


