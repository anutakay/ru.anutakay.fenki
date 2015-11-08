package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.size.ColumnTemplate;
import ru.anutakay.fenki.model.size.Size;

public class NodeThreadNeighborer {

    public static ThreadIndex getNeighborThreadIndex(final Size size,
            final NodeIndex nodeIndex, final H hDirection, final V vDirection) {

        int i = nodeIndex.i;
        int j = nodeIndex.j;
        ColumnTemplate column = size.columnTemplate(j);
        int t = column.isShort(H.LEFT) ? 1 : 0;
        i = i * 2 + t;
        if (vDirection == V.NEXT) {
            j = j + 1;
        }
        if (hDirection == H.RIGHT) {
            i = i + 1;
        }
        return new ThreadIndex(i, j);
    }
}
