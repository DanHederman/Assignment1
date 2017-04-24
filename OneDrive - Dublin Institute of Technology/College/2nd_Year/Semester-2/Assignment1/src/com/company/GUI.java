package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
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

    //Param args for the GUI

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    public JPanel panelMain;

    //Scanner/filewriter

    private FileWriter fWriter = null;
    private BufferedWriter writer = null;
    private Scanner scan = null;
    private Scanner scan2 = null;
    private String str = null;
    private String str2 = null;
    int count =0;
    private Scanner scancheck = null;
    private String upstr2 =null;

    //Counters initalised to zero

    int shout = 0;
    int swear = 0;
    int totalwords = 0;
    int totalabuse = 0;
    float percentage = 0;

    //Constructor

    public GUI() {

        //Action listener for the button that reads new abusive words into the file

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Used to read in the new abusive word from the textfield into the abusive words file

                JTextField textField = textField1; //
                String text = textField.getText();

                File filecheck = new File("C:\\Users\\danhe\\OneDrive - Dublin Institute of Technology\\College\\2nd_Year\\Semester-2\\Assignment1\\abuse.txt");

                try {
                    scancheck = new Scanner(filecheck);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                //Initialisation of the arraylist checkdict

                ArrayList<String> checkdict = new ArrayList<>();

                while(scancheck.hasNextLine())
                {
                    str = scancheck.nextLine();
                    checkdict.add(str);
                }

                for (String str: checkdict) {
                    if (text.contains(str)) {
                        count++;
                    }
                }

                //Try catch to read the abuse text file into the variable abuse

                if(count == 0) {

                    try {

                        //Reads in the file of abusive words and appends the new
                        //word the user wishes to add to the end of the file

                        fWriter = new FileWriter("abuse.txt", true);
                        writer = new BufferedWriter(fWriter);
                        writer.write(String.valueOf(text));
                        writer.newLine();
                        writer.close();
                        String saved = ("Your input of " + text.length() + " characters was saved.");
                        JOptionPane.showMessageDialog(null, saved);
                    } catch (Exception c) {
                        System.out.println("Error!");
                    }

                }

                //This else statement tells the user when the word they are
                //trying to add is already in the file.

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

                shout = 0;
                swear = 0;
                totalwords = 0;
                totalabuse = 0;
                percentage = 0;

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


                //While loop to read the the abusive text file into the array abusivedictionary

                while(scan.hasNextLine())
                {
                    str = scan.nextLine();
                    abusivedictionary.add(str);
                }

                //While loop to scan the file to be checked for abusive words

                while (scan2.hasNextLine())
                {
                    str2 = scan2.nextLine();

                    //For loop to split the line of text into individual words

                    for (String s2: str2.split(" ")) {

                        //Increment total words counter

                        totalwords++;

                        //Nested if loop inside a for loop to count the number of each abusive word in the file

                        for (String str : abusivedictionary) {

                            //set variable upstr2 to all caps and compare
                            //to search for shouting

                            upstr2 = s2.toUpperCase();
                            if(upstr2.equals(s2))
                            {
                                shout++;
                            }

                            //Set str and 2s2 to lowercase to check for swear words

                            str = str.toLowerCase();
                            s2 = s2.toLowerCase();
                            if (s2.equals(str)) {
                                swear++;
                            }
                        }
                    }
                }

                //Message dialogue boxes to give the user feedback

                String output = (swear + " Swear words appeared");
                JOptionPane.showMessageDialog(null, output);


                String caps = (shout + " Words shouted ");
                JOptionPane.showMessageDialog(null, caps);

                totalabuse = swear + shout;

                String totwords = ("Total abusive words(including shouting): " + totalabuse);
                JOptionPane.showMessageDialog(null, totwords);

                percentage = 100/totalwords*totalabuse;

                String end = ("Total percentage of abuse: " + percentage);
                JOptionPane.showMessageDialog(null, end);
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