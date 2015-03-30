package ru.anutakay.fenki.model;

import ru.anutakay.fenki.view.IGroupColorSchema;

public class GroupColorSchema implements IGroupColorSchema {
	
	@Override
	public int getColorID(final int threadID) {
		return (threadID)%3;
	}

}
