package com.pluralsight.Person;

import javax.swing.*;
import java.awt.*;
//An event represents a state change in an object, often triggered by user actions or system occurrences
import java.awt.event.*;
import java.util.List;

public class PersonWorld extends JPanel implements KeyListener { //KeyListener uses an interface to detect you using the Keyboard keys
    private final Person person;

    // Constructor: sets up the world and keyboard input
    public PersonWorld(Person person) {
        this.person = person;
        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);
        addKeyListener(this);
    }

    // Called whenever the panel needs to be redrawn
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Point> path = person.getPath();

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE); // Trail color
        g2.setStroke(new BasicStroke(2)); // Line thickness

        // Draw the trail by connecting points
        for (int i = 1; i < path.size(); i++) {
            Point p1 = path.get(i - 1);
            Point p2 = path.get(i);
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Draw current position as red dot
        Point current = person.getCurrentPosition();
        g2.setColor(Color.RED);
        g2.fillOval(current.x - 4, current.y - 4, 8, 8);
    }

    // Respond to arrow key presses
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            //VK stand for Virtual Key
            case KeyEvent.VK_UP:
                person.walkForward(20);   // Move forward
                break;
            case KeyEvent.VK_DOWN:
                person.walkBackward(20);  // Move backward
                break;
            case KeyEvent.VK_LEFT:
                person.turnLeft(15);      // Turn left
                break;
            case KeyEvent.VK_RIGHT:
                person.turnRight(15);     // Turn right
                break;
        }

        repaint(); // Redraw with updated path
    }

    // Reset person to start
    public void resetPerson() {
        person.reset(200, 200);
        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {
        //This method is called when the user releases a key on the keyboard — that is, after pressing and then letting go of a key.
    }
    @Override public void keyTyped(KeyEvent e) {
        //This method is called when the user types a character (like a letter, number, or symbol).
        //In this case the Arrow Keys
    }
}
