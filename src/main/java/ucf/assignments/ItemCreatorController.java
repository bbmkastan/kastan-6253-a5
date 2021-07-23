package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Bao Kastan
 */

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        return false;
    }

    String getName() {
        if (itemNameTextField.getText().trim().length() > 1 && itemNameTextField.getText().trim().length() < 257) {
            return itemNameTextField.getText();
        }
        // warning box
        return null;
    }

    String getSerialNum() {
        if (serialNumberTextField.getText().trim().length() == 10) {
            if (!isDuplicateSerialNum(serialNumberTextField.getText())) {
                return serialNumberTextField.getText();
            }
            // warning box
            return null;
        }
        // warning box
        return null;
    }

    String getPrice() {
        String price = null;
        try {
            double num = Double.parseDouble(valueTextField.getText().trim());
            price = String.format("$%.2f", num);
        } catch(NumberFormatException e) {
            // warning box
        }
        return price;
    }

}
