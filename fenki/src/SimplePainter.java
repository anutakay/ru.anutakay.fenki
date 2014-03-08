
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import ru.fenki.model.Node;



public class SimplePainter {

	JPanel panel;
	Graphics _g;
	
	private int shiftX;
	private int shiftY;

	int a = 20;// ширина клетки
	
	SimplePainter(JPanel p) {
		panel = p;
	}

	public void paint(Graphics g) {
		_g = g;
		shiftX = panel.getX();
		shiftY = panel.getY();
	}
	
	private int shiftX(int x) {
		return x + shiftX;
	}

	protected int shiftY(int y) {
		return y + shiftY;
	}
	
	protected void paintRhomb(Point p, int w, boolean fill) {

		int x = shiftX(p.x);
		int y = shiftY(p.y);

		int[] xPoints = { x, (int) (x - w / 2.), x, (int) (x + w / 2.) };
		int[] yPoints = { (int) (y - w / 2.), y, (int) (y + w / 2.), y };

		if(fill){
			_g.fillPolygon(xPoints, yPoints, 4);
		}
		_g.setColor(Color.BLACK);
		_g.drawPolygon(xPoints, yPoints, 4);
	}
	
	protected void paintRhomb(Point p, int w){
		 paintRhomb(p, w,true ) ;
	} 
	
	protected void paintArrow(Point p, int w, int t) {
		int x = shiftX(p.x);
		int y = shiftY(p.y);
		// _g.drawOval(x-10, y-10, 20, 20);
		int _w = w;
		w = (int) (w / Math.sqrt(2));

		switch (t) {
		case Node.NODE_DIRECT_RIGHT:
			_g.drawLine(x - (int) (w / 4.), y - (int) (w / 4.), x
					+ (int) (w / 4.), y + (int) (w / 4.));
			break;
		case Node.NODE_DIRECT_LEFT:
			_g.drawLine(x + (int) (w / 4.), y - (int) (w / 4.), x
					- (int) (w / 4.), y + (int) (w / 4.));
			break;
		case Node.NODE_BACK_RIGHT:
			x = (int) (x - _w / 8.);
			_g.drawLine(x + (int) (w / 3.), y - (int) (w / 3.), x, y);
			_g.drawLine(x, y, x + (int) (w / 3.), y + (int) (w / 3.));
			break;
		case Node.NODE_BACK_LEFT:
			x = (int) (x + _w / 8.);
			_g.drawLine(x - (int) (w / 3.), y - (int) (w / 3.), x, y);
			_g.drawLine(x, y, x - (int) (w / 3.), y + (int) (w / 3.));
			break;
		}

	}
	
	
}
