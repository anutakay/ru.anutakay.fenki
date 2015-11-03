package ru.anutakay.fenki.model;

public class Size {
	
	private static final int MIN_NUM_OF_COLUMN = 1;

	private static final int MIN_NUM_OF_THREAD = 2;

	private boolean firstCrossIsNode = true;
	
	private int numOfThreads;
	
	private int numOfColumns;
	
	public Size() {
		this(MIN_NUM_OF_THREAD, MIN_NUM_OF_COLUMN);
	}
	
	public Size(final int numberOfThreads, 
			final int numberOfColumns, 
			final boolean firstCrossIsNode) {
		this(numberOfThreads, numberOfColumns);
		this.firstCrossIsNode = firstCrossIsNode;
	}
	
	public Size(final int numberOfThreads, 
			final int numberOfColumns) {
		this.numOfThreads = numberOfThreads;
		this.numOfColumns = numberOfColumns;

		checkNums();	
	}

	private void checkNums() {
		if (this.numOfThreads < MIN_NUM_OF_THREAD) {
			this.numOfThreads = MIN_NUM_OF_THREAD;
		}
		if (this.numOfColumns < MIN_NUM_OF_COLUMN) {
			this.numOfColumns = MIN_NUM_OF_COLUMN;
		}
	}

	public int getThreadNumber() {
		return numOfThreads;
	}

	public int getColumnNumber() {
		return numOfColumns;
	}

	public boolean firstCrossIsNode() {
		return firstCrossIsNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (firstCrossIsNode ? 1231 : 1237);
		result = prime * result + numOfColumns;
		result = prime * result + numOfThreads;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Size other = (Size) obj;
		if (firstCrossIsNode != other.firstCrossIsNode)
			return false;
		if (numOfColumns != other.numOfColumns)
			return false;
		if (numOfThreads != other.numOfThreads)
			return false;
		return true;
	}
	
	
}
