package app.model;

import app.model.components.*;
import app.model.components.checkboxes.DecrementCheckBox;
import app.model.components.checkboxes.SortedCheckBox;
import app.model.components.checkboxes.TrailingCheckBox;
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

    @Override
    public void start(Stage stage) {

        // Create pane and scene
        VBox pane = new VBox();
        Scene scene = new Scene(pane, 1000, 700);

        // Set logo image and title
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png")));
        stage.getIcons().add(image);
        stage.setTitle("Fast Rename | Rename your files quickly! | Made by Artur Aghajanyan");

        pane.setBackground(new Background(new BackgroundFill(Color.valueOf("#73A5C6"), CornerRadii.EMPTY, Insets.EMPTY)));
        // Set pane padding and spacing
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setAlignment(Pos.TOP_CENTER);

        // Form
        GridPane form = new GridPane();
        form.setStyle("-fx-background-color: #bcd2e8; -fx-padding: 10px; -fx-background-radius: 10px;");
        form.setHgap(20);
        form.setVgap(10);
        form.setAlignment(Pos.TOP_CENTER);

        // Components
        FolderPane folderPane = new FolderPane();
        StartingNumberPane startingNumberPane = new StartingNumberPane();
        NumberOfDigitsPane digitsPane = new NumberOfDigitsPane();
        IncrementPane incrementPane = new IncrementPane();
        PatternPane patternPane = new PatternPane();
        DateHBox dateHBox = new DateHBox();
        DecrementCheckBox decrementCheck = new DecrementCheckBox();
        TrailingCheckBox trailingCheck = new TrailingCheckBox();
        SortedCheckBox sortedCheck = new SortedCheckBox();

        // Submit button
        SubmitButton submit = new SubmitButton();
        submit.setOnAction(e -> {
            // Handle form submission
            String directoryPath = folderPane.getFolderAddress();
            String pattern = patternPane.getPattern();
            int startNumber = startingNumberPane.getStartingNumber();
            int incrementBy = incrementPane.getIncrement();
            int digitsNumber = digitsPane.getStartingNumber();
            boolean decrement = decrementCheck.isDecrement();
            boolean patternTrailing = trailingCheck.isTrailing();
            boolean sorted = sortedCheck.isSorted();
            String dateFormatSelection = dateHBox.getDateFormat();
            String dateFormatPattern = dateHBox.getDateFormatPattern();

            RenameUtil.renameToPattern(directoryPath, pattern, startNumber, incrementBy,
                    digitsNumber, !decrement, !patternTrailing, sorted, dateFormatSelection, dateFormatPattern);
        });

        // Add components to form
        form.add(folderPane, 0, 0);
        form.add(startingNumberPane, 0, 1);
        form.add(digitsPane, 1, 1);
        form.add(incrementPane, 0, 2);
        form.add(patternPane, 1, 2);
        form.add(dateHBox, 0, 6, 2, 1);
        form.add(decrementCheck, 0, 3);
        form.add(trailingCheck, 1, 3);
        form.add(sortedCheck, 0, 4, 2, 1);
        form.add(submit, 0, 8, 2, 1);

        // Add all to VBox
        pane.getChildren().addAll(form);

        // Show stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}