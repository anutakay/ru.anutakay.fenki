package ru.fenki.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rhomb {

	private Point _p;
	private int _a;	//от центра до угла
	private Color _color;
	private String string = "";
	private boolean _fill = true;
	private boolean _visible = true;
	
	private int _shift=0;
	
	public Rhomb(int a){
		_a = a;
		_shift=-a;
	}
	
	public Rhomb setLocation(Point p){
		_p = p;
		return this;
	}
	
	public Rhomb setText(String s){
		string = s;
		return this;
	}
	
	public Rhomb setColor(Color color){
		_color = color;
		return this;
	}
	
	public Rhomb setFill(boolean fill){
		_fill = fill;
		return this;
	}
	
	public Rhomb setVisible(boolean visible){
		_visible = visible;
		return this;
	}
	
	public void draw(Graphics g){
		draw(g,_fill);
	}
	
	private void draw(Graphics g, boolean fill){
		if(_visible==false){
			return;
		}
		int x = _p.x+_shift;
		int y = _p.y;
		int w = 2*_a;

		int[] xPoints = { x, (int) (x - w / 2.), x, (int) (x + w / 2.) };
		int[] yPoints = { (int) (y - w / 2.), y, (int) (y + w / 2.), y };

		g.setColor(_color);
		
		if(fill){
			g.fillPolygon(xPoints, yPoints, 4);
		}
		g.setColor(Color.BLACK);
		g.drawPolygon(xPoints, yPoints, 4);
		
		g.drawString(string, x, y);
	}
	
	public void clear(){
		setText("").setColor(Color.WHITE).setFill(true).setVisible(true);
	}

}
