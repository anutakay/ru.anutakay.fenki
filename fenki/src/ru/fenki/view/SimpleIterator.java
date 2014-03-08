package ru.fenki.view;

import ru.fenki.service.Dimension;
import ru.fenki.service.Index;

public class SimpleIterator extends Index {
	
	protected Dimension mDimension;

	private SimpleIterator(int i, int j) {
		super(i, j);
	}
	
	public SimpleIterator(Dimension d) {
		this(-1, -1);
		mDimension = d;
	}
	
	public SimpleIterator begin() {
		i = 0; 
		j = -1;
		return this;
	}
	
	public SimpleIterator next(){
		do{
			j++;
			if(j >= getDimension().getColumnNumber()){
				j = 0;
				i++;
				if(i >= getDimension().getStringNumber()){
					return null;
				}
			}
		}while(!isSatisfies());
		return this;
	}
	
	public SimpleIterator end() {
		i = mDimension.getStringNumber()-1; 
		j = mDimension.getColumnNumber()-1;
		return this;
	}
	
	public 	Dimension getDimension(){
		return mDimension;
	}
	
	protected boolean isSatisfies(){
		if(j<0||i<0){
			return false;
		}
		return true;
	}
}
