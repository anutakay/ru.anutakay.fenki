package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Node.Horizontal;
import ru.anutakay.fenki.model.Node.Vertical;

public class NodeThreadNeighborer {
	
	public static ThreadIndex getNeighborThreadIndex(
			final Size dimensions,
			final NodeIndex nodeIndex, 
			final Horizontal hDirection, 
			final Vertical vDirection) {
			
		int i = nodeIndex.i;
		int j = nodeIndex.j;
		int t = SchemaTemplate.isShortColumn(dimensions, j, Horizontal.LEFT) ? 1 : 0;
		i = i*2 + t;
		if (vDirection == Vertical.NEXT) {
			j = j + 1;
		}
		if (hDirection == Horizontal.RIGHT) {
			i = i + 1;
		}
		return new ThreadIndex(i, j);
	}
}
