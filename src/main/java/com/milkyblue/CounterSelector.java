package com.milkyblue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

// CounterSelector class.
public class CounterSelector {
  private JFrame mainFrame;
  private JPanel mainPanel, topPanel, centerPanel, bottomPanel;
  private JLabel lblSelect;
  private String[] options;
  private JRadioButton[] rButtons;
  private ButtonGroup group;
  private JButton btnSelect;

  // Class constructor.
  public CounterSelector() {
    mainFrame = new JFrame("Counter Selector");
    mainPanel = new JPanel(new BorderLayout());
    topPanel = new JPanel();
    centerPanel = new JPanel();
    bottomPanel = new JPanel();
    lblSelect = new JLabel("Select a counter:");
    options = new String[] { "No thread counter", "Inner thread counter", "Runnable counter", "Multi thread counter",
        "Countdown counter" };
    rButtons = new JRadioButton[options.length];

    // Initializes each element in radio button array based on option array.
    for (int i = 0; i < rButtons.length; i++) {
      rButtons[i] = new JRadioButton(options[i]);
      rButtons[i].setActionCommand(Integer.toString(i));
    }

    group = new ButtonGroup();
    btnSelect = new JButton("Select");

    // Main methods are called.
    addAttributes();
    addListeners();
    build();
    launch();
  }

  // Adds attributes to elements in the class.
  private void addAttributes() {
    topPanel.setPreferredSize(new Dimension(200, 25));

    // Adds radio buttons to button group.
    for (JRadioButton rBtn : rButtons)
      group.add(rBtn);

    rButtons[0].setSelected(true);
    centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    mainFrame.setResizable(false);
  }

  // Adds listeners to GUI events.
  private void addListeners() {
    // Creates a new instance based on the selected radio button label, then
    // disposes this window.
    btnSelect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        switch (Integer.parseInt(group.getSelection().getActionCommand())) {
          case 0:
            new NoThreadCounter();
            break;
          case 1:
            new InnerThreadCounter();
            break;
          case 2:
            new RunnableCounter();
            break;
          case 3:
            new MultiThreadCounter(1);
            break;
          case 4:
            new CountDownCounter();
            break;
        }
        mainFrame.dispose();
      }
    });
  }

  // Builds the GUI.
  private void build() {
    topPanel.add(lblSelect);

    // Adds the radio buttons to the centerPanel.
    for (JRadioButton rBtn : rButtons)
      centerPanel.add(rBtn);

    bottomPanel.add(btnSelect);
    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    mainFrame.add(mainPanel);
  }

  // Launches the window by setting its visible value to true. Then its resized
  // and centered.
  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }
}