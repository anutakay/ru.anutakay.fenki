package ru.anutakay.fenki.model.size;

import ru.anutakay.fenki.model.H;
import ru.anutakay.fenki.model.NodeIndex;

public class ColumnTemplate {

    int column;

    Size size;

    ColumnTemplate(final int column, final Size size) {
        this.column = column;
        this.size = size;
    }

    public int lenght() {
        final int j = column;
        final int threads = size.threads();
        final boolean first = size.first();
        final int i = threads
                / 2
                - ((threads % 2 == 0 && j % 2 == 1 && first)
                        || (threads % 2 == 0 && j % 2 == 0 && !first) ? 1 : 0);
        return i;
    }

    public boolean hasCorner(final H horizontal) {
        final int j = column;
        boolean first = size.first();
        final boolean left = (j % 2 == 1 && first) || (j % 2 == 0 && !first);
        if (horizontal == H.LEFT) {
            return left;
        } else {
            return (left && size.threads() % 2 == 0)
                    || (!left && size.threads() % 2 == 1);
        }
    }

    public NodeIndex getCornerIndex(H h) {
        switch (h) {
        case LEFT:
            return new NodeIndex(-1, column);
        case RIGHT:
            return new NodeIndex(lenght(), column);
        default:
            return null;
        }
    }
}
