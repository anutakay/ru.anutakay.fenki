package ru.fenki.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import ru.fenki.model.Schema;
import ru.fenki.service.Dimension;
import ru.fenki.view.ClickHandlerInterface;
import ru.fenki.view.Rhomb;
import ru.fenki.view.SchemaAdapter;
import ru.fenki.view.SchemaIterator;
import ru.fenki.view.SimpleIterator;
import ru.fenki.view.mouse.RhombMouseListener;

public class RhombPanel extends JPanel implements ClickHandlerInterface  {
	
	Dimension mDimension;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8000365258069500848L;
	
	private SchemaAdapter adapter;
	private Schema mSchema;
	
	public RhombPanel(){
		super();
		RhombMouseListener rl = new RhombMouseListener();
		rl.setClickHandler(this);
		addMouseListener(rl);
		mSchema = new Schema(5, 5, true);
		mDimension = mSchema.getDimensions().toDimension();
		setPreferredSize(new java.awt.Dimension(mDimension.getColumnNumber()*2*a+1, mDimension.getStringNumber()*a+a+1));
		adapter = new SchemaAdapter(mSchema, a);
	}
	
	public void init(){
		mSchema.build();
	}
	
	final static int a = 30;
	final static int shift = 0;

	@Override
	protected void paintComponent(Graphics g){
		g.setColor(getBackground());
		/*g.setColor(Color.CYAN);
		g.fillRect(a+shift, 0, getPreferredSize().width, getPreferredSize().height);
		
		for(SimpleIterator it = new SchemaIterator(mDimension)
				.begin(); it != null; it = it.next()){
			if(!it.isSatisfies()){
				continue;
			}
			Rhomb r = adapter.getRhomb(it);
			int i = it.j*2+it.i%2;
			int j = it.i;
			if(r!=null){
				//r.setMult(1.5);
				r.setText(i+","+j);
			}
			drawIfExist(g, r);
		}*/
		
		for(SimpleIterator it = new SchemaIterator(mDimension)
				.begin(); it != null; it = it.next()){
			Rhomb r = adapter.getThread(it);
			drawIfExist(g, r);
		}
		
		for(SimpleIterator it = new SchemaIterator(mDimension)
				.begin(); it != null; it = it.next()){
			Rhomb r = adapter.getCorner(it);
			drawIfExist(g, r);
		}
		
		for(SimpleIterator it = new SchemaIterator(mDimension)
				.begin(); it != null; it = it.next()){
			Rhomb r = adapter.getNode(it);
			if(r!=null){
				//r.setMult(1.5);
				r.setText(it.i+","+it.j);
			}
			drawIfExist(g, r);
		}
		
	}
	
	private void drawIfExist(Graphics g, Rhomb r){
		if(r!=null){
			r.draw(g, shift);
		}
	}

	@Override
	public void onClicked(int x, int y) {
		System.out.println("+:" + (x+y)/(2*a) + " -:" + (x-y)/a);
		
	}
}
