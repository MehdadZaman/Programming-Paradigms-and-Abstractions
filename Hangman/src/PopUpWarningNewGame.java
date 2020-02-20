import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.concurrent.atomic.AtomicInteger;

public class PopUpWarningNewGame {

    public static AtomicInteger display(String title, String message)
    {
        AtomicInteger ret = new AtomicInteger();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        stage.setMaxWidth(300);
        stage.setMinWidth(300);
        stage.setMaxHeight(200);
        stage.setMinHeight(200);

        Label messageLabel = new Label();
        messageLabel.setText(message);

        Button yes = new Button("Yes");
        yes.setOnAction(e ->
        {
            Hangman.save.fire();
            stage.close();
            ret.set(-1);
        });

        Button no = new Button("No");
        no.setOnAction(e ->
        {
            stage.close();
            ret.set(-1);
        });

        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> stage.close());

        HBox buttons = new HBox();
        buttons.getChildren().addAll(yes, no, cancel);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();

        return ret;
    }
}
