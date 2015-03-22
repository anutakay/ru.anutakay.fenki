package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Node.HDirection;

public class SchemaTemplate {
	
	public static int numberOfNodeInColumn(	final Dimensions dimensions, 
											final int columnNumber) {
		int j = columnNumber;
		int i = dimensions.getThreadNumber()/2
				- ((dimensions.getThreadNumber()%2 == 0 && j%2 == 1 && dimensions.firstCrossIsNode())
						|| (dimensions.getThreadNumber()%2 == 0 && j%2 == 0 && !dimensions.firstCrossIsNode()) ? 1
						: 0);
		return i;
	}
	
	public static boolean isShortColumn(final Dimensions dimensions, 
										final int columnNumber, 
										final HDirection hDirection) {
		int j = columnNumber;
		boolean left = (j%2 == 1 && dimensions.firstCrossIsNode())
				|| (j%2 == 0 && !dimensions.firstCrossIsNode());
		if (hDirection == HDirection.LEFT) {
			return left;
		} else {
			return (left && dimensions.getThreadNumber()%2 == 0)
					|| (!left && dimensions.getThreadNumber()%2 == 1);
		}
	}
	
	public static boolean columnHasLeftCorner(	final Dimensions dimensions, 
												final int columnNumber) {
		int j = columnNumber/2;
		boolean first = dimensions.firstCrossIsNode();
		boolean t = (first && j%2 == 1) || (!first && j%2 == 0);
		return t;
	}
	
	public static boolean columnHasRightCorner(	final Dimensions dimensions, 
												final int columnNumber) {
		int j = columnNumber;
		if (dimensions.getThreadNumber()%2 == 1) {
			return !columnHasLeftCorner(dimensions, j);
		} else {
			return columnHasLeftCorner(dimensions, j);
		}
	}
}
