/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Orion Kinsler
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryListTest {
    InventoryList iManager = new InventoryList();

    @Test
    void createItem_multiple_items() {
        iManager.createItem("Test1", "123456789A", "50");
        iManager.createItem("Test2", "123456789B", "95");

        assertEquals(2, iManager.itemList.size());
    }

    @Test
    void createItem_detect_duplicate_serial() {
        iManager.createItem("Test", "123456789A", "50");
        iManager.createItem("Test1", "123456789A", "95");

        assertEquals(1, iManager.itemList.size());
    }

    @Test
    void createItem_detect_wrong_serial_inputs() {
        iManager.createItem("Test", "123456789A", "50");
        iManager.createItem("Test1", "123456789&", "95");

        assertEquals(1, iManager.itemList.size());
    }

    @Test
    void createItem_detect_wrong_value_inputs() {
        iManager.createItem("Test", "123456789A", "50");
        iManager.createItem("Test1", "123456789B", "ABC");

        assertEquals(1, iManager.itemList.size());
    }

    @Test
    void createItem_detect_wrong_value_inputs_special_characters() {
        iManager.createItem("Test", "123456789A", "50");
        iManager.createItem("Test1", "123456789B", "*&");

        assertEquals(1, iManager.itemList.size());
    }

    @Test
    void checkSerial_correct_input() {
        String input = "123456789A";

        Boolean actual = iManager.checkSerial(input);

        assertEquals(true, actual);
    }

    @Test
    void checkSerial_short_serial() {
        String input = "123456";

        Boolean actual = iManager.checkSerial(input);

        assertEquals(false, actual);
    }

    @Test
    void checkSerial_special_characters() {
        String input = "123456****";

        Boolean actual = iManager.checkSerial(input);

        assertEquals(false, actual);
    }

    @Test
    void checkValue_correct_value() {
        String input = "20.56";

        Boolean actual = iManager.checkValue(input);

        assertEquals(true, actual);
    }

    @Test
    void checkValue_letter_inputs() {
        String input = "abc";

        Boolean actual = iManager.checkValue(input);

        assertEquals(false, actual);
    }

    @Test
    void checkValue_special_characters() {
        String input = "^&*";

        Boolean actual = iManager.checkValue(input);

        assertEquals(false, actual);
    }

    @Test
    void addItem_one_item() {
        iManager.addItem("Test1", "123456789A", "50");

        assertEquals(1, iManager.itemList.size());
    }

    @Test
    void addItem_multiple_items() {
        iManager.addItem("Test1", "123456789A", "50");
        iManager.addItem("Test2", "123456789B", "51");
        iManager.addItem("Test3", "123456789C", "52");

        assertEquals(3, iManager.itemList.size());
    }

    @Test
    void deleteItem_one_item() {
        iManager.addItem("Test1", "123456789A", "50");
        iManager.addItem("Test2", "123456789B", "51");
        iManager.addItem("Test3", "123456789C", "52");

        iManager.deleteItem(2);

        assertEquals(2, iManager.itemList.size());
    }

    @Test
    void deleteItem_correct_item() {
        iManager.addItem("Test1", "123456789A", "50");
        iManager.addItem("Test2", "123456789B", "51");
        iManager.addItem("Test3", "123456789C", "52");

        iManager.deleteItem(1);

        assertEquals("Test3", iManager.itemList.get(1).name);
    }

    @Test
    void clearList_clears_list() {
        iManager.addItem("Test1", "123456789A", "50");
        iManager.addItem("Test2", "123456789B", "51");
        iManager.addItem("Test3", "123456789C", "52");

        iManager.clearList();

        assertEquals(1, iManager.itemList.size());
    }

    @Test
    void sortList_sorts_name() {
        iManager.addItem("Test1", "123456789C", "50");
        iManager.addItem("Test2", "123456789B", "52");
        iManager.addItem("Test3", "123456789A", "51");

        iManager.sortList(0);

        String[] actual = new String[3];

        for (int i = 0; i < iManager.itemList.size(); i++){
            actual[i] = iManager.itemList.get(i).name;
        }

        String[] expected = {"Test1", "Test2", "Test3"};

        assertArrayEquals(expected,actual);
    }

    @Test
    void sortList_sorts_serial_number() {
        iManager.addItem("Test1", "123456789C", "50");
        iManager.addItem("Test2", "123456789B", "52");
        iManager.addItem("Test3", "123456789A", "51");

        iManager.sortList(1);

        String[] actual = new String[3];

        for (int i = 0; i < iManager.itemList.size(); i++){
            actual[i] = iManager.itemList.get(i).serialNumber;
        }

        String[] expected = {"123456789A", "123456789B", "123456789C"};

        assertArrayEquals(expected,actual);
    }

    @Test
    void sortList_sorts_value() {
        iManager.addItem("Test1", "123456789C", "50");
        iManager.addItem("Test2", "123456789B", "52");
        iManager.addItem("Test3", "123456789A", "51");

        iManager.sortList(2);

        String[] actual = new String[3];

        for (int i = 0; i < iManager.itemList.size(); i++){
            actual[i] = iManager.itemList.get(i).value;
        }

        String[] expected = {"50", "51", "52"};

        assertArrayEquals(expected,actual);
    }

    @Test
    void editItemName_correct_item() {
        iManager.addItem("Test1", "123456789C", "50");
        iManager.addItem("Test2", "123456789B", "52");
        iManager.addItem("Test3", "123456789A", "51");

        iManager.editItemName(2, "Test4");

        assertEquals("Test4", iManager.itemList.get(2).name);
    }

    @Test
    void editSerialNumber_correct_serial_number() {
        iManager.addItem("Test1", "123456789C", "50");
        iManager.addItem("Test2", "123456789B", "52");
        iManager.addItem("Test3", "123456789A", "51");

        iManager.editSerialNumber(2, "123456789D");

        assertEquals("123456789D", iManager.itemList.get(2).serialNumber);
    }

    @Test
    void editItemValue_correct_value() {
        iManager.addItem("Test1", "123456789C", "50");
        iManager.addItem("Test2", "123456789B", "52");
        iManager.addItem("Test3", "123456789A", "51");

        iManager.editItemValue(2, "99");

        assertEquals("99", iManager.itemList.get(2).value);
    }

    @Test
    void editItemValue_letter_inputs() {
        iManager.addItem("Test1", "123456789C", "50");
        iManager.addItem("Test2", "123456789B", "52");
        iManager.addItem("Test3", "123456789A", "51");

        iManager.editItemValue(1, "ABC");

        assertEquals("52", iManager.itemList.get(1).value);
    }
}