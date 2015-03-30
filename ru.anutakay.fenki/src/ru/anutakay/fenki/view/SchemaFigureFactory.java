package ru.anutakay.fenki.view;

@SuppressWarnings("rawtypes")
public class SchemaFigureFactory<T extends Iterator2D>  extends FigureFactory {

	private PointAdapter pointAdapter;
	
	
	private IThreadColorSchema threadColorSchema;
	
	@SuppressWarnings("unchecked")
	public SchemaFigureFactory(final Adapter<? super Iterator2D, ? super Object> adapter) {
		super(adapter);
		this.pointAdapter = new PointAdapter(adapter.getIterator());
	}
	
	public SchemaFigureFactory(final Adapter<? super Iterator2D, ? super Object> adapter, 
								final IGroupColorSchema groupColorSchema, 
								final IColorSchema colorSchema) {
		this(adapter);
		this.threadColorSchema = new ThreadColorSchema(groupColorSchema, colorSchema);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Figure makeFigure(final Iterator2D it) {
		
		final Figure1 figure = new Figure1( pointAdapter.getObject(it));
		
		final Integer threadID = (Integer) mAdapter.getObject(it);
		
		if (threadID == null) {
			return null;
		}
		
		figure.setColor(threadColorSchema.getColorByThreadID(threadID));
		
		return figure;
	}

}
