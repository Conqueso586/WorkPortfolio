import java.util.*;

/**
 * Class for sorting a list that implements the IndexedUnorderedList interface
 * using either natural order or a Comparator. Sorting methods must be
 * implemented using iterators, not using indexing.
 * 
 * @author Connor Nagel
 */
public class Sort {
	/**
	 * Sorts a list that implements the IndexedUnorderedList interface using the
	 * natural ordering of list elements.
	 * 
	 * ************DO NOT MODIFY THIS METHOD ***********
	 * 
	 * @param <T>
	 *            - class of elements in list, must extend Comparable interface
	 * @param list
	 *            - IndexedOrderedList<T> implementation
	 * @see IndexedUnorderedList
	 */
	public static <T extends Comparable<T>> void sort(
			IndexedUnorderedList<T> list) {
		int first = 0;
		int last = list.size() - 1;

		mergesort(list, first, last);
	}

	/**
	 * Recursive Merge sort algorithm. Must not use direct indexing of list,
	 * must use only iterators.
	 * 
	 * @param list
	 *            - IndexedUnorderedList<T> implementation
	 * @param start
	 *            - int value
	 * @param end
	 *            - int value
	 */
	private static <T extends Comparable<T>> void mergesort(
			IndexedUnorderedList<T> list, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergesort(list, start, mid);
			mergesort(list, mid + 1, end);
			mergeList(list, start, mid, end);

		}

	}

	private static <T extends Comparable<T>> IndexedUnorderedList<T> mergeList(
			IndexedUnorderedList<T> list, int start, int mid, int end) {

		ListIterator<T> itr1 = list.listIterator(start);
		ListIterator<T> itr2 = list.listIterator(mid + 1);
		IndexedUnorderedList<T> temp = new WrappedDLL<T>();

		T element1 = itr1.next();
		T element2 = itr2.next();

		do {
			if (element1.compareTo(element2) < 0) {
				temp.add(element1);
				element1 = itr1.nextIndex() < mid + 1 ? itr1.next() : null;
			} else {
				temp.add(element2);
				element2 = itr2.nextIndex() < end + 1 ? itr2.next() : null;
			}
		} while (element1 != null && element2 != null);

		if (element1 != null) {
			temp.add(element1);
		}
		if (element2 != null) {
			temp.add(element2);
		}
		while (itr1.nextIndex() < mid + 1) {
			temp.add(itr1.next());
		}
		while (itr2.nextIndex() < end + 1) {
			temp.add(itr2.next());
		}

		int index = 0;

		for (ListIterator<T> i = list.listIterator(start); i.nextIndex() < end + 1;) {
			i.next();
			i.set(temp.get(index));
			index++;
		}
		return temp;
	}

	/**
	 * Sorts a list that implements the IndexedUnorderedList interface using
	 * provided Comparator.
	 * 
	 * ************DO NOT MODIFY THIS METHOD ***********
	 * 
	 * @param <T>
	 *            - class of elements in list, must extend Comparable interface
	 * @param list
	 *            - IndexedOrderedList<T> implementation
	 * @param c
	 *            - Comparator<T> object
	 * @see IndexedUnorderedList
	 */
	public static <T extends Comparable<T>> void sort(
			IndexedUnorderedList<T> list, Comparator<T> c) {
		int first = 0;
		int last = list.size() - 1;
		mergesort(list, first, last, c);
	}

	/**
	 * Recursive Merge sort algorithm using Comparator. Must not use direct
	 * indexing of list, must use only iterators to implement sort.
	 * 
	 * @param list
	 *            - IndexedUnorderedList<T> implementation
	 * @param start
	 *            - int value
	 * @param end
	 *            - int value
	 * @param c
	 *            - Comparator<T> object
	 */
	private static <T extends Comparable<T>> void mergesort(
			IndexedUnorderedList<T> list, int start, int end, Comparator<T> c) {

		if (start < end) {
			int mid = (start + end) / 2;
			mergesort(list, start, mid, c);
			mergesort(list, mid + 1, end, c);
			mergeList(list, start, mid, end, c);

		}

	}

	private static <T extends Comparable<T>> IndexedUnorderedList<T> mergeList(
			IndexedUnorderedList<T> list, int start, int mid, int end,
			Comparator<T> c) {
		ListIterator<T> itr1 = list.listIterator(start);
		ListIterator<T> itr2 = list.listIterator(mid + 1);
		IndexedUnorderedList<T> temp = new WrappedDLL<T>();

		T element1 = itr1.next();
		T element2 = itr2.next();
		do {
			if (c.compare(element1, element2) < 0) {
				temp.add(element1);
				element1 = itr1.nextIndex() < mid + 1 ? itr1.next() : null;
			} else {
				temp.add(element2);
				element2 = itr2.nextIndex() < end + 1 ? itr2.next() : null;
			}
		} while (element1 != null && element2 != null);

		if (element1 != null) {
			temp.add(element1);
		}
		if (element2 != null) {
			temp.add(element2);
		}
		while (itr1.nextIndex() < mid + 1) {
			temp.add(itr1.next());
		}
		while (itr2.nextIndex() < end + 1) {
			temp.add(itr2.next());
		}

		int index = 0;

		for (ListIterator<T> i = list.listIterator(start); i.nextIndex() < end + 1;) {
			i.next();
			i.set(temp.get(index));
			index++;
		}
		return temp;
	}

}
