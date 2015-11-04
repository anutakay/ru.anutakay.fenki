package ru.anutakay.fenki.model;

public enum Arrow {
    NONE, RIGHT_DIRECT, LEFT_DIRECT, RIGHT_BACK, LEFT_BACK;

    public H getBegin() {
        if (this == Arrow.NONE) {
            return H.NONE;
        }
        if (this == Arrow.RIGHT_DIRECT || this == Arrow.RIGHT_BACK) {
            return H.RIGHT;
        } else {
            return H.LEFT;
        }
    }

    public H getEnd() {
        if (this == Arrow.NONE) {
            return H.NONE;
        }
        if (this == Arrow.RIGHT_DIRECT || this == Arrow.LEFT_BACK) {
            return H.LEFT;
        } else {
            return H.RIGHT;
        }
    }
}
