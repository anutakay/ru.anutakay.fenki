package ru.anutakay.fenki.model;

public class NodeStoreDimensions {
	
	private static final int MIN_NUMBER_OF_COLUMN = 1;

	private static final int MIN_NUMBER_OF_THREAD = 2;

	private boolean firstCrossIsNode;
	
	private int numberOfThreads;
	
	private int numberOfColumns;

	public NodeStoreDimensions(final int numberOfThreads, final int numberOfColumns, final boolean firstCrossIsNode) {
		this.numberOfThreads = numberOfThreads;
		this.numberOfColumns = numberOfColumns;
		this.firstCrossIsNode = firstCrossIsNode;
		
		if (this.numberOfThreads < MIN_NUMBER_OF_THREAD) {
			this.numberOfThreads = MIN_NUMBER_OF_THREAD;
		}
		if (this.numberOfColumns < MIN_NUMBER_OF_COLUMN) {
			this.numberOfColumns = MIN_NUMBER_OF_COLUMN;
		}
		
	}

	public int getThreadNumber() {
		return numberOfThreads;
	}

	public int getColumnNumber() {
		return numberOfColumns;
	}

	public boolean firstCrossIsNode() {
		return firstCrossIsNode;
	}

	/*public int numberOfNodeInColumn(final int columnNumber) {
		int j = columnNumber;
		int i = numberOfThreads/ 2
				- ((numberOfThreads % 2 == 0 && j % 2 == 1 && firstCrossIsNode())
						|| (numberOfThreads % 2 == 0 && j % 2 == 0 && !firstCrossIsNode) ? 1
						: 0);
		//System.out.println("в " + j + " ряду " + i + " узлов ");
		return i;
	}
	
	public boolean isShortColumn(final int columnNumber, final HDirection hDirection) {
		int j = columnNumber;
		boolean left = (j % 2 == 1 && firstCrossIsNode())
				|| (j % 2 == 0 && !firstCrossIsNode());
		if (hDirection == HDirection.LEFT) {
			return left;
		} else {
			return (left && getThreadNumber() % 2 == 0)
					|| (!left && getThreadNumber() % 2 == 1);
		}
	}
	
	public boolean columnHasLeftCorner(final int columnNumber){
		int j = columnNumber/2;
		boolean first = firstCrossIsNode();
		boolean t = (first && j%2 == 1) || (!first && j%2 == 0);
		return t;
	}
	
	public boolean columnHasRightCorner(final int columnNumber){
		int j = columnNumber;
		if (getThreadNumber()%2 == 1) {
			return !columnHasLeftCorner(j);
		} else {
			return columnHasLeftCorner(j);
		}
	}*/
}
