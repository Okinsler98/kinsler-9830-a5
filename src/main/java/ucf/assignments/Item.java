/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Orion Kinsler
 */

package ucf.assignments;

public class Item {
    String name;
    String serialNumber;
    Double value;

    public void editName(String newName){
        name = newName;
    }

    public void editSerialNumber(String newSerialNumber){
        serialNumber = newSerialNumber;
    }

    public void editValue(Double newValue){
        value = newValue;
    }
}
