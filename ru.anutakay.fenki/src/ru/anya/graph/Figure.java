package ru.anya.graph;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Figure{
	
	Point center;
	
	abstract void paintComponent(Graphics g);
	
	Figure(Point p){
		center = p;
	}
	
}
