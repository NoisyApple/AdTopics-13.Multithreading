package com.milkyblue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Class InnerThreadCounter.
public class InnerThreadCounter {
  private int count;
  private JFrame mainFrame;
  private JPanel mainPanel;
  private JButton btnStart, btnStop;
  private JTextField txtCount;
  private boolean isRunning;

  // Class constructor.
  public InnerThreadCounter() {
    count = 0;
    mainFrame = new JFrame("Inner Thread Counter");
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

  // Attributes are added to the elements in the class.
  private void addAttributes() {
    txtCount.setText(Integer.toString(count));
    btnStop.setEnabled(false);
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // Adds listeners to GUI events.
  private void addListeners() {
    // Creates a new instance of CountThread, which starts the counter.
    btnStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new CountThread();
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

  // Launches the window by setting its visible value to true, the it is centered
  // and resized.
  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

  // CountThread class.
  class CountThread extends Thread {

    // Class constructor. Just starst the thread, which calls the run method.
    public CountThread() {
      start();
    }

    // Triggers the main functionality of the counter. Is stopped when isRunning is
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
}