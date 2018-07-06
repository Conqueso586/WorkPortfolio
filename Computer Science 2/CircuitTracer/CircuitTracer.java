import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Search for shortest paths between start and end points on a circuit board as
 * read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to a
 * GUI according to options specified via command-line arguments.
 * 
 * @author Connor Nagel
 */
public class CircuitTracer {
	private CircuitBoard board;

	private Storage<TraceState> stateStore;
	private ArrayList<TraceState> bestPaths;
	Point startingPoint;
	Point endingPoint;
	int startingRow;
	int startingCol;
	int endingRow;
	int endingCol;

	/**
	 * launch the program
	 * 
	 * @param args
	 *            three required arguments: first arg: -s for stack or -q for
	 *            queue second arg: -c for console output or -g for GUI output
	 *            third arg: input file name
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {
			new CircuitTracer(args);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {

		System.out.println("Corrent Usage:");
		System.out.println("CircuitTracer arg1 arg2 arg3");
		System.out.println();
		System.out.println("arg1: '-s' for stack or '-q' for queue");
		System.out
				.println("arg2: '-c for console output or -g for GUI output'");
		System.out.println("arg3: input file name");
	}

	/**
	 * Set up the CircuitBoard and all other components based on command line
	 * arguments.
	 * 
	 * @param args
	 *            command line arguments passed through from main()
	 */
	private CircuitTracer(String[] args) throws FileNotFoundException {

	//Takes in command line arguments to determine the way that the TraceStates are stored
		if (args[0].equals("-s")) {
			stateStore = new Storage<TraceState>(Storage.DataStructure.stack);
		}
		if (args[0].equals("-q")) {

			stateStore = new Storage<TraceState>(Storage.DataStructure.queue);

		}
		if (args[1].equals("-c")) {
			board = new CircuitBoard(args[2]);
		}
		if (args[1].equals("-g")) {
			System.out.println("GUI Option Not Supported");
		}
		
		
	/**
	 * The below code uses the below private methods to find the shortest path from
	 * the starting point to the end point.
	 * 
	 * 
	 * */	
		bestPaths = new ArrayList<TraceState>();

		startingPoint = board.getStartingPoint();
		endingPoint = board.getEndingPoint();
		startingRow = startingPoint.x;
		startingCol = startingPoint.y;
		endingRow = endingPoint.x;
		endingCol = endingPoint.y;

		checkOpenStartingPositions(startingRow, startingCol);

		while (!stateStore.isEmpty()) {

			TraceState next = stateStore.retrieve();
			if (isAdjacentToEndpoint(next.getRow(), next.getCol())) {
				if (bestPaths.size() == 0
						|| bestPaths.get(0).pathLength() == next.pathLength()) {
					bestPaths.add(next);
				} else if (bestPaths.get(0).pathLength() >= next.pathLength()) {
					bestPaths = new ArrayList<TraceState>();
					bestPaths.add(next);
				}
			} else {
				nextTraces(next);
			}
		}

		System.out.println("Shortest Paths:");
		for (TraceState i : bestPaths) {
			System.out.println(i.toString());
			System.out.println();

		}

	}

	/**
	 * Using the starting point, this method then check for open adjacent spaces. 
	 * A TraceState object is created for each open space.
	 * @param row
	 * @param col
	 */
	private void checkOpenStartingPositions(int row, int col) {

		if (col != 0 && board.charAt(row, col - 1) == 'O') {
			TraceState state = new TraceState(board, row, col - 1);
			stateStore.store(state);
		}
		if (col < board.numCols() - 1 && board.charAt(row, col + 1) == 'O') {
			TraceState state = new TraceState(board, row, col + 1);
			stateStore.store(state);
		}

		if (row != 0 && board.charAt(row - 1, col) == 'O') {
			TraceState state = new TraceState(board, row - 1, col);
			stateStore.store(state);

		}
		if (row < board.numRows() - 1 && board.charAt(row + 1, col) == 'O') {
			TraceState state = new TraceState(board, row + 1, col);
			stateStore.store(state);
		}

	}

	/**
	 * Checks to see if the current Tracestate is adjacent to the endpoint
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean isAdjacentToEndpoint(int row, int col) {
		boolean isAdjacent = false;

		if (row < board.numRows() - 1 && board.charAt(row + 1, col) == '2') {
			isAdjacent = true;
		}
		if (col < board.numCols() - 1 && board.charAt(row, col + 1) == '2') {
			isAdjacent = true;
		}
		if (row != 0 && board.charAt(row - 1, col) == '2') {
			isAdjacent = true;
		}
		if (col != 0 && board.charAt(row, col - 1) == '2') {
			isAdjacent = true;
		}

		return isAdjacent;
	}

	/**
	 * If a TraceState is not equal or less than the shortest path, then all open spaces
	 * become new TraceState instances.
	 * @param previousState
	 */
	private void nextTraces(TraceState previousState) {
		int currentX = previousState.getRow();
		int currentY = previousState.getCol();
		if (currentX < previousState.getBoard().numRows() - 1
				&& previousState.getBoard().charAt(currentX + 1, currentY) == 'O') {
			TraceState state = new TraceState(previousState, currentX + 1,
					currentY);
			stateStore.store(state);
		}
		if (currentY < previousState.getBoard().numCols() - 1
				&& previousState.getBoard().charAt(currentX, currentY + 1) == 'O') {
			TraceState state = new TraceState(previousState, currentX,
					currentY + 1);
			stateStore.store(state);
		}
		if (currentX != 0
				&& previousState.getBoard().charAt(currentX - 1, currentY) == 'O') {
			TraceState state = new TraceState(previousState, currentX - 1,
					currentY);
			stateStore.store(state);
		}
		if (currentY != 0
				&& previousState.getBoard().charAt(currentX, currentY - 1) == 'O') {
			TraceState state = new TraceState(previousState, currentX,
					currentY - 1);
			stateStore.store(state);
		}

	}

}
// class CircuitTracer
