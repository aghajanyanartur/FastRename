package app.model.components.menus;

import app.model.FastRenameApp;
import javafx.scene.control.MenuBar;

public class MainMenuBar extends MenuBar {

    private final FileMenu fileMenu;
    private final HelpMenu helpMenu;
    private final AboutMenu aboutMenu;

    public MainMenuBar(FastRenameApp application) {
        super();

        fileMenu = new FileMenu(application);
        helpMenu = new HelpMenu(application);
        aboutMenu = new AboutMenu(application);

        getMenus().add(fileMenu);
        getMenus().add(helpMenu);
        getMenus().add(aboutMenu);
    }
}
