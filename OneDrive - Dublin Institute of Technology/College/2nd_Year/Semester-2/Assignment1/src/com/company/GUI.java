//This is the package the entire assignment will be using.

package com.company;
/**

 Author: Dan Hederman

 Student Number: C15410232

 Java Editor: IntelliJ

 Assignment: Abusive context detector.

 */

//These are the import statements to make the java swing GUI work

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class GUI extends JFrame implements ActionListener {

    /**
     * @param args
     */
    JButton button1, button2;
    JTextField text;
    FileWriter fWriter = null;
    BufferedWriter writer = null;

    // constructor

    GUI(String title) {

        super(title);
        setSize(500, 300);
        setLayout(new FlowLayout());
        Color blue = new Color(0, 0, 255);

        button1 = new JButton("Add abuse text file");

        button1.addActionListener(this);
        add(button1);

        //Placeholder for the text field to allow users to add to the abusive words file

        text = new JTextField("Placeholder");
        add(text);

        button2 = new JButton("Check for abusive language");
        add(button2);

        //Makes the GUI visible to the user

        setVisible(true);
    }

    //Action performed to control what happens when a button is pressed.

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {

            JTextField textField = text; //
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

        if (e.getSource() == button2)

        {
            JOptionPane.showMessageDialog(this, "Abuse.txt");
            FileManager file = new FileManager("abuse.txt");
            file.connectToFile();
            System.out.println("\n");
            for (int i = 0; i < 1; i++) {
                System.out.print(file.readFile()[i]);
            }
        }
    }
}
