package app.model.components.menus;

import app.model.FastRenameApp;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FileMenu extends Menu {

    private final MenuItem menuItemOpenFolder;
    private final MenuItem menuItemOpenConfig;
    private final MenuItem menuItemSaveConfig;
    private final MenuItem menuItemExit;

    private DirectoryChooser directoryChooser;
    private FileChooser fileChooser;
    private FastRenameApp application;

    public FileMenu(FastRenameApp application) {
        super("File");
        this.application = application;

        directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder");

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Configuration File");
        fileChooser.setInitialFileName("config.properties");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Properties Files", "*.properties"));

        menuItemOpenFolder = new MenuItem("Open folder");
        menuItemOpenConfig = new MenuItem("Open configuration file");
        menuItemSaveConfig = new MenuItem("Save configuration file");
        menuItemExit = new MenuItem("Exit");

        getItems().add(menuItemOpenFolder);
        getItems().add(menuItemOpenConfig);
        getItems().add(menuItemSaveConfig);
        getItems().add(menuItemExit);

        menuItemOpenFolder.setOnAction(e -> openDirectoryChooser());
        menuItemOpenConfig.setOnAction(e -> openConfig());
        menuItemSaveConfig.setOnAction(e -> saveConfig());
        menuItemExit.setOnAction(e -> Platform.exit());
    }

    private void openConfig() {
        File configFile = fileChooser.showOpenDialog(application.getFolderPane().getScene().getWindow());

        if (configFile != null) {
            try (FileInputStream inputStream = new FileInputStream(configFile)) {
                Properties props = new Properties();
                props.load(inputStream);

                application.getExtensionFilterPane().setFilterByExtension(Boolean.parseBoolean(props.getProperty("filterByExtension")));
                application.getExtensionFilterPane().setExtension(props.getProperty("extension"));
                application.getPatternPane().setPattern(props.getProperty("pattern"));
                application.getStartingNumberPane().setStartingNumber(Integer.parseInt(props.getProperty("startNumber")));
                application.getIncrementPane().setIncrement(Integer.parseInt(props.getProperty("incrementBy")));
                application.getDigitsPane().setDigitsNumber(Integer.parseInt(props.getProperty("digitsNumber")));
                application.getDecrementCheck().setDecrement(Boolean.parseBoolean(props.getProperty("decrement")));
                application.getSortedCheck().setSorted(Boolean.parseBoolean(props.getProperty("sorted")));
                application.getDateHBox().setDateFormat(props.getProperty("dateFormatSelection"));
                application.getDateHBox().setDateFormatPattern(props.getProperty("dateFormatPattern"));
                application.getCurrentNamePane().setKeepName(Boolean.parseBoolean(props.getProperty("keepCurrentName")));
                application.getCurrentNamePane().setCase(props.getProperty("nameCase"));
                application.getCurrentNamePane().setFind(props.getProperty("getFind"));
                application.getCurrentNamePane().setReplace(props.getProperty("getReplace"));
                application.getChangeExtensionPane().setChangeExtension(Boolean.parseBoolean(props.getProperty("changeExtension")));
                application.getChangeExtensionPane().setNewExtension(props.getProperty("newExtension"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveConfig() {
        File configFile = fileChooser.showSaveDialog(application.getFolderPane().getScene().getWindow());

        if (configFile != null) {

            try (FileWriter writer = new FileWriter(configFile)) {
                Properties props = new Properties();

                props.setProperty("filterByExtension", String.valueOf(application.getExtensionFilterPane().filterByExtension()));
                props.setProperty("extension", application.getExtensionFilterPane().getExtension());
                props.setProperty("pattern", application.getPatternPane().getPattern());
                props.setProperty("startNumber", String.valueOf(application.getStartingNumberPane().getStartingNumber()));
                props.setProperty("incrementBy", String.valueOf(application.getIncrementPane().getIncrement()));
                props.setProperty("digitsNumber", String.valueOf(application.getDigitsPane().getDigitsNumber()));
                props.setProperty("decrement", String.valueOf(application.getDecrementCheck().isDecrement()));
                props.setProperty("sorted", String.valueOf(application.getSortedCheck().isSorted()));
                props.setProperty("dateFormatSelection", application.getDateHBox().getDateFormat());
                props.setProperty("dateFormatPattern", application.getDateHBox().getDateFormatPattern());
                props.setProperty("keepCurrentName", String.valueOf(application.getCurrentNamePane().keepName()));
                props.setProperty("nameCase", application.getCurrentNamePane().getCase());
                props.setProperty("getFind", application.getCurrentNamePane().getFind());
                props.setProperty("getReplace", application.getCurrentNamePane().getReplace());
                props.setProperty("changeExtension", String.valueOf(application.getChangeExtensionPane().changeExtension()));
                props.setProperty("newExtension", application.getChangeExtensionPane().getNewExtension());

                props.store(writer, "User Configuration");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openDirectoryChooser() {
        File selectedDirectory = directoryChooser.showDialog(application.getFolderPane().getScene().getWindow());
        if (selectedDirectory != null) {
            application.getFolderPane().getTextField().setText(selectedDirectory.getAbsolutePath());
        }
    }
}
