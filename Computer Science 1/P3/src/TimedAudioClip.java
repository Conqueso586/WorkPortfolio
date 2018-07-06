import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

/**
  * This class represents a sound clip that extends the functionality
  * of the <code>Clip</code> class to make it easier to use in CS121.
  * <p>There are better ways of handling sound files, but they are too
  * advanced for this class.</p>
  *
  * <p><code>TimedAudioClip</code> objects can be played just like regular
  * <code>Clip</code> objects.  The call to <code>play()</code> returns
  * immediately.  <code>TimedAudioClip</code> has the additional feature that
  * it can be played using the <code>playAndWait()</code> method, which will not
  * return until the clip has entirely finished playing.
  *
  * <p>The ability to play and wait can be a useful feature when one wants the
  * execution of the program to pause until the sound clip has finished for
  * some reason.  The way the <code>TimedAudioClip</code> object knows how long
  * it takes to play itself is by the passing of a duration field to its
  * constructor.
  */
public class TimedAudioClip
{
	private AudioClip clip;
	private int playTime;
	private String title;

	/** Constructs a new <code>TimedAudioClip</code> to play the given
	  * sound file, which is the given playTime in length.
	  *
	  * @param title The title of the song to play; may not be null.
	  * @param filepath The filepath of the song to play; may not be null.
	  * @param playTime The length in seconds of the given clip;
	  *                 may not be less than zero.
	  */
	public TimedAudioClip(String title, String filepath, int playTime)
	{
		if (title == null)
			throw new IllegalArgumentException("invalid title passed");
		if (filepath == null)
			throw new IllegalArgumentException("invalid file path passed");
		else if (playTime < 0)
			throw new IllegalArgumentException("negative duration passed");
		else {
			this.playTime = playTime;
			this.title = title;
			try {
				URL url = new URL("file:" + new File(filepath).getAbsolutePath());
				this.clip = Applet.newAudioClip(url);
			} catch(Exception e) {
				System.out.println("PlayMusic: Error loading sound clip :( You may not hear anything.");
			}
		}
	}

	/**
	 * Plays this <code>TimedAudioClip</code>.
	 * Returns instantly.
	 */
	public void play()
	{
		if(clip != null) {
			clip.play();
		}
	}

	/**
	 * Halts the playing of this <code>TimedAudioClip</code>.
	 */
	public void stop()
	{
		if(clip != null) {
			clip.stop();
		}
	}


	/**
	 * Plays this <code>TimedAudioClip</code>.
	 * Does not return until playing has completed.
	 */
	public void playAndWait()
	{
		play();
		for (int i = 0; i < playTime * 10; i++) {
			try {
		        Thread.sleep(100); // in millisecs
		    	System.out.print("Playing " + title + ": " + i/10.0 + "\r");
			} catch (InterruptedException ie) {
				break;
			}
		}
		System.out.print("\b\b\b\b\b\b"); // clear countdown chars.
		System.out.println("Finished playing " + title + ".");
		stop();
	}
}