package app.model.components.panes;

import app.model.constraints.Constraints;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PatternPane extends Pane {

    private Label label;
    private TextField textField;
    private Button currentNameButton;
    private Button counterButton;
    private Button dateButton;
    private Button resetButton;

    public PatternPane() {
        label = new Label("Create Name Pattern:");
        label.setFont(Constraints.FONT);

        textField = new TextField();
        textField.setFont(Constraints.FONT);
        textField.setPrefWidth(500);

        resetButton = new Button("Reset");
        resetButton.setFont(Constraints.FONT);
        resetButton.setCursor(Cursor.HAND);
        resetButton.setOnAction(e -> textField.setText(""));

        currentNameButton = new Button("Current Name");
        currentNameButton.setFont(Constraints.FONT);
        currentNameButton.setCursor(Cursor.HAND);
        currentNameButton.setOnAction(e -> addCurrentName());
        currentNameButton.setDisable(true);

        counterButton = new Button("Counter");
        counterButton.setFont(Constraints.FONT);
        counterButton.setCursor(Cursor.HAND);
        counterButton.setOnAction(e -> addCounter());

        dateButton = new Button("Date");
        dateButton.setFont(Constraints.FONT);
        dateButton.setCursor(Cursor.HAND);
        dateButton.setOnAction(e -> addDate());

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20);
        buttonBox.getChildren().addAll(currentNameButton, counterButton, dateButton);

        HBox patternBox = new HBox();
        patternBox.setSpacing(20);
        patternBox.getChildren().addAll(textField, resetButton);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(label, buttonBox, patternBox);

        getChildren().addAll(vBox);
    }

    private void addCurrentName() {
        textField.setText(textField.getText() + "{current_name}");
    }

    private void addCounter() {
        textField.setText(textField.getText() + "{counter}");
    }

    private void addDate() {
        textField.setText(textField.getText() + "{date}");
    }

    public String getPattern() {
        return textField.getText();
    }

    public Button getCurrentNameButton() {
        return currentNameButton;
    }

    public void setPattern(String pattern) {
        textField.setText(pattern);
    }
}
