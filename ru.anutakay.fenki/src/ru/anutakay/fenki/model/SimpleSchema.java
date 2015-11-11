package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.model.thread.Thread;
import ru.anutakay.fenki.view.CornerIndex;

public class SimpleSchema {
    protected Size size;
    private NodesImpl nodes;
    private Fragments threads;
    Random r;

    public SimpleSchema(final Size size) {
        NodeFactory factory = new NodeFactory();
        this.size = size;
        this.nodes = new NodesImpl(size, factory);
        this.threads = new Fragments(size);
    }

    public Size getSize() {
        return size;
    }

    public Node getNode(final NodeIndex nodeIndex) {
        return nodes.getNode(nodeIndex);
    }

    public Thread getThread(final ThreadIndex threadIndex) {
        return threads.getThread(threadIndex);
    }

    public Node getNode(final int i, final int j) {
        return getNode(new NodeIndex(j, i));
    }

    public Thread getThread(final int i, final int j) {
        return getThread(new ThreadIndex(i, j));
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
        Thread threadFragment = getThread(threadIndex);
        return threadFragment;
    }

    public void setNextThreadForNode(final NodeIndex nodeIndex,
            final H hDirection, final Thread threadID) {
        ThreadIndex threadIndex = NodeThreadNeighborer.getNeighborThreadIndex(
                this.size, nodeIndex, hDirection, V.NEXT);
        threads.setThread(threadIndex, threadID);
    }
}