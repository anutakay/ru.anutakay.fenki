package ru.anutakay.fenki.model;

import java.util.HashMap;
import java.util.Map;

import ru.anutakay.fenki.model.thread.Thread;
import ru.anutakay.fenki.model.thread.ThreadFactory;

public class NodeImpl implements Node {

    private Arrow arrow = Arrow.NONE;

    private Map<H, Thread> threads;

    NodeImpl() {
        this(Arrow.NONE);
    }

    NodeImpl(final Arrow arrow) {
        setArrow(arrow);
        threads = new HashMap<H, Thread>();
        threads.put(H.LEFT, ThreadFactory.createEmptyThread());
        threads.put(H.RIGHT, ThreadFactory.createEmptyThread());
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
    public void setBegin(Thread left, Thread right) {
        threads.put(H.LEFT, left);
        threads.put(H.RIGHT, right);
    }

    @Override
    public void setBegin(H h, Thread thread) {
        threads.put(h, thread);
    }

    @Override
    public Thread getFirst() {
        if (this.arrow == Arrow.NONE) {
            return ThreadFactory.createEmptyThread();
        }
        return getBegin(getArrow().getBegin());
    }

    @Override
    public Thread getSecond() {
        if (this.arrow == Arrow.NONE) {
            return ThreadFactory.createEmptyThread();
        }
        if (getArrow().getBegin() != H.LEFT) {
            return getBegin(H.LEFT);
        } else {
            return getBegin(H.RIGHT);
        }
    }

    @Override
    public Thread getBegin(H h) {
        return threads.get(h);
    }

    @Override
    public Thread getEnd(H h) {
        if (this.getArrow() == Arrow.NONE) {
            return ThreadFactory.createEmptyThread();
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
