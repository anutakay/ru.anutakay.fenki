package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Const.HDirection;

public class Node {
	
	public enum Direction { NONE, 
							DIRECT_RIGHT, 
							DIRECT_LEFT, 
							BACK_RIGHT, 
							BACK_LEFT }

	private Direction direction = Direction.NONE;
	
	private HDirection enter;
	
	private HDirection exit;
	
	private Thread firstColor = new Thread(-1);
	
	private Thread secondColor = new Thread(-1);
	
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
	
	private HDirection getEnterFromDirection(final Direction direction) {
		
		if(direction == Direction.NONE) {
			return HDirection.NONE;
		}
		
		if(direction == Direction.DIRECT_RIGHT ||
				direction == Direction.BACK_RIGHT) {
			return HDirection.RIGHT;
		} else {
			return HDirection.LEFT;
		}
		
	}
	
	private HDirection getExitFromDirection(final Direction direction) {
		if(direction == Direction.NONE) {
			return HDirection.NONE;
		}
		if(direction == Direction.DIRECT_RIGHT ||
				direction == Direction.BACK_LEFT) {
			return HDirection.LEFT;
		} else {
			return HDirection.RIGHT;
		}
	}

	public void setFirstColor(final Thread rightColor) {
		this.firstColor = rightColor;
	}
	
	public void setSecondColor(final Thread leftColor) {
		this.secondColor = leftColor;
	}

	public Direction getDirection() {
		return direction;
	}

	public Thread getFirstColor() {
		return firstColor;
	}
	
	public Thread getSecondColor() {
		return secondColor;
	}
	
	public HDirection getExit() {
		return exit;
	}
	
	public HDirection getEnter() {
		return enter;
	}

}
