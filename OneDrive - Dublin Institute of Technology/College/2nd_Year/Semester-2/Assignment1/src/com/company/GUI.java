package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
                FileManager file = new FileManager("abuse.txt");
                file.connectToFile();
                System.out.println("\n");
                for (int i = 0; i < 1; i++) {
                    System.out.print(file.readFile()[i]);
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
