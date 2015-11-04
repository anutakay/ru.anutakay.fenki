package ru.anutakay.fenki.model;

public class SchemaTemplate {
	
	public static int numberOfNodeInColumn(	final Size dimensions, 
											final int columnNumber) {
		int j = columnNumber;
		int i = dimensions.getThreadNumber()/2
				- ((dimensions.getThreadNumber()%2 == 0 && j%2 == 1 && dimensions.firstCrossIsNode())
						|| (dimensions.getThreadNumber()%2 == 0 && j%2 == 0 && !dimensions.firstCrossIsNode()) ? 1
						: 0);
		return i;
	}
	
	public static boolean isShortColumn(final Size dimensions, 
										final int columnNumber, 
										final Horizontal hDirection) {
		int j = columnNumber;
		boolean left = (j%2 == 1 && dimensions.firstCrossIsNode())
				|| (j%2 == 0 && !dimensions.firstCrossIsNode());
		if (hDirection == Horizontal.LEFT) {
			return left;
		} else {
			return (left && dimensions.getThreadNumber()%2 == 0)
					|| (!left && dimensions.getThreadNumber()%2 == 1);
		}
	}
	
	public static boolean columnHasLeftCorner(	final Size dimensions, 
												final int columnNumber) {
		int j = columnNumber/2;
		boolean first = dimensions.firstCrossIsNode();
		boolean t = (first && j%2 == 1) || (!first && j%2 == 0);
		return t;
	}
	
	public static boolean columnHasRightCorner(	final Size dimensions, 
												final int columnNumber) {
		int j = columnNumber;
		if (dimensions.getThreadNumber()%2 == 1) {
			return !columnHasLeftCorner(dimensions, j);
		} else {
			return columnHasLeftCorner(dimensions, j);
		}
	}
}
