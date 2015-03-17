package ru.anutakay.fenki.model;

public class Node {
	
	public enum Direction { NONE, 
							DIRECT_RIGHT, 
							DIRECT_LEFT, 
							BACK_RIGHT, 
							BACK_LEFT }

	private Direction direction = Direction.NONE;
	
	private int enter = -1;
	
	private int exit = -1;
	
	private int firstColor = -1;
	
	private int secondColor = -1;
	
	Node() {
		this(Direction.NONE);
	}

	Node(final Direction direction) {
		setDirection(direction);
	}

	public void setDirection(final Direction direction) {
		this.direction = getCorectDirection(direction);
		enter =  getEnterFromDirection(this.direction);
		exit = getExitFromDirection(this.direction);
	}
	
	private Direction getCorectDirection(final Direction direction) {
		if(direction == null) {
			return Direction.NONE;
		} else {
			return direction;
		}
	}
	
	private int getEnterFromDirection(final Direction direction) {
		
		if(direction == Direction.NONE) {
			return -1;
		}
		
		if(direction == Direction.DIRECT_RIGHT ||
				direction == Direction.BACK_RIGHT) {
			return Const.RIGHT;
		} else {
			return Const.LEFT;
		}
		
	}
	
	private int getExitFromDirection(final Direction direction) {
		if(direction == Direction.NONE) {
			return -1;
		}
		if(direction == Direction.DIRECT_RIGHT ||
				direction == Direction.BACK_LEFT) {
			return Const.LEFT;
		} else {
			return Const.RIGHT;
		}
	}

	public void setFirstColor(final int firstColor) {
		this.firstColor = firstColor;
	}
	
	public void setSecondColor(final int secondColor) {
		this.secondColor = secondColor;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getFirstColor() {
		return firstColor;
	}
	
	public int getSecondColor() {
		return secondColor;
	}
	
	public int getExit() {
		return exit;
	}
	
	public int getEnter() {
		return enter;
	}

}
