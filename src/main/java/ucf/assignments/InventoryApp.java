package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXDecorator;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Bao Kastan
 */

import java.io.IOException;
import java.util.Objects;

public class InventoryApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneManager sceneManager = new SceneManager();
        sceneManager.loadInventoryAppScene(primaryStage);
    }
}
