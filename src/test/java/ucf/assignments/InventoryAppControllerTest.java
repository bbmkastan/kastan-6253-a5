package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InventoryAppControllerTest {

    @Test
    public void isDuplicateSerialNumTest() {
        ObservableList<Item> list = FXCollections.observableArrayList();
        list.addAll(new Item("testItem1","1234567890","78.43"),
                new Item("testItem2","0987654321","56.56"),
                new Item("testItem3","6543217890","63.05"));
        InventoryAppController iac = new InventoryAppController();
        assertTrue(iac.isDuplicateSerialNum("0987654321", list));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test.txt",
            "test.html",
            "test.json"})
    public void saveTest(String fileName) throws IOException {
        ObservableList<Item> list = FXCollections.observableArrayList();
        list.addAll(new Item("testItem1","1234567890","78.43"),
                new Item("testItem2","0987654321","56.56"),
                new Item("testItem3","6543217890","63.05"));
        InventoryAppController iac = new InventoryAppController();
        File file = new File("files/testing/" + fileName);
        iac.save(file, list);
        assertTrue(file.exists());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test.txt",
            "test.html",
            "test.json"})
    public void loadTest(String fileName) throws IOException {
        ObservableList<Item> actualList = FXCollections.observableArrayList();
        ObservableList<Item> expectedList = FXCollections.observableArrayList();
        expectedList.addAll(new Item("testItem1","1234567890","78.43"),
                new Item("testItem2","0987654321","56.56"),
                new Item("testItem3","6543217890","63.05"));
        InventoryAppController iac = new InventoryAppController();
        File file = new File("files/testing/" + fileName);
        iac.save(file, expectedList);
        iac.load(file, actualList);
        assertEquals(expectedList.size(), actualList.size());
    }

}