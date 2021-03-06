package ru.anutakay.fenki;

import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import ru.anutakay.fenki.controller.RandomFiller;
import ru.anutakay.fenki.controller.SchemaController;
import ru.anutakay.fenki.model.FieldIterator;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.color.ColorAdapter;
import ru.anutakay.fenki.model.color.ColorSchema;
import ru.anutakay.fenki.model.color.ColorSchemaImpl;
import ru.anutakay.fenki.model.color.ThreadColorSchema;
import ru.anutakay.fenki.model.color.ThreadColorSchemaImpl;
import ru.anutakay.fenki.view.Adapter;
import ru.anutakay.fenki.view.FigureFactory;
import ru.anutakay.fenki.view.SchemaPane;
import ru.anutakay.fenki.view.Iterator2D;
import ru.anutakay.fenki.view.SchemaFigureFactory;

public class TabbedSchemaPane extends JTabbedPane {

    private static final String EDITOR_STRING = "Редактор";

    private static final String SCHEMA_STRING = "Схема";

    private static final int NUM_OF_COLUMNS = 10;

    private static final long serialVersionUID = -4336057985717689708L;

    SchemaController schemaController;
    
    private ThreadColorSchema threadColorSchema;

    public TabbedSchemaPane() {
        super();
        schemaController = createSchemaController();
        createUI();
    }

    private void createUI() {

        final JScrollPane scrollPane = new JScrollPane(
                makeSchemaPanel(this.schemaController));

        addTab(SCHEMA_STRING, scrollPane);
        addTab(EDITOR_STRING, getEditorPane(this.schemaController));

        setTabPlacement(BOTTOM);
    }

    @SuppressWarnings("unchecked")
    private SchemaPane makeSchemaPanel(SchemaController schemaController) {

        Adapter<? super Iterator2D, ? super Object> adapter = new ColorAdapter<FieldIterator>(
                schemaController);

        FigureFactory<Iterator2D> figureFactory = new SchemaFigureFactory<FieldIterator>(
                adapter, threadColorSchema);

        return new SchemaPane(figureFactory);
    }

    private JTextPane getEditorPane(SchemaController schemaController) {
        return new JTextPane();
    }

    private SchemaController createSchemaController() {
        final int threads = Math.abs(new Random().nextInt()) % 20;
        Schema schema = new Schema(threads, NUM_OF_COLUMNS, false);
        threadColorSchema = createThreadColorSchema();
        SchemaController controller = new SchemaController(schema);
        controller.fill(new RandomFiller());
        controller.build();
        return controller;
    }

    private ThreadColorSchema createThreadColorSchema() {
        ColorSchema colorSchema = new ColorSchemaImpl();
        return new ThreadColorSchemaImpl(colorSchema);
    }

    public SchemaController getSchemaController() {
        return schemaController;
    }

    public Schema getSchema() {
        return getSchemaController().getSchema();
    }
///add comment
}
