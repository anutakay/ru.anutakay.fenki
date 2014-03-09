package ru.fenki.panels;

import java.awt.Graphics;

import javax.swing.JPanel;

import ru.fenki.model.Schema;
import ru.fenki.service.Dimension;
import ru.fenki.view.SchemaAdapter;
import ru.fenki.view.SchemaIterator;
import ru.fenki.view.SimpleIterator;

public class RhombPanel extends JPanel  {
	
	Dimension mDimension;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8000365258069500848L;
	
	private SchemaAdapter adapter;
	private Schema mSchema;
	
	public RhombPanel(){
		super();
		mSchema = new Schema(4, 22, true);
		mDimension = mSchema.getDimensions().toDimension();
		setPreferredSize(new java.awt.Dimension(mDimension.getColumnNumber()*2*a+1, mDimension.getStringNumber()*a+a+1));
		adapter = new SchemaAdapter(mSchema, a);
	}
	
	public void init(){
		mSchema.build();
	}
	
	final static int a = 30;

	@Override
	protected void paintComponent(Graphics g){
		g.setColor(getBackground());
		g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);
		
		for(SimpleIterator it = new SchemaIterator(mDimension)
				.begin(); it != null; it = it.next()){
			adapter.getRhomb(it).draw(g);
		}
	}
}
