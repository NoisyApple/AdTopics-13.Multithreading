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

    addAttributes();
    addListeners();
    build();
    launch();
  }

  private void addAttributes() {
    txtCount.setText(Integer.toString(count));
    btnStop.setEnabled(false);

    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void addListeners() {
    btnStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selfThread = new Thread(RunnableCounter.this);
        selfThread.start();
      }
    });

    btnStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        isRunning = false;
        btnStop.setEnabled(false);
      }
    });
  }

  private void build() {
    mainPanel.add(txtCount);
    mainPanel.add(btnStart);
    mainPanel.add(btnStop);

    mainFrame.add(mainPanel);
  }

  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

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