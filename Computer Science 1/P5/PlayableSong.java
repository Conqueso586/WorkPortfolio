import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

/**
 * Provides methods for playing a song clip.
 *
 * @author CS121 Instructors
 *
 */
public class PlayableSong
{
	/** Arbitrary maximum number of plays for heat map visualization. */
	public static final int MAX_PLAYS = 40;

	private AudioClip clip;
	private int timesPlayed;

	/**
	 * Initializes the audio clip so the song can be played.
	 *
	 * @param filePath The file path fo the clip that will be played.
	 */
	public PlayableSong(String filePath)
	{
		try {
			URL url = new URL("file:" + new File(filePath).getAbsolutePath());
			this.clip = Applet.newAudioClip(url);
		} catch(Exception e) {
			System.out.println("PlayMusic: Error loading sound clip :( You may not hear anything.");
		}
	}
	/**
	 * Returns the number of times the song has been played. The count is incremented
	 * every time the play method is called.
	 *
	 * @return The number of times the song has been played.
	 */
	public int getTimesPlayed()
	{
		return timesPlayed;
	}

	/**
	 * Plays the song and continues playing.
	 */
	public void play()
	{
		if(clip != null) {
			clip.play();
			timesPlayed++;
		}
	}

	/**
	 * Stops the song.
	 */
	public void stop()
	{
		if(clip != null) {
			clip.stop();
		}
	}
}
