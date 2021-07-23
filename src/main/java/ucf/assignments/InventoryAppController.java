package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Bao Kastan
 */

import com.jfoenix.controls.JFXDecorator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryAppController implements Initializable {


    public TableView<Item> tableListView;
    private final ObservableList<Item> list = FXCollections.observableArrayList();
    FilteredList<Item> filteredData = new FilteredList<>(list, p -> true);
    SortedList<Item> sortedList = new SortedList<>(filteredData);
    @FXML
    private TableColumn<Item, String> colName;
    @FXML
    private TableColumn<Item, String> colSerialNum;
    @FXML
    private TableColumn<Item, String> colPrice;

    @FXML
    private TextField searchBar;

    public ObservableList<Item> getList() {
        return list;
    }

    @FXML
    void LoadButtonClicked(ActionEvent event) {

    }

    @FXML
    void addButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemCreator.fxml"));
        Parent parent = fxmlLoader.load();
        ItemCreatorController ItemCreator = fxmlLoader.<ItemCreatorController>getController();
        ItemCreator.setList(list);

        Stage stage = new Stage();
        JFXDecorator decorator = new JFXDecorator(stage, parent);
        decorator.setCustomMaximize(true);

        Scene scene = new Scene(decorator);
        scene.getStylesheets().add("ucf/assignments/StageFrame.css");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {

    }

    @FXML
    void helpButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveAsHTMLButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveAsJSONButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveAsTSVButtonClicked(ActionEvent event) {

    }

    @FXML
    void sortByNameButtonClicked(ActionEvent event) {

    }

    @FXML
    void sortBySerialNumButtonClicked(ActionEvent event) {

    }

    @FXML
    void sortByValueButtonClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colSerialNum.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn());

        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSerialNum.setCellValueFactory(new PropertyValueFactory<>("SerialNum"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        tableListView.setItems(list);
    }
}
