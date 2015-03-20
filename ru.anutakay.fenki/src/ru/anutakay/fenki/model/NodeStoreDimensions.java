package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Const.HDirection;

public class NodeStoreDimensions {

	private boolean first;
	
	private int numberOfThreads;
	
	private int numberOfColumns;

	public NodeStoreDimensions(final int numberOfThreads, final int numberOfColumns, final boolean first) {
		this.numberOfThreads = numberOfThreads;
		this.numberOfColumns = numberOfColumns;
		this.first = first;
		
		if (this.numberOfThreads < 2) {
			this.numberOfThreads = 2;
		}
		if (this.numberOfColumns < 1) {
			this.numberOfColumns = 2;
		}
		
	}

	public int getThreadNumber() {
		return numberOfThreads;
	}

	public int getColumnNumber() {
		return numberOfColumns;
	}

	public boolean getFirst() {
		return first;
	}

	public int numberOfNodeInColumn(final int columnNumber) {
		int j = columnNumber;
		int i = numberOfThreads/ 2
				- ((numberOfThreads % 2 == 0 && j % 2 == 1 && getFirst())
						|| (numberOfThreads % 2 == 0 && j % 2 == 0 && !first) ? 1
						: 0);
		//System.out.println("в " + j + " ряду " + i + " узлов ");
		return i;
	}
	
	public boolean isShort(final int columnNumber, final HDirection left2) {
		int j = columnNumber;
		boolean left = (j % 2 == 1 && getFirst())
				|| (j % 2 == 0 && !getFirst());
		if (left2 == HDirection.LEFT) {
			return left;
		} else {
			return (left && getThreadNumber() % 2 == 0)
					|| (!left && getThreadNumber() % 2 == 1);
		}
	}
	
	public boolean leftCorner(final int columnNumber){
		int j = columnNumber;
		j = j/2;
		boolean first = getFirst();
		boolean t = (first && j%2 == 1) || (!first && j%2 == 0);
		return t;
	}
	
	public boolean rightCorner(final int columnNumber){
		int j = columnNumber;
		if(getThreadNumber()%2 == 1){
			return !leftCorner(j);
		}else{
			return leftCorner(j);
		}
	}
	
	boolean isValidIndex(final int i, final int j){
		if(i < 0 || i >= getColumnNumber()*2 + 1){
			return false;
		}
		if(j < -1 || j >= getThreadNumber()){
			
		}
		if((j>-1)||(i%2 != 0)){
			return true;
		}
		return false;
	}
	
	public boolean isThread(final int i, final int j){
		if(!isValidIndex(i,j)){
			return false;
		}
		if(i%2 == 1){
			return false;
		}
		return true;
	}
	
	public boolean isNode(final int i, final int j){		
		if(!isValidIndex(i, j)){
			return false;
		}
		if(i%2 == 0){
			return false;
		}
		if(j < 0 || j >= numberOfNodeInColumn(i/2)*2){
			return false;
		}
		if(j%2 != (isShort(i/2, HDirection.LEFT) ? 1 : 0)){
			return false;
		}
		return true;
	}
	
	public boolean isEmpty(final int i, final int j){		
		if(!isValidIndex(i, j)){
			return false;
		}
		if(isCorner(i, j)||isNode(i, j)||isThread(i, j)){
			return false;
		}
		return true;
	}
	
	public boolean isCorner(final int i, final int j){
		if(!isValidIndex(i,j)){
			return false;
		}
		
		if(i%2 == 0){
			return false;
		}
		
		if(isNode(i, j)){
			return false;
		}
		
		if(j == -1 && (isShort(i/2, HDirection.LEFT))){
			return true;
		}
		

		if(j%2 == 0 && j == numberOfNodeInColumn(i)*2 && (isShort(i/2, HDirection.RIGHT))){
			if(isNode(i, j - 1) || j == 0){
				return false;
			}
			return true;
		}
		if(j%2 == 1 && (j + 1 == numberOfNodeInColumn(i)*2 || j - 1 == numberOfNodeInColumn(i)*2) && (isShort(i/2, HDirection.RIGHT))){
			if(isNode(i, j - 1) || j == 0){
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	public boolean isEmptyCorner(final int i, final int j){
		if(!isValidIndex(i, j)){
			return false;
		}
		if(j == -1 && !(isShort(i/2, HDirection.LEFT))){
			return true;
		}
		if(j == numberOfNodeInColumn(i)*2
				&& i%2 == 1 && !(isShort(i/2, HDirection.RIGHT))){
			return true;
		}
		return false;
	}

}
