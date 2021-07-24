/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Orion Kinsler
 */

package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryList {
    ArrayList<Item> itemList = new ArrayList();
    FileManager fManager = new FileManager();
    Gson gson = new Gson();

    public int createItem(String name, String serialNumber, Double value){
        for (int i = 0; i < itemList.size(); i++){
            if (serialNumber.equalsIgnoreCase(itemList.get(i).serialNumber)){
                return 1;
            }
        }
        return 0;
    }

    public void deleteItem(int itemNumber){
        itemList.remove(itemNumber);
    }

    public void editItemName(int itemNumber, String newName){
        itemList.get(itemNumber).editName(newName);
    }

    public int editSerialNumber(int itemNumber, String newSerialNumber){
        for (int i = 0; i < itemList.size(); i++){
            if (newSerialNumber.equalsIgnoreCase(itemList.get(i).serialNumber)){
                return 1;
            }
        }
        itemList.get(itemNumber).editSerialNumber(newSerialNumber);
        return 0;
    }

    public void editItemValue(int itemNumber, Double newValue){
        itemList.get(itemNumber).editValue(newValue);
    }

    public void exportListJSON() throws IOException {
        String output = new String();

        for (int i = 0; i < itemList.size(); i++){
            output = gson.toJson(itemList);
        }

        //Use File Manager to export to JSON file
    }

    public void exportListTSV() throws IOException {
        String output = new String();

        //Print items to output String in tabular format

        //Use File Manager to export to JSON file
    }

    public void exportListHTML() throws IOException {

    }
}
