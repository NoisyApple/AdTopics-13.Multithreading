package com.milkyblue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Class RunnableCounter.
public class RunnableCounter implements Runnable {
  private int count;
  private JFrame mainFrame;
  private JPanel mainPanel;
  private JButton btnStart, btnStop;
  private JTextField txtCount;
  private boolean isRunning;
  private Thread selfThread;

  // Class constructor.
  public RunnableCounter() {
    count = 0;
    mainFrame = new JFrame("Runnable Counter");
    mainPanel = new JPanel();
    btnStart = new JButton("Start");
    btnStop = new JButton("Stop");
    txtCount = new JTextField(10);
    isRunning = true;
    selfThread = null;

    // Main methods are called.
    addAttributes();
    addListeners();
    build();
    launch();
  }

  // Adds attributes to element in the class.
  private void addAttributes() {
    txtCount.setText(Integer.toString(count));
    btnStop.setEnabled(false);
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // Adds listeners to GUI events.
  private void addListeners() {
    // Defines the selfThread based on the actual instance of RunnableCounter, then
    // the Thread is started which also starts the counter.
    btnStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selfThread = new Thread(RunnableCounter.this);
        selfThread.start();
      }
    });

    // Stops the counter by setting isRunning to false.
    btnStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        isRunning = false;
        btnStop.setEnabled(false);
      }
    });
  }

  // Builds the GUI.
  private void build() {
    mainPanel.add(txtCount);
    mainPanel.add(btnStart);
    mainPanel.add(btnStop);
    mainFrame.add(mainPanel);
  }

  // Launches the window by setting its visible value to true, then it is resized
  // and centered.
  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

  // Triggers the main functionality of the counter. Its stopped when isRunning is
  // set to false.
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