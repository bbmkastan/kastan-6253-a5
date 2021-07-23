package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Bao Kastan
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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
        SceneManager sceneManager = new SceneManager();
        sceneManager.loadItemCreatorScene(list);
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {

    }

    @FXML
    void helpButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveAsButtonClicked(ActionEvent event) {

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
        tableListView.setItems(list);

        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colSerialNum.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn());

        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSerialNum.setCellValueFactory(new PropertyValueFactory<>("SerialNum"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }
}
