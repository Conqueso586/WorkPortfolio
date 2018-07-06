import java.io.File;

/**
 * This class extends your existing PlayList. It contains all the methods
 * necessary to play and stop the songs in your playlist.
 *
 * @author CS121 Instructors
 */
public class MyTunesPlayList extends PlayList
{
	private Song playing;
	private boolean sequential;

	/**
	 * Creates a PlayList from the given song data.
	 * @param file The file to load the song data from.
	 */
	public MyTunesPlayList(File file)
	{
		// TODO: This error will go away when you implement your overloaded
		// constructor in PlayList.java
		super(file);
		this.playing = null;
		this.sequential = false;
	}

	private void play()
	{
		if(playing != null) {
			System.out.println("Playing " + playing.getTitle());
			playing.play();
		}
	}

	/**
	 * Stops the song that is currently playing (if any) and plays the given song if it exists
	 * in the playlist. Does nothing if the song is not in the list.
	 * @param song The song to play.
	 */
	public void play(Song song) {
		stop();
		if(song != null) {
			playing = song;
			play();
		} else {
			throw new IllegalArgumentException("Cannot play null song.");
		}
	}

	/**
	 * Stops the song that is currently playing (if any) and plays the song at the given index.
	 * Does nothing if the index is invalid.
	 * @param index The index of the song to play.
	 */
	public void play(int index)
	{
		stop();
		if(index >= 0 && index < getSongList().size()) {
			play(getSongList().get(index));
		} else {
			throw new IllegalArgumentException("Invalid song index: " + index);
		}
	}

	/**
	 * Stops the song that is currently playing (if any) and plays the next song in the list. If the
	 * song is the last song in the list, then it will wrap around and start playing at song 0.
	 */
	public void playNext() {
		int current = getPlayingIndex();
		int next = current + 1;
		if(next >= getSongList().size()) {
			next = 0;
		}
		play(next);
	}

	/**
	 * Stops the song that is currently playing (if any) and plays the previous song in the list. If the
	 * song is the first song in the list, then it will wrap around and start playing the last song.
	 */
	public void playPrev() {
		int current = getPlayingIndex();
		int prev = current - 1;
		if(prev < 0) {
			prev = getSongList().size() - 1;
		}
		play(prev);
	}

	/**
	 * Stops the playlist from playing. If no songs are playing, does nothing.
	 */
	public void stop()
	{
		if(playing != null) {
			System.out.println("Stopping " + playing.getTitle());
			playing.stop();
			playing = null;
		}
	}

	/**
	 * Returns a reference to the song that is currently playing.
	 * @return The song that is currently playing, or null if no song is playing.
	 */
	public Song getPlaying()
	{
		return playing;
	}

	/**
	 * Returns the index of the song that is currently playing.
	 * @return The index of the song that is playing, or -1 if no song is playing.
	 */
	public int getPlayingIndex()
	{
		if(playing == null) {
			return -1;
		}
		return getSongList().indexOf(playing);
	}

	/**
	 * Returns whether or not a song is currently playing.
	 * @return true if a song is playing, false otherwise.
	 */
	public boolean isPlaying()
	{
		if(playing == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Sets whether the playlist will continue to play all songs in order or not. If set to true,
	 * the next song should play after the previous finishes.
	 * @param flag - specify true to turn on, false to turn off.
	 */
	public void setSequentialPlay(boolean flag)
	{
		this.sequential = flag;
	}

	/**
	 * Returns whether the playlist will continue to play all songs in order or not.
	 * @return true if turned on, false if turned off.
	 */
	public boolean isSequentialPlay()
	{
		return this.sequential;
	}
}
