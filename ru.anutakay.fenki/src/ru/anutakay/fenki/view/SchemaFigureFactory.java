package ru.anutakay.fenki.view;

import java.awt.Color;

@SuppressWarnings("rawtypes")
public class SchemaFigureFactory<T extends Iterator2D>  extends FigureFactory{
	
	PointAdapter mPointAdapter;
	
	@SuppressWarnings("unchecked")
	public SchemaFigureFactory(Adapter<? super Iterator2D, ? super Object> adapter){
		super(adapter);
		mPointAdapter = new PointAdapter(adapter.getIterator());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Figure makeFigure(Iterator2D it) {
		
		Figure1 f = new Figure1( mPointAdapter.getObject(it));
		f.color = (Color) mAdapter.getObject(it);
		
		return f;
	}

}
