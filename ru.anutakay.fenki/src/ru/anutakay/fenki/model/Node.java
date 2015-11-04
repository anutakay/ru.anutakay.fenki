package ru.anutakay.fenki.model;

public interface Node {

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