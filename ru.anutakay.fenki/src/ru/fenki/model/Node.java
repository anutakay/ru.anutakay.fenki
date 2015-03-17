package ru.fenki.model;



public class Node {

	public final static int NODE_NONE = -1;
	public final static int NODE_DIRECT_RIGHT = 0;
	public final static int NODE_DIRECT_LEFT = 1;
	public final static int NODE_BACK_RIGHT = 2;
	public final static int NODE_BACK_LEFT = 3;

	private int direction = -1;
	private int mFirstColor = -1;
	private int mSecondColor = -1;
	private int enter = -1;
	private int exit = -1;

	Node(int d) {
		setDirection(d);
	}

	public void setDirection(int d) {
		direction = d;
		if(direction == -1){
			enter = exit = -1;
			return;
		}
		if(direction == NODE_DIRECT_RIGHT ||direction == NODE_BACK_RIGHT){
			enter = Const.RIGHT;
		}else{
			enter = Const.LEFT;
		}
		if(direction == NODE_DIRECT_RIGHT ||direction == NODE_BACK_LEFT){
			exit = Const.LEFT;
		}else{
			exit = Const.RIGHT;
		}
	}

	public void setColor(int c) {
		mFirstColor = c;
	}
	
	public void setSecondColor(int c){
		mSecondColor = c;
	}

	public int getDirection() {
		return direction;
	}

	public int getColor() {
		return mFirstColor;
	}
	
	public int getSecondColor(){
		return mSecondColor;
	}
	
	public int getExit(){
		return exit;
	}
	
	public int getEnter(){
		return enter;
	}

}
