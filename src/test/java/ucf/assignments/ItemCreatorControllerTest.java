package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemCreatorControllerTest {

    @Test
    void createItemTest() {
        ObservableList<Item> list = FXCollections.observableArrayList();
        ItemCreatorController icc = new ItemCreatorController();
        icc.createItem("testItem1","1234567890","78.43", list);
        assertEquals("1234567890", list.get(0).getSerialNum());
    }

    @Test
    void isDuplicateSerialNumTest() {
        ObservableList<Item> list = FXCollections.observableArrayList();
        list.addAll(new Item("testItem1","1234567890","78.43"),
                new Item("testItem2","0987654321","56.56"),
                new Item("testItem3","6543217890","63.05"));
        ItemCreatorController icc = new ItemCreatorController();
        assertTrue(icc.isDuplicateSerialNum("0987654321", list));
    }
}