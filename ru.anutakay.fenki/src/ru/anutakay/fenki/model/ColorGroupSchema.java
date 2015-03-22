package ru.anutakay.fenki.model;

public class ColorGroupSchema {
	
	public int getColorID(int threadID){
		return (threadID)%3;
	}

}
