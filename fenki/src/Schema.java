

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import ru.fenki.model.Const;
import ru.fenki.model.Node;
import ru.fenki.model.NodeAndThreadStorage;
import ru.fenki.model.NodeIndex;
import ru.fenki.model.NodeStoreDimensions;
import ru.fenki.model.ThreadIndex;

public class Schema 
{
	private NodeAndThreadStorage mNodeStorage;
	private NodeStoreDimensions mDimensions;

	public Schema() 
	{
		this(2, 1, true);
	}

	public Schema(int thread_num, int column_num, boolean first) 
	{
		mDimensions = (new NodeStoreDimensions(thread_num, column_num, first));
		mNodeStorage = new NodeAndThreadStorage(mDimensions);	
	}

	public void build() {
		for (int j = 0; j < getColumnNumber(); j++) {
			mNodeStorage.build_corner(j, Const.LEFT);
			for (int i = 0; i < mDimensions.numberOfNodeInColumn(j); i++) {
				//mNodeStorage.build_node(new NodeIndex(i, j));
			}
			mNodeStorage.build_corner(j, Const.RIGHT);
		}
	}
	
	public void draw(JPanel p, Graphics g){
		new Painter(p).paint(g, this);
	}
	
	private int getColumnNumber() {
		return mDimensions.getColumnNumber();
	}
	
	private NodeStoreDimensions getDimensions() {
		return mNodeStorage.getDimensions();
	}
	
	private Node node(int i, int j) {
		return null;//mNodeStorage.getNode(new NodeIndex(i, j));
	}

	private int getThread(ThreadIndex ti) {
		return mNodeStorage.getThread(ti);
	}

	private int getCornerColor(int i, int j) {
		int left_right = Const.RIGHT;
		if(i == -1){
			left_right = Const.LEFT;
		}
		return mNodeStorage.getCorner(j, left_right);
	}

	public Iterator begin(){
		return new Iterator( mNodeStorage);
	}
	
	private class Painter extends SimplePainter {

		Painter(JPanel p) {
			super(p);
		}

		Schema s;

		Color[] colors = { new Color(255, 215, 0), new Color(50, 205, 50),
				new Color(0, 206, 209) };
		
		private Point rhombCenter(int i, int j){
			return new Point(((j+1)*2+(i%2))*a, (i+1)*a);
		}
		

		public void paint(Graphics g, Schema schema) {
			
			super.paint(g);
			
			s = schema;
			
			for(Iterator it = schema.begin(); it != null; it=it.next()){
				if(s.getDimensions().isThread(it.i, it.j)){
					paintThread(it.j, it.i);
				}
			}
			
			for(Iterator it = schema.begin(); it != null; it=it.next()){
				if(s.getDimensions().isCorner(it.i,it.j)){
					if(s.getDimensions().leftCorner(it.i)){
						paintCorner(-1,it.i/2);
					}
					
					if (s.getDimensions().rightCorner(it.i/2)) {
						paintCorner((s.getDimensions().getThreadNumber() - 1), it.i/2);
					}
				}
				
				if(s.getDimensions().isNode(it.i, it.j)){
					paintNode(it.j, it.i);
				}
			}
		}
		
		private Point threadCenter(int i, int j){
			int x = (i+1)*a*2;
			int y = (j*2+1)*a ;
			return new Point(x,y);
		}
		
		private void paintThread(int i, int j) {
			j=j/2;
			int aaa = s.getThread(new ThreadIndex(i,j));
			if (aaa == -1) {
				_g.setColor(Color.WHITE);
				
			} else {
				_g.setColor(colors[aaa % colors.length]);
			}
			paintRhomb(threadCenter(i,j), 2*a);
		}
		
		private Point nodeCenter(int i, int j){
			int x = (i * 4  + 3 + 2* (s.getDimensions().isShort(j, Const.LEFT) ? 1 : 0))*a;
			int y = (j + 1)*2*a;
			return new Point(x, y);
		}

		private void paintNode(int i, int j) {
			i=i/2;j=j/2;
			int aaa = s.node(i,j).getColor();
			if (aaa == -1) {
				_g.setColor(Color.WHITE);
			} else {
				_g.setColor(colors[aaa % colors.length]);
			}
			Point p = nodeCenter(i, j);
			paintRhomb(p, (int) (2 * a));
			paintArrow(p, (int) (2 * a), s.node(i, j).getDirection());
		}
		
		private Point cornerCenter(int i, int j){
			int x = 2*i * a + 2*a + a;
			int y = (j+1) *2* a;
			return new Point(x, y);
		}

		private void paintCorner(int i, int j) {
			int aaa = s.getCornerColor(i, j);
			if (aaa == -1) {
				_g.setColor(Color.WHITE);
			} else {
				_g.setColor(colors[aaa % colors.length]);
			}
			paintRhomb(cornerCenter(i,j), 2*a);
		}

		

	}

}
