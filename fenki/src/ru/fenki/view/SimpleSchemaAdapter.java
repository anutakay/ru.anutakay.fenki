package ru.fenki.view;

import java.awt.Color;
import java.awt.Point;

import ru.fenki.service.Index;

public class SimpleSchemaAdapter implements SchemaAdapterInterface {
	
	private int _a;
	private Rhomb mRhomb;
	
	public SimpleSchemaAdapter(int a){
		_a = a;
		mRhomb = new Rhomb(a).setColor(Color.WHITE); 
	}

	@Override
	public Rhomb getRhomb(SimpleIterator it) {
		return mRhomb.setLocation(rhombCenter(it));
	}
	
	private Point rhombCenter(Index index){
		return new Point(((index.j+1)*2+(index.i%2)-1)*_a, (index.i+1)*_a);
	}
}
