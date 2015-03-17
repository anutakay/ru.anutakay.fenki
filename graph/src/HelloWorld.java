
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ru.fenki.model.Schema;

public class HelloWorld extends JFrame implements ChangeListener {

	/**
	 * 
	 */
	
	JTabbedPane tabbedPane;
	ControlPanel controlPanel;
	
	private static final long serialVersionUID = 3431369082324981806L;
	
	boolean IS_MAC;

	public HelloWorld(String string) {
		super(string);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				HelloWorld frame = new HelloWorld("Феньки");
				frame.createGUI();
			}
		});
	}

	public void createGUI() {

		String lcOSName = System.getProperty("os.name").toLowerCase();
		IS_MAC = lcOSName.startsWith("mac os x");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setProperty("com.apple.macos.smallTabs", "true");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		tabbedPane = (JTabbedPane)createTabbedPane();
		controlPanel = ControlPanel.getInstance();
		panel.add(tabbedPane, BorderLayout.CENTER);
		panel.add(controlPanel, BorderLayout.EAST);
		setContentPane(panel);
		
		setJMenuBar(createMenuBar());

		setPreferredSize(new Dimension(600, 600));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private JComponent createTabbedPane(){
		JTabbedPane tp;
		tp = new JTabbedPane();
		
		/*tp.addTab("", new MyTabbedPane());
		tp.setTabComponentAt(tp.getTabCount() - 1, tab("Безымянный"));

		/*tp.addTab("", new MyTabbedPane());
		tp.setTabComponentAt(tp.getTabCount() - 1, tab("Безымянный2"));*/
		
		tp.addChangeListener(this);
		return tp;
	}
	
	private void addNewTab(){
		tabbedPane.addTab("", new MyTabbedPane());
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab("Безымянный"));
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menu = new JMenuBar();
		Font font = new Font("Verdana", Font.PLAIN, 11);
		String bStr="";
		String eStr="";
		
		if(!IS_MAC){
			bStr="<html><body leftmargin=15 marginwidth=15>";
			eStr="</body></html>";
		}

		JMenu fileMenu = new JMenu(bStr + "Файл" + eStr);
		fileMenu.setFont(font);

		JMenuItem createItem = new JMenuItem(bStr + "Создать"+ eStr);
		createItem.setFont(font);
		createItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addNewTab();
			}
			
		});
		JMenuItem openItem = new JMenuItem(bStr + "Открыть"+ eStr);
		openItem.setFont(font);
		openItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFile = new JFileChooser("./files");
				int ret = openFile.showDialog(null, "Открыть файл");
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = openFile.getSelectedFile();
					 tabbedPane.addTab("", new MyTabbedPane( ));
					 tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1,
					 tab(file.getName()));
				}
			}
		});
		JMenuItem saveItem = new JMenuItem(bStr + "Сохранить"+ eStr);
		saveItem.setFont(font);

		fileMenu.add(createItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);

		menu.add(fileMenu);
		return menu;
	}
	
	private JComponent tab(String str) {
		JComponent panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		JLabel lblTitle = new JLabel(
				"<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>"
						+ str + "</body></html>");
		ImageIcon icon = new ImageIcon("image/close.png");
		if(icon.getImage()==null){
			System.out.print("not found");
		}

		JLabel btnClose = new JLabel();
		btnClose.setIcon(icon);
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

	@Override
	public void stateChanged(ChangeEvent paramChangeEvent) {
		int index = tabbedPane.getSelectedIndex();
		if(index == -1){
			controlPanel.setCurrentModel(null);
			return;
		}
		Schema currentSchema = ((MyTabbedPane)tabbedPane.getComponentAt(index)).getSchema();
		controlPanel.setCurrentModel(currentSchema);
	}
}