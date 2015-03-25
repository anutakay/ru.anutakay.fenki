package ru.anutakay.fenki.view;

import java.awt.Graphics;
import javax.swing.JPanel;

import ru.anutakay.fenki.model.FieldIterator;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {	
	
	public GridPanel(Adapter<Iterator2D, ? super Object> adapter){
		super();
		setAdapter(adapter);	
	}
	
	public GridPanel(){
		super();
	}

	FigureFactory<Iterator2D> mFigureFactory;
	
	@SuppressWarnings("unchecked")
	private void setAdapter(Adapter<? super Iterator2D, ? super Object> adapter){

		mFigureFactory = new SchemaFigureFactory<FieldIterator>(adapter);
		repaint();
	}
	
	@SuppressWarnings("unchecked")
	public Adapter<Iterator2D, ? super Object> getAdapter(){
		return (Adapter<Iterator2D, ? super Object>) mFigureFactory.getAdapter();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		for(Iterator2D it = mFigureFactory.getIterator().begin(); it != null; it = it.next() ){
			Figure f =  mFigureFactory.makeFigure(it);
			if(f == null){
				continue;
			}
			f.paintComponent(g);
		}
	}

}
