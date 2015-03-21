package ru.anutakay.fenki.view;

public class Iterator1D extends Iterator {
	
	private int i = 0;
	private int num = 10;

	@Override
	public Iterator next() {
		i++;
		if(i >= num){
			return null;
		}else{
			return this;
		}
	}

	@Override
	public Iterator previous() {
		i--;
		if(i<0){
			return null;
		}else{
			return this;
		}
	}

	@Override
	public Iterator begin() {
		i=0;
		return this;
	}

	@Override
	public Iterator end() {
		i = num-1;
		return this;
	}
	
	public int getI(){
		return i;
	}
	

}
