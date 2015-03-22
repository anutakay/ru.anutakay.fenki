package ru.anutakay.fenki.model;

import java.awt.Color;

public class ColorSchema {
	
	Color[] colors = {new Color(180, 50 , 200), new Color(0, 200, 180)};
	
	public Color getColorByID(final int colorID) { 
		if (checkID(colorID)) {
			return colors[colorID];
		} else {
			return null;
		}
	}
	
	private boolean checkID(final int colorID) {
		if (colorID < 0||colorID >= colors.length) {
			return false;
		} else {
			return true;
		}
	}

}
