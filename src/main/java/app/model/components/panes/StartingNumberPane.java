package app.model.components.panes;

import app.model.constraints.Constraints;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;

public class StartingNumberPane extends Pane {
    private Label label;
    private Spinner<Integer> spinner;

    public StartingNumberPane() {
        label = new Label("Starting number:");
        label.setFont(Constraints.FONT);

        spinner = new Spinner<>(Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        spinner.setPrefWidth(100);
        spinner.setEditable(false);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(label, spinner);

        getChildren().addAll(vBox);
    }

    public int getStartingNumber() {
        return spinner.getValue();
    }

    public void setStartingNumber(int startingNumber) {
        spinner.getValueFactory().setValue(startingNumber);
    }

    private UnaryOperator<TextFormatter.Change> integerFilter() {
        return change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change; // Allow empty for clear operation
            }
            if (newText.matches("-?\\d*")) {
                return change; // Allow numeric values
            }
            return null; // Reject non-numeric
        };
    }
}