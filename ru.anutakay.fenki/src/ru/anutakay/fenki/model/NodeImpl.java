package ru.anutakay.fenki.model;

import java.util.HashMap;
import java.util.Map;

public class NodeImpl implements Node {

    private Arrow arrow = Arrow.NONE;

    private Map<H, Integer> threads;

    NodeImpl() {
        this(Arrow.NONE);
    }

    NodeImpl(final Arrow arrow) {
        setArrow(arrow);
        threads = new HashMap<H, Integer>();
        threads.put(H.LEFT, ThreadPool.createEmptyThread());
        threads.put(H.RIGHT, ThreadPool.createEmptyThread());
    }

    @Override
    public void setArrow(final Arrow arrow) {
        if (arrow == null) {
            throw new NullPointerException();
        }
        this.arrow = getCorrectDirection(arrow);
    }

    private Arrow getCorrectDirection(final Arrow arrow) {
        if (arrow == null) {
            return Arrow.NONE;
        } else {
            return arrow;
        }
    }

    @Override
    public Arrow getArrow() {
        return arrow;
    }

    @Override
    public void setBegin(Integer left, Integer right) {
        threads.put(H.LEFT, left);
        threads.put(H.RIGHT, right);
    }

    @Override
    public void setBegin(H h, Integer thread) {
        threads.put(h, thread);
    }

    @Override
    public Integer getFirst() {
        if (this.arrow == Arrow.NONE) {
            return ThreadPool.createEmptyThread();
        }
        return getBegin(getArrow().getBegin());
    }

    @Override
    public Integer getSecond() {
        if (this.arrow == Arrow.NONE) {
            return ThreadPool.createEmptyThread();
        }
        if (getArrow().getBegin() != H.LEFT) {
            return getBegin(H.LEFT);
        } else {
            return getBegin(H.RIGHT);
        }
    }

    @Override
    public Integer getBegin(H h) {
        return threads.get(h);
    }

    @Override
    public Integer getEnd(H h) {
        if (this.getArrow() == Arrow.NONE) {
            return ThreadPool.createEmptyThread();
        }

        if (getArrow().getEnd() == h) {
            return getFirst();
        } else {
            return getSecond();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arrow == null) ? 0 : arrow.hashCode());
        result = prime * result + ((threads == null) ? 0 : threads.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NodeImpl other = (NodeImpl) obj;
        if (arrow != other.arrow)
            return false;
        if (threads == null) {
            if (other.threads != null)
                return false;
        } else if (!threads.equals(other.threads))
            return false;
        return true;
    }

}
