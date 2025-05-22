package com.pluralsight.Person;

import javax.swing.*;
import java.awt.*;

public class MainPerson {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Person person = new Person(200, 200);             // Start in center
            PersonWorld panel = new PersonWorld(person);      // Drawing area

            JFrame frame = new JFrame("Person On Path Drawer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 550);                          // Extra height for button
            frame.setLayout(new BorderLayout());

            frame.add(panel, BorderLayout.CENTER);            // Add drawing area

            // Create and add Reset button
            JButton resetButton = new JButton("Reset Canvas");
            resetButton.addActionListener(e -> panel.resetPerson()); // Clear path
            frame.add(resetButton, BorderLayout.SOUTH);        // Add below canvas

            frame.setVisible(true);                            // Show window
            panel.requestFocusInWindow();                      // Enable key input
        }); //Closes the  SwingUtilities.invokeLater(()
    }
}
