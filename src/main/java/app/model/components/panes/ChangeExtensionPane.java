package app.model.components.panes;

import app.model.constraints.Constraints;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ChangeExtensionPane extends Pane {

    private CheckBox changeExtensionCheckBox;
    private TextField newExtensionField;

    public ChangeExtensionPane() {
        changeExtensionCheckBox = new CheckBox("Change extension");
        changeExtensionCheckBox.setFont(Constraints.FONT);
        changeExtensionCheckBox.setOnAction(e -> toggleComponents(changeExtensionCheckBox.isSelected()));

        newExtensionField = new TextField();
        newExtensionField.setFont(Constraints.FONT);
        newExtensionField.setPrefWidth(150);
        newExtensionField.setPromptText("New extension");
        newExtensionField.setDisable(true);

        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(changeExtensionCheckBox, newExtensionField);

        getChildren().addAll(hBox);
    }

    private void toggleComponents(boolean enable) {
        newExtensionField.setDisable(!enable);
    }

    public boolean changeExtension() {
        return changeExtensionCheckBox.isSelected();
    }

    public void setChangeExtension (boolean selected) {
        changeExtensionCheckBox.setSelected(selected);
        toggleComponents(selected);
    }

    public void setNewExtension(String newExtension) {
        newExtensionField.setText(newExtension);
    }

    public String getNewExtension() {
        return newExtensionField.getText();
    }
}
