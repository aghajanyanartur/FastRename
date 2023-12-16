package app.model.components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PatternPane extends Pane {

    private Label label;
    private TextField textField;
    private Text explainText;

    public PatternPane() {
        label = new Label("Additional Name Pattern:");
        label.setLayoutX(10);
        label.setLayoutY(10);

        textField = new TextField();
        textField.setLayoutX(10);
        textField.setLayoutY(30);
        textField.setPrefWidth(300);

        explainText = new Text();
        explainText.setLayoutX(20);
        explainText.setLayoutY(60);
        explainText.setText("Remember: Windows does not allow the following characters in file names: \\ / : * ? \" < > |");
        explainText.setFont(Font.font("Arial", 10));
        explainText.setFill(Color.web("#333"));

        getChildren().addAll(label, textField, explainText);
    }

    public String getPattern() {
        return textField.getText();
    }
}
