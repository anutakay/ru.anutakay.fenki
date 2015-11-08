package ru.anutakay.fenki.model.color;

import java.awt.Color;

import ru.anutakay.fenki.model.thread.Thread;


public interface ThreadColorSchema {

    Color getColorByThreadID(Thread threadID);

}
