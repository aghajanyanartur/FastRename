package app.model.components.menus;

import app.model.FastRenameApp;
import app.model.components.dialogs.AboutDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class AboutMenu extends Menu {

    private final MenuItem aboutItem;

    public AboutMenu(FastRenameApp application) {
        super("About");
        aboutItem = new MenuItem("About the program");
        getItems().add(aboutItem);

        aboutItem.setOnAction(event -> {
            AboutDialog aboutDialog = new AboutDialog(application.getHostServices());
            aboutDialog.show();
        });
    }
}
