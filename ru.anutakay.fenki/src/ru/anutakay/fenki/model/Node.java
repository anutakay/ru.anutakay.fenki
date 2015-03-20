package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Const.HDirection;

public class Node {
	
	public enum Direction { NONE, 
							RIGHT_DIRECT, 
							LEFT_DIRECT, 
							RIGHT_BACK, 
							LEFT_BACK }

	private Direction direction = Direction.NONE;
	
	private HDirection begin = HDirection.NONE;
	
	private HDirection end = HDirection.NONE;
	
	private int leftThreadID = -1;
	
	private int rightThreadID = -1;

	Node() {
		this(Direction.NONE);
	}

	Node(final Direction direction) {
		setDirection(direction);
	}

	//Зависимость начала и конца от направления реализована реактивно
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
		
		if (direction == Direction.RIGHT_DIRECT ||
				direction == Direction.RIGHT_BACK) {
			return HDirection.RIGHT;
		} else {
			return HDirection.LEFT;
		}
		
	}
	
	private HDirection getEndFromDirection(final Direction direction) {
		if (direction == Direction.NONE) {
			return HDirection.NONE;
		}
		if (direction == Direction.RIGHT_DIRECT ||
				direction == Direction.LEFT_BACK) {
			return HDirection.LEFT;
		} else {
			return HDirection.RIGHT;
		}
	}
	
	public void setLeftTopThreadID(final int leftThreadID) {
		this.leftThreadID = leftThreadID;
	}
	
	public void setRightTopThreadID(final int rightThreadID) {
		this.rightThreadID = rightThreadID;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public int getFirstThreadID() {
		if(this.getDirection() == Direction.NONE){
			return -1;
		}
		if(this.getBegin() == HDirection.LEFT){
			return getLeftTopThreadID();
		} else {
			return getRightTopThreadID();
		}
	}
	
	public int getSecondThreadID() {
		if(this.getDirection() == Direction.NONE){
			return -1;
		}
		if(this.getBegin() != HDirection.LEFT){
			return getLeftTopThreadID();
		} else {
			return getRightTopThreadID();
		}
	}	
	
	HDirection getBegin() {
		return begin;
	}
	
	HDirection getEnd() {
		return end;
	}
	
	public int getLeftTopThreadID() {
		return leftThreadID;
	}
	
	public int getRightTopThreadID() {
		return rightThreadID;
	}
	
	public int getLeftBottomThreadID() {
		return getBottomThreadID(HDirection.LEFT);
	}
	
	public int getRightBottomThreadID() {
		return getBottomThreadID(HDirection.RIGHT);
	}
	
	public int getBottomThreadID(HDirection hDirection){
		if(this.getDirection() == Direction.NONE){
			return -1;
		}
		
		if (getEnd() == hDirection) {
			return getFirstThreadID();
		} else {
			return getSecondThreadID();
		}
	}
}
