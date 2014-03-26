package ru.fenki.view;

import ru.fenki.service.Dimension;

public class SchemaIterator extends SimpleIterator{

	public SchemaIterator(Dimension d) {
		super(d);
	}
	
	@Override
	public boolean isSatisfies(){
		if(!super.isSatisfies()){
			return false;
		}
		if(j == 0 && i%2 == 0){
			return false;
		}else{
			return true;
		}
	}
	
}
