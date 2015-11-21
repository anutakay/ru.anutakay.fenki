package ru.anutakay.fenki;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ru.anutakay.fenki.HelloWorld.MenuFactory;
import ru.anutakay.fenki.model.Schema;

public class MFrame extends JFrame implements ChangeListener {

    private static final long serialVersionUID = 1L;
    
    private static final String SAVE = "Сохранить";
    private static final String OPEN = "Открыть";
    private static final String CREATE = "Создать";
    private static final String FILE = "Файл";
    private static final String UNNAMED = "Безымянный";
    
    JTabbedPane tabbedPane;
    ControlPanel controlPanel;

    public MFrame(String string) {
        super(string);
    }
    
    public void createGUI() {
        JPanel panel = createPanel();
        setContentPane(panel);

        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        tabbedPane = (JTabbedPane) createTabbedPane();
        controlPanel = ControlPanel.getInstance();
        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.EAST);

        return panel;
    }
    
    private JComponent createTabbedPane() {
        JTabbedPane tp = new ClosableTabbedPane();

        tp.addChangeListener(this);
        return tp;
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menu = new JMenuBar();
 
        JMenu fileMenu = MenuFactory.createMenu(FILE);

        JMenuItem createItem = MenuFactory.createItem(CREATE);
        createItem.addActionListener(createListener);
        
        JMenuItem openItem = MenuFactory.createItem(OPEN);
        openItem.addActionListener(openListener);
        
        JMenuItem saveItem = MenuFactory.createItem(SAVE);

        fileMenu.add(createItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        menu.add(fileMenu);
        return menu;
    }
    
    ActionListener createListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            tabbedPane.addTab("", new TabbedSchemaPane());
            tabbedPane
                    .setTabComponentAt(tabbedPane.getTabCount() - 1, tab(UNNAMED));
        }

    };

    ActionListener openListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            JFileChooser openFile = new JFileChooser("./files");
            int ret = openFile.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = openFile.getSelectedFile();
                tabbedPane.addTab("", new TabbedSchemaPane());
                tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
                        tab(file.getName()));
            }
        }
    };
    
    private JComponent tab(String str) {
        JComponent panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        JLabel lblTitle = new JLabel(
                "<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>"
                        + str + "</body></html>");
        ImageIcon icon = new ImageIcon("image/close.png");
        if (icon.getImage() == null) {
            System.out.print("not found");
        }

        JLabel btnClose = new JLabel();
        btnClose.setIcon(icon);
        
        btnClose.addMouseListener(mouseListener);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        panel.add(lblTitle, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        panel.add(btnClose, gbc);

        return panel;
    }
    
    MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent arg0) {
            tabbedPane.remove(tabbedPane.getSelectedIndex());
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
        }

    };
    
    @Override
    public void stateChanged(ChangeEvent paramChangeEvent) {
        int index = tabbedPane.getSelectedIndex();
        if (index == -1) {
            controlPanel.setCurrentModel(null);
            return;
        }
        Schema currentSchema = ((TabbedSchemaPane) tabbedPane
                .getComponentAt(index)).getSchema();
        controlPanel.setCurrentModel(currentSchema);
    }

}
