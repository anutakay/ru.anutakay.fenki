package ru.anutakay.fenki.model;

public interface Node {
    
    public enum Direction {
        NONE, RIGHT_DIRECT, LEFT_DIRECT, RIGHT_BACK, LEFT_BACK
    }

    public enum Horizontal {
        NONE, RIGHT, LEFT
    }

    public enum Vertical {
        NONE, PREV, NEXT
    }

    void setLeftThreadID(int leftThreadID);

    void setRightThreadID(int rightThreadID);

    Direction getDirection();

    int getFirstThreadID();

    int getSecondThreadID();

    Horizontal getBegin();

    Horizontal getEnd();

    int getEndThreadID(Horizontal hDirection);

    int getBeginThreadID(Horizontal hDirection);

}