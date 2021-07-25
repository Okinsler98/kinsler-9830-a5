/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Orion Kinsler
 */

package ucf.assignments;

import java.io.*;
import java.util.Scanner;

public class FileManager {

    public void exportFile(String fileName, String fileText) throws IOException {
        File fileInput = new File(fileName);
        FileWriter fileWriter = new FileWriter(fileInput);

        if (fileInput.exists()){
            fileInput.delete();
        }

        fileInput.createNewFile();
        fileWriter.write(fileText);
        fileWriter.close();
    }

    public String importFile(String fileName) throws FileNotFoundException {
        File fileInput = new File(fileName);

        if (!fileInput.exists()){
            return "";
        }
        Scanner fileReader = new Scanner(fileInput);
        String output  = new String();

        while (fileReader.hasNextLine()) {
            output += fileReader.nextLine();
        }

        fileReader.close();
        return output;
    }
}
