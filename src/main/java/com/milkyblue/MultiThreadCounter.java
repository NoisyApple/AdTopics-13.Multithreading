package com.milkyblue;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MultiThreadCounter {
  private JFrame mainFrame;
  private JPanel mainPanel, topPanel, centerPanel;
  private JLabel lblAdd;
  private JButton btnAdd;
  private Stack<Counter> counters;

  public MultiThreadCounter(int initialCounters) {
    mainFrame = new JFrame("Multithread Counter");
    mainPanel = new JPanel(new BorderLayout());
    topPanel = new JPanel();
    centerPanel = new JPanel(new GridBagLayout());
    centerPanel = new JPanel();
    lblAdd = new JLabel("Add a new counter: ");
    btnAdd = new JButton("ADD");
    counters = new Stack<Counter>();

    for (int i = 0; i < initialCounters; i++)
      counters.push(new Counter());

    System.out.println(counters.size());

    addAttributes();
    addListeners();
    build();
    launch();
  }

  private void addAttributes() {
    // centerPanel.setPreferredSize(new Dimension(300, 0));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // mainFrame.setResizable(false);
  }

  private void addListeners() {
    btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Counter newCounter = new Counter();
        counters.push(newCounter);
        centerPanel.add(newCounter);
        launch();
      }
    });
  }

  private void build() {
    topPanel.add(lblAdd);
    topPanel.add(btnAdd);

    for (Counter c : counters) {
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 1;
      gbc.gridy = counters.indexOf(c) + 1;
      System.out.println(counters.indexOf(c) + 1);
      centerPanel.add(c, gbc);
    }

    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);

    mainFrame.add(mainPanel);
  }

  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

  class Counter extends JPanel implements Runnable {
    public int id = (int) Math.floor(Math.random() * 100);
    private int count;
    private JTextField txtCount;
    private JButton btnStart, btnStop;

    public Counter() {
      count = 0;
      txtCount = new JTextField(10);
      btnStart = new JButton("Start");
      btnStop = new JButton("Stop");

      this.addAttributes();
      this.addListeners();
      this.build();
    }

    private void addAttributes() {
      setBackground(Color.CYAN);
    }

    private void addListeners() {
      btnStart.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("Hello");
        }
      });
    }

    private void build() {
      add(txtCount);
      add(btnStart);
      add(btnStop);
    }

    public void run() {
    }

  }
}