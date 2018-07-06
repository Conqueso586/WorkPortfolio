

public class HashObject extends Object {

	private int freqCount = 0;
	public Object hashObject = null;
	int hashCode;

	public HashObject(Object objectKey) {
		hashObject = objectKey;
		hashCode = hashObject.hashCode();

	}

	public boolean equals(HashObject comparedObject) {
		if(comparedObject.getKey() instanceof Integer){
			if (Integer.parseInt(this.toString()) == Integer.parseInt(comparedObject.toString())) {

				return true;
				}
			 else {
				return false;
			}
		}else if(comparedObject.getKey() instanceof Long ){
			if(Long.parseLong(this.toString()) == Long.parseLong(comparedObject.toString())){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(this.toString().equalsIgnoreCase(comparedObject.toString())){
				return true;
			}else{
				return false;
			}
		}

	}

	@Override
	public String toString() {

		return hashObject.toString();
	}

	public Object getKey() {

		return this.hashObject;
	}

	public void increaseFreq() {
		freqCount++;
	}

	public int getFreq() {
		return freqCount;
	}
}
