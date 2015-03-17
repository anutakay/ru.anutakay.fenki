package ru.anya.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Figure1 extends Figure{
	
	int a;
	public Color color;

	public Figure1(Point point) {
		super(point);
		a = getDiameter();		
	}

	@SuppressWarnings("static-access")
	@Override
	public void paintComponent(Graphics g){
		if(center == null){
			return;
		}
		
		int[] b = {center.x-2*a, center.x, center.x+2*a, center.x};
		int[] b1 = {center.y, center.y-2*a, center.y, center.y+2*a};
		
		
		if(color != null){
			g.setColor(color);
		}else{
			g.setColor(color.WHITE);
		}
		g.fillPolygon(b, b1, 4);
		
		g.setColor(Color.black);
		g.drawPolygon(b, b1, 4);
	}
	
	public abstract int getDiameter();

}
