package ru.anutakay.fenki.model.color;


public class GroupColorSchemaImpl implements GroupColorSchema {

    @Override
    public int getColorID(final int threadID) {
        return (threadID) % 3;
    }

}
