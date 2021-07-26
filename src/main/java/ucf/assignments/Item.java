/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Orion Kinsler
 */

package ucf.assignments;

public class Item {
    String name;
    String serialNumber;
    String value;

    public void editName(String newName){
        name = newName;
    }

    public void editSerialNumber(String newSerialNumber){
        serialNumber = newSerialNumber;
    }

    public void editValue(String newValue){
        value = newValue;
    }

    public String getName(){
        return name;
    }

    public String getSerialNumber(){
        return serialNumber;
    }

    public String getValue(){
        return value;
    }
}
