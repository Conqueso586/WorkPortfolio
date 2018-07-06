import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BTree {
	private int degree;
	private BTreeNode root, s, r, z, child;
	private int nodeCount, maxKeys = 0;
	RandomAccessFile raf;
	long nodeSize;
	boolean useCache = false;
	// Cache Cache;
	Cache<BTreeNode> Cache;
	int sizeOfCache = 0;

	public BTree(int degree, File file) throws IOException {

		this.degree = degree;
		raf = new RandomAccessFile(file, "rw");
		maxKeys = 2 * degree - 1;
		int cacheSize = 1000;
		// Cache = new Cache(1000);
		sizeOfCache = cacheSize;
		// if (sizeOfCache > 0) {
		// useCache = true;
		// this.Cache = new Cache<BTreeNode>(sizeOfCache);
		// }
		raf.seek(0);
		root = new BTreeNode();
		root.isLeaf = true;
		root.numKeys = 0;
		root.current = 0;
	}

	public void insertNode(TreeObject o) throws IOException {
		r = root;
		if (r.isFull()) {
			// uh-oh, the root is full, we have to split it
			s = new BTreeNode();
			nodeCount++;
			s.current = nodeCount;
			root = s; // new root node
			s.isLeaf = false; // will have some children
			s.numKeys = 0; // for now
			s.childPointers[0] = r.current; // child is the old root node
			splitNode(s, 0); // r is split
			insertNodeNonFull(s, o); // s is clearly not full
		} else
			insertNodeNonFull(r, o);
	}

	public void insertNodeNonFull(BTreeNode x, TreeObject o) throws IOException {
		
		int i = x.numKeys - 1;
		if (x.isLeaf) {
			 //shift everything over to the "right" up to the
			 //point where the new key k should go
			while (i >= 0 && o.compareTo(x.keys[i]) < 0) {
				x.keys[i + 1] = x.keys[i];
				i--;
			}
			x.keys[i + 1] = o;
			x.numKeys++;
			diskWrite(x);
		}else{
			while (i >= 0 && o.compareTo(x.keys[i]) < 0) {
				i--;
			}
			i++;
			BTreeNode child= DiskRead(x.childPointers[i]);
			if (child.numKeys == maxKeys) {
				splitNode(x, i);
				if (o.compareTo(x.keys[i]) > 0) {
					i++;
				}
			}
			insertNodeNonFull(DiskRead(x.childPointers[i]), o);
		}
	}


	private void splitNode(BTreeNode x, int i)
			throws IOException {

		z = new BTreeNode();
		BTreeNode y = DiskRead(x.childPointers[i]);
		
		nodeCount++; // We need to keep track of the amount of nodes.
		z.current = nodeCount;
		// new node is a leaf if old node was
		z.isLeaf = y.isLeaf;
		// we since y is full, the new node must have t-1 keys
		z.numKeys = degree - 1;
		// copy over the "right half" of y into split
		for (int j = 0; j < degree - 1; j++) {
			z.keys[j] = y.keys[degree + j];
			y.keys[degree + j] = null;
	
		}
		// copy over the child pointers if y isn't a leaf
		if (!y.isLeaf) { // If not in a leaf go through the tree.
			for (int j = 0; j < degree; j++) {
				z.childPointers[j] = y.childPointers[degree + j];
				y.childPointers[degree + j] = -1;
			}
		}
		// having "chopped off" the right half of y, it now has t-1 keys
		y.numKeys = degree - 1;
		// shift everything in x over from i+1, then stick the new child in x;
		// y will half its former self as ci[x] and split will
		// be the other half as ci+1[x]
		for (int j = x.numKeys; j > i; j--) {
			x.childPointers[j + 1] = x.childPointers[j];
		}

		x.childPointers[i + 1] = z.current;
		// the keys have to be shifted over as well...
		for (int j = x.numKeys - 1; j >= i; j--) {
			x.keys[j + 1] = x.keys[j];
		}
		// ...to accomodate the new key we're bringing in from the middle
		// of y (if you're wondering, since (t-1) + (t-1) = 2t-2, where
		// the other key went, its coming into x)
		x.keys[i] = y.keys[degree - 1];
		y.keys[degree - 1] = null;
		
		x.numKeys++;

		// write everything out to disk
		diskWrite(y);
		diskWrite(z);
		diskWrite(x);

	}

	private int diskWrite(BTreeNode x) throws IOException {

		raf.seek(0);

		// Writing Meta Data
		raf.writeInt(degree);
		raf.writeInt(x.current);
		 //Size of meta data 13.
		
		raf.seek(13 + x.current * nodeSize());
		
		raf.writeBoolean(x.isLeaf);
		raf.writeInt(x.numKeys);
		raf.writeInt(x.current);

		// Writing the KeyObject
		for (int i = 0; i < x.numKeys; i++) {
			
			raf.writeLong(x.keys[i].getKey());
			raf.writeInt(x.keys[i].getFreq());
		}

		// Writing the pointers
		for (int i = 0; i < 2 * degree; i++) {
			raf.writeInt(x.childPointers[i]);

		}

		return x.current;
	}

	private BTreeNode DiskRead(long offset) throws IOException {

		raf.seek(0);

		BTreeNode node = new BTreeNode();

		// Reading the degree from the file.
		degree = raf.readInt();
		
		
		raf.seek(13 + offset * nodeSize());

		node.isLeaf = raf.readBoolean();
		node.numKeys = raf.readInt();
		node.current = raf.readInt();//Size of meta data 13.
		
		// Reading the KeyObject
		for (int i = 0; i < node.numKeys; i++) {
			node.keys[i] = new TreeObject(raf.readLong());
			node.keys[i].setFreq(raf.readInt());
		}

		// Reading the pointers
		for (int i = 0; i < 2 * degree; i++) {
			node.childPointers[i] = raf.readInt();
		}

		// Reading MetaData

		return node;
	}

	private long nodeSize() {
		int keyObjectSize = Long.BYTES + Integer.BYTES;
		int pointer = Integer.BYTES;
		int numPointers = 2 * degree;
		int numKeys = 2 * degree - 1;
		int current = Integer.BYTES;
		int degreeSize = Integer.BYTES;

		int size = (keyObjectSize * numKeys) + (pointer * numPointers)
				 + current + 4 + 1;
		return size;
	}
	
	private Object traverseTree(BTreeNode x) throws IOException {
		
		for (int i = 0; i < x.childPointers.length; i++) {;
		return traverseTree(DiskRead(x.childPointers[i]));	
		}
		return traverseTree(DiskRead(x.childPointers[x.childPointers.length - 1]));
	}
	
	private File printDebugInfo(File travFile){
		//Call the traversal and print to the travFile all the debug stuff.
		return travFile;
	}

	/**
	 * B-TREE-SEARCH(x, k) method from book
	 * @return returns a TreeObject
	 */
//	public TreeObject bTreeSearch(BTreeNode x, TreeObject o) {
//		
//		int i = 0;
//		while (i < x.numKeys && o.compareTo(x.keys[i]) > 0) {
//			i++;
//		}
//		if (i < x.numKeys && o.compareTo(x.keys[i]) == 0) {
//			return x.keys[i];
//		} else if (x.isLeaf) {	
//			return null;
//		}
//		else{
//				BTreeNode child = DiskRead(x.childPointers[i]);
//			    return bTreeSearch(child, o);
//		}}
//	}
	
	
/*	
	public TreeObject bTreeSearch(BTreeNode x, TreeObject o) {
		
		int i = 0;
		while (i < x.numKeys && o.compareTo(x.keys[i]) > 0) {
			i++;
		}
		if (i < x.numKeys && o.compareTo(x.keys[i]) == 0) {
			return x.keys[i];
		} else if (x.isLeaf) {	
			
			return null;
			BTreeNode child = DiskRead(x.childPointers[i]);
		}else {
				if(useCache){
					readCache(x.childPointers[i]);
				}else{
					DiskRead(x.childPointers[i]);
				}
				return bTreeSearch(child,o);
			}
		}
	
	public BTreeNode readCache(BTreeNode x){
		  if (Cache.removeObject(x)){
		  		Cache.addObject(x);
		  		
		  }else{
		  		x = DiskRead(x);
			  	BTreeNode dump = Cache.addObject(x);
		  		if (dump!=null){
		  			diskWrite(dump);
		  		}
		  }
		  return x;
		
	}
	
	public void useCache(BTreeNode x){

	  if (Cache.removeObject(x)){
	  		Cache.addObject(x);
	  }else{
	  		BTreeNode dump = Cache.addObject(x);
	  		if (dump!=null){
	  			diskWrite(dump);
	  		}
	  }
	 
	}	
	*/

	private class BTreeNode {

		TreeObject[] keys;
		public int current = -1; // Keeps track of were we are at.
		int[] childPointers; // This will be useful for a couple of things
		int numKeys; // So we know when we are full.

		boolean isLeaf; // We will have to set this when we reach a leaf.

		// Not sure if we need both constructors lol just shotgunning this one.
		BTreeNode() {
			keys = new TreeObject[maxKeys];
			childPointers = new int[2 * degree];
			for(int i = 0; i < 2 * degree; i++){
				childPointers[i] = -1;
			}
			numKeys = 0;
		}

//		BTreeNode(int i) {
//			keys = new TreeObject[2 * i - 1];
//			childPointers = new int[2 * i];
//			for(int j = 0; j < 2 * degree; j++){
//				childPointers[j] = -1;
//			}
//			numKeys = 0;
//		}

		TreeObject getKeys(int i) {
			return keys[i];

		}

		void setKey(TreeObject k, int i) {
			keys[i] = k;
		}

		boolean isFull() {
			if (numKeys == keys.length) {
				return true;
			} else {
				return false;
			}
		}
	}
}

