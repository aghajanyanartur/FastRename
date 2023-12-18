package app.model.components.panes;

import app.model.constraints.Constraints;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class IncrementPane extends Pane {

    private Label label;
    private Spinner<Integer> spinner;

    public IncrementPane() {
        label = new Label("Increment By:");
        label.setFont(Constraints.FONT);

        spinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
        spinner.setPrefWidth(100);
        spinner.setEditable(false);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(label, spinner);

        getChildren().addAll(vBox);
    }

    public int getIncrement() {
        return spinner.getValue();
    }

    public void setIncrement(int increment) {
        spinner.getValueFactory().setValue(increment);
    }
}