package com.company;

import javax.swing.*;

/**
 * Created by danhe on 01/03/2017.
 */
public class Main {

    public static void main(String[] args) {

        JFrame abuse = new JFrame("Abusive Text Detector");
        abuse.setContentPane(new AbusiveLanguageDetector().panelMain);
        abuse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        abuse.pack();
        abuse.setVisible(true);

    }

}
