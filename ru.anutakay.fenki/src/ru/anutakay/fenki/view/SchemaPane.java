package ru.anutakay.fenki.view;

import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SchemaPane extends JPanel {	
	
	FigureFactory<Iterator2D> figureFactory;
	
	public SchemaPane(FigureFactory<Iterator2D> figureFactory){
		super();
		this.figureFactory = figureFactory;
	}
	
	@SuppressWarnings("unchecked")
	public Adapter<Iterator2D, ? super Object> getAdapter(){
		return (Adapter<Iterator2D, ? super Object>) figureFactory.getAdapter();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		for(Iterator2D it = figureFactory.getIterator().begin(); it != null; it = it.next() ){
			Figure f =  figureFactory.makeFigure(it);
			if(f == null){
				continue;
			}
			f.paintComponent(g);
		}
	}

}
