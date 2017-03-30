//This is the package the entire assignment will be using.

package com.company;

//These are the import statements to make the java swing GUI work

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**

 Author: Dan Hederman

 Student Number: C15410232

 Java Editor: IntelliJ

 Assignment: Abusive context detector.

 */
public class GUI extends JFrame implements ActionListener{

    /**
     * @param args
     */
    JButton button1, button2;
    JTextField text1;
    JLabel label1;
    JCheckBox checkBox1;

    // constructor

    GUI(String title) {

        super(title);
        setSize(500, 300);
        setLayout(new FlowLayout());
        Color  blue   = new Color(0, 0, 255);

        button1 = new JButton("Roles");

        button1.addActionListener(this);
        add(button1);

        button1.setBackground(Color.blue);
        getContentPane().setBackground(Color.green);

        //Placeholder for the text field to allow users to add to the abusive words file

        text1 = new JTextField("Placeholder");
        add(text1);
        //PromptSupport.setPrompt("Bunnies", bunnies);

        //Makes the GUI visible to the user

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button1)
        {
            //JOptionPane.showMessageDialog(this, "MyFirst event!");
            FileManager file = new FileManager("roles.txt");
            file.connectToFile();
            System.out.println("\n");
            for(int i = 0; i <3;i++){
                System.out.print(file.readFile()[i]);
            }
        }


    }
}

