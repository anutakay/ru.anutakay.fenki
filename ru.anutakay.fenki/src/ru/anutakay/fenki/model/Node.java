package ru.anutakay.fenki.model;

public interface Node {

    void setLeftThreadID(ThreadID id);
    
    void setRightThreadID(ThreadID id);

    Direction getDirection();

    ThreadID getFirstThreadID();

    ThreadID getSecondThreadID();

    Horizontal getBegin();

    Horizontal getEnd();

    ThreadID getEndThreadID(Horizontal hDirection);

    ThreadID getBeginThreadID(Horizontal hDirection);

   

}