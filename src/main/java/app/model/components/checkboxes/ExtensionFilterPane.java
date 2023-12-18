package app.model.components.checkboxes;

import app.model.constraints.Constraints;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ExtensionFilterPane extends Pane {

    private CheckBox extensionFilterCheckBox;
    private TextField extensionField;

    public ExtensionFilterPane() {

        extensionFilterCheckBox = new CheckBox("Filter by extension");
        extensionFilterCheckBox.setFont(Constraints.FONT);
        extensionFilterCheckBox.setOnAction(e -> toggleComponents(extensionFilterCheckBox.isSelected()));

        extensionField = new TextField();
        extensionField.setPromptText("Extension");
        extensionField.setFont(Constraints.FONT);
        extensionField.setDisable(true);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(extensionFilterCheckBox, extensionField);

        getChildren().addAll(hBox);
    }

    private void toggleComponents(boolean enable) {
        extensionField.setDisable(!enable);
    }

    public boolean filterByExtension() {
        return extensionFilterCheckBox.isSelected();
    }

    public void setFilterByExtension(boolean filterByExtension) {
        extensionFilterCheckBox.setSelected(filterByExtension);
        toggleComponents(filterByExtension);
    }

    public String getExtension() {
        return extensionField.getText();
    }

    public void setExtension(String extension) {
        extensionField.setText(extension);
    }
}
