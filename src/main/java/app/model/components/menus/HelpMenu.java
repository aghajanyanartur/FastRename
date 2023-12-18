package app.model.components.menus;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class HelpMenu extends Menu {

    private final MenuItem helpItem;

    public HelpMenu(Application application) {
        super("Help");
        helpItem = new MenuItem("Get help on web");
        getItems().add(helpItem);

        helpItem.setOnAction(e -> application.getHostServices()
                .showDocument("https://aghajanyanartur.github.io/fast-rename-web/#/how-it-works"));
    }
}
