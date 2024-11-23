package BTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import Main.*;

public class BTree {
	protected static final String FAILED_TO_OPEN_OUTPUT_FILE = "Failed to Open Output File";
	protected static final String FAILED_TO_INIT_SEEK_0 = "Failed to Init Seek 0";
	protected static final String FAILED_TO_WRITE_ROOT_NODE = "Failed to Write Root Node";
	protected static final String DISK_WRITE_FAILED_AT = "Disk Write Failed at: ";
	protected static final String FAILED_TO_SEEK_TO_0_POSITION = "Failed to Seek to 0 position";
	protected static final String FAILED_TO_READ_DEGREE = "Failed to Read Degree";
	protected static final String FAILED_TO_SEEK_TO_POSITION = "Failed to Seek to position: ";
	protected static final String FAILED_TO_READ = "Failed to Read";
	protected static final String FAILED_TO_READ_KEY_OBJECT = "Failed to Read KeyObject";
	protected static final String FAILED_TO_READ_POINTERS = "Failed to Read Pointers";
	protected static final String FAILED_TO_RETRIEVE_ROOT_NODE = "Failed to Retrieve Root Node";
	
	private int degree;
	private BTreeNode root;
	private int nodeCount;
	private final int maxKeys;
	RandomAccessFile raf;
	//long nodeSize;
	//boolean useCache = false;
	//Cache Cache;
	//Cache<BTreeNode> Cache;
	int sizeOfCache;
	private int keyCount;

	public BTree(int degree, File file){

		this.degree = degree;
        try {
            raf = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
			GeneBankCreateBTree.Fail(FAILED_TO_OPEN_OUTPUT_FILE);
        }
        maxKeys = 2 * degree - 1;
		// Cache = new Cache(1000);
		sizeOfCache = 1000;
		// if (sizeOfCache > 0) {
		// useCache = true;
		// this.Cache = new Cache<BTreeNode>(sizeOfCache);
		// }
        try {
            raf.seek(0);
        } catch (IOException e) {
			GeneBankCreateBTree.Fail(FAILED_TO_INIT_SEEK_0);
        }
        root = new BTreeNode();
		root.isLeaf = true;
		root.numKeys = 0;
		root.current = 0;
	}

	public void insertNode(TreeObject o)  {
		BTreeNode r = root;
		if (r.isFull()) {
			// uh-oh, the root is full, we have to split it
			BTreeNode s = new BTreeNode();
			nodeCount++;
			s.current = nodeCount;
			root = s; // new root node
			s.isLeaf = false; // will have some children
			s.numKeys = 0; // for now
			s.childPointers[0] = r.current; // child is the old root node
			splitNode(s, 0, r); // r is split
			insertNodeNonFull(s, o); // s is clearly not full
		} else
			insertNodeNonFull(r, o);
	}

