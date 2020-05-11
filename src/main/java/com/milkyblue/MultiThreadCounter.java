package com.milkyblue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.BoxLayout;
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
    centerPanel = new JPanel();
    lblAdd = new JLabel("Add a new counter: ");
    btnAdd = new JButton("ADD");
    counters = new Stack<Counter>();

    for (int i = 0; i < initialCounters; i++)
      counters.push(new Counter());

    addAttributes();
    addListeners();
    build();
    launch();
  }

  private void addAttributes() {
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
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

    for (Counter c : counters)
      centerPanel.add(c);

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
    private int count;
    private JTextField txtCount;
    private JButton btnStart, btnStop;
    private boolean isRunning;
    private Thread selfThread;

    public Counter() {
      count = 0;
      txtCount = new JTextField(10);
      btnStart = new JButton("Start");
      btnStop = new JButton("Stop");
      isRunning = false;
      selfThread = null;

      this.addAttributes();
      this.addListeners();
      this.build();
    }

    private void addAttributes() {
      txtCount.setText(Integer.toString(count));
      btnStop.setEnabled(false);
    }

    private void addListeners() {
      btnStart.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          selfThread = new Thread(Counter.this);
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
      add(txtCount);
      add(btnStart);
      add(btnStop);
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
}