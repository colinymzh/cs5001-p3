package view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Manages the menu bar for the GUI application.
 */
public class MenuBarManager {
    private JMenuBar menuBar;

    /**
     * Initializes the MenuBarManager with action listeners for menu items.
     *
     * @param exitListener            Action listener for the "Exit" menu item.
     * @param shapeColorListener      Action listener for the "Set Shape Color" menu item.
     * @param backgroundColorListener Action listener for the "Set Background Color" menu item.
     * @param undoListener            Action listener for the "Undo" menu item.
     * @param exportListener          Action listener for the "Export" menu item.
     */
    public MenuBarManager(ActionListener exitListener, ActionListener shapeColorListener, ActionListener backgroundColorListener, ActionListener undoListener, ActionListener exportListener) {
        menuBar = new JMenuBar();

        // Create the "File" menu
        JMenu menu_File = new JMenu("File");
        JMenuItem menuItemExport = new JMenuItem("Export");
        menuItemExport.addActionListener(exportListener);
        menu_File.add(menuItemExport);
        JMenuItem menuItemUndo = new JMenuItem("Undo");
        menuItemUndo.addActionListener(undoListener);
        menu_File.add(menuItemUndo);
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(exitListener);
        menu_File.add(menuItemExit);

        // Create the "Color" menu
        JMenu menu_Color = new JMenu("Color");
        JMenuItem menuItemShapeColor = new JMenuItem("Set Shape Color");
        menuItemShapeColor.addActionListener(shapeColorListener);
        JMenuItem menuItemBackGroundColor = new JMenuItem("Set Background Color");
        menuItemBackGroundColor.addActionListener(backgroundColorListener);
        menu_Color.add(menuItemShapeColor);
        menu_Color.add(menuItemBackGroundColor);

        // Add the menus to the menu bar
        menuBar.add(menu_File);
        menuBar.add(menu_Color);

    }

    /**
     * Gets the menu bar.
     *
     * @return The JMenuBar instance.
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
