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

// MultiThreadCounter class.
public class MultiThreadCounter {
  private JFrame mainFrame;
  private JPanel mainPanel, topPanel, centerPanel;
  private JLabel lblAdd;
  private JButton btnAdd;
  private Stack<Counter> counters;

  // Class constructor.
  public MultiThreadCounter(int initialCounters) {
    mainFrame = new JFrame("Multithread Counter");
    mainPanel = new JPanel(new BorderLayout());
    topPanel = new JPanel();
    centerPanel = new JPanel();
    lblAdd = new JLabel("Add a new counter: ");
    btnAdd = new JButton("ADD");
    counters = new Stack<Counter>();

    // Push as many Counter objects as specified in initialCounters to the counters
    // stack.
    for (int i = 0; i < initialCounters; i++)
      counters.push(new Counter());

    // Main methods are called.
    addAttributes();
    addListeners();
    build();
    launch();
  }

  // Adds attributes to elements in class.
  private void addAttributes() {
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
  }

  // Adds listeners to events in the GUI.
  private void addListeners() {
    // Adds a new Counter object to the GUI and pushes it to the array, then resizes
    // the window.
    btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Counter newCounter = new Counter();
        counters.push(newCounter);
        centerPanel.add(newCounter);
        launch();
      }
    });
  }

  // Builds the GUI.
  private void build() {
    topPanel.add(lblAdd);
    topPanel.add(btnAdd);

    // Adds every element in the stack to the centerPanel.
    for (Counter c : counters)
      centerPanel.add(c);

    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);

    mainFrame.add(mainPanel);
  }

  // Launches the window by setting its visible value to true. Then the window is
  // centered and resized.
  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

  // Counter inner Class, inherits JPanel to model a Counter GUI and runs on a
  // dedicated Thread.
  @SuppressWarnings("serial")
  class Counter extends JPanel implements Runnable {
    private int count;
    private JTextField txtCount;
    private JButton btnStart, btnStop;
    private boolean isRunning;
    private Thread selfThread;

    // Class constructor.
    public Counter() {
      count = 0;
      txtCount = new JTextField(10);
      btnStart = new JButton("Start");
      btnStop = new JButton("Stop");
      isRunning = false;
      selfThread = null;

      // Main methods are called.
      this.addAttributes();
      this.addListeners();
      this.build();
    }

    // Adds attributes to the elements in the class.
    private void addAttributes() {
      txtCount.setText(Integer.toString(count));
      btnStop.setEnabled(false);
    }

    // Adds listeners to the elements in the class.
    private void addListeners() {
      // Declares a Thread based on the instance on this class and starts it.
      // Therefore the Counter is started.
      btnStart.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          selfThread = new Thread(Counter.this);
          selfThread.start();
        }
      });

      // Stops the Counter by setting isRunning to false.
      btnStop.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          isRunning = false;
          btnStop.setEnabled(false);
        }
      });
    }

    // Builds the inner Class GUI.
    private void build() {
      add(txtCount);
      add(btnStart);
      add(btnStop);
    }

    // Triggers the main functionality of the Counter, when isRunning is set to
    // false, the while loop is stopped and therefore the Counter too.
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