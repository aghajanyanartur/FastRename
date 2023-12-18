package app.model.components.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class ErrorWindow extends Alert {

    public ErrorWindow(String message) {
        super(AlertType.INFORMATION);
        setGraphic(null);
        setTitle("Something went wrong");
        setHeaderText(null);
        Stage alertStage = (Stage) getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));

        setContentText(message);
    }
}
