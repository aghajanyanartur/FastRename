package app.model.components;

import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;

public class SubmitButton extends Button {

    public SubmitButton() {
        super("Rename");
        setStyle("-fx-background-color: #333; -fx-text-fill: white;" +
                "-fx-padding: 10px 20px; -fx-font-size: 16px;" +
                "-fx-border-radius: 5px;");

        InnerShadow shadow = new InnerShadow();
        shadow.setOffsetX(4.0f);
        shadow.setOffsetY(4.0f);

        setOnMouseEntered(event -> setEffect(shadow));
        setOnMouseExited(event -> setEffect(null));
    }
}
