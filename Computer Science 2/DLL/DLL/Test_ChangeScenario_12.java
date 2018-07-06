



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Testing for IndexedUnorderedList interface implementation: 
 * Tests for Change Scenario 12: [A] -> remove(A) -> [] - empty list
 * 
 * @author Connor Nagel
 */
public class Test_ChangeScenario_12
{
	// List running tests on
	IndexedUnorderedList<Integer> list;
	
	//****** Constants used in tests *****************
			// No first element in list
			private static final Integer FIRST = null;
			// No last element in list 
			private static final Integer LAST = null;	
			// Invalid index outside of list 
			private static final int INVALID_INDEX = 0;
			// Only valid indexes where can add a new element 
			private static final int VALID_ADD_INDEX = 0; 
			// Invalid indexes outside range of where can add elements
			private static final Object[][] INVALID_ADD_INDEXES = { {-1}, {1} };
			// Size of the list 
			private static final int SIZE = 0; 
	
	//****** Don't change these constants *****************
			// An element not in list 
			private static final Integer ELEMENT = TestCase.E; 
			// Another element not in list - used for negative testing 
			private static final Integer INVALID_ELEMENT = TestCase.F;

	//********************Before Each Test********************
	/**
	 * Sets up list for testing: uses Parameter in .xml file to select the 
	 * dynamic type of the list. 
	 * @param listType - String representing the dynamic type of the list. 
	 */
	@Parameters("listType")
	@BeforeMethod
	public void initialize(String listType)
	{
		list = TestCase.newList(listType);
	}
	
	//******************* Tests ***************************
	/**
	 * Test: removeFirst() - try to remove first element of list
	 * Expected Result: EmptyCollectionException
	 */
	@Test(expectedExceptions = EmptyCollectionException.class)
	public void testRemoveFirst()
	{
		TestCase.removeFirst(list, FIRST);
	}

	/**
	 * Test: removeLast() - try to remove last element of list 
	 * Expected Result: EmptyCollectionException
	 */
	@Test(expectedExceptions = EmptyCollectionException.class)
	public void testRemoveLast()
	{
		TestCase.removeLast(list, LAST);
	}
    
	/**
	 * Test: remove(INVALID_ELEMENT) - try to remove invalid element (F) from list 
	 * Expected Result: ElementNotFoundException
	 */
	@Test(expectedExceptions = ElementNotFoundException.class)
	public void testRemove_invalidElement()
	{
		TestCase.remove(list, INVALID_ELEMENT);
	}
	
	/**
	 * Test: first() - try to get reference to first element 
	 * Expected Result: EmptyCollectionException
	 */
	@Test(expectedExceptions = EmptyCollectionException.class)
	public void testFirst()
	{
		TestCase.first(list, FIRST);
	}

	/**
	 * Test: last() - try to get reference to last element 
	 * Expected Result: EmptyCollectionException
	 */
	@Test(expectedExceptions = EmptyCollectionException.class)
	public void testLast()
	{
		TestCase.last(list, LAST);
	}

	/**
	 * Test: contains(ELEMENT) - whether list contains element (E)  
	 * Expected Result: false
	 */
	@Test
	public void testContains_invalidElement()
	{
		TestCase.contains(list, INVALID_ELEMENT, false);
	}

	/**
	 * Test: isEmpty() - whether list is empty 
	 * Expected Result: true
	 */
	@Test
	public void testIsEmpty()
	{
		TestCase.isEmpty(list, true);
	}

	/**
	 * Test: size() - number of elements in list  
	 * Expected Result: SIZE (0)
	 */
	@Test
	public void testSize()
	{
		TestCase.size(list, SIZE);
	}

	/**
	 * Test: iterator() - try to get reference to an Iterator object 
	 * Expected Result: Null 
	 */
	@Test(expectedExceptions = NullPointerException.class)
	public void testIterator()
	{
		TestCase.iterator(list);
	}

	/**
	 * Test: addToFront(ELEMENT) - adds element (E) to front of list  
	 * Expected Result: No Exception 
	 */
	@Test
	public void testAddToFront()
	{
		TestCase.addToFront(list, ELEMENT);
	}

	/**
	 * Test: addToRear(ELEMENT) - adds element (E) to back of list  
	 * Expected Result: No Exception 
	 */
	@Test
	public void testAddToRear()
	{
		TestCase.addToRear(list, ELEMENT);
	}

	/**
	 * Test: addAfter(ELEMENT, INVALID_ELEMENT) - tries to add element (E) after invalid element (F) in the list  
	 * Expected Result: ElementNotFoundException
	 */
	@Test(expectedExceptions = ElementNotFoundException.class)
	public void testAddAfter_invalidElement()
	{
		TestCase.addAfter(list, ELEMENT, INVALID_ELEMENT);
	}
	
	/**
	 * Test: add(VALID_ADD_INDEX, ELEMENT) - adds element (E) at valid index (0)   
	 * Expected Result: No Exception
	 */
	@Test
	public void testAdd_validIndex()
	{
		TestCase.add(list, VALID_ADD_INDEX, ELEMENT);
	}
	
	/**
	 * Test: add(X, ELEMENT) - tries to add element (E) at invalid indexes X   
	 * Expected Result: IndexOutOfBoundsException
	 */
	@Test(dataProvider = "invalidAddIndexes", expectedExceptions = IndexOutOfBoundsException.class)
	public void testAdd_invalidIndex(int index)
	{
		TestCase.add(list, index, ELEMENT);
	}
	
	/**
	 * Test: remove(INVALID_INDEX) - removes element at invalid index (0)   
	 * Expected Result: IndexOutOfBoundsException
	 */
	@Test(expectedExceptions = IndexOutOfBoundsException.class)
	public void testRemove_invalidIndex()
	{
		TestCase.remove(list, INVALID_INDEX, ELEMENT);
	}

	/**
	 * Test: set(INVALID_INDEX, ELEMENT) - tries to sets value of element at invalid index (0) to 
	 * to element (E)    
	 * Expected Result: IndexOutOfBoundsException
	 */
	@Test(expectedExceptions = IndexOutOfBoundsException.class)
	public void testSet()
	{
		TestCase.set(list, INVALID_INDEX, ELEMENT);
	}

	/**
	 * Test: get(INVALID_INDEX) - tries to return reference to element at invalid index (0)   
	 * Expected Result: IndexOutOfBoundsException
	 */
	@Test(expectedExceptions = IndexOutOfBoundsException.class)
	public void testGet()
	{
		TestCase.get(list, INVALID_INDEX, ELEMENT);
	}
	
	/**
	 * Test: indexOf(INVALID_ELEMENT) - tries to return index of invalid element (E)   
	 * Expected Result: -1 (not found) 
	 */
	@Test
	public void testIndexOf_invalidElement()
	{
		TestCase.indexOf(list, -1, INVALID_ELEMENT);
	}

	//********** Data Provider ***************************
	   @DataProvider
	   public static Object[][] invalidAddIndexes() 
	   {
	      return INVALID_ADD_INDEXES;
	   }

}
