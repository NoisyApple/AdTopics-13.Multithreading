package com.milkyblue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.tomaslanger.chalk.Chalk;

// CountDownCounter class.
public class CountDownCounter {
  private JFrame mainFrame;
  private JPanel mainPanel;
  private JLabel lblCDown;
  private JButton btnCDown;

  // Class constructor.
  public CountDownCounter() {
    Chalk.setColorEnabled(true);

    mainFrame = new JFrame("Countdown counter");
    mainPanel = new JPanel();
    lblCDown = new JLabel("Add a new countdown");
    btnCDown = new JButton("Add");

    // Main methods are called.
    addAttributes();
    addListeners();
    build();
    launch();
  }

  // Adds attributes to elements in the class.
  private void addAttributes() {
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // Adds listeners to events from the GUI.
  private void addListeners() {
    // Creates a new instance of CountDown class when pressed.
    btnCDown.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new CountDown();
      }
    });
  }

  // Builds the GUI.
  private void build() {
    mainPanel.add(lblCDown);
    mainPanel.add(btnCDown);
    mainFrame.add(mainPanel);
  }

  // Launches the window by setting it visible value to true. Then the window is
  // resized and centered.
  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }
}

// CountDown Class.
class CountDown extends Thread {
  private int countDown;
  private static int idCount = 0;
  private int id;

  // Class constructor, assign an unique id to the instance and starts the Thread.
  public CountDown() {
    countDown = (int) Math.floor(Math.random() * 10) + 10;
    id = ++idCount;
    // Countdown starts.
    coloredPrint(id, "CREATED");
    start();
  }

  // Triggers the main functionality of the Countdown,
  public void run() {
    while (true) {
      try {
        Thread.sleep(500);
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Prints the countdown.
      coloredPrint(id, Integer.toString(countDown));
      if (--countDown <= 0) {
        // The countdown is completed.
        coloredPrint(id, "DISPOSED");
        break;
      }
    }
  }

  // Utility method. Prints a custom format message on console with a color
  // determined by the id passed.
  private void coloredPrint(int id, String message) {
    Chalk coloredMsg = null;
    switch (id % 3) {
      case 0:
        coloredMsg = Chalk.on("Thread-" + id).cyan();
        break;
      case 1:
        coloredMsg = Chalk.on("Thread-" + id).yellow();
        break;
      case 2:
        coloredMsg = Chalk.on("Thread-" + id).magenta();
        break;
    }
    System.out.println("[" + coloredMsg + "] " + message);
  }
}