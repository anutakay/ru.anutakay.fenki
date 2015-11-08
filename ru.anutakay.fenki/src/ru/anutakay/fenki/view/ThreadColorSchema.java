package ru.anutakay.fenki.view;

import java.awt.Color;

public class ThreadColorSchema implements IThreadColorSchema {

    private static final Color EMPTY_COLOR = Color.WHITE;

    IGroupColorSchema groupColorSchema;

    IColorSchema colorSchema;

    public ThreadColorSchema(final IGroupColorSchema groupColorSchema,
            final IColorSchema colorSchema) {
        this.groupColorSchema = groupColorSchema;
        this.colorSchema = colorSchema;
    }

    @Override
    public Color getColorByThreadID(final int threadID) {
        final Integer colorID = this.groupColorSchema.getColorID(threadID);
        final Color color = this.colorSchema.getColorByID(colorID);
        if (color == null) {
            return EMPTY_COLOR;
        } else {
            return color;
        }
    }
}
