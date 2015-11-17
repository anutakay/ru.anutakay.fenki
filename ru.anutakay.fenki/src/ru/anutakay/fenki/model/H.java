package ru.anutakay.fenki.model;

public enum H {
    NONE, RIGHT, LEFT;

    public static H reverse(H h) {
        switch (h) {
        case LEFT:
            return RIGHT;
        case RIGHT:
            return LEFT;
        default:
            return NONE;
        }
    }
}
