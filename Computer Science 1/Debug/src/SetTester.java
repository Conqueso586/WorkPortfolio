/**
 * A unit test class for implementations of the SimpleSet ADT. This is a set of black box tests that
 * should work for any implementation of this interface.
 * 
 * @author mvail
 */
public class SetTester {

	// possible results expected in tests
	private enum Result {
		ElementNotFound, NoException, UnexpectedException, True, False, Pass, Fail, MatchingValue, ValidString
	};

	// named elements for use in tests
	private static final Integer ELEMENT_A = new Integer(1);
	private static final Integer ELEMENT_B = new Integer(2);
	private static final Integer ELEMENT_C = new Integer(3);
	private static final Integer ELEMENT_D = new Integer(4);

	// instance variables for tracking test results
	private int passes = 0;
	private int failures = 0;
	private int total = 0;

	/**
	 * @param args
	 *            not used
	 */
	public static void main(String[] args)
	{
		// to avoid every method being static
		SetTester tester = new SetTester();
		tester.runTests();
	}

	/**
	 * Print test results in a consistent format
	 * 
	 * @param testDesc
	 *            description of the test
	 * @param result
	 *            indicates if the test passed or failed
	 */
	private void printTest(String testDesc, boolean result)
	{
		total++;
		if (result) {
			passes++;
		} else {
			failures++;
		}
		System.out.printf("%-46s\t%s\n", testDesc, (result ? "   PASS" : "***FAIL***"));
	}

	/** Print a final summary */
	private void printFinalSummary()
	{
		System.out.printf("\nTotal Tests: %d,  Passed: %d,  Failed: %d\n", total, passes, failures);
	}

	// XXX <- see the blue box on the right of the scroll bar? this tag aids in navigating long files
	/** Run tests to confirm required functionality from set constructors and methods */
	private void runTests()
	{
		// recommended scenario naming: start_change_result
		test_emptySet(); // aka noSet_constructor_emptySet
		test_emptySet_addA_A();
		test_A_removeA_emptySet();
		test_A_addA_A();
		test_A_addB_AB();
		test_AB_removeA_B();
		test_AB_removeB_A();
		test_AB_addC_ABC();
		test_ABC_removeA_BC();
		test_ABC_removeB_AC();
		test_ABC_removeC_AB();

		// report final verdict
		printFinalSummary();
	}

	// ///////////////////////////////////////
	// XXX SCENARIO: NEW EMPTY SET
	// ///////////////////////////////////////

	/**
	 * Returns a SimpleSet for the "new empty set" scenario. Scenario: no set -> constructor -> [ ]
	 *
	 * @return a new, empty SimpleSet
	 */
	private SimpleSet<Integer> emptySet()
	{
		return new ArraySet<Integer>(2); // intentionally set small enough to exercise set growth
	}

	/** Run all tests on scenario: no set -> constructor -> [ ] */
	private void test_emptySet()
	{
		try {
			// recommended test naming: scenario_testName where scenario is start_change_result
			// for example emptySet_addA_A_testIsEmpty
			System.out.println("NEW EMPTY SET");
			printTest("emptySet_testToString", testToString(emptySet(), Result.ValidString));
			printTest("emptySet_testIsEmpty", testIsEmpty(emptySet(), Result.True));
			printTest("emptySet_testSize", testSize(emptySet(), 0));
			printTest("emptySet_testContainsA", testContains(emptySet(), ELEMENT_A, Result.False));
			printTest("emptySet_testAddA", testAdd(emptySet(), ELEMENT_A, Result.NoException));
			printTest("emptySet_testRemoveA", testRemove(emptySet(), ELEMENT_A, Result.ElementNotFound));

		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptySet");
			e.printStackTrace();
		}
		System.out.println("=======================================================");

	}

	// //////////////////////////////////////////////
	// XXX SCENARIO: [ ] -> add(A) -> [A]
	// //////////////////////////////////////////////

