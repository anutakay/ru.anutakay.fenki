package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Node.HDirection;
import ru.anutakay.fenki.model.Node.VDirection;

public class NodeThreadNeighborer {
	
	public static ThreadIndex getNeighborThreadIndex(
			final Dimensions dimensions,
			final NodeIndex nodeIndex, 
			final HDirection hDirection, 
			final VDirection vDirection) {
			
		int i = nodeIndex.i;
		int j = nodeIndex.j;
		int t = SchemaTemplate.isShortColumn(dimensions, j, HDirection.LEFT) ? 1 : 0;
		i = i*2 + t;
		if (vDirection == VDirection.NEXT) {
			j = j + 1;
		}
		if (hDirection == HDirection.RIGHT) {
			i = i + 1;
		}
		return new ThreadIndex(i, j);
	}
}
