package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 Author: Dan Hederman

 Start Date: 20/3/2017

 Interpreter: Intellij

 For my second year semester 2 Java Assignment in object orientated programmingI choose to
 code an abusive content detecting program. The idea here is to have a text file full of
 abusive words. The user will input the name of a file that will be checked against the
 file full of abusive words. The user will be able to enter new abusive words into the
 text file full of abusive words.

 intellij has a built in gui form creator which I will use as well as


 */
public class GUI {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    public JPanel panelMain;
    FileWriter fWriter = null;
    BufferedWriter writer = null;
    Scanner scan = null;
    Scanner scan2 = null;
    String str = null;
    String str2 = null;
    int count =0;
    String nextToken = null;
    Scanner scancheck = null;
    String strcheck = null;
    Scanner scan3 = null;

    public GUI() {

        //Action listener for the button that reads new abusive words into the file

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Used to read in the new abusive word from the textfield into the abusive words file

                JTextField textField = textField1; //
                String text = textField.getText();

                //Try catch to append the new abusive word to the end of the abusive words file

                BufferedReader Confirm  = null;

                //Try catch to read the abuse text file into the variable abuse

                File filecheck = new File("C:\\Users\\danhe\\OneDrive - Dublin Institute of Technology\\College\\2nd_Year\\Semester-2\\Assignment1\\abuse.txt");

                try {
                    scancheck = new Scanner(filecheck);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                ArrayList<String> checkdict = new ArrayList<>();

                while(scancheck.hasNextLine())
                {
                    str = scancheck.nextLine();
                    checkdict.add(str);
                }

                for (String str : checkdict) {
                    if (text.contains(str)) {
                        count++;
                    }
                }

                System.out.println(count);
                //Try catch to read the abuse text file into the variable abuse

                if(count == 0) {

                    try {
                        fWriter = new FileWriter("abuse.txt", true);
                        writer = new BufferedWriter(fWriter);
                        writer.write(String.valueOf(text));
                        writer.newLine();
                        writer.close();
                        System.err.println("Your input of " + text.length() + " characters was saved.");
                    } catch (Exception c) {
                        System.out.println("Error!");
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Word already added");
                    count=0;
                }
            }
        });

        //Action listener for the second button to check the file for abusive language

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int shout = 0;

                //Reads in the users file of choice to be checked for abusive words

                JTextField textField = textField2;
                String text2 = textField.getText();


                File file = new File("C:\\Users\\danhe\\OneDrive - Dublin Institute of Technology\\College\\2nd_Year\\Semester-2\\Assignment1\\abuse.txt");

                //By using the +text2 I allow the user to input the file name only without the extension

                File file2 = new File("C:\\Users\\danhe\\OneDrive - Dublin Institute of Technology\\College\\2nd_Year\\Semester-2\\Assignment1\\" + text2 + ".txt");

                //First scanner to read the abusive text file

                try {
                    scan = new Scanner(file);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                //File scanner to read the text file to be checked for abusive words

                try {
                    scan2 = new Scanner(file2);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                //This array will have all the words to check against the text file

                ArrayList<String> abusivedictionary = new ArrayList<>();

                //The hashmap will have the number of times each abusive word is in the file to be checked

                HashMap<String,Integer> hits = new HashMap<String, Integer>();

                //While loop to read the the abusive text file into the array abusivedictionary

                while(scan.hasNextLine())
                {
                    str = scan.nextLine();
                    abusivedictionary.add(str);
                    hits.put(str, 0);
                }

                //While loop to scan the file to be checked for abusive words

                while (scan2.hasNextLine())
                {
                    str2 = scan2.nextLine();

                    //For loop to split the line of text into individual words

                    for (String s2: str2.split(" ")) {

                        //Nested if loop inside a for loop to count the number of each abusive word in the file

                        for (String str : abusivedictionary) {

                            str = str.toUpperCase();
                            System.out.println(str);
                            if(str.equals(s2))
                            {
                                shout++;
                            }

                            str = str.toLowerCase();
                            s2 = s2.toLowerCase();
                            if (s2.contains(str)) {
                                hits.put(str, hits.get(str) + 1);
                            }
                        }
                    }
                }

                //For loop to print out a message box to the user the number of each abusive word in the text file

                for(String str: abusivedictionary)
                {
                    String output = (str + " appeared " + hits.get(str) + " times");
                    JOptionPane.showMessageDialog(null, output);
                }

                String caps = (shout + " Words shouted ");
                JOptionPane.showMessageDialog(null, caps);
            }
        });

        //Action listener to display the contents of the abuse dictionary

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Set the buffered reader abuse to null

                BufferedReader abuse  = null;

                //Try catch to read the abuse text file into the variable abuse

                try {
                    abuse = new BufferedReader(new FileReader("abuse.txt"));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                String line = null;

                //Try catch to read the abuse file line by line

                try {
                    line = abuse.readLine();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //While loop to print the contents of the abuse file to the textarea for the user to see

                while(line != null){
                    textArea1.append(line + "\n");
                    try {
                        line = abuse.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
