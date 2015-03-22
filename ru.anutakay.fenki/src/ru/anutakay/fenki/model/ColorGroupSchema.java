package ru.anutakay.fenki.model;

public class ColorGroupSchema {
	
	public int getColorID(final int threadID) {
		return (threadID)%3;
	}

}
