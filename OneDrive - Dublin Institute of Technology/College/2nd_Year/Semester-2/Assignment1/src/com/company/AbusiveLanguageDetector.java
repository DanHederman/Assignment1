package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Created by danhe on 04/04/2017.
 */
public class AbusiveLanguageDetector extends JFrame implements ActionListener {
    private JButton addToAbusiveDictionaryButton;
    private JTextField textField2;
    private JTextField textField3;
    private JButton checkFileButton;
    public JPanel panelMain;
    FileWriter fWriter = null;
    BufferedWriter writer = null;

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }


        //Action performed to control what happens when a button is pressed.

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addToAbusiveDictionaryButton) {

            JTextField textField = textField2; //
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

        if (e.getSource() == checkFileButton)

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

