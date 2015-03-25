package ru.anutakay.fenki.view;

import java.awt.Color;

import ru.anutakay.fenki.model.ColorGroupSchema;
import ru.anutakay.fenki.model.ColorSchema;

@SuppressWarnings("rawtypes")
public class SchemaFigureFactory<T extends Iterator2D>  extends FigureFactory {
	
	PointAdapter mPointAdapter;
	ColorGroupSchema colorGroupSchema;
	
	@SuppressWarnings("unchecked")
	public SchemaFigureFactory(Adapter<? super Iterator2D, ? super Object> adapter){
		super(adapter);
		mPointAdapter = new PointAdapter(adapter.getIterator());
	}
	
	public SchemaFigureFactory(Adapter<? super Iterator2D, ? super Object> adapter, ColorGroupSchema colorGroupSchema){
		this(adapter);
		this.colorGroupSchema = colorGroupSchema;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Figure makeFigure(Iterator2D it) {
		
		Figure1 f = new Figure1( mPointAdapter.getObject(it));
		
		Integer threadID = (Integer) mAdapter.getObject(it);
		
		if (threadID == null) {
			return null;
		}
		
		f.color = getColorByThreadID(threadID);
		
		return f;
	}
	
	private Color getColorByThreadID( int threadID){
		Integer colorID = this.colorGroupSchema.getColorID(threadID);
		Color color = new ColorSchema().getColorByID(colorID);
		if (color == null){
			return Color.WHITE;
		} else {
			return color;
		}
	}

}
