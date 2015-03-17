import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ru.fenki.model.Schema;


@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	
	private static ControlPanel panel = null;
	
	SimpleControlPanel scp1;
	SimpleControlPanel scp2;
	
	Schema model;
	
	private ControlPanel(){
	}

	public static ControlPanel  getInstance(){
		if( panel == null){
			panel = new ControlPanel();
		}
		
		panel.build();
		
		return panel;
	}
	
	private void build(){
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(200, 24));
		
		p.add(panel1);
		
		scp1 = SimpleControlPanel.getInstance("Нитки");
		scp2 = SimpleControlPanel.getInstance("Ряды");
		
		p.add(scp1);
		p.add(scp2);
		
		panel.add(p);
	}
	
	public void setCurrentModel(Schema schema){
		model = schema;
		if(model != null && model.getDimensions() != null){
			scp1.setNum(model.getDimensions().getThreadNumber());
		}else{
			scp1.setNum(0);
		}
		
		if(model != null && model.getDimensions() != null){
			scp2.setNum(model.getDimensions().getColumnNumber());
		}else{
			scp2.setNum(0);
		}
	}
	
}
