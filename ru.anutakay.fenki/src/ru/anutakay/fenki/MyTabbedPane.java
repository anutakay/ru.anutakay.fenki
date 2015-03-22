package ru.anutakay.fenki;

import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import ru.anutakay.fenki.controller.ColorAdapter;
import ru.anutakay.fenki.controller.Filler;
import ru.anutakay.fenki.controller.RandomFiller;
import ru.anutakay.fenki.controller.SchemaController;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.view.GridPanel;

public class MyTabbedPane extends JTabbedPane {

	private static final int NUM_OF_COLUMNS = 10;

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
		mSchema = new Schema(threads, NUM_OF_COLUMNS, false);
		Filler filler = new RandomFiller();
		filler.fill(mSchema);
		SchemaController schemaController = new SchemaController(mSchema);
		schemaController.buildSchema();
		return new GridPanel(new ColorAdapter(schemaController));
	}
	
	public Schema getSchema(){
		return mSchema;
	}

}
