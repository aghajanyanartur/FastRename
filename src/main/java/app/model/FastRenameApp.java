package app.model;

import app.model.components.buttons.SubmitButton;
import app.model.components.checkboxes.*;
import app.model.components.menus.MainMenuBar;
import app.model.components.panes.*;
import app.model.constraints.Constraints;
import app.model.services.FormData;
import app.model.services.RenameService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.util.Objects;

public class FastRenameApp extends Application {

    // Components
    private FolderPane folderPane;
    private ExtensionFilterPane extensionFilterPane;
    private StartingNumberPane startingNumberPane;
    private NumberOfDigitsPane digitsPane;
    private IncrementPane incrementPane;
    private PatternPane patternPane;
    private CurrentNamePane currentNamePane;
    private ChangeExtensionPane changeExtensionPane;
    private DateHBox dateHBox;
    private DecrementCheckBox decrementCheck;
    private SortedCheckBox sortedCheck;

    @Override
    public void start(Stage stage) {

        // Create pane and scene
        VBox pane = new VBox();
        Scene scene = new Scene(pane, 750, 590);

        // Set logo image and title
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png")));
        stage.getIcons().add(image);
        stage.setTitle("Fast Rename | Rename your files quickly! | Made by Artur Aghajanyan");

        pane.setBackground(new Background(new BackgroundFill(Color.valueOf("#f9f9f9"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setAlignment(Pos.TOP_CENTER);

        // Form
        GridPane form = new GridPane();
        form.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 30px;");
        form.setAlignment(Pos.TOP_CENTER);
        form.setHgap(20);
        form.setVgap(10);

        // Components
        folderPane = new FolderPane();
        extensionFilterPane = new ExtensionFilterPane();
        startingNumberPane = new StartingNumberPane();
        digitsPane = new NumberOfDigitsPane();
        incrementPane = new IncrementPane();
        patternPane = new PatternPane();
        currentNamePane = new CurrentNamePane(patternPane);
        changeExtensionPane = new ChangeExtensionPane();
        dateHBox = new DateHBox();
        decrementCheck = new DecrementCheckBox();
        sortedCheck = new SortedCheckBox();

        // menu bar
        MainMenuBar menuBar = new MainMenuBar(this);

        // Submit button
        SubmitButton submit = new SubmitButton();
        submit.setOnAction(e -> {
            // Handle form submission
            String directoryPath = folderPane.getFolderAddress();
            boolean filterByExtension = extensionFilterPane.filterByExtension();
            String extension = extensionFilterPane.getExtension();
            String pattern = patternPane.getPattern();
            int startNumber = startingNumberPane.getStartingNumber();
            int incrementBy = incrementPane.getIncrement();
            int digitsNumber = digitsPane.getDigitsNumber();
            boolean decrement = decrementCheck.isDecrement();
            boolean sorted = sortedCheck.isSorted();
            String dateFormatSelection = dateHBox.getDateFormat();
            String dateFormatPattern = dateHBox.getDateFormatPattern();
            boolean keepCurrentName = currentNamePane.keepName();
            String nameCase = currentNamePane.getCase();
            String getFind = currentNamePane.getFind();
            String getReplace = currentNamePane.getReplace();
            boolean changeExtension = changeExtensionPane.changeExtension();
            String newExtension = changeExtensionPane.getNewExtension();

            FormData formData = new FormData();
            formData.setDirectoryPath(directoryPath);
            formData.setPattern(pattern);
            formData.setStartNumber(startNumber);
            formData.setIncrementBy(incrementBy);
            formData.setDigitsNumber(digitsNumber);
            formData.setDecrement(decrement);
            formData.setSorted(sorted);
            formData.setDateFormatSelection(dateFormatSelection);
            formData.setDateFormatPattern(dateFormatPattern);
            formData.setFilterByExtension(filterByExtension);
            formData.setExtension(extension);
            formData.setKeepCurrentName(keepCurrentName);
            formData.setNameCase(nameCase);
            formData.setFind(getFind);
            formData.setReplace(getReplace);
            formData.setChangeExtension(changeExtension);
            formData.setNewExtension(newExtension);

            // Rename files
            RenameService renameService = new RenameService(formData);
            renameService.rename();
        });

        // Add components to form
        form.add(folderPane, 0, 0);

        HBox firstHBox = new HBox();
        firstHBox.setSpacing(20);
        firstHBox.setBorder(Constraints.BORDER);
        firstHBox.setPadding(new Insets(10, 10, 10, 10));
        firstHBox.getChildren().addAll(extensionFilterPane, sortedCheck);

        HBox secondHBox = new HBox();
        secondHBox.setSpacing(20);
        secondHBox.setBorder(Constraints.BORDER);
        secondHBox.setPadding(new Insets(10, 10, 10, 10));
        secondHBox.getChildren().addAll(currentNamePane);

        HBox thirdHBox = new HBox();
        thirdHBox.setSpacing(20);
        thirdHBox.setBorder(Constraints.BORDER);
        thirdHBox.setPadding(new Insets(10, 10, 10, 10));
        thirdHBox.getChildren().addAll(startingNumberPane, digitsPane, incrementPane, decrementCheck);

        HBox fourthHBox = new HBox();
        fourthHBox.setSpacing(20);
        fourthHBox.setBorder(Constraints.BORDER);
        fourthHBox.setPadding(new Insets(10, 10, 10, 10));
        fourthHBox.getChildren().addAll(patternPane);

        HBox fifthHBox = new HBox();
        fifthHBox.setSpacing(20);
        fifthHBox.setBorder(Constraints.BORDER);
        fifthHBox.setPadding(new Insets(10, 10, 10, 10));
        fifthHBox.getChildren().addAll(changeExtensionPane);

        form.add(firstHBox, 0, 1);
        form.add(secondHBox, 0, 2);
        form.add(thirdHBox, 0, 3);
        form.add(dateHBox, 0, 4);
        form.add(fourthHBox, 0, 5);
        form.add(fifthHBox, 0, 6);
        form.add(submit, 0, 7);

        // Add all to VBox
        pane.getChildren().addAll(menuBar, form);

        // Show stage
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public FolderPane getFolderPane() {
        return folderPane;
    }

    public ExtensionFilterPane getExtensionFilterPane() {
        return extensionFilterPane;
    }

    public StartingNumberPane getStartingNumberPane() {
        return startingNumberPane;
    }

    public NumberOfDigitsPane getDigitsPane() {
        return digitsPane;
    }

    public IncrementPane getIncrementPane() {
        return incrementPane;
    }

    public PatternPane getPatternPane() {
        return patternPane;
    }

    public CurrentNamePane getCurrentNamePane() {
        return currentNamePane;
    }

    public ChangeExtensionPane getChangeExtensionPane() {
        return changeExtensionPane;
    }

    public DateHBox getDateHBox() {
        return dateHBox;
    }

    public DecrementCheckBox getDecrementCheck() {
        return decrementCheck;
    }

    public SortedCheckBox getSortedCheck() {
        return sortedCheck;
    }

    public static void main(String[] args) {
        launch(args);
    }
}