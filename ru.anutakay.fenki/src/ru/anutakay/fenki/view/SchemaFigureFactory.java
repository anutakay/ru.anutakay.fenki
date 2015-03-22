package ru.anutakay.fenki.view;

import java.awt.Color;

public class SchemaFigureFactory<T extends Iterator2D>  extends FigureFactory{
	
	PointAdapter mPointAdapter;
	
	public SchemaFigureFactory(Adapter<? super Iterator2D, ? super Object> adapter){
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
