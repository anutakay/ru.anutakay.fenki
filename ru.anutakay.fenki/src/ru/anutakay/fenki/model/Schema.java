package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.view.CornerIndex;

public class Schema {
    
    private static final int MIN_NUMBER_OF_COLUMN = 1;

    private static final int MIN_NUMBER_OF_THREAD = 2;
    
    protected Size size;
    private NodesImpl nodes;
    private Threads threads;
    Random r;
    
    public Schema() {
        this(new Size(MIN_NUMBER_OF_THREAD, MIN_NUMBER_OF_COLUMN, true));
    }

    public Schema(final Size size) {
        NodeFactory factory = new NodeFactory();
        this.size = size;
        this.nodes = new NodesImpl(size, factory);
        this.threads = new Threads(size);
    }

    public Schema(int threads, int columns, boolean first) {
        this(new Size(threads, columns, first));
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