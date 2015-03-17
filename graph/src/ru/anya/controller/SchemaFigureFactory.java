package ru.anya.controller;

import java.awt.Color;

import ru.anya.graph.Figure;
import ru.anya.graph.Figure1;
import ru.anya.graph.Iterator2D;

public class SchemaFigureFactory extends FigureFactory<Iterator2D> {
	
	PointAdapter mPointAdapter;
	
	public SchemaFigureFactory(Adapter<Iterator2D, ? super Object> adapter){
		super(adapter);
		mPointAdapter = new PointAdapter(adapter.getIterator());
	}
	
	@Override
	public Figure makeFigure(Iterator2D it) {
		
		Figure1 f
		 = new Figure1( mPointAdapter.getObject(it)){

			@Override
			public int getDiameter() {
				return 7; 
			}
			
		};
		f.color = (Color) mAdapter.getObject(it);
		
		return f;
	}

}
