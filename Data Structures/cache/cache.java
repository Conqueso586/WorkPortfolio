import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class cache<T> {

	private int maxCache1size, maxCache2size, cache1size, cache2size;

	private Object cache1HIT = false;
	private Object cache2HIT = false;

	LinkedList<Object> cache1 = null;
	LinkedList<Object> cache2 = null;
	File file = null;
	Scanner scan;

	private double globalCacheHitratio, cache1Hitratio, cache2Hitratio = 0;
	private double cache1Hits, cache2Hits = 0;
	private double cache1References, cache2References = 0;

	public cache(int size, String inputFile) {
		file = new File(inputFile);
		cache1 = new LinkedList<Object>();
		maxCache1size = size;
		cache1size = 0;
		cache1HIT = false;
		fileScan();
	}

	public cache(int sizeCache1, int sizeCache2, String inputFile) {
		file = new File(inputFile);

		cache1 = new LinkedList<Object>();
		cache2 = new LinkedList<Object>();

		maxCache1size = sizeCache1;
		maxCache2size = sizeCache2;
		cache1size = 0;
		cache2size = 0;
		cache1HIT = false;
		cache2HIT = false;
	}

	public Object getObject(int i) {
		Object object = null;
		if (i > maxCache1size) {
			object = cache2.get(i);
		} else {
			object = cache1.get(i);
		}
		return object;

	}

	public Object evaluateObject(Object s) {

		cache1References++;
		cache2References++;
		if (cache2 == null) {
			cache1HIT = searchCache(1, s);
			if (cache1HIT != null) {
				addObject(s);
				cache1Hits++;
				return cache1HIT;
			} else {
				addObject(s);
				return s;
			}
		} else {
			cache1HIT = searchCache(1, s);

			cache2HIT = searchCache(2, s);

			if (cache1HIT != null) {
				addObject(s);
				cache1Hits++;
				cache2Hits++;
				return cache1HIT;

			} else if (cache1HIT == null && cache2HIT != null) {
				addObject(s);
				cache2Hits++;
				return cache2HIT;

			} else if (cache1HIT == null && cache2HIT == null) {
				addObject(s);
				return s;

			} else {
				return null;
			}
		}

	}

	public Object addObject(Object objectElement) {
		if (cache2 != null) {
			if (cache1size == maxCache1size) {
				cache1.removeLast();
				cache1size--;
			}
			if (cache2size == maxCache2size) {
				cache2.removeLast();
				cache2size--;
			}
			cache1.addFirst(objectElement);
			cache2.addFirst(objectElement);
			cache1size++;
			cache2size++;
			return objectElement;
		} else {
			if (cache1size == maxCache1size) {
				cache1.removeLast();
				cache1size--;
			}
			cache1.addFirst(objectElement);
			cache1size++;
			return objectElement;
		}
	}

	private Object searchCache(int cacheNum, Object s) {
		int i = 0;
		if (cacheNum == 1) {
			for (i = 0; i < cache1size; i++) {
				if (cache1.get(i).equals(s)) {
					cache1size--;
					return cache1.remove(i);
				}
			}
		} else if (cacheNum == 2) {
			for (i = 0; i < cache2size; i++) {
				if (cache2.get(i).equals(s)) {
					cache2size--;
					return cache2.remove(i);
				}
			}
		} else {

		}

		return null;
	}

	public void calculate1CacheHitratio() {
		System.out.println("***********************************************************************************");
		System.out.println("Cache hits: " + cache1Hits + "\nReferences to Cache: " + cache1References);
		System.out.println("***********************************************************************************");

		globalCacheHitratio = cache1Hits / cache1References;

	}

	public void calculate2CacheHitratio() {
		globalCacheHitratio = (cache1Hits + cache2Hits) / cache1References;
		cache1Hitratio = cache1Hits / cache1References;
		cache2Hitratio = cache2Hits / cache2References;
		System.out.println("***********************************************************************************");
		System.out.println("Cache 1 hits: " + cache1Hits + "\n References to Cache 1: " + cache1References
				+ "\n Cache 1 Hit Ratio: " + cache1Hitratio);
		System.out.println("Cache 2 hits: " + cache2Hits + "\n References to Cache 2: " + cache2References
				+ "\n Cache 2 Hit Ratio: " + cache2Hitratio);
		System.out.println("Global Hit Ratio:" + globalCacheHitratio);
		System.out.println("***********************************************************************************");
	}

	public void clearCache() {
		if (cache2 == null) {
			cache1 = new LinkedList<Object>();
		} else {
			cache1 = new LinkedList<Object>();
			cache2 = new LinkedList<Object>();
			cache2size = 0;
		}
		cache1size = 0;
	}

	public void printCache() {
		int i = 0;
		for (i = 0; i < cache1size; i++) {
			System.out.print(i + ": " + cache1.get(i).toString() + " ");
		}
		System.out.println();
		i = 0;
		for (i = 0; i < cache2size; i++) {
			System.out.print(i + ": " + cache2.get(i).toString() + " ");
		}
	}

	private void fileScan() {
		try {
			scan = new Scanner(file);

			while (scan.hasNextLine()) {
				Scanner s2 = new Scanner(scan.nextLine());
				while (s2.hasNext()) {
					Object s = s2.next();

					evaluateObject(s);

				}
				s2.close();
			}
			if (cache2 == null) {
				calculate1CacheHitratio();
			} else {
				calculate2CacheHitratio();
			}
		} catch (FileNotFoundException e) {
			System.err.print(e + "The given file name was not found");
		}
	}

	public static void main(String[] args) {

		try {
			if (args.length == 2) {
				System.out.println("Cache created of size: " + args[0]);
				new cache<Object>(Integer.parseInt(args[0]), args[1]);
			} else if (args.length == 3) {
				System.out.println("Cache1 size: " + args[0] + "\nCache2 size: " + args[1]);

				new cache<Object>(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
			} else {
				System.out.println(
						"Usage: cacheTest <int sizeOfcache>, <String fileName> or cacheTest <int sizeOfcache1>, <int sizeOfcache2>, <String fileName>");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(
					"Usage: cacheTest <int sizeOfcache>, <String fileName> or cacheTest <int sizeOfcache1>, <int sizeOfcache2>, <String fileName>");
		}

	}

}
