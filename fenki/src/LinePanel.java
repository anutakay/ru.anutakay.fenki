import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;


public class LinePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7068660895836248536L;
	private Schema mSchema;

	public LinePanel(Schema schema) {
		super();
		setSchema(schema);
	}

	void setSchema(Schema schema) {
		mSchema = schema;
		mSchema.build();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		mSchema.draw(this, g);
	}
	
	
}
