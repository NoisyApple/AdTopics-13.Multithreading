package com.milkyblue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.tomaslanger.chalk.Chalk;

public class CountDownCounter {
  private JFrame mainFrame;
  private JPanel mainPanel;
  private JLabel lblCDown;
  private JButton btnCDown;

  public CountDownCounter() {
    Chalk.setColorEnabled(true);

    mainFrame = new JFrame("Countdown counter");
    mainPanel = new JPanel();
    lblCDown = new JLabel("Add a new countdown");
    btnCDown = new JButton("Add");

    addAttributes();
    addListeners();
    build();
    launch();
  }

  private void addAttributes() {
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void addListeners() {
    btnCDown.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new CountDown();
      }
    });
  }

  private void build() {
    mainPanel.add(lblCDown);
    mainPanel.add(btnCDown);

    mainFrame.add(mainPanel);
  }

  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }
}

class CountDown extends Thread {
  private int countDown;
  private static int idCount = 0;
  private int id;

  public CountDown() {
    countDown = (int) Math.floor(Math.random() * 10) + 10;
    id = ++idCount;
    coloredPrint(id, "CREATED");
    start();
  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(500);
      } catch (Exception e) {

      }
      coloredPrint(id, Integer.toString(countDown));
      if (--countDown <= 0) {
        coloredPrint(id, "DISPOSED");
        break;
      }
    }
  }

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