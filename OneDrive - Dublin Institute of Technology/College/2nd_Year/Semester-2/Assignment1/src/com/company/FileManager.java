package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {
    // attributes

    String fileName;
    File fileExample;
    Scanner myScanner;
    PrintWriter pwInput;

    // Constructor
    FileManager(String fileName) {

        this.fileName = fileName;

    }

    // Method to get a connection to the file, before reading or writing
    void connectToFile() {
        fileExample = new File(this.fileName);
    }


    // Method to read a  file, returning a string of lines
    String[] readFile() {

        String[] values = new String[6];
        try {
            // initialise your array
            int i = 0;

            //get a scanner object
            myScanner = new Scanner(fileExample);

            //use the scanner to scroll through the lines of the file
            while (myScanner.hasNextLine()) {

                values[i] = myScanner.nextLine();
                i++;
            }
            return values;

        } catch (FileNotFoundException e) {
            System.out.println("run time error " + e.getMessage());
        }

        return values;

    }


    // File writing: method to get hold of a Print writer object
    void getFileWriter() {
        try {
            pwInput = new PrintWriter(fileExample);
        } catch (FileNotFoundException e) {
            System.out.println("run time error " + e.getMessage());
        }

    }

    // File writing: method to write a string to the file
    void writeLineToFile(String line) {
        System.out.println(line);
        pwInput.println(line);
    }


    // Method to close a file that was read
    void closeReadFile() {
        myScanner.close();
    }

    // Method to close a file that was written to..
    void closeWriteFile() {
        pwInput.close();
    }
}

