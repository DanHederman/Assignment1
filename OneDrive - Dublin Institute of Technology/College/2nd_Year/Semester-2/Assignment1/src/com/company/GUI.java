package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by danhe on 12/04/2017.
 */
public class GUI {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    public JPanel panelMain;
    File file;
    FileWriter fWriter = null;
    BufferedWriter writer = null;
    Scanner scan = null;
    Scanner scan2 = null;
    String str = null;
    String str2 = null;


    public GUI() {

        //Action listener for the button that reads new abusive words into the file

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = textField1; //
                String text = textField.getText();


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
        });

        //Action listener for the second button to check the file for abusive language

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField textField = textField2;
                String text2 = textField.getText();


                File file = new File("C:\\Users\\danhe\\OneDrive - Dublin Institute of Technology\\College\\2nd_Year\\Semester-2\\Assignment1\\abuse.txt");
                File file2 = new File("C:\\Users\\danhe\\OneDrive - Dublin Institute of Technology\\College\\2nd_Year\\Semester-2\\Assignment1\\" + text2 + ".txt");

                try {
                    scan = new Scanner(file);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    scan2 = new Scanner(file2);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                //This array will have alll the words to check against the text file

                ArrayList<String> dictionary = new ArrayList<>();

                //The hashmap will have the number of times each abusive word is in the file to be checked

                HashMap<String,Integer> hits = new HashMap<String, Integer>();
                while(scan.hasNextLine())
                {
                    str = scan.nextLine();
                    dictionary.add(str);
                    hits.put(str, 0);
                }
                while (scan2.hasNextLine())
                {
                    str2 = scan2.nextLine();
                    System.out.println(str2);
                    for(String str: dictionary)
                    {
                        if(str2.contains(str))
                        {
                            hits.put(str, hits.get(str) + 1);
                        }
                    }
                }
                for(String str: dictionary)
                {
                    System.out.println(str + " appeared " + hits.get(str) + " times");
                }
            }

        });

        //Action listener to display the contents of the abuse dictionary

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader("abuse.txt"));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                String line = null;
                try {
                    line = in.readLine();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                while(line != null){
                    textArea1.append(line + "\n");
                    try {
                        line = in.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
    }

}
