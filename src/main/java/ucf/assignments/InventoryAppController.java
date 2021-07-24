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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryAppController implements Initializable {
    FileChooser fileChooser = new FileChooser();

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
    void loadButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveAsButtonClicked(ActionEvent event) throws IOException {
        fileChooser.setInitialFileName("Inventory");
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            fileChooser.setInitialDirectory(file.getParentFile());

            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, file.getName().length());
            FileManager fileManager = new FileManager();
            switch (fileExtension) {
                case "txt" -> fileManager.saveAsTVS(file.toString(), list);
                case "html" -> fileManager.saveAsHTML(file.toString(), list);
                case "json" -> fileManager.saveAsJSON(file.toString(), list);
            }
        }
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
        fileChooser.setInitialDirectory(new File("C:"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("text file (*.txt)","*.txt"),
                new FileChooser.ExtensionFilter("html file (*.html)", "*.html"),
                new FileChooser.ExtensionFilter("json file (*.json)", "*.json"));

        sortedList.comparatorProperty().bind(tableListView.comparatorProperty());

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(item -> {

            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            if (item.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (item.getSerialNum().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else return item.getPrice().toLowerCase().contains(lowerCaseFilter);
        }));

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
