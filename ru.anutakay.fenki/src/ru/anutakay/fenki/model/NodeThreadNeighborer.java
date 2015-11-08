package ru.anutakay.fenki.model;

public class NodeThreadNeighborer {
	
	public static ThreadIndex getNeighborThreadIndex(
			final Size dimensions,
			final NodeIndex nodeIndex, 
			final H hDirection, 
			final V vDirection) {
			
		int i = nodeIndex.i;
		int j = nodeIndex.j;
		int t = new ColumnTemplate(j, dimensions).isShort(H.LEFT) ? 1 : 0;
		i = i*2 + t;
		if (vDirection == V.NEXT) {
			j = j + 1;
		}
		if (hDirection == H.RIGHT) {
			i = i + 1;
		}
		return new ThreadIndex(i, j);
	}
}
