package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Const.HDirection;

public class Node {
	
	public enum Direction { NONE, 
							DIRECT_RIGHT, 
							DIRECT_LEFT, 
							BACK_RIGHT, 
							BACK_LEFT }

	private Direction direction = Direction.NONE;
	
	private HDirection begin;
	
	private HDirection end;
	
	private int firstThreadID = -1;
	
	private int secondThreadID = -1;
	
	Node() {
		this(Direction.NONE);
	}

	Node(final Direction direction) {
		setDirection(direction);
	}

	public void setDirection(final Direction direction) {
		this.direction = getCorectDirection(direction);
		this.begin =  getBeginFromDirection(this.direction);
		this.end = getEndFromDirection(this.direction);
	}
	
	private Direction getCorectDirection(final Direction direction) {
		if (direction == null) {
			return Direction.NONE;
		} else {
			return direction;
		}
	}
	
	private HDirection getBeginFromDirection(final Direction direction) {
		
		if (direction == Direction.NONE) {
			return HDirection.NONE;
		}
		
		if (direction == Direction.DIRECT_RIGHT ||
				direction == Direction.BACK_RIGHT) {
			return HDirection.RIGHT;
		} else {
			return HDirection.LEFT;
		}
		
	}
	
	private HDirection getEndFromDirection(final Direction direction) {
		if (direction == Direction.NONE) {
			return HDirection.NONE;
		}
		if (direction == Direction.DIRECT_RIGHT ||
				direction == Direction.BACK_LEFT) {
			return HDirection.LEFT;
		} else {
			return HDirection.RIGHT;
		}
	}

	public void setFirstThreadID(final int rightColor) {
		this.firstThreadID = rightColor;
	}
	
	public void setSecondThreadID(final int leftColor) {
		this.secondThreadID = leftColor;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getFirstThreadID() {
		return firstThreadID;
	}
	
	public int getSecondThreadID() {
		return secondThreadID;
	}	
	
	public HDirection getBegin() {
		return begin;
	}
	
	public HDirection getEnd() {
		return end;
	}
	


}
