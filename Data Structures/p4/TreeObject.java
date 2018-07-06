public class TreeObject implements Comparable<TreeObject>{

	private Long key;
	private int freq = 1;
	
	TreeObject(Long o){
		key = o;
	}
	
	public void increaseFrequency(){
		freq++;
	}
	
	public String toString(){ 
		return key.toString();
	}
	
	public int getFreq(){
		return freq;
	}
	
	public void setFreq(int f){
		this.freq = f;
}
	
	public Long getKey(){
		return (Long) key;
	}
	
	public boolean equals(TreeObject o){
		return o.key.equals(key);
	}

	public int compareTo(TreeObject o) {
		return key.compareTo(o.key);
	}

}
