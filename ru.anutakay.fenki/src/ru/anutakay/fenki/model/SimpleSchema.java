package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.view.CornerIndex;

public class SimpleSchema {
    protected Size size;
    private NodesImpl nodes;
    private Threads threads;
    Random r;

    public SimpleSchema(final Size size) {
        NodeFactory factory = new NodeFactory();
        this.size = size;
        this.nodes = new NodesImpl(size, factory);
        this.threads = new Threads(size);
    }

    public Size getSize() {
        return size;
    }

    public Node getNode(final NodeIndex nodeIndex) {
        return nodes.getNode(nodeIndex);
    }

    public ThreadFragment getThreadFragment(final ThreadIndex threadIndex) {
        return threads.getThread(threadIndex);
    }

    public Node getNode(final int i, final int j) {
        return getNode(new NodeIndex(j, i));
    }

    public ThreadFragment getThreadFragment(final int i, final int j) {
        return getThreadFragment(new ThreadIndex(i, j));
    }

    public Thread getCorner(final int j, final H hDirection) {
        if (hDirection == H.LEFT) {
            return getPrevThreadForNode(new NodeIndex(-1, j), H.RIGHT);
        } else {
            int n = size.columnTemplate(j).lenght();
            return getPrevThreadForNode(new NodeIndex(n, j), H.LEFT);
        }
    }

    public Thread getCorner(final CornerIndex cornerIndex) {
        return getCorner(cornerIndex.i, cornerIndex.hDirection);
    }

    public Thread getPrevThreadForNode(final NodeIndex nodeIndex,
            final H hDirection) {
        ThreadIndex threadIndex = NodeThreadNeighborer.getNeighborThreadIndex(
                this.size, nodeIndex, hDirection, V.PREV);
        ThreadFragment threadFragment = getThreadFragment(threadIndex);
        return threadFragment.getThread();
    }

    public void setNextThreadForNode(final NodeIndex nodeIndex,
            final H hDirection, final Thread threadID) {
        ThreadIndex threadIndex = NodeThreadNeighborer.getNeighborThreadIndex(
                this.size, nodeIndex, hDirection, V.NEXT);
        threads.getThread(threadIndex).setTop(reverseDirection(hDirection));
        threads.getThread(threadIndex).setThread(threadID);
    }

    private H reverseDirection(final H hDirection) {
        if (hDirection == H.RIGHT) {
            return H.LEFT;
        } else if (hDirection == H.LEFT) {
            return H.RIGHT;
        }
        return H.NONE;
    }
}