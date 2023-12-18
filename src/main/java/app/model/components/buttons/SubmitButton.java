package app.model.components.buttons;

import app.model.constraints.Constraints;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class SubmitButton extends Button {

    public SubmitButton() {
        super("Rename");

        setPrefHeight(40);
        setPrefWidth(120);
        setFont(Constraints.FONT);
        setCursor(Cursor.HAND);
    }
}
