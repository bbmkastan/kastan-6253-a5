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
    public TableColumn<Item, String> colName;
    public TableColumn<Item, String> colSerialNum;
    public TableColumn<Item, String> colPrice;

    private final ObservableList<Item> list = FXCollections.observableArrayList();
    FilteredList<Item> filteredData = new FilteredList<>(list, p -> true);
    SortedList<Item> sortedList = new SortedList<>(filteredData);

    @FXML
    private TextField searchBar;

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
        int visibleIndex = tableListView.getSelectionModel().getSelectedIndex();
        list.remove(visibleIndex);
        tableListView.refresh();
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

    boolean isDuplicateSerialNum(String serialNum) {
        for (Item item : list) {
            if (item.getSerialNum().equals(serialNum)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colName.setOnEditCommit(event -> {
            Item item = event.getRowValue();
            if (event.getNewValue().trim().length() > 1 && event.getNewValue().trim().length() < 257) {
                item.setName(event.getNewValue());
            } else {
                SceneManager sceneManager = new SceneManager();
                sceneManager.loadAlertErrorBox("Invalid Input",
                        "Names have to be at least 2 characters long and" +
                        "at most 256 characters long.");
                item.setName(event.getOldValue());
            }
            tableListView.refresh();
        });

        colSerialNum.setCellFactory(TextFieldTableCell.forTableColumn());
        colSerialNum.setCellValueFactory(new PropertyValueFactory<>("SerialNum"));
        colSerialNum.setOnEditCommit(event -> {
            SceneManager sceneManager = new SceneManager();
            String serialNum = event.getNewValue();
            Item item = event.getRowValue();
            if (serialNum.trim().length() == 10) {
                if (!isDuplicateSerialNum(serialNum)) {
                    item.setSerialNum(event.getNewValue());
                } else {
                    sceneManager.loadAlertErrorBox("Invalid Input",
                            "This serial number already exist");
                    item.setSerialNum(event.getOldValue());
                }
            }
            else {
                sceneManager.loadAlertErrorBox("Invalid Input",
                        "Serial numbers have to be 10 characters long");
                item.setSerialNum(event.getOldValue());
            }
            tableListView.refresh();
        });

        colPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colPrice.setOnEditCommit(event -> {
            Item item = event.getRowValue();
            try {
                double num = Double.parseDouble(event.getNewValue());
                item.setPrice(String.format("%.2f", num));
            } catch (NumberFormatException e) {
                SceneManager sceneManager = new SceneManager();
                sceneManager.loadAlertErrorBox("Invalid Input",
                        "Invalid Input in price text field:" +
                        "Make sure to to put in numbers only");
                item.setPrice(event.getOldValue());
            }
            tableListView.refresh();
        });

        tableListView.setItems(sortedList);
    }
}
