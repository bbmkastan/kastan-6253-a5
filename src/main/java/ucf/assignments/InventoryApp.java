package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXDecorator;

import java.io.IOException;
import java.util.Objects;

public class InventoryApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryApp.fxml")));
        JFXDecorator decorator = new JFXDecorator(primaryStage, root);
        decorator.setCustomMaximize(true);

        Scene scene = new Scene(decorator);
        scene.getStylesheets().add("ucf/assignments/StageFrame.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
