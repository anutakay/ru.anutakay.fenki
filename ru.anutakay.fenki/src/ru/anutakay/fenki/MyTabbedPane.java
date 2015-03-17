package ru.anutakay.fenki;

import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import ru.anutakay.fenki.controller.ColorAdapter;
import ru.anutakay.fenki.graph.GridPanel;
import ru.anutakay.fenki.model.Schema;

public class MyTabbedPane extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4336057985717689708L;
	
	Schema mSchema;

	public MyTabbedPane() {
		super();
		JPanel rp = makePanel();
		//rp.init();
		JScrollPane scrollPane = new JScrollPane(rp);
		addTab("Схема", scrollPane);
		addTab("Редактор", new JTextPane());
		setTabPlacement(BOTTOM);
	}
	
	private JPanel makePanel(){
		int threads = Math.abs(new Random().nextInt())%20;
		System.out.println(threads);
		mSchema = new Schema(threads, 10, false);
		mSchema.build();
		return new GridPanel(new ColorAdapter(mSchema));
	}
	
	public Schema getSchema(){
		return mSchema;
	}

}
