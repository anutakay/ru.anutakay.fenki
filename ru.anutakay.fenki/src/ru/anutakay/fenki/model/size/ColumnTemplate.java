package ru.anutakay.fenki.model.size;

import ru.anutakay.fenki.model.H;

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

    public boolean isShort(final H horizontal) {
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

    private boolean hasLeftCorner() {
        int j = column / 2;
        boolean first = size.first();
        boolean t = (first && j % 2 == 1) || (!first && j % 2 == 0);
        return t;
    }

    private boolean hasRightCorner() {
        if (size.threads() % 2 == 1) {
            return !hasLeftCorner();
        } else {
            return hasLeftCorner();
        }
    }

    public boolean hasCorner(H horizontal) {
        switch (horizontal) {
        case LEFT:
            return hasLeftCorner();
        case RIGHT:
            return hasRightCorner();
        default:
            return false;
        }
    }
}
