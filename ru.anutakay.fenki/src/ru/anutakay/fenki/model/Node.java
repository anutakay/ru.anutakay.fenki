package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Const.HDirection;

public class Node {
	
	private final static int NONE_THREAD = -1;
	
	public enum Direction { NONE, 
							RIGHT_DIRECT, 
							LEFT_DIRECT, 
							RIGHT_BACK, 
							LEFT_BACK }

	private Direction direction = Direction.NONE;
	
	private HDirection begin = HDirection.NONE;
	
	private HDirection end = HDirection.NONE;
	
	private int leftThreadID = NONE_THREAD;
	
	private int rightThreadID = NONE_THREAD;

	Node() {
		this(Direction.NONE);
	}

	Node(final Direction direction) {
		setDirection(direction);
	}

	//Зависимость начала и конца от направления реализована реактивно
	public void setDirection(final Direction direction) {
		this.direction = getCorectDirection(direction);
		this.begin =  getBeginByDirection(this.direction);
		this.end = getEndByDirection(this.direction);
	}
	
	private Direction getCorectDirection(final Direction direction) {
		if (direction == null) {
			return Direction.NONE;
		} else {
			return direction;
		}
	}
	
	private HDirection getBeginByDirection(final Direction direction) {
		
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
	
	private HDirection getEndByDirection(final Direction direction) {
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
	
	public void setLeftThreadID(final int leftThreadID) {
		this.leftThreadID = leftThreadID;
	}
	
	public void setRightThreadID(final int rightThreadID) {
		this.rightThreadID = rightThreadID;
	}
	
	public Direction getDirection() {
		return this.direction;
	}

	public int getFirstThreadID() {
		if(this.direction == Direction.NONE){
			return NONE_THREAD;
		}
		if(this.begin == HDirection.LEFT){
			return getLeftThreadID();
		} else {
			return getRightThreadID();
		}
	}
	
	public int getSecondThreadID() {
		if(this.direction == Direction.NONE){
			return NONE_THREAD;
		}
		if(this.begin != HDirection.LEFT){
			return getLeftThreadID();
		} else {
			return getRightThreadID();
		}
	}	
	
	HDirection getBegin() {
		return this.begin;
	}
	
	HDirection getEnd() {
		return this.end;
	}
	
	public int getLeftThreadID() {
		return this.leftThreadID;
	}
	
	public int getRightThreadID() {
		return this.rightThreadID;
	}
	
	public int getBottomThreadID(HDirection hDirection){
		if(this.getDirection() == Direction.NONE){
			return NONE_THREAD;
		}
		
		if (getEnd() == hDirection) {
			return getFirstThreadID();
		} else {
			return getSecondThreadID();
		}
	}
}
