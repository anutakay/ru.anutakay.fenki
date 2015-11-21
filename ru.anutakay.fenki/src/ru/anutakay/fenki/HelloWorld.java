package ru.anutakay.fenki;

import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HelloWorld {

    private static boolean IS_MAC;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MFrame frame = new MFrame("Феньки");
                IS_MAC = isMacOS();
                setGuiProperties();
                frame.createGUI();
            }
        });
    }

    private static boolean isMacOS() {
        String lcOSName = System.getProperty("os.name").toLowerCase();
        return lcOSName.startsWith("mac os x");
    }

    private static void setGuiProperties() {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name",
                "WikiTeX");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        System.setProperty("com.apple.macos.smallTabs", "true");
    }

    static class MenuFactory {
        static Font font = new Font("Verdana", Font.PLAIN, 11);
        
        public static JMenu createMenu(String str) {
            String bStr = "";
            String eStr = "";
            if (!IS_MAC) {
                bStr = "<html><body leftmargin=15 marginwidth=15>";
                eStr = "</body></html>";
            }
            JMenu menu = new JMenu(bStr + str + eStr);
            menu.setFont(font);
            return menu;
        }
        
        public static JMenuItem createItem(String str) {
            String bStr = "";
            String eStr = "";
            if (!IS_MAC) {
                bStr = "<html><body leftmargin=15 marginwidth=15>";
                eStr = "</body></html>";
            }
            JMenuItem item =  new JMenuItem(bStr + str + eStr);
            item.setFont(font);
            return item;
        }
    }
}