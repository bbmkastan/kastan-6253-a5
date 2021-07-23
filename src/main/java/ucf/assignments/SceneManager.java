package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
}
