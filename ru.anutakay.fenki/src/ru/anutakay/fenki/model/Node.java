package ru.anutakay.fenki.model;

public class Node {
	
	public enum Direction { NONE, 
							DIRECT_RIGHT, 
							DIRECT_LEFT, 
							BACK_RIGHT, 
							BACK_LEFT }

	private Direction direction = Direction.NONE;
	
	private int mFirstColor = -1;
	
	private int mSecondColor = -1;
	
	private int enter = -1;
	
	private int exit = -1;

	Node(final Direction d) {
		setDirection(d);
	}

	public void setDirection(final Direction d) {
		direction = d;
		enter =  getEnterFromDirection(d);
		exit = getExitFromDirection(d);
	}
	
	private int getEnterFromDirection(final Direction d) {
		
		if(d == Direction.NONE || d == null) {
			return -1;
		}
		
		if(d == Direction.DIRECT_RIGHT ||
				d == Direction.BACK_RIGHT) {
			return Const.RIGHT;
		} else {
			return Const.LEFT;
		}
		
	}
	
	private int getExitFromDirection(final Direction d) {
		if(d == Direction.NONE || d == null) {
			return -1;
		}
		if(d == Direction.DIRECT_RIGHT ||
				d == Direction.BACK_LEFT) {
			return Const.LEFT;
		} else {
			return Const.RIGHT;
		}
	}

	public void setFirstColor(final int color) {
		mFirstColor = color;
	}
	
	public void setSecondColor(final int color) {
		mSecondColor = color;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getFirstColor() {
		return mFirstColor;
	}
	
	public int getSecondColor() {
		return mSecondColor;
	}
	
	public int getExit() {
		return exit;
	}
	
	public int getEnter() {
		return enter;
	}

}
