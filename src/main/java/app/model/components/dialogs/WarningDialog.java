package app.model.components.dialogs;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class WarningDialog extends Dialog<ButtonType> {
    ButtonType dismissButtonType;
    ButtonType renameAnywayButtonType;
    public WarningDialog(String message) {
        super();
        setGraphic(null);
        setTitle("Caution");
        setHeaderText(null);
        setContentText(message);
        Stage dialogStage = (Stage) getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));

        dismissButtonType = new ButtonType("Dismiss");
        renameAnywayButtonType = new ButtonType("Rename Anyway");
        getDialogPane().getButtonTypes().addAll(dismissButtonType, renameAnywayButtonType);
    }

    public ButtonType getDismissButtonType() {
        return dismissButtonType;
    }

    public ButtonType getRenameAnywayButtonType() {
        return renameAnywayButtonType;
    }
}
