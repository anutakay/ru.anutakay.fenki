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

    public Integer getThread(final ThreadIndex threadIndex) {
        return threads.getThread(threadIndex);
    }

    public Node getNode(final int i, final int j) {
        return getNode(new NodeIndex(j, i));
    }

    public Integer getThread(final int i, final int j) {
        return getThread(new ThreadIndex(i, j));
    }

    public Integer getCorner(final int j, final H hDirection) {
        if (hDirection == H.LEFT) {
            return getPrevThreadForNode(new NodeIndex(-1, j), H.RIGHT);
        } else {
            int n = size.columnTemplate(j).lenght();
            return getPrevThreadForNode(new NodeIndex(n, j), H.LEFT);
        }
    }

    public Integer getCorner(final CornerIndex cornerIndex) {
        return getCorner(cornerIndex.i, cornerIndex.hDirection);
    }

    public Integer getPrevThreadForNode(final NodeIndex nodeIndex,
            final H hDirection) {
        ThreadIndex threadIndex = NodeThreadNeighborer.getNeighborThreadIndex(
                this.size, nodeIndex, hDirection, V.PREV);
        Integer threadFragment = getThread(threadIndex);
        return threadFragment;
    }

    public void setNextThreadForNode(final NodeIndex nodeIndex,
            final H hDirection, final Integer threadID) {
        ThreadIndex threadIndex = NodeThreadNeighborer.getNeighborThreadIndex(
                this.size, nodeIndex, hDirection, V.NEXT);
        threads.setThread(threadIndex, threadID);
    }
}