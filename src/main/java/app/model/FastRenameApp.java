package app.model;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.Objects;

public class FastRenameApp extends Application {

    private static final String FONT = "Arial";

    @Override
    public void start(Stage stage) {

        // Create pane and scene
        VBox pane = new VBox();

        Scene scene = new Scene(pane, 1000, 700);

        // Set logo image and title
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png")));
        stage.getIcons().add(image);
        stage.setTitle("Fast Rename | Rename your files quickly! | Made by Artur Aghajanyan");

        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#CCC")),
                new Stop(0.33, Color.web("#8EC5FC")),
                new Stop(0.66, Color.web("#6284FF")),
                new Stop(1, Color.web("#FC00FF")));

        // Set pane background
        pane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // Set pane padding and spacing
        pane.setPadding(new Insets(30, 20, 20, 20));
        pane.setSpacing(20);
        pane.setAlignment(Pos.CENTER);

        // Page title
        Label title = new Label("Fast Rename");
        title.setFont(Font.font(FONT, FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill: #000; -fx-margin: 40px; -fx-text-alignment: center;");
        title.setAlignment(Pos.CENTER);

        // Description text
        Text descText = new Text();
        descText.setText("""
                Fast Rename is the perfect tool for anyone who needs to rename files on a regular basis.
                With its intuitive interface and powerful renaming capabilities,
                you can streamline your file management process and save valuable time.
                With just a few clicks, you can rename the files in a folder according to your custom pattern."""
        );
        descText.setFont(Font.font(FONT, 16));
        descText.setFill(Color.web("#000"));
        descText.setStyle("-fx-background-color: #f0f0f0;");

        // Form
        GridPane form = new GridPane();
        form.setStyle("-fx-background-color: rgba(255,255,255,0.7);" +
                "-fx-padding: 20px;" +
                "-fx-margin-top: 20px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 2, 5);");
        form.setHgap(20);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        // Folder path
        Label folderLabel = new Label("Folder Address:");
        folderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        TextField folderField = new TextField();
        folderField.setPromptText("ex: C:/User/my_folder");
        form.add(folderLabel, 0, 0);
        form.add(folderField, 1, 0);

        // Starting number
        Label startLabel = new Label("Starting Number:");
        startLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        TextField startField = new TextField("1");
        startField.setStyle("-fx-font-size: 16px;");
        form.add(startLabel, 0, 1);
        form.add(startField, 1, 1);

        // Number of digits
        Label digitsLabel = new Label("Number of Digits to Fill:");
        digitsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        TextField digitsField = new TextField("2");
        digitsField.setStyle("-fx-font-size: 16px;");
        form.add(digitsLabel, 0, 2);
        form.add(digitsField, 1, 2);

        // Increment by
        Label incrementLabel = new Label("Increment By:");
        incrementLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        TextField incrementField = new TextField("1");
        incrementField.setStyle("-fx-font-size: 16px;");
        form.add(incrementLabel, 0, 3);
        form.add(incrementField, 1, 3);

        // Additional name pattern
        Label patternLabel = new Label("Additional Name Pattern:");
        patternLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        TextField patternField = new TextField("-");
        patternField.setStyle("-fx-font-size: 16px;");
        form.add(patternLabel, 0, 4);
        form.add(patternField, 1, 4);

        // TextField with explanation
        Text patternText = new Text();
        patternText.setText("Remember: Windows does not allow the following characters in file names: \\/ : * ? \" < > |");
        patternText.setFont(Font.font(FONT, 14));
        patternText.setFill(Color.web("#333"));
        form.add(patternText, 0, 5, 2, 1);

        // Date format
        Label dateFormatLabel = new Label("Add date:");
        dateFormatLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        ComboBox<String> dateFormatCombo = new ComboBox<>();
        dateFormatCombo.getItems().addAll("No Date", "Creation Date", "Last Modified Date", "Current Date");
        dateFormatCombo.setValue("No Date");
        ComboBox<String> dateFormatPatternCombo = new ComboBox<>();
        dateFormatPatternCombo.getItems().addAll("yyyyMMdd", "yyyy-MM-dd", "yyyy.MM.dd", "yyyy_MM_dd",
                "MMddyyyy", "MM-dd-yyyy", "MM.dd.yyyy", "MM_dd_yyyy",
                "ddMMyyyy", "dd-MM-yyyy", "dd.MM.yyyy", "dd_MM_yyyy");
        dateFormatPatternCombo.setValue("yyyyMMdd");

        HBox date = new HBox(140);
        date.setAlignment(Pos.CENTER_LEFT); // Align to the right
        date.getChildren().addAll(dateFormatLabel, dateFormatCombo, dateFormatPatternCombo);

        form.add(date, 0, 6, 2, 1);

        // Checkboxes
        CheckBox decrementCheck = new CheckBox("Decrement");
        decrementCheck.setFont(Font.font(FONT, 16));
        CheckBox trailingCheck = new CheckBox("Name Pattern Trailing");
        trailingCheck.setFont(Font.font(FONT, 16));
        CheckBox sortedCheck = new CheckBox("Sort Alphabetically Before Renaming");
        sortedCheck.setFont(Font.font(FONT, 16));

        HBox checks = new HBox(10);
        checks.getChildren().addAll(decrementCheck, trailingCheck, sortedCheck);
        checks.setAlignment(Pos.CENTER_LEFT);
        form.add(checks, 0, 7, 2, 1);

        // Submit button
        Button submit = new Button("Rename");
        submit.setStyle("-fx-background-color: #333; -fx-text-fill: white;" +
                "-fx-padding: 10px 20px; -fx-font-size: 16px;" +
                "-fx-border-radius: 5px;");
        submit.setOnAction(e -> {
            // Handle form submission
            String directoryPath = folderField.getText();
            String pattern = patternField.getText();
            int startNumber = Integer.parseInt(startField.getText());
            int incrementBy = Integer.parseInt(incrementField.getText());
            int digitsNumber = Integer.parseInt(digitsField.getText());
            boolean decrement = decrementCheck.isSelected();
            boolean patternTrailing = trailingCheck.isSelected();
            boolean sorted = sortedCheck.isSelected();
            String dateFormatSelection = dateFormatCombo.getValue();
            String dateFormatPattern = dateFormatPatternCombo.getValue();

            RenameUtil.renameToPattern(directoryPath, pattern, startNumber, incrementBy,
                    digitsNumber, !decrement, !patternTrailing, sorted, dateFormatSelection, dateFormatPattern);
        });
        form.add(submit, 0, 8, 2, 1);

        // Author information
        Hyperlink personalWebsiteLink = new Hyperlink("Go to my personal website");
        personalWebsiteLink.setOnAction(e -> getHostServices().showDocument("https://aghajanyanartur.github.io"));
        personalWebsiteLink.setFont(Font.font(FONT, FontWeight.BOLD, 14));
        personalWebsiteLink.setStyle("-fx-text-fill: #000;");
        personalWebsiteLink.setUnderline(true);
        personalWebsiteLink.setBorder(Border.EMPTY);

        Hyperlink projectWebsiteLink = new Hyperlink("Get help with the application here");
        projectWebsiteLink.setOnAction(e -> getHostServices().showDocument("https://aghajanyanartur.github.io/fast-rename-web/"));
        projectWebsiteLink.setFont(Font.font(FONT, FontWeight.BOLD, 14));
        projectWebsiteLink.setStyle("-fx-text-fill: #000;");
        projectWebsiteLink.setUnderline(true);
        projectWebsiteLink.setBorder(Border.EMPTY);

        Text authorName = new Text("Author: Artur Aghajanyan \n");
        authorName.setFont(Font.font(FONT, FontWeight.BOLD, 14));
        Text thankyouText = new Text("\nThank you for using Fast Rename!");
        thankyouText.setFont(Font.font(FONT, FontWeight.BOLD, 14));

        TextFlow authortextFlow = new TextFlow(authorName, personalWebsiteLink, new Text("\n"), projectWebsiteLink, thankyouText);
        authortextFlow.setTextAlignment(TextAlignment.CENTER);

        // Create inner shadow effect
        InnerShadow shadow = new InnerShadow();
        shadow.setOffsetX(4.0f);
        shadow.setOffsetY(4.0f);

        // Add effect on hover
        submit.setOnMouseEntered(event -> submit.setEffect(shadow));
        submit.setOnMouseExited(event -> submit.setEffect(null));

        // Add all to VBox
        pane.getChildren().addAll(title, descText, form, authortextFlow);

        // Show stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}