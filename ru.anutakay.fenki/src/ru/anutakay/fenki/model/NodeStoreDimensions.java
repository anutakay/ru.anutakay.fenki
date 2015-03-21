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
}
