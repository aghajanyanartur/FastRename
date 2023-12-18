package app.model.constraints;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Constraints {

    private Constraints() {
        throw new IllegalStateException("Utility class");
    }

    public static final Border BORDER = new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT));
    public static final Font FONT = new Font("Arial", 12);
    public static final String ABOUT_TEXT = """
            Fast Rename is a powerful and user-friendly application designed to
            streamline the process of renaming files in bulk.
            With its intuitive interface and flexible renaming rules,
            Fast Rename makes organizing your digital files easier and faster.
            
            The application is created by Artur Aghajanyan.

            Please visit the following websites for more information.
            Thank you for using Fast Rename!""";
}
