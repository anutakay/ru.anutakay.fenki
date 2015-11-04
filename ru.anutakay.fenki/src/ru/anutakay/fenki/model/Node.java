package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.NodeImpl.Direction;
import ru.anutakay.fenki.model.NodeImpl.Horizontal;

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