package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Bao Kastan
 */

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {
    public void loadInventoryAppScene (Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryApp.fxml")));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void loadItemCreatorScene (ObservableList<Item> list) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemCreator.fxml"));
        Parent parent = fxmlLoader.load();
        ItemCreatorController ItemCreator = fxmlLoader.getController();
        ItemCreator.setList(list);

        Stage stage = new Stage();
        Scene scene = new Scene(parent);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void loadAlertErrorBox(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
