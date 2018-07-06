/**
 * A test class for validating correctness of the <code>Song</code> class.
 * This does not check everything, but it will check the basic methods to make
 * sure everything is on the right track.
 *
 * @author CS121 Instructors
 */
public class SongTest
{
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
		// TODO: There will be errors in this class until you write the Song class.
		// You don't need to AND SHOULD NOT change this class at all.

		Song song = new Song(songTitles[0], songArtists[0], songTimes[0], songFiles[0]);

		// Testing getters (and constructor)
		String title = song.getTitle();
		if(!title.equals(songTitles[0])) {
			fail("song.getTitle()", songTitles[0], title);
		} else {
			pass("song.getTitle()");
		}

		String artist = song.getArtist();
		if(!artist.equals(songArtists[0])) {
			fail("song.getArtist()", songArtists[0], artist);
		} else {
			pass("song.getArtist()");
		}

		int time = song.getPlayTime();
		if(time != songTimes[0]) {
			fail("song.getPlayTime()", Integer.toString(songTimes[0]), Integer.toString(time));
		} else {
			pass("song.getPlayTime()");
		}

		String file = song.getFileName();
		if(!file.equals(songFiles[0])) {
			fail("song.getFileName()", songFiles[0], file);
		} else {
			pass("song.getFileName()");
		}

		// Testing setters
		song.setTitle(songTitles[1]);
		song.setArtist(songArtists[1]);
		song.setPlayTime(songTimes[1]);
		song.setFileName(songFiles[1]);

		title = song.getTitle();
		if(!title.equals(songTitles[1])) {
			fail("song.setTitle()", songTitles[1], title);
		} else {
			pass("song.setTitle()");
		}

		artist = song.getArtist();
		if(!artist.equals(songArtists[1])) {
			fail("song.setArtist()", songArtists[1], artist);
		} else {
			pass("song.setArtist()");
		}

		time = song.getPlayTime();
		if(time != songTimes[1]) {
			fail("song.setPlayTime()", Integer.toString(songTimes[1]), Integer.toString(time));
		} else {
			pass("song.setPlayTime()");
		}

		file = song.getFileName();
		if(!file.equals(songFiles[1])) {
			fail("song.setFileName()", songFiles[1], file);
		} else {
			pass("song.setFileName()");
		}

		// Testing toString
		String result = song.toString();
		String expected = String.format("%-20s %-25s %-25s %-20s", title, artist, timeStrings[1], file);
		if(!result.equals(expected)) {
			fail("song.toString()", expected, result);
		} else {
			pass("song.toString()");
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
}
