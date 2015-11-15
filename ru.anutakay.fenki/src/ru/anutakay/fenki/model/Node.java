package ru.anutakay.fenki.model;

public interface Node {

    void setArrow(Arrow arrow);

    Arrow getArrow();

    void setBegin(H horizontal, Integer id);

    Integer getBegin(H horizontal);

    Integer getEnd(H horizontal);

    Integer getFirst();

    Integer getSecond();

    void setBegin(Integer left, Integer right);
}