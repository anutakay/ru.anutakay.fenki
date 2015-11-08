package ru.anutakay.fenki.view;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Figure {

    Point center;

    abstract void paintComponent(Graphics g);

    Figure(Point p) {
        center = p;
    }

}
