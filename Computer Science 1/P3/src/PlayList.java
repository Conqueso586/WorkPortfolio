
import java.util.ArrayList;
/**
 * @author cnagel
 *
 */
public class PlayList {

	private String name = "";
	private int playCount = 0;
	ArrayList<Song> songList = new ArrayList<Song>();
	
public PlayList(String name){
		this.name = name;
		playCount = 0;
		songList = new ArrayList<Song>();
		}
	


/**
 * @return name of playlist
 */
public String getName(){
	return name;
}
/**
 * @return songs contained in playlist
 */
public ArrayList<Song> getSongList(){
	return songList;
}
/**
 * @return playcount of playlist songs
 */
public int getPlayCount(){
	return playCount;
}
/**
 * @param sets name of playlist
 */
public void setName(String name){
	this.name = name;
}
/**
 * @param adds song to playlist
 */
public void addSong(Song s)
{
   songList.add(s);
}
/**
 * @param removes song from playlist in location 'id'
 */
public void removeSong(int id)
{
    if(id < getNumSongs()){
    	songList.remove(id);
    }
}
/**
 * @return number of songs on playlist
 */
public int getNumSongs()
{
     int size = songList.size();
     return size;
}

/**
 * Plays all songs on playlist
 */
public void playAll()
{
   for(Song i: songList){
	i.play();
	  playCount++; 
   } 
}
public String toString(){
	if (getNumSongs() > 0){
	return "------------------\n" + "Test list " +"(" + getNumSongs() + " songs)" + "\n"
	+ "------------------\n" + songList.toString();}
	else {return "------------------\n" + "Test list " +"(" + getNumSongs() + " songs)" + "\n"
			+ "------------------\n" + "There are no songs." + "\n------------------\n" ;}
}
}