package app.model.components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class IncrementPane extends Pane {

    private Label label;
    private TextField textField;

    public IncrementPane() {
        label = new Label("Increment By:");
        label.setLayoutX(10);
        label.setLayoutY(10);

        textField = new TextField();
        textField.setLayoutX(10);
        textField.setLayoutY(30);
        textField.setPrefWidth(300);

        getChildren().addAll(label, textField);
    }

    public int getIncrement() {
        return Integer.parseInt(textField.getText());
    }
}
