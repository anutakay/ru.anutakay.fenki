package ru.anutakay.fenki.model;

public class NodeImpl implements Node {

    private final static int NONE_THREAD = ThreadFragment.NONE_THREAD_ID;

    private Direction direction = Direction.NONE;

    private Horizontal begin = Horizontal.NONE;

    private Horizontal end = Horizontal.NONE;

    private int leftThreadID = NONE_THREAD;

    private int rightThreadID = NONE_THREAD;

    NodeImpl() {
        this(Direction.NONE);
    }

    NodeImpl(final Direction direction) {
        setDirection(direction);
    }

    // Зависимость начала и конца от направления реализована реактивно
    public void setDirection(final Direction direction) {
        this.direction = getCorrectDirection(direction);
        begin = getBeginByDirection(direction);
        end = getEndByDirection(direction);
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
    public void setLeftThreadID(final int leftThreadID) {
        this.leftThreadID = leftThreadID;
    }

    @Override
    public void setRightThreadID(final int rightThreadID) {
        this.rightThreadID = rightThreadID;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getFirstThreadID() {
        if (this.direction == Direction.NONE) {
            return NONE_THREAD;
        }
        return getBeginThreadID(begin);
    }

    @Override
    public int getSecondThreadID() {
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
    public int getBeginThreadID(Horizontal hDirection) {
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
    public int getEndThreadID(Horizontal hDirection) {
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
        result = prime * result
                + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + leftThreadID;
        result = prime * result + rightThreadID;
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
        if (direction != other.direction)
            return false;
        if (leftThreadID != other.leftThreadID)
            return false;
        if (rightThreadID != other.rightThreadID)
            return false;
        return true;
    }

}
