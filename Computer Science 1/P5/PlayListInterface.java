import java.util.ArrayList;

/**
 * @author CS121 Instructors
 *
 */
public interface PlayListInterface
{
	/**
	 * Sets the name of this playlist.
	 * @param name The name.
	 */
	public void setName(String name);

	/**
	 * Returns the name of this playlist.
	 * @return The name.
	 */
	public String getName();

	/**
	 * Returns the number of times the playlist has been played.
	 * @return The number of times.
	 */
	public int getPlayCount();

	/**
	 * Adds the given song to the end of this playlist.
	 * @param s The song to add.
	 */
	public void addSong(Song s);

	/**
	 * Removes the song with the given id from this playlist. Does nothing
	 * if the id is invalid.
	 * @param id The id of the song to remove.
	 */
	public void removeSong(int id);

	/**
	 * Returns the number of songs in this playlist.
	 * @return The number of songs.
	 */
	public int getNumSongs();

	/**
	 * Added for P5
	 * @return The total play time (in seconds)
	 */
	public int getTotalPlayTime();

	/**
	 * Returns the songlist. This is not a copy, so be careful!
	 * @return A reference to the songlist.
	 */
	public ArrayList<Song> getSongList();

	/**
	 * Added for P5.
	 * Returns an array of all the songs in the playlist.
	 * @return An array of songs.
	 */
	public Song[] getSongArray();

	/**
	 * Added for P5.
	 * Moves the song at the given index to the previous index in the list (index - 1). All other elements
	 * in the list will be shifted. Does nothing if the list is empty or the given index is already
	 * the first in the list (e.g. 0).
	 * @param index The index of the song to move.
	 * @return The new index of the song (after the move).
	 */
	public int moveUp(int index);

	/**
	 * Added for P5.
	 * Moves the song at the given index to the next index in the list (index + 1). All other elements
	 * in the list will be shifted. Does nothing if the list is empty or the given index is already
	 * the last in the list (e.g. songList.size()-1).
	 * @param index The index of the song to move.
	 * @return The new index of the song (after the move).
	 */
	public int moveDown(int index);

	/**
	 * Added for P5.
	 * Returns a 2 dimensional musical square. The dimension of the square is calculated based on the number of
	 * songs in the playlist. If the number of songs in the list are not a square number, then the remaining slots
	 * are filled starting with the first song.
	 *
	 * <p>
	 * For example, if the playlist contains 7 songs, the generated array would contain songs in the following
	 * order.
	 * </p>
	 *
	 * <pre>
	 * song0 song1 song2
	 * song3 song4 song5
	 * song6 song0 song1
	 * </pre>
	 * @return - the 2 dimensional array of songs.
	 */
	public Song[][] getMusicSquare();

}
