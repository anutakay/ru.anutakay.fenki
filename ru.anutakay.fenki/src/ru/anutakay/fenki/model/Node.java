package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.thread.Thread;

public interface Node {

    void setArrow(Arrow arrow);

    Arrow getArrow();

    void setBegin(H horizontal, Thread id);

    void setBegin(Thread left, Thread right);

    Thread getBegin(H horizontal);

    Thread getEnd(H horizontal);

    Thread getFirst();

    Thread getSecond();
}