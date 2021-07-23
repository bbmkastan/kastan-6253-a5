package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Bao Kastan
 */

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ItemCreatorController {

    private ObservableList<Item> itemList;

    @FXML
    private TextField itemNameTextField;

    @FXML
    private TextField serialNumberTextField;

    @FXML
    private TextField valueTextField;

    @FXML
    void createItemButtonClicked(ActionEvent event) throws IOException {
        String name = getName();
        String serialNum = getSerialNum();
        String price = getPrice();
        if (name != null && serialNum != null && price != null) {
            Item item = new Item(name, serialNum, price);
            itemList.add(item);
        }
    }

    public void setList(ObservableList<Item> list) {
        this.itemList = list;
    }

    boolean isDuplicateSerialNum(String serialNum) {
        for (Item item : itemList) {
            if (item.getSerialNum().equals(serialNum)) {
                return true;
            }
        }
        // warning box
        return false;
    }

    String getName() {
        if (itemNameTextField.getText().trim().length() > 1 && itemNameTextField.getText().trim().length() < 257) {
            return itemNameTextField.getText();
        }
        SceneManager sm = new SceneManager();
        sm.loadAlertErrorBox("Invalid Input","Names have to be at least 2 characters long and" +
                "at most 256 characters long.");
        return null;
    }

    String getSerialNum() {
        SceneManager sm = new SceneManager();
        if (serialNumberTextField.getText().trim().length() == 10) {
            if (!isDuplicateSerialNum(serialNumberTextField.getText())) {
                return serialNumberTextField.getText();
            }
            sm.loadAlertErrorBox("Invalid Input", "This serial number already exist");
            return null;
        }
        sm.loadAlertErrorBox("Invalid Input", "Serial numbers have to be 10 characters long");
        return null;
    }

    String getPrice() {
        String price = null;
        try {
            double num = Double.parseDouble(valueTextField.getText().trim());
            price = String.format("%.2f", num);
        } catch (NumberFormatException e) {
            SceneManager sm = new SceneManager();
            sm.loadAlertErrorBox("Invalid Input", "Invalid Input in price text field:" +
                    "Make sure to to put in numbers only");
        }
        return price;
    }

}
