package pong;

/**
 * @author Jeremy
 */
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
	public static final AudioClip SCORE = Applet.newAudioClip(Sound.class.getResource("score.wav"));
	public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("back.wav"));
        public static final AudioClip VICTORY1 = Applet.newAudioClip(Sound.class.getResource("v1.wav"));
        public static final AudioClip VICTORY2 = Applet.newAudioClip(Sound.class.getResource("v2.wav"));
}
