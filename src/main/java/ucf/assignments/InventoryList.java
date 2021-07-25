/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Orion Kinsler
 */

package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.util.ArrayList;

public class InventoryList {
    ArrayList<Item> itemList = new ArrayList();
    FileManager fManager = new FileManager();
    Gson gson = new Gson();

    public String createItem(String name, String serialNumber, String value){
        if (checkSerial(serialNumber) && checkValue(value)){
            addItem(name, serialNumber, value);
            return "Item Created";
        }
        return "Incorrect entries!";
    }

    public Boolean checkSerial(String serialNumber){
        if (serialNumber.length() != 10){
            return false;
        }
        for (int i = 0; i < serialNumber.length(); i++){
            if (!Character.isLetterOrDigit(serialNumber.charAt(i))){
                return false;
            }
        }
        for (int i = 0; i < itemList.size(); i++){
            if (serialNumber.equalsIgnoreCase(itemList.get(i).serialNumber)){
                return false;
            }
        }
        return true;
    }

    public Boolean checkValue(String value){
        if (value.isEmpty()){
            return false;
        }
        for (int i = 0; i < value.length(); i++){
            if (!Character.isDigit(value.charAt(i)) && value.charAt(i) != '.'){
                return false;
            }
        }
        return true;
    }

    public void addItem(String name, String serialNumber, String value){
        Item addItem = new Item();
        addItem.editName(name);
        addItem.editSerialNumber(serialNumber);
        addItem.editValue(value);
        itemList.add(addItem);
    }

    public void deleteItem(int itemNumber){
        itemList.remove(itemNumber);
    }

    public void editItemName(int itemNumber, String newName){
        itemList.get(itemNumber).editName(newName);
    }

    public void editSerialNumber(int itemNumber, String newSerialNumber){
        for (int i = 0; i < itemList.size(); i++){
            if (newSerialNumber.equalsIgnoreCase(itemList.get(i).serialNumber)){
                return;
            }
        }
        if (checkSerial(newSerialNumber)){
            itemList.get(itemNumber).editSerialNumber(newSerialNumber);
        }
    }

    public void editItemValue(int itemNumber, String newValue){
        if (checkValue(newValue)){
            itemList.get(itemNumber).editValue(newValue);
        }
    }

    public void exportListJSON(String directory, String fileName) throws IOException {
        String output = new String();

        for (int i = 0; i < itemList.size(); i++){
            output = gson.toJson(itemList);
        }

        fManager.exportFile(directory + "\\" + fileName + ".json", output);
    }

    public void exportListTSV(String directory, String fileName) throws IOException {
        String output = new String();

        //Print items to output String in tabular format

        fManager.exportFile(directory + "\\" + fileName + ".txt", output);
    }

    public void exportListHTML(String directory, String fileName) throws IOException {
        String output = new String();

        //Print items to output String in HTML format

        fManager.exportFile(directory + "\\" + fileName + ".html", output);
    }

    public void importList (String directory, String fileName, int fileType) throws IOException {
        //Filetypes 0 - JSON, 1 - HTML, 2 - TCV
        String output = new String();

        output = fManager.importFile(directory + "\\" + fileName);

        switch (fileType) {
            case 0 :
                Item item = new Item();
                JsonArray array = JsonParser.parseString(output).getAsJsonArray();
                for (int i = 0; i < array.size(); i++){
                    itemList.set(i, gson.fromJson(array.get(i), Item.class));
                }
                break;
            case 1 :
                //Import HTML File
                break;
            case 2  :
                //Import TCV File
                break;
        }
    }
}
