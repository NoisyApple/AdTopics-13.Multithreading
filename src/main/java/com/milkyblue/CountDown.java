package com.milkyblue;

public class CountDown extends Thread {
  private int countDown;
  private static int idCount = 0;
  private int id;

  public CountDown() {
    countDown = (int) Math.floor(Math.random() * 10) + 10;
    id = ++idCount;

    System.out.println("T-" + id);
    start();
  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(500);
      } catch (Exception e) {

      }
      System.out.println("T-" + id + " => " + countDown);
      if (--countDown <= 0)
        break;
    }
  }

}
