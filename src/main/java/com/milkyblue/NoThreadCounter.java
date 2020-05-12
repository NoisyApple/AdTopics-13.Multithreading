package com.milkyblue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Class NoThreadCounter.
public class NoThreadCounter {
  private int count;
  private JFrame mainFrame;
  private JPanel mainPanel;
  private JButton btnStart, btnStop;
  private JTextField txtCount;
  private boolean isRunning;

  // Class constructor.
  public NoThreadCounter() {
    count = 0;
    mainFrame = new JFrame("No Thread Counter");
    mainPanel = new JPanel();
    btnStart = new JButton("Start");
    btnStop = new JButton("Stop");
    txtCount = new JTextField(10);
    isRunning = true;

    // Main methods are called.
    addAttributes();
    addListeners();
    build();
    launch();
  }

  // Adds attributes to elements in the class.
  private void addAttributes() {
    txtCount.setText(Integer.toString(count));
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // Adds functionality to events.
  private void addListeners() {
    // Simply calls the run method when pressed.
    btnStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        run();
      }
    });

    // Changes the state of isRunning, therefore the run method stops.
    btnStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        isRunning = false;
      }
    });
  }

  // Builds the program's GUI.
  private void build() {
    mainPanel.add(txtCount);
    mainPanel.add(btnStart);
    mainPanel.add(btnStop);
    mainFrame.add(mainPanel);
  }

  // Launches the program by setting the frame's visible value to true, then
  // resizes and centers the window.
  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

  // Runs the counter and sets isRunning to true, if it is changed again to
  // false, the while loop stops and so the counter does too.
  public void run() {
    btnStart.setEnabled(false);
    btnStop.setEnabled(true);
    isRunning = true;
    while (true) {
      try {
        Thread.sleep(100);
      } catch (Exception e) {
        System.out.println("Interrupted");
      }
      if (isRunning) {
        txtCount.setText(Integer.toString(count++));
      } else {
        break;
      }
    }
    btnStart.setEnabled(true);
  }

}