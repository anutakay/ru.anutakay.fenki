package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.view.CornerIndex;
import ru.anutakay.fenki.view.Iterator2D;

public class FieldIterator extends Iterator2D {

    private Size size;

    public FieldIterator(final Size size) {
        this.size = size;
    }

    @Override
    public int getNumOfString() {
        return 1 + size.columns() * 2;
    }

    @Override
    public int getNumOfColumn() {
        return 1 + size.threads() * 2;
    }

    public ThreadIndex getThreadIndex() {
        int i = this.getI() / 2;
        int j = this.getJ() / 2;
        return new ThreadIndex(j, i);
    }

    public NodeIndex getNodeIndex() {
        int i = (this.getI() - 1) / 2;
        int j = ((this.getJ()) - 2) / 4;
        return new NodeIndex(j, i);
    }

    public CornerIndex getCornerIndex() {
        int i = (this.getI() - 1) / 2;
        H hDirection = H.RIGHT;
        if (this.getJ() == 0) {
            hDirection = H.LEFT;
        }
        return new CornerIndex(i, hDirection);
    }

    public boolean isThread() {
        if (this.getI() % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSemiNode() {
        if (this.getI() % 2 == 0) {
            return false;
        }
        int a = this.getJ() / 2 + 1;
        if (isShortLeft()) {
            a = a + 1;
        }
        if (a % 2 == 0) {
            return false;
        }
        return true;
    }

    public boolean isNode() {
        if (this.isSemiNode() && !this.isCorner()) {
            return true;
        }
        return false;
    }

    private boolean isShortLeft() {
        int a = this.getI() / 2;
        if (this.getI() % 2 == 0) {
            return false;
        }

        if (size.first()) {
            a = a + 1;
        }
        a = a % 2;
        if (a == 0) {
            return false;
        }
        return true;
    }

    public boolean isCorner() {
        if (isSemiNode()
                && (this.getJ() == 0 || this.getJ() == this.getNumOfColumn() - 1)) {
            return true;
        } else {
            return false;
        }
    }

}
