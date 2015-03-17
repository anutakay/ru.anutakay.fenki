import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SimpleControlPanel extends JPanel {
	
	JLabel number;
	
	private SimpleControlPanel(){
		super();
	}
	
	public static SimpleControlPanel getInstance(String text){
		SimpleControlPanel panel = new SimpleControlPanel(); 
		panel.setPreferredSize(new Dimension(200, 50));
		panel.setLayout(new BorderLayout());
		
		JLabel label = new JLabel();
		label.setText(text + ":");
		label.setPreferredSize(new Dimension(200, 10));
		
		JPanel center = new JPanel();
		
		panel.number = new JLabel();
		panel.setNum(0);
		
		JButton button_1 = new JButton("-");
		JButton button_2 = new JButton("+");
		
		center.add(button_1);
		center.add(panel.number);
		center.add(button_2);
		panel.add(center, BorderLayout.CENTER);
		panel.add(label, BorderLayout.NORTH);
		
		return panel;
	}
	
	public void setNum(int num){
		number.setText("" + num);
	}

}
