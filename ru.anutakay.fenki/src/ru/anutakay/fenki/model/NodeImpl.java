package ru.anutakay.fenki.model;

public class NodeImpl implements Node {

    private final static ThreadID NONE_THREAD = ThreadID.emptyID();

    private Direction direction = Direction.NONE;

    private Horizontal begin = Horizontal.NONE;

    private Horizontal end = Horizontal.NONE;

    private ThreadID leftThreadID = NONE_THREAD;

    private ThreadID rightThreadID = NONE_THREAD;

    NodeImpl() {
        this(Direction.NONE);
    }

    NodeImpl(final Direction direction) {
        setDirection(direction);
    }

    // Зависимость начала и конца от направления реализована реактивно
    public void setDirection(final Direction direction) {
        this.direction = getCorrectDirection(direction);
        begin = getBeginByDirection(this.direction);
        end = getEndByDirection(this.direction);
    }

    private Direction getCorrectDirection(final Direction direction) {
        if (direction == null) {
            return Direction.NONE;
        } else {
            return direction;
        }
    }

    private Horizontal getBeginByDirection(final Direction direction) {

        if (direction == Direction.NONE) {
            return Horizontal.NONE;
        }

        if (direction == Direction.RIGHT_DIRECT
                || direction == Direction.RIGHT_BACK) {
            return Horizontal.RIGHT;
        } else {
            return Horizontal.LEFT;
        }

    }

    private Horizontal getEndByDirection(final Direction direction) {
        if (direction == Direction.NONE) {
            return Horizontal.NONE;
        }
        if (direction == Direction.RIGHT_DIRECT
                || direction == Direction.LEFT_BACK) {
            return Horizontal.LEFT;
        } else {
            return Horizontal.RIGHT;
        }
    }

    @Override
    public void setLeftThreadID(final ThreadID id) {
        leftThreadID = id;
    }

    @Override
    public void setRightThreadID(final ThreadID id) {
        rightThreadID = id;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public ThreadID getFirstThreadID() {
        if (this.direction == Direction.NONE) {
            return NONE_THREAD;
        }
        return getBeginThreadID(begin);
    }

    @Override
    public ThreadID getSecondThreadID() {
        if (this.direction == Direction.NONE) {
            return NONE_THREAD;
        }
        if (this.begin != Horizontal.LEFT) {
            return getBeginThreadID(Horizontal.LEFT);
        } else {
            return getBeginThreadID(Horizontal.RIGHT);
        }
    }

    @Override
    public Horizontal getBegin() {
        return this.begin;
    }

    @Override
    public Horizontal getEnd() {
        return this.end;
    }
    
    @Override
    public ThreadID getBeginThreadID(Horizontal hDirection) {
       switch (hDirection) {
       case LEFT:
           return leftThreadID;
       case RIGHT:
           return rightThreadID;
       default: 
           return NONE_THREAD;
       }
    }

    @Override
    public ThreadID getEndThreadID(Horizontal hDirection) {
        if (this.getDirection() == Direction.NONE) {
            return NONE_THREAD;
        }

        if (getEnd() == hDirection) {
            return getFirstThreadID();
        } else {
            return getSecondThreadID();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((begin == null) ? 0 : begin.hashCode());
        result = prime * result
                + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result
                + ((leftThreadID == null) ? 0 : leftThreadID.hashCode());
        result = prime * result
                + ((rightThreadID == null) ? 0 : rightThreadID.hashCode());
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
        if (begin != other.begin)
            return false;
        if (direction != other.direction)
            return false;
        if (end != other.end)
            return false;
        if (leftThreadID == null) {
            if (other.leftThreadID != null)
                return false;
        } else if (!leftThreadID.equals(other.leftThreadID))
            return false;
        if (rightThreadID == null) {
            if (other.rightThreadID != null)
                return false;
        } else if (!rightThreadID.equals(other.rightThreadID))
            return false;
        return true;
    }
}
