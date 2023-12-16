package app.model.components;

import javafx.application.Application;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.text.*;

public class AuthorInformation extends TextFlow {
    private Hyperlink personalWebsiteLink;
    private Hyperlink projectWebsiteLink;
    private Text authorName;
    private Text thankyouText;

    public AuthorInformation(Application application) {
        personalWebsiteLink = new Hyperlink("Go to my personal website");

        personalWebsiteLink.setOnAction(e -> application.getHostServices().showDocument("https://aghajanyanartur.github.io"));
        personalWebsiteLink.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        personalWebsiteLink.setStyle("-fx-text-fill: #000;");
        personalWebsiteLink.setUnderline(true);
        personalWebsiteLink.setBorder(Border.EMPTY);

        projectWebsiteLink = new Hyperlink("Get help with the application here");
        projectWebsiteLink.setOnAction(e -> application.getHostServices().showDocument("https://aghajanyanartur.github.io/fast-rename-web/"));
        projectWebsiteLink.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        projectWebsiteLink.setStyle("-fx-text-fill: #000;");
        projectWebsiteLink.setUnderline(true);
        projectWebsiteLink.setBorder(Border.EMPTY);

        authorName = new Text("Author: Artur Aghajanyan \n");
        authorName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        thankyouText = new Text("\nThank you for using Fast Rename!");
        thankyouText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        setTextAlignment(TextAlignment.CENTER);
        getChildren().addAll(authorName, personalWebsiteLink, new Text("\n"), projectWebsiteLink, thankyouText);
    }
}