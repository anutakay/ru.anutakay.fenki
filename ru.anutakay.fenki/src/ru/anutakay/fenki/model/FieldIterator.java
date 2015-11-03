package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Node.HDirection;
import ru.anutakay.fenki.view.CornerIndex;
import ru.anutakay.fenki.view.Iterator2D;

public class FieldIterator extends Iterator2D {
	
	private Size dimensions;
	
	public FieldIterator(final Size dimensions) {
		this.dimensions = dimensions;
	}

	@Override
	public int getNumOfString() {
		return 1 + dimensions.getColumnNumber()*2;
	}

	@Override
	public int getNumOfColumn() {
		return 1 + dimensions.getThreadNumber()*2;
	}
	
	public ThreadIndex getThreadIndex(){
		int i = this.getI()/2;
		int j = this.getJ()/2;
		return new ThreadIndex(j, i);
	}
	
	public NodeIndex getNodeIndex() {
		int i = (this.getI()-1)/2;
		int j = ((this.getJ())-2)/4;
		return new NodeIndex( j, i);
	}
	
	public CornerIndex getCornerIndex() {
		int i = (this.getI()-1)/2;
		HDirection hDirection = HDirection.RIGHT;
		if(this.getJ() == 0){
			hDirection = HDirection.LEFT;
		}
		return new CornerIndex(i, hDirection);
	}
	
	public boolean isThread() {
		if(this.getI()%2 == 0){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isSemiNode() {
		if(this.getI()%2 == 0){
			return false;
		}
		int a = this.getJ()/2+1;
		if(isShortLeft()){
			a = a+1;
		}
		if(a%2 == 0){
			return false;
		}
		return true;
	}
	
	public boolean isNode() {
		if(this.isSemiNode()&&!this.isCorner()) {
			return true;
		}
		return false;
	}
	
	private boolean isShortLeft() {
		int a = this.getI()/2;
		if(this.getI()%2 == 0){
			return false;
		}
		
		if(dimensions.firstCrossIsNode()) {
			a=a+1;
		}
		a = a%2;
		if(a == 0 ){
			return false;
		}
		return true;
	}
	
	public boolean isCorner() {
		if(isSemiNode()&&(this.getJ() == 0||this.getJ() == this.getNumOfColumn()-1)) {
			return true;
		}else{
			return false;
		}
	}

}
