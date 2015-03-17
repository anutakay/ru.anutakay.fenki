package ru.anya.graph;

import java.awt.Graphics;

import javax.swing.JPanel;

import ru.anya.controller.Adapter;
import ru.anya.controller.FigureFactory;
import ru.anya.controller.SchemaFigureFactory;

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
	
	private void setAdapter(Adapter<Iterator2D, ? super Object> adapter){

		mFigureFactory = new SchemaFigureFactory(adapter);
		repaint();
	}
	
	@SuppressWarnings("unchecked")
	public Adapter<Iterator2D, ? super Object> getAdapter(){
		return (Adapter<Iterator2D, ? super Object>) mFigureFactory.getAdapter();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		for(Iterator2D it = mFigureFactory.getIterator().begin(); it != null; it = it.next() ){
			Figure1 f =  (Figure1) mFigureFactory.makeFigure(it);
			if(f == null){
				continue;
			}
			if(f.color != null){
				f.paintComponent(g);
			}
		}
	}

}
