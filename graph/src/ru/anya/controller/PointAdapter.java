package ru.anya.controller;

import java.awt.Point;

import ru.anya.graph.Iterator2D;

public class PointAdapter implements Adapter<Iterator2D, Point> {
	
	int a = 7;
	Iterator2D mIterator;
	
	public PointAdapter(Iterator2D it){
		mIterator = it;
	}
	
	@Override
	public Point getObject(Iterator2D it) {
		return new Point(it.getJ()*2*a+2*a, it.getI()*2*a+2*a);
	}

	@Override
	public Iterator2D getIterator() {
		return mIterator;		
	}
	
}
