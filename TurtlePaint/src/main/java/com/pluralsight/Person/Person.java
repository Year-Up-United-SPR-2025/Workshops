package com.pluralsight.Person;

import java.awt.*;
import java.util.ArrayList;

public class Person {
    private int x, y;               // Current position
    private double angle;          // Current direction in degrees
    private final ArrayList<Point> path; // List of all steps taken (the trail)

    // Constructor: initialize position and path
    public Person(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.angle = 0; // Facing right (0 degrees)
        this.path = new ArrayList<>();
        path.add(new Point(x, y)); // Save the starting point
    }

    // Turn right by some degrees
    public void turnRight(double degrees) {
        angle += degrees;
    }

    // Turn left by some degrees
    public void turnLeft(double degrees) {
        angle -= degrees;
    }

    // Move forward in current direction
    public void walkForward(int distance) {
        x += (int)(distance * Math.cos(Math.toRadians(angle)));
        y += (int)(distance * Math.sin(Math.toRadians(angle)));
        path.add(new Point(x, y));
    }

    // Move backward (opposite direction)
    public void walkBackward(int distance) {
        x -= (int)(distance * Math.cos(Math.toRadians(angle)));
        y -= (int)(distance * Math.sin(Math.toRadians(angle)));
        path.add(new Point(x, y));
    }

    // Clear the path and reset to starting position
    public void reset(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.angle = 0;
        path.clear();
        path.add(new Point(x, y));
    }

    // Return the trail
    public ArrayList<Point> getPath() {
        return path;
    }

    // Return current location
    public Point getCurrentPosition() {
        return new Point(x, y);
    }
}
