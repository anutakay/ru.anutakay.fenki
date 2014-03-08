package ru.fenki.panels;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;


public class MyTabbedPane extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4336057985717689708L;

	public MyTabbedPane() {
		super();
		RhombPanel rp = new RhombPanel();
		rp.init();
		JScrollPane scrollPane = new JScrollPane(rp);
		addTab("Схема", scrollPane);
		addTab("Редактор", new JTextPane());
		setTabPlacement(BOTTOM);
	}

}