	/**
	 * Scenario: empty set -> add(A) -> [A]
	 * 
	 * @return [A] after add(A)
	 */
	private SimpleSet<Integer> emptySet_addA_A()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		return set;
	}

	/** Run all tests on scenario: empty set -> add(A) -> [A] */
	private void test_emptySet_addA_A()
	{
		try {
			System.out.println("SCENARIO: [ ] -> add(A) -> [A]");
			printTest("emptySet_addA_A_testToString", testToString(emptySet_addA_A(), Result.ValidString));
			printTest("emptySet_addA_A_testIsEmpty", testIsEmpty(emptySet_addA_A(), Result.False));
			printTest("emptySet_addA_A_testSize", testSize(emptySet_addA_A(), 1));
			printTest("emptySet_addA_A_testContainsA", testContains(emptySet_addA_A(), ELEMENT_A, Result.True));
			printTest("emptySet_addA_A_testContainsB", testContains(emptySet_addA_A(), ELEMENT_B, Result.False));
			printTest("emptySet_addA_A_testAddA", testAdd(emptySet_addA_A(), ELEMENT_A, Result.NoException));
			printTest("emptySet_addA_A_testRemoveA", testRemove(emptySet_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptySet_addA_A_testRemoveB", testRemove(emptySet_addA_A(), ELEMENT_B, Result.ElementNotFound));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptySet_addA_A");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// //////////////////////////////////////////////
	// XXX SCENARIO: [A] -> remove(A) -> [ ]
	// //////////////////////////////////////////////

	/**
	 * Scenario: [A] -> remove(A) -> [ ]
	 * 
	 * @return [ ] after remove(A)
	 */
	private SimpleSet<Integer> A_removeA_emptySet()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		set.remove(ELEMENT_A);
		return set;
	}

	/** Run all tests on scenario: [A] -> remove(A) -> [ ] */
	private void test_A_removeA_emptySet()
	{
		try {
			System.out.println("SCENARIO: [A] -> remove(A) -> [ ]");
			printTest("A_removeA_emptySet_testToString", testToString(A_removeA_emptySet(), Result.ValidString));
			printTest("A_removeA_emptySet_testIsEmpty", testIsEmpty(A_removeA_emptySet(), Result.True));
			printTest("A_removeA_emptySet_testSize", testSize(A_removeA_emptySet(), 0));
			printTest("A_removeA_emptySet_testContainsA", testContains(A_removeA_emptySet(), ELEMENT_A, Result.False));
			printTest("A_removeA_emptySet_testAddA", testAdd(A_removeA_emptySet(), ELEMENT_A, Result.NoException));
			printTest("A_removeA_emptySet_testRemoveA", testRemove(A_removeA_emptySet(), ELEMENT_A, Result.ElementNotFound));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_removeA_emptySet");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// //////////////////////////////////////////////
	// XXX SCENARIO: [A] -> add(A) -> [A]
	// //////////////////////////////////////////////

	/**
	 * Scenario: [A] -> add(A) -> [A]
	 * 
	 * @return [A] after add(A)
	 */
	private SimpleSet<Integer> A_addA_A()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		set.add(ELEMENT_A);
		return set;
	}

	/** Run all tests on scenario: [A] -> add(A) -> [A] */
	private void test_A_addA_A()
	{
		try {
			System.out.println("SCENARIO: [A] -> add(A) -> [A]");
			printTest("A_addA_A_testToString", testToString(A_addA_A(), Result.ValidString));
			printTest("A_addA_A_testIsEmpty", testIsEmpty(A_addA_A(), Result.False));
			printTest("A_addA_A_testSize", testSize(A_addA_A(), 1));
			printTest("A_addA_A_testContainsA", testContains(A_addA_A(), ELEMENT_A, Result.True));
			printTest("A_addA_A_testContainsB", testContains(A_addA_A(), ELEMENT_B, Result.False));
			printTest("A_addA_A_testAddA", testAdd(A_addA_A(), ELEMENT_A, Result.NoException));
			printTest("A_addA_A_testAddB", testAdd(A_addA_A(), ELEMENT_B, Result.NoException));
			printTest("A_addA_A_testRemoveA", testRemove(A_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addA_A_testRemoveB", testRemove(A_addA_A(), ELEMENT_B, Result.ElementNotFound));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_addA_A");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// //////////////////////////////////////////////
	// XXX SCENARIO: [A] -> add(B) -> [A,B]
	// //////////////////////////////////////////////

	/**
	 * Scenario: [A] -> add(B) -> [A,B]
	 * 
	 * @return [A,B] after add(B)
	 */
	private SimpleSet<Integer> A_addB_AB()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		set.add(ELEMENT_B);
		return set;
	}

	/** Run all tests on scenario: [A] -> add(B) -> [A,B] */
	private void test_A_addB_AB()
	{
		try {
			System.out.println("SCENARIO: [A] -> add(B) -> [A,B]");
			printTest("A_addB_AB_testToString", testToString(A_addB_AB(), Result.ValidString));
			printTest("A_addB_AB_testIsEmpty", testIsEmpty(A_addB_AB(), Result.False));
			printTest("A_addB_AB_testSize", testSize(A_addB_AB(), 2));
			printTest("A_addB_AB_testContainsA", testContains(A_addB_AB(), ELEMENT_A, Result.True));
			printTest("A_addB_AB_testContainsB", testContains(A_addB_AB(), ELEMENT_B, Result.True));
			printTest("A_addB_AB_testContainsC", testContains(A_addB_AB(), ELEMENT_C, Result.False));
			printTest("A_addB_AB_testAddA", testAdd(A_addB_AB(), ELEMENT_A, Result.NoException));
			printTest("A_addB_AB_testAddB", testAdd(A_addB_AB(), ELEMENT_B, Result.NoException));
			printTest("A_addB_AB_testAddC", testAdd(A_addB_AB(), ELEMENT_C, Result.NoException));
			printTest("A_addB_AB_testRemoveA", testRemove(A_addB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addB_AB_testRemoveB", testRemove(A_addB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addB_AB_testRemoveC", testRemove(A_addB_AB(), ELEMENT_C, Result.ElementNotFound));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_addB_AB");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// ////////////////////////////////////////////////
	// XXX SCENARIO: [A,B] -> remove(A) -> [B]
	// ////////////////////////////////////////////////

	/**
	 * Scenario: [A,B] -> remove(A) -> [B]
	 * 
	 * @return [B] after remove(A)
	 */
	private SimpleSet<Integer> AB_removeA_B()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		set.add(ELEMENT_B);
		set.remove(ELEMENT_A);
		return set;
	}

	/** Run all tests on scenario: [A,B] -> remove(A) -> [B] */
	private void test_AB_removeA_B()
	{
		try {
			System.out.println("SCENARIO: [A,B] -> remove(A) -> [B]");
			printTest("AB_removeA_B_testToString", testToString(AB_removeA_B(), Result.ValidString));
			printTest("AB_removeA_B_testIsEmpty", testIsEmpty(AB_removeA_B(), Result.False));
			printTest("AB_removeA_B_testSize", testSize(AB_removeA_B(), 1));
			printTest("AB_removeA_B_testContainsA", testContains(AB_removeA_B(), ELEMENT_A, Result.False));
			printTest("AB_removeA_B_testContainsB", testContains(AB_removeA_B(), ELEMENT_B, Result.True));
			printTest("AB_removeA_B_testAddA", testAdd(AB_removeA_B(), ELEMENT_A, Result.NoException));
			printTest("AB_removeA_B_testAddB", testAdd(AB_removeA_B(), ELEMENT_B, Result.NoException));
			printTest("AB_removeA_B_testRemoveA", testRemove(AB_removeA_B(), ELEMENT_A, Result.ElementNotFound));
			printTest("AB_removeA_B_testRemoveB", testRemove(AB_removeA_B(), ELEMENT_B, Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_removeA_B");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// ////////////////////////////////////////////////
	// XXX SCENARIO: [A,B] -> remove(B) -> [A]
	// ////////////////////////////////////////////////

	/**
	 * Scenario: [A,B] -> remove(B) -> [A]
	 * 
	 * @return [A] after remove(B)
	 */
	private SimpleSet<Integer> AB_removeB_A()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		set.add(ELEMENT_B);
		set.remove(ELEMENT_B);
		return set;
	}

	/** Run all tests on scenario: [A,B] -> remove(B) -> [A] */
	private void test_AB_removeB_A()
	{
		try {
			System.out.println("SCENARIO: [A,B] -> remove(B) -> [A]");
			printTest("AB_removeB_A_testToString", testToString(AB_removeB_A(), Result.ValidString));
			printTest("AB_removeB_A_testSize", testSize(AB_removeB_A(), 1));
			printTest("AB_removeB_A_testIsEmpty", testIsEmpty(AB_removeB_A(), Result.False));
			printTest("AB_removeB_A_testContainsA", testContains(AB_removeB_A(), ELEMENT_A, Result.True));
			printTest("AB_removeB_A_testContainsB", testContains(AB_removeB_A(), ELEMENT_B, Result.False));
			printTest("AB_removeB_A_testAddA", testAdd(AB_removeB_A(), ELEMENT_A, Result.NoException));
			printTest("AB_removeB_A_testAddB", testAdd(AB_removeB_A(), ELEMENT_B, Result.NoException));
			printTest("AB_removeB_A_testRemoveA", testRemove(AB_removeB_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testRemoveB", testRemove(AB_removeB_A(), ELEMENT_B, Result.ElementNotFound));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_removeA_B");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// ////////////////////////////////////////////////
	// XXX SCENARIO: [A,B] -> add(C) -> [A,B,C]
	// ////////////////////////////////////////////////

	/**
	 * Scenario: [A,B] -> add(C) -> [A,B,C]
	 * 
	 * @return [A,B,C] after add(C)
	 */
	private SimpleSet<Integer> AB_addC_ABC()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		set.add(ELEMENT_B);
		set.add(ELEMENT_C);
		return set;
	}

	/** Run all tests on scenario: [A,B] -> add(C) -> [A,B,C] */
	private void test_AB_addC_ABC()
	{
		try {
			System.out.println("SCENARIO: [A,B] -> add(C) -> [A,B,C]");
			printTest("AB_addC_ABC_testToString", testToString(AB_addC_ABC(), Result.ValidString));
			printTest("AB_addC_ABC_testIsEmpty", testIsEmpty(AB_addC_ABC(), Result.False));
			printTest("AB_addC_ABC_testSize", testSize(AB_addC_ABC(), 3));
			printTest("AB_addC_ABC_testContainsA", testContains(AB_addC_ABC(), ELEMENT_A, Result.True));
			printTest("AB_addC_ABC_testContainsB", testContains(AB_addC_ABC(), ELEMENT_B, Result.True));
			printTest("AB_addC_ABC_testContainsC", testContains(AB_addC_ABC(), ELEMENT_C, Result.True));
			printTest("AB_addC_ABC_testContainsD", testContains(AB_addC_ABC(), ELEMENT_D, Result.False));
			printTest("AB_addC_ABC_testAddA", testAdd(AB_addC_ABC(), ELEMENT_A, Result.NoException));
			printTest("AB_addC_ABC_testAddB", testAdd(AB_addC_ABC(), ELEMENT_B, Result.NoException));
			printTest("AB_addC_ABC_testAddC", testAdd(AB_addC_ABC(), ELEMENT_C, Result.NoException));
			printTest("AB_addC_ABC_testAddD", testAdd(AB_addC_ABC(), ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testRemoveA", testRemove(AB_addC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveB", testRemove(AB_addC_ABC(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveC", testRemove(AB_addC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveD", testRemove(AB_addC_ABC(), ELEMENT_D, Result.ElementNotFound));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_addC_ABC");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// ////////////////////////////////////////////////////
	// XXX SCENARIO: [A,B,C] -> remove(A) -> [B,C]
	// ////////////////////////////////////////////////////

	/**
	 * Scenario: [A,B,C] -> remove(A) -> [B,C]
	 * 
	 * @return [B,C] after remove(A)
	 */
	private SimpleSet<Integer> ABC_removeA_BC()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		set.add(ELEMENT_B);
		set.add(ELEMENT_C);
		set.remove(ELEMENT_A);
		return set;
	}

	/** Run all tests on scenario: [A,B,C] -> remove(A) -> [B,C] */
	private void test_ABC_removeA_BC()
	{
		try {
			System.out.println("SCENARIO: [A,B,C] -> remove(A) -> [B,C]");
			printTest("ABC_removeA_BC_testToString", testToString(ABC_removeA_BC(), Result.ValidString));
			printTest("ABC_removeA_BC_testIsEmpty", testIsEmpty(ABC_removeA_BC(), Result.False));
			printTest("ABC_removeA_BC_testSize", testSize(ABC_removeA_BC(), 2));
			printTest("ABC_removeA_BC_testContainsA", testContains(ABC_removeA_BC(), ELEMENT_A, Result.False));
			printTest("ABC_removeA_BC_testContainsB", testContains(ABC_removeA_BC(), ELEMENT_B, Result.True));
			printTest("ABC_removeA_BC_testContainsC", testContains(ABC_removeA_BC(), ELEMENT_C, Result.True));
			printTest("ABC_removeA_BC_testAddA", testAdd(ABC_removeA_BC(), ELEMENT_A, Result.NoException));
			printTest("ABC_removeA_BC_testAddB", testAdd(ABC_removeA_BC(), ELEMENT_B, Result.NoException));
			printTest("ABC_removeA_BC_testAddC", testAdd(ABC_removeA_BC(), ELEMENT_C, Result.NoException));
			printTest("ABC_removeA_BC_testRemoveA", testRemove(ABC_removeA_BC(), ELEMENT_A, Result.ElementNotFound));
			printTest("ABC_removeA_BC_testRemoveB", testRemove(ABC_removeA_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeA_BC_testRemoveC", testRemove(ABC_removeA_BC(), ELEMENT_C, Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_removeA_BC");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// ////////////////////////////////////////////////////
	// XXX SCENARIO: [A,B,C] -> remove(B) -> [A,C]
	// ////////////////////////////////////////////////////

	/**
	 * Scenario: [A,B,C] -> remove(B) -> [A,C]
	 * 
	 * @return [A,C] after remove(B)
	 */
	private SimpleSet<Integer> ABC_removeB_AC()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		set.add(ELEMENT_B);
		set.add(ELEMENT_C);
		set.remove(ELEMENT_B);
		return set;
	}

	/** Run all tests on scenario: [A,B,C] -> remove(B) -> [A,C] */
	private void test_ABC_removeB_AC()
	{
		try {
			System.out.println("SCENARIO: [A,B,C] -> remove(B) -> [A,C]");
			printTest("ABC_removeB_AC_testToString", testToString(ABC_removeB_AC(), Result.ValidString));
			printTest("ABC_removeB_AC_testIsEmpty", testIsEmpty(ABC_removeB_AC(), Result.False));
			printTest("ABC_removeB_AC_testSize", testSize(ABC_removeB_AC(), 2));
			printTest("ABC_removeB_AC_testContainsA", testContains(ABC_removeB_AC(), ELEMENT_A, Result.True));
			printTest("ABC_removeB_AC_testContainsB", testContains(ABC_removeB_AC(), ELEMENT_B, Result.False));
			printTest("ABC_removeB_AC_testContainsC", testContains(ABC_removeB_AC(), ELEMENT_C, Result.True));
			printTest("ABC_removeB_AC_testAddA", testAdd(ABC_removeB_AC(), ELEMENT_A, Result.NoException));
			printTest("ABC_removeB_AC_testAddB", testAdd(ABC_removeB_AC(), ELEMENT_B, Result.NoException));
			printTest("ABC_removeB_AC_testAddC", testAdd(ABC_removeB_AC(), ELEMENT_C, Result.NoException));
			printTest("ABC_removeB_AC_testRemoveA", testRemove(ABC_removeB_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeB_AC_testRemoveB", testRemove(ABC_removeB_AC(), ELEMENT_B, Result.ElementNotFound));
			printTest("ABC_removeB_AC_testRemoveC", testRemove(ABC_removeB_AC(), ELEMENT_C, Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_removeB_AC");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// ////////////////////////////////////////////////////
	// XXX SCENARIO: [A,B,C] -> remove(C) -> [A,B]
	// ////////////////////////////////////////////////////

	/**
	 * Scenario: [A,B,C] -> remove(C) -> [A,B]
	 * 
	 * @return [A,B] after remove(C)
	 */
	private SimpleSet<Integer> ABC_removeC_AB()
	{
		SimpleSet<Integer> set = emptySet();
		set.add(ELEMENT_A);
		set.add(ELEMENT_B);
		set.add(ELEMENT_C);
		set.remove(ELEMENT_C);
		return set;
	}

	/** Run all tests on scenario: [A,B,C] -> remove(C) -> [A,B] */
	private void test_ABC_removeC_AB()
	{
		try {
			System.out.println("SCENARIO: [A,B,C] -> remove(C) -> [A,B]");
			printTest("ABC_removeC_AB_testToString", testToString(ABC_removeC_AB(), Result.ValidString));
			printTest("ABC_removeC_AB_testIsEmpty", testIsEmpty(ABC_removeC_AB(), Result.False));
			printTest("ABC_removeC_AB_testSize", testSize(ABC_removeC_AB(), 2));
			printTest("ABC_removeC_AB_testContainsA", testContains(ABC_removeC_AB(), ELEMENT_A, Result.True));
			printTest("ABC_removeC_AB_testContainsB", testContains(ABC_removeC_AB(), ELEMENT_B, Result.True));
			printTest("ABC_removeC_AB_testContainsC", testContains(ABC_removeC_AB(), ELEMENT_C, Result.False));
			printTest("ABC_removeC_AB_testAddA", testAdd(ABC_removeC_AB(), ELEMENT_A, Result.NoException));
			printTest("ABC_removeC_AB_testAddB", testAdd(ABC_removeC_AB(), ELEMENT_B, Result.NoException));
			printTest("ABC_removeC_AB_testAddC", testAdd(ABC_removeC_AB(), ELEMENT_C, Result.NoException));
			printTest("ABC_removeC_AB_testRemoveA", testRemove(ABC_removeC_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testRemoveB", testRemove(ABC_removeC_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testRemoveC", testRemove(ABC_removeC_AB(), ELEMENT_C, Result.ElementNotFound));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_removeB_AC");
			e.printStackTrace();
		}
		System.out.println("=======================================================");
	}

	// /////////////////////////////////
	// XXX SIMPLE_SET ADT TESTS
	// /////////////////////////////////

	/**
	 * Runs add() method on given set and checks result against expectedResult
	 * 
	 * @param set
	 *            a set already prepared for a given change scenario
	 * @param element
	 *            element to add
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAdd(SimpleSet<Integer> set, Integer element, Result expectedResult)
	{
		Result result;
		try {
			set.add(element);
			result = Result.NoException;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAdd", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs remove() method on given set and checks result against expectedResult
	 * 
	 * @param set
	 *            a set already prepared for a given change scenario
	 * @param element
	 *            element to remove
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testRemove(SimpleSet<Integer> set, Integer element, Result expectedResult)
	{
		Result result;
		try {
			Integer retVal = set.remove(element);
			if (retVal.equals(element)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (ElementNotFoundException e) {
			result = Result.ElementNotFound;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testRemove", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs contains() method on a given set and element and checks result against expectedResult
	 * 
	 * @param set
	 *            a set already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testContains(SimpleSet<Integer> set, Integer element, Result expectedResult)
	{
		Result result;
		try {
			if (set.contains(element)) {
				result = Result.True;
			} else {
				result = Result.False;
			}
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testContains", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs isEmpty() method on a given set and checks result against expectedResult
	 * 
	 * @param set
	 *            a set already prepared for a given change scenario
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIsEmpty(SimpleSet<Integer> set, Result expectedResult)
	{
		Result result;
		try {
			if (set.isEmpty()) {
				result = Result.True;
			} else {
				result = Result.False;
			}
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIsEmpty", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs size() method on a given set and checks result against expectedResult
	 * 
	 * @param set
	 *            a set already prepared for a given change scenario
	 * @param expectedSize
	 * @return test success
	 */
	private boolean testSize(SimpleSet<Integer> set, int expectedSize)
	{
		try {
			return (set.size() == expectedSize);
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testSize", e.toString());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Runs toString() method on given set and attempts to confirm non-default or empty String difficult
	 * to test - just confirm that default address output has been overridden
	 * 
	 * @param set
	 *            a set already prepared for a given change scenario
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testToString(SimpleSet<Integer> set, Result expectedResult)
	{
		Result result;
		try {
			String str = set.toString();
			System.out.println("toString() output: " + str);
			if (str.length() == 0) {
				result = Result.Fail;
			}
			char lastChar = str.charAt(str.length() - 1);
			if (str.contains("@") && !str.contains(" ") && Character.isLetter(str.charAt(0)) && (Character.isDigit(lastChar) || (lastChar >= 'a' && lastChar <= 'f'))) {
				result = Result.Fail; // looks like default toString()
			} else {
				result = Result.ValidString;
			}
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testToString", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

}// end class SetTester
