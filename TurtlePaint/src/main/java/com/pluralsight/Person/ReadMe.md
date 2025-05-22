# 🎨 About Drawing Project 🖌️
This project demonstrates how Java can be used to create a graphical user interface (GUI), allowing users to interact with the screen to draw shapes or, more challenging, render words.

# Roadmap
1. Looked through the documentation and decided what I wanted to do.
2. Go on places like Oracle, GeeksforGeeks, StackOverflow, and Google to find how Java can interact with the user.
3. Created the three classes needed for this project named MainPerson, Person, and PersonWorld.
4. This Java class Person simulates a person moving on a 2D plane, tracking their path using x/y coordinates and direction (angle). 
It allows turning left or right, walking forward or backward, and resets the position and path. It was built using basic trigonometry with Math.cos/Math.sin and Java's Point and ArrayList classes to store movement history.
5. The PersonWorld class creates a visual environment where a Person moves on a 2D panel in response to keyboard arrow keys.
It uses Java Swing to draw the person’s trail (as blue lines) and current position (as a red dot), updating the display each time a key is pressed. 
The class listens for key events to control movement and rotation, and it repaints the panel to reflect changes in real time.
6. Finally, my MainPerson class sets up the main window for the application using Java Swing, placing a drawing panel (PersonWorld) in the center and a "Reset Canvas" button at the bottom. 
It initializes the Person at position (200, 200) and links keyboard input to control the movement. 
Using SwingUtilities.invokeLater, it ensures that all UI setup runs on the Event Dispatch Thread for safe and responsive rendering.

# Screenshots
![Screenshot 2025-05-22 153304.png](../../../../../../Screenshots/Screenshot%202025-05-22%20153304.png)

As you can see when you press build you will be taken into the GUI for the project as you can see you see a red dot representing you in a gray void and a button that says Reset Canvas.

From the recording that I have here you can see how my project works:
[Recording 2025-05-22 153724.mp4](../../../../../../Screenshots/Recording%202025-05-22%20153724.mp4)

# Interesting Code
![carbon (1).png](../../../../../../Screenshots/carbon%20%281%29.png)

# Refences I used to make this project 
https://github.com/Year-Up-United-SPR-2025/Workshops/blob/main/TurtlePaint/src/main/java/com/pluralsight/References/References.txt

