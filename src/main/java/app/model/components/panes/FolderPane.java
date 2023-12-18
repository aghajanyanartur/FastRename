package app.model.components.panes;

import app.model.constraints.Constraints;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class FolderPane extends Pane {
    private Label label;
    private TextField textField;
    private Button browseButton;
    private DirectoryChooser directoryChooser;

    public FolderPane() {
        label = new Label("Folder address:");
        label.setFont(Constraints.FONT);
        textField = new TextField();
        textField.setFont(Constraints.FONT);
        textField.setPrefWidth(300);
        browseButton = new Button("Browse");
        browseButton.setFont(Constraints.FONT);
        browseButton.setCursor(Cursor.HAND);
        browseButton.setOnAction(e -> openDirectoryChooser());

        directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(label, textField, browseButton);

        getChildren().addAll(hBox);
    }

    private void openDirectoryChooser() {
        File selectedDirectory = directoryChooser.showDialog(getScene().getWindow());
        if (selectedDirectory != null) {
            textField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    public String getFolderAddress() {
        return textField.getText();
    }

    public TextField getTextField() {
        return textField;
    }
}