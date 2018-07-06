import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

/**
 * A test class for validating correctness of the <code>PlayList</code> class.
 * This does not check everything, but it will check the basic methods to make
 * sure everything is on the right track.
 *
 * @author CS121 Instructors
 */
public class PlayListTest
{
private static final String testName = "Test List";
	private static final String[] songTitles = {"Mary Had a Little Lamb", "Yankee Doodle"};
	private static final String[] songArtists = {"Mother Goose", "George Washington"};
	private static final int[] songTimes = {10, 20};
	private static final String[] timeStrings = {"00:10", "00:20"};
	private static final String[] songFiles = {"sounds/hitchcock.wav", "sounds/classical.wav"};
	private static int status = 0;

	/**
	 * Runs test cases.
	 * @param args (unused)
	 */
	public static void main(String[] args)
	{
		// TODO: There will be errors in this class until you write the Song and PlayList classes.
		// You don't need to AND SHOULD NOT change this class at all.

		Song[] testSongs = new Song[songTitles.length];
		for(int i = 0; i < testSongs.length; i++) {
			testSongs[i] = new Song(songTitles[i], songArtists[i], songTimes[i], songFiles[i]);
		}

		PlayList playList = new PlayList(testName);

		String name = playList.getName();
		if(!name.equals(testName)) {
			fail("playList.getName()", testName, name);
		} else {
			pass("playList.getName()");
		}

		int playCount = playList.getPlayCount();
		if(playCount != 0) {
			fail("playList.getPlayCount()", Integer.toString(0), Integer.toString(playCount));
		} else {
			pass("playList.getPlayCount()");
		}

		playList.addSong(testSongs[0]);

		int numSongs = playList.getNumSongs();
		if(numSongs != 1) {
			fail("playList.addSong()", "Size of playList should be " + Integer.toString(1),
					"Size of playList is " + Integer.toString(numSongs));
		} else {
			pass("playList.addSong()");
		}


		try {
			playList.removeSong(1); // should fail to remove song.

			numSongs = playList.getNumSongs();
			if(numSongs != 1) {
				fail("playList.removeSong(1) - numSongs after remove", Integer.toString(1), Integer.toString(numSongs));
			} else {
				pass("playList.removeSong(1)");
			}
		} catch (IndexOutOfBoundsException e) {
			fail("playList.removeSong(1) - Throws exception! Don't forget to check if id is in range before removing.", "do nothing", "throws IndexOutOfBoundsException");
		}


		playList.removeSong(0);

		numSongs = playList.getNumSongs();
		if(numSongs != 0) {
			fail("playList.removeSong()", "Size of playList should be " + Integer.toString(1),
					"Size of playList is " + Integer.toString(numSongs));
		} else {
			pass("playList.removeSong()");
		}

		for(Song s : testSongs) {
			playList.addSong(s);
		}
		ArrayList<Song> songs = playList.getSongList();
		if(songs.size() != playList.getNumSongs()) {
			fail("playList.getSongList()",  "Size of copy should be " + Integer.toString(testSongs.length),
					"Size of copy is " + Integer.toString(songs.size()));
		} else {
			pass("playList.getSongList()");
		}

		// Testing toString
		String result = playList.toString();
		StringBuilder builder = new StringBuilder();
		builder.append("\n------------------\n");
		builder.append(testName + " (" + testSongs.length + " songs)\n");
		builder.append("------------------\n");
		for(int i = 0; i < testSongs.length; i++) {
			builder.append("(" + i + ") ");
			builder.append(String.format("%-20s %-25s %-25s %-20s\n",
						songTitles[i], songArtists[i], timeStrings[i], songFiles[i]));
		}
		builder.append("------------------\n");

		String expected = builder.toString();
		if(!equalsIgnoreWhitespace(result, expected)) {
			fail("song.toString()", expected, "\n" + result);
		} else {
			pass("song.toString()");
		}


		// Testing empty list toString
		playList.removeSong(1);
		playList.removeSong(0);

		result = playList.toString();
		builder = new StringBuilder();
		builder.append("------------------\n");
		builder.append(testName + " (" + 0 + " songs)\n");
		builder.append("------------------\n");
		builder.append("There are no songs.\n");
		builder.append("------------------\n");
		expected = builder.toString();

		if(!result.equals(expected)) {
			fail("song.toString() on empty list", "\n" + expected, "\n" + result);
		} else {
			pass("song.toString() on empty list");
		}

		System.exit(status);
	}

	public static void fail(String methodName, String expected, String actual)
	{
		System.err.println("FAILED: " + methodName);
		System.err.println("    --> expected: " + expected);
		System.err.println("    -->   actual: " + actual);
		status = 1;
	}

	public static void pass(String methodName)
	{
		System.out.println("PASSED: " + methodName);
	}

	public static boolean equalsIgnoreWhitespace(String one, String two)
	{
		return one.replaceAll("\\s+","").equalsIgnoreCase(two.replaceAll("\\s+",""));
	}
}
