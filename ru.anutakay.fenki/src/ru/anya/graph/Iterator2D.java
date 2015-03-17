package ru.anya.graph;

public abstract class Iterator2D extends Iterator 
//Доделать счет с конца
{	
	private int i = 0;
	private int j = 0;
	private int n = 0;

	@Override
	public final Iterator2D next() {
		do {
			n++;
			setIJ();
		} while(!this.isExist() & n < getNum());
		if(n >= getNum()){
			return null;
		}else{
			return this;
		}
	}

	@Override
	public final Iterator2D previous() {
		n--;
		setIJ();
		if(n<0){
			return null;
		}else{
			return this;
		}
	}

	@Override
	public final Iterator2D begin() {
		n = 0;
		setIJ();
		while(!this.isExist() & n < getNum()) {
			n++;
			setIJ();
		};
		return this;
	}

	@Override
	public final Iterator2D end() {
		n = getNum()-1;
		setIJ();
		return this;
	}
	
	public final int getI() {
		return i;
	}
	
	public final int getJ() {
		return j;
	}
	
	private void setIJ() {
		i = n/getNumOfColumn();
		j = n%getNumOfColumn();
	}
	
	protected boolean isExist() {
		if((i+j)%2 == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public abstract int getNumOfString();
	
	public abstract int getNumOfColumn();
	
	private int getNum(){
		return getNumOfString()*getNumOfColumn();
	}
	
}
