import javax.swing.*;

import ru.fenki.panels.MyTabbedPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HelloWorld extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3431369082324981806L;
	JTabbedPane tabbedPane;

	public HelloWorld(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	public void createGUI() {

		Font font = new Font("Verdana", Font.PLAIN, 11);

		// JFrame frame = new JFrame("Феньки");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPane = new JTabbedPane();

		setContentPane(tabbedPane);

		tabbedPane.addTab("", new MyTabbedPane());
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab(""));

		JMenuBar menu = new JMenuBar();

		JMenu fileMenu = new JMenu("<html><body leftmargin=15 marginwidth=15>"
				+ "Файл" + "</body></html>");
		fileMenu.setFont(font);

		JMenuItem createItem = new JMenuItem(
				"<html><body leftmargin=15 marginwidth=15>" + "Создать"
						+ "</body></html>");
		createItem.setFont(font);
		JMenuItem openItem = new JMenuItem(
				"<html><body leftmargin=15 marginwidth=15>" + "Открыть"
						+ "</body></html>");
		openItem.setFont(font);
		openItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser openFile = new JFileChooser("./files");
				int ret = openFile.showDialog(null, "Открыть файл");
				if (ret == JFileChooser.APPROVE_OPTION) {
					//File file = openFile.getSelectedFile();
					// tabbedPane.addTab("", new MyTabbedPane( ));
					// tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1,
					// tab(file.getName()));
				}
			}
		});
		JMenuItem saveItem = new JMenuItem(
				"<html><body leftmargin=15 marginwidth=15>" + "Сохранить"
						+ "</body></html>");
		saveItem.setFont(font);

		fileMenu.add(createItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);

		menu.add(fileMenu);
		setJMenuBar(menu);

		setPreferredSize(new Dimension(600, 600));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				HelloWorld frame = new HelloWorld("Феньки");
				frame.createGUI();

			}
		});
	}

	private JComponent tab(String str) {
		JComponent panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		// = new
		// JLabel("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>"
		// + str +"</body></html>");
		JLabel lblTitle = new JLabel(
				"<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>"
						+ str + "</body></html>");
		JLabel btnClose = new JLabel(
				"<html><body rightmargin=5 leftmargin=5 topmargin=8 marginwidth=5 marginheight=5>"
						+ "x" + "</body></html>");
		btnClose.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				tabbedPane.remove(tabbedPane.getSelectedIndex());
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

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
}