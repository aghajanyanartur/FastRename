package app.model.components.panes;

import app.model.constraints.Constraints;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CurrentNamePane extends Pane {

    private CheckBox keepNameCheckBox;
    private ComboBox<String> caseComboBox;
    private TextField findTextField;
    private Label replaceLabel;
    private TextField replaceTextField;

    private PatternPane patternPane;

    public CurrentNamePane(PatternPane patternPane) {

        this.patternPane = patternPane;

        keepNameCheckBox = new CheckBox("Use current name");
        keepNameCheckBox.setFont(Constraints.FONT);
        keepNameCheckBox.setOnAction(e -> toggleComponents(keepNameCheckBox.isSelected()));

        caseComboBox = new ComboBox<>();
        caseComboBox.getItems().addAll("Keep Cases", "Lowercase", "Uppercase");
        caseComboBox.setValue("Keep Cases");
        caseComboBox.setDisable(true);

        replaceLabel = new Label("Find and replace:");
        replaceLabel.setFont(Constraints.FONT);
        replaceLabel.setDisable(true);

        findTextField = new TextField();
        findTextField.setFont(Constraints.FONT);
        findTextField.setPromptText("Find");
        findTextField.setDisable(true);

        replaceTextField = new TextField();
        replaceLabel.setFont(Constraints.FONT);
        replaceTextField.setPromptText("Replace");
        replaceTextField.setDisable(true);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(keepNameCheckBox, caseComboBox, replaceLabel, findTextField, replaceTextField);

        getChildren().addAll(hBox);
    }

    private void toggleComponents(boolean enable) {
        caseComboBox.setDisable(!enable);
        replaceLabel.setDisable(!enable);
        findTextField.setDisable(!enable);
        replaceTextField.setDisable(!enable);
        patternPane.getCurrentNameButton().setDisable(!enable);
    }

    public boolean keepName() {
        return keepNameCheckBox.isSelected();
    }

    public void setKeepName(boolean keepName) {
        keepNameCheckBox.setSelected(keepName);
        toggleComponents(keepName);
    }

    public String getCase() {
        return caseComboBox.getValue();
    }

    public void setCase(String nameCase) {
        caseComboBox.setValue(nameCase);
    }

    public String getFind() {
        return findTextField.getText();
    }

    public void setFind(String find) {
        findTextField.setText(find);
    }

    public String getReplace() {
        return replaceTextField.getText();
    }

    public void setReplace(String replace) {
        replaceTextField.setText(replace);
    }
}
