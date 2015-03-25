package ru.anutakay.fenki;

import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import ru.anutakay.fenki.controller.RandomFiller;
import ru.anutakay.fenki.controller.SchemaController;
import ru.anutakay.fenki.model.FieldIterator;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.view.ColorAdapter;
import ru.anutakay.fenki.view.GridPanel;

public class MyTabbedPane extends JTabbedPane {

	private static final int NUM_OF_COLUMNS = 10;

	private static final long serialVersionUID = -4336057985717689708L;
	
	SchemaController schemaController;

	public MyTabbedPane() {
		super();
		JPanel rp = makePanel();
		JScrollPane scrollPane = new JScrollPane(rp);
		addTab("Схема", scrollPane);
		addTab("Редактор", new JTextPane());
		setTabPlacement(BOTTOM);
	}
	
	@SuppressWarnings("unchecked")
	private JPanel makePanel(){
		return new GridPanel(new ColorAdapter<FieldIterator, Color>(getSchemaController()));
	}
	
	public SchemaController getSchemaController() {
		if(schemaController == null)  {
			schemaController = createSchemaController();
		}
		return schemaController;
	}
	
	private SchemaController createSchemaController() {
		int threads = Math.abs(new Random().nextInt())%20;
		Schema schema = new Schema(threads, NUM_OF_COLUMNS, false);
		SchemaController controller = new SchemaController(schema);
		controller.fillSchema(new RandomFiller());
		controller.buildSchema();
		return controller;
	}

}
