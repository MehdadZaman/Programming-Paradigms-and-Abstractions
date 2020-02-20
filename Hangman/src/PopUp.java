import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class PopUp {

    public static void display(String title, String message) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMaxWidth(350);
        stage.setMinWidth(350);
        stage.setMaxHeight(200);
        stage.setMinHeight(200);
        Label messageLabel = new Label();
        messageLabel.setText(message);

        messageLabel.setAlignment(Pos.CENTER);

        Button close = new Button("Close");
        close.setOnAction(e -> stage.close());

        close.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
