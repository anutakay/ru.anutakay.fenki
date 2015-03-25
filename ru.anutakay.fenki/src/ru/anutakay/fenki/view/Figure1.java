package ru.anutakay.fenki.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Figure1 extends Figure {
	
	private static final int DIAMETER = 7;
	
	int a;
	public Color color;

	public Figure1(final Point point) {
		super(point);
		a = DIAMETER; 		
	}

	@Override
	public void paintComponent(final Graphics g){
		if(center == null || color == null){
			return;
		}
		
		int[] b = {center.x-2*a, center.x, center.x+2*a, center.x};
		int[] b1 = {center.y, center.y-2*a, center.y, center.y+2*a};
		
		g.setColor(color);
		g.fillPolygon(b, b1, 4);
		
		g.setColor(Color.BLACK);
		g.drawPolygon(b, b1, 4);
	}

}
