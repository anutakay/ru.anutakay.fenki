package ru.fenki.model;

import ru.fenki.service.Dimension;



public class NodeStoreDimensions {

	private boolean mFirst;
	private int mThreadNumber;
	private int mColumnNumber;

	public NodeStoreDimensions(int threadNumber, int columnNumber, boolean first) {
		if (threadNumber < 2) {
			threadNumber = 2;
		}
		if (columnNumber < 1) {
			columnNumber = 2;
		}
		mThreadNumber = threadNumber;
		mColumnNumber = columnNumber;
		mFirst = first;
	}

	public int getThreadNumber() {
		return mThreadNumber;
	}

	public int getColumnNumber() {
		return mColumnNumber;
	}

	public boolean getFirst() {
		return mFirst;
	}

	public int numberOfNodeInColumn(int j) {
		int i = mThreadNumber/ 2
				- ((mThreadNumber % 2 == 0 && j % 2 == 1 && getFirst())
						|| (mThreadNumber % 2 == 0 && j % 2 == 0 && !mFirst) ? 1
						: 0);
		//System.out.println("в " + j + " ряду " + i + " узлов ");
		return i;
	}
	
	public boolean isShort(int j, int left_right) {
		boolean left = (j % 2 == 1 && getFirst())
				|| (j % 2 == 0 && !getFirst());
		if (left_right == Const.LEFT) {
			return left;
		} else {
			return (left &&getThreadNumber() % 2 == 0)
					|| (!left && getThreadNumber() % 2 == 1);
		}
	}
	
	public boolean leftCorner(int j){
		j=j/2;
		boolean first = getFirst();
		boolean t = (first && j % 2 == 1) || (!first && j % 2 == 0);
		return t;
	}
	
	public boolean rightCorner(int j){
		if(getThreadNumber() % 2 == 1){
			return !leftCorner(j);
		}else{
			return leftCorner(j);
		}
	}
	
	boolean isValidIndex(int i, int j){
		if(i<0||i >= getColumnNumber()*2+1){
			return false;
		}
		if(j<-1||j>= getThreadNumber()){
			
		}
		if((j>-1)||(i%2 != 0)){
			return true;
		}
		return false;
	}
	
	public boolean isThread(int i, int j){
		if(!isValidIndex(i,j)){
			return false;
		}
		if(i%2 == 1){
			return false;
		}
		return true;
	}
	
	public boolean isNode(int i, int j){		
		if(!isValidIndex(i,j)){
			return false;
		}
		if(i%2==0){
			return false;
		}
		if(j<0||j >= numberOfNodeInColumn(i/2)*2){
			return false;
		}
		if(j%2!=(isShort(i/2, Const.LEFT) ? 1 : 0)){
			return false;
		}
		return true;
	}
	
	public boolean isEmpty(int i, int j){		
		if(!isValidIndex(i,j)){
			return false;
		}
		if(i%2==0){
			return false;
		}
		if(j<0||j >= numberOfNodeInColumn(i/2+1)*2){
			return false;
		}
		if(j%2==(isShort(i/2, Const.LEFT) ? 1 : 0)){
			return false;
		}
		return true;
	}
	
	public boolean isCorner(int i, int j){
		if(!isValidIndex(i,j)){
			return false;
		}
		if(j==-1&&(isShort(i/2, Const.LEFT))){
			return true;
		}
		if(j==numberOfNodeInColumn(i)*2
				&&i%2==1&&(isShort(i/2, Const.RIGHT))){
			return true;
		}
		return false;
	}
	
	public Dimension toDimension(){
		return new Dimension(mColumnNumber*2+1, mThreadNumber+1);
	}

}