	private void insertNodeNonFull(BTreeNode x, TreeObject o) {
		boolean dup = false;
		int i = x.numKeys - 1;
		for (int a = 0; a < x.numKeys; a++) {
			if (x.keys[a].compareTo(o) == 0) {
				x.keys[a].increaseFrequency();
				dup = true;
				diskWrite(x);
				break;
			}
		}
		if (!dup) {
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
			} else {
				while (i >= 0 && o.compareTo(x.keys[i]) < 0) {
					i--;
				}
				i++;
				BTreeNode child = DiskRead(x.childPointers[i]);
				if (child.numKeys == maxKeys) {
					splitNode(x, i, child);
					if (o.compareTo(x.keys[i]) > 0) {
						i++;
					}
				}
				insertNodeNonFull(DiskRead(x.childPointers[i]), o);
			}
		}
	}

	private void splitNode(BTreeNode x, int i, BTreeNode y) {
		//printTree();
		BTreeNode z = new BTreeNode();
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
		// ...to accommodate the new key we're bringing in from the middle
		// of y (if you're wondering, since (t-1) + (t-1) = 2t-2, where
		// the other key went, it's coming into x)
		x.keys[i] = y.keys[degree - 1];
		y.keys[degree - 1] = null;

		x.numKeys++;

		// write everything out to disk
		diskWrite(y);
		diskWrite(z);
		diskWrite(x);
		//printTree();
	}

	public void WriteRoot() {
		try {
			raf.seek(0);
			raf.writeInt(degree);
			raf.writeInt(root.current);
		} catch (IOException e) {
			GeneBankCreateBTree.Fail(FAILED_TO_WRITE_ROOT_NODE);
		}
	}

	private void diskWrite(BTreeNode x) {
        try {
            raf.seek(0);

            // Writing Meta Data
            raf.writeInt(degree);
            raf.writeInt(x.current);
            //Size of metadata 13.

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
        } catch (IOException e) {
			GeneBankCreateBTree.Fail(DISK_WRITE_FAILED_AT + x.toString() + e.getCause());
        }
    }

	private BTreeNode DiskRead(long offset) {
		BTreeNode node = new BTreeNode();
			try {
				raf.seek(0);
			} catch (IOException e) {
				GeneBankCreateBTree.Fail(FAILED_TO_SEEK_TO_0_POSITION);
			}
			// Reading the degree from the file.
			try {
				degree = raf.readInt();
			} catch (IOException e) {
				GeneBankCreateBTree.Fail(FAILED_TO_READ_DEGREE);
			}

			try {
				//Size of metadata 13.
				raf.seek(13 + offset * nodeSize());
			} catch (IOException e) {
				GeneBankCreateBTree.Fail(FAILED_TO_SEEK_TO_POSITION + (13 + offset * nodeSize()));
			}
			try {
				node.isLeaf = raf.readBoolean();
				node.numKeys = raf.readInt();
				node.current = raf.readInt();
			} catch (IOException e) {
				GeneBankCreateBTree.Fail(FAILED_TO_READ);
			}
			// Reading the KeyObject
			try {
				for (int i = 0; i < node.numKeys; i++) {
					node.keys[i] = new TreeObject(raf.readLong());
					node.keys[i].setFreq(raf.readInt());
				}
			} catch (IOException e) {
				GeneBankCreateBTree.Fail(FAILED_TO_READ_KEY_OBJECT);
			}

			try {
				// Reading the pointers
				for (int i = 0; i < 2 * degree; i++) {
					node.childPointers[i] = raf.readInt();
				}
			} catch (IOException e) {
				GeneBankCreateBTree.Fail(FAILED_TO_READ_POINTERS);
			}
		return node;
	}

	private long nodeSize() {
		int keyObjectSize = Long.BYTES + Integer.BYTES;
		int pointer = Integer.BYTES;
		int numPointers = 2 * degree;
		int numKeys = 2 * degree - 1;
		int current = Integer.BYTES;

        return ((long) keyObjectSize * numKeys) + ((long)pointer * numPointers) + current + 4 + 1;
	}

	public void printTree(){
		System.out.println("PRINTING TREE -----------------------------");
		keyCount = 0;
		System.out.println("Total Keys in Tree: " + traverseTree(root));
	}

	private int traverseTree(BTreeNode parentNode) {
		System.out.print("Current Node: " + parentNode.current);
		System.out.print("\t IsLeaf: " + parentNode.isLeaf);
		System.out.println("\t NumKeys: " + parentNode.numKeys);
		System.out.print("Keys: { ");
		for(int i = 0; i < parentNode.numKeys; i++){
			System.out.print(parentNode.keys[i].getKey() + " ");
			keyCount++;
		}
		System.out.println("}");
		System.out.println();
		if(!parentNode.isLeaf){
			System.out.print("Children: ");
		for(int i = 0; i < parentNode.childPointers.length; i++)
			if(parentNode.childPointers[i] != -1)System.out.print(parentNode.childPointers[i] + ", ");
		System.out.println();
		System.out.println();
		for (int i = 0; i < parentNode.childPointers.length; i++) {
			if(parentNode.childPointers[i] != -1){
				traverseTree(DiskRead(parentNode.childPointers[i]));
			}
		}
		}
		return keyCount;
	}

	private BTreeNode getRoot() {
		int rootNum;
		BTreeNode rootNode = null;
		try {
            raf.seek(0);
			raf.readInt();
            rootNum = raf.readInt();
			rootNode =  DiskRead(rootNum);
        } catch (IOException e) {
			GeneBankCreateBTree.Fail(FAILED_TO_RETRIEVE_ROOT_NODE);
		}
		return rootNode;
	}

	public TreeObject SearchTreeForObject(TreeObject o){
		return bTreeSearch(getRoot(), o);
	}

	/**
	 * B-TREE-SEARCH(x, k) method from book
	 * @return returns a TreeObject
	 */
	private TreeObject bTreeSearch(BTreeNode x, TreeObject o){
		int i = 0;
		while (i < x.numKeys && o.compareTo(x.keys[i]) > 0) {
			i++;
		}
		if (i < x.numKeys && o.compareTo(x.keys[i]) == 0) {
			return x.keys[i];
		} else if (x.isLeaf) {	
			return null;
		}
		else{
            BTreeNode child;
			child = DiskRead(x.childPointers[i]);

            return bTreeSearch(child, o);
		}
	}

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

//		TreeObject getKeys(int i) {
//			return keys[i];
//		}
//
//		void setKey(TreeObject k, int i) {
//			keys[i] = k;
//		}

		boolean isFull() {
            return numKeys == keys.length;
		}
	}



}

