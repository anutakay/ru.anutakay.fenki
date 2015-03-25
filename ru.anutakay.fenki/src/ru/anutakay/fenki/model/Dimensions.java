package ru.anutakay.fenki.model;

public class Dimensions {
	
	private static final int MIN_NUMBER_OF_COLUMN = 1;

	private static final int MIN_NUMBER_OF_THREAD = 2;

	private boolean firstCrossIsNode = true;
	
	private int numberOfThreads;
	
	private int numberOfColumns;
	
	public Dimensions() {
		this(MIN_NUMBER_OF_THREAD, MIN_NUMBER_OF_COLUMN);
	}
	
	public Dimensions(	final int numberOfThreads, 
			final int numberOfColumns) {
		this.numberOfThreads = numberOfThreads;
		this.numberOfColumns = numberOfColumns;

		if (this.numberOfThreads < MIN_NUMBER_OF_THREAD) {
			this.numberOfThreads = MIN_NUMBER_OF_THREAD;
		}
		if (this.numberOfColumns < MIN_NUMBER_OF_COLUMN) {
			this.numberOfColumns = MIN_NUMBER_OF_COLUMN;
		}	
	}

	public Dimensions(	final int numberOfThreads, 
						final int numberOfColumns, 
						final boolean firstCrossIsNode) {
		this(numberOfThreads, numberOfColumns);
		this.firstCrossIsNode = firstCrossIsNode;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (firstCrossIsNode ? 1231 : 1237);
		result = prime * result + numberOfColumns;
		result = prime * result + numberOfThreads;
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
		Dimensions other = (Dimensions) obj;
		if (firstCrossIsNode != other.firstCrossIsNode)
			return false;
		if (numberOfColumns != other.numberOfColumns)
			return false;
		if (numberOfThreads != other.numberOfThreads)
			return false;
		return true;
	}
	
	
}
