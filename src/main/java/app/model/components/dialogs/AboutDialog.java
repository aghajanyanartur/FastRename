package app.model.components.dialogs;

import app.model.constraints.Constraints;
import javafx.application.HostServices;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class AboutDialog extends Dialog<Void> {

    public AboutDialog(HostServices hostServices) {
        setTitle("Fast Rename");
        setHeaderText(null);
        DialogPane dialogPane = getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
        Stage dialogStage = (Stage) getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));

        Hyperlink websiteLink = new Hyperlink("Visit the application website");
        websiteLink.setFont(Constraints.FONT);
        websiteLink.setOnAction(e -> hostServices.showDocument("http://aghajanyanartur.github.io/fast-rename-web"));

        Hyperlink authorLink = new Hyperlink("Visit the author's website");
        authorLink.setFont(Constraints.FONT);
        authorLink.setOnAction(e -> hostServices.showDocument("http://aghajanyanartur.github.io"));

        Label label = new Label(Constraints.ABOUT_TEXT);
        label.setFont(Constraints.FONT);

        VBox content = new VBox(label, websiteLink, authorLink);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);

        dialogPane.setContent(content);
    }
}
