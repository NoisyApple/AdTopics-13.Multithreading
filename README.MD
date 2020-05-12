# [Advanced Programming Topics] #13 MultiThreading

This is the **13th** project part of my Advanced Programming Topics course at Leon Institute of Thechnology :mortar_board::lion:.

## :dart:GOAL

Show the functionality of **Threads** in Java through various examples. Each of these examples are shown in a java program, which opens a new instance of the selected example. The examples are based on **Counters**.

<p align="center">
<img src="./snapshots/Annotation 2020-05-12 030057.png"/>
</p>

## :eyeglasses:BRIEF EXPLANATION

The following examples explains some of the functionalities of Threads in java:

### No Thread Counter :no_entry_sign:🧵

This Counter example shows what happens when a **multithread** implementation is needed. The program shows a GUI with a textfield to show the count and two buttons, each of them to stop and start the counter:

<p align="center">
<img src="./snapshots/Annotation 2020-05-12 033557.png"/>
</p>

So, the problem comes when the start button is pressed and its listener called, it should start the counter and therefore update the textfield, but the textfield update event is never called because the counter method is running, like if it was running in the background. So, it can be said that the counter method is using the main **Thread**.

<p align="center">
<img src="./snapshots/Annotation 2020-05-12 030305.png"/>
</p>

While the main **Thread** is being used by the counter method all the GUI is **frozen** and no event can be triggered again, even trying to close the window would not work.

So, to solve this problem we need to use a dedicated **Thread** for the counter functionality as shown in the next examples.

### Inner Thread Counter :inbox_tray:🧵

This example solves the previous problem by using an **inner class** dedicated for the Counter functionality which extends the **Thread class**:

```java
public class InnerThreadCounter{
  /*
  ** Main class code (includes GUI functionality).
  */
  class CountThread extends Thread{
    public CountThread(){
      start();
    }
    public void run(){
      /*
      ** Counter functionality.
      */
    }
  }
}
```

This way, when the start button is pressed a new instance of the **CountThread** class is created, which calls the **run** method with the counter functionality into a dedicated **Thread**, and the GUI will not freeze.

<p align="center">
<img src="./snapshots/Annotation 2020-05-12 030747.png"/>
<img src="./snapshots/Annotation 2020-05-12 030347.png"/>
</p>

**:memo:Note:** When Thread is extended into a class, **run** method implementation is required since this will be called when the thread is start (by the call of Thread class **start** method).

### Runnable Counter :running:🧵

In the same way as the previous example, this one runs the counter functionality in a different dedicated **Thread**, but in this case no inner class is required, instead **Runnable** interface is implemented into the class:

```java
public class RunnableCounter implements Runnable {
  private Thread selfThread = null;
  /*
  ** More code (including GUI functionality).
  */
  private void addListeners() {
    btnStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selfThread = new Thread(RunnableCounter.this);
        selfThread.start();
      }
    });
  }

  public void run(){
    /*
    ** Counter functionality.
    */
  }
}
```

When the **Runnable** interface is implemented into a class its required to also implement a **run** method which as in the previous example will be called when the dedicated **Thread** is called, but in this case we also need to declare that **Thread** in our class.
So, every time **btnStart** is pressed, **selfThread** is defined with a new instance of **Thread** class using our actual **RunnableCounter** instance, and then call the **start** method. This way the Counter will work as expected:

<p align="center">
<img src="./snapshots/Annotation 2020-05-12 030810.png"/>
<img src="./snapshots/Annotation 2020-05-12 030420.png"/>
</p>

**:memo:Note:** Actually, the **Thread** class implements **Runnable** interface, that is the reason why we need to implement **run** method in the previous example.

### Multi Thread Counter 🧵🧵🧵

So far we know how to implement **Threads**, but we are using it just for solve the **frozen GUI** problem, so lets enhance our Counter program adding the ability to create as many Counter **Threads** as we want. To do this, we need use an **inner class** with the **Runnable** interface implemented:

```java
public class MultiThreadCounter {
  private Stack<Counter> counters = new Stack<Counter>();
  /*
  ** Main frame code.
  */
  private void addListeners() {
    btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Counter newCounter = new Counter();
        counters.push(newCounter);
        centerPanel.add(newCounter); // Adds newCounter to the panel.
        launch(); // Resizes the frame.
      }
    });
  }

  class Counter extends JPanel implements Runnable {
    private Thread selfThread = null;
    /*
    ** More code (including inner class GUI functionality).
    */
    private void addListeners() {
      btnStart.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          selfThread = new Thread(RunnableCounter.this);
          selfThread.start();
        }
      });
    }

    public void run(){
      /*
      ** Counter functionality.
      */
    }
  }

}
```

This way, when we press **btnAdd** a new instance of **Counter** class is added to both **counters** stack and to **centerPanel**, that instance will work just like the previous examples, with the difference that now it can be replicated.

<p align="center">
<img src="./snapshots/Annotation 2020-05-12 030451.png"/>
<img src="./snapshots/Annotation 2020-05-12 030511.png"/>
</p>

**:memo:Note:** Since we are extending from **JPanel** to create our Counter class we can no longer extend from **Thread** due to the **java's multi inheritance restriction**, so we have to use **Runnable** interface instead.

### Countdown Counter :timer_clock::hourglass:

This last example covers the use of a multithread implementation just like the previous, but now we will extend just from **Thread** (in the last example we needed to extend from **JPanel** and therefore we were not able to also extend from **Thread** and use **Runnable** interface instead).
Now we will create a **countdown counter** generator, so each time we add a new **countdown counter**, its **countdown** will be shown in console until it reaches 0 and therefore be **disposed**.

```java
public class CountDownCounter {
  /*
  ** GUI code.
  */
  private void addListeners() {
    btnCDown.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new CountDown();
      }
    });
  }

  class CountDown extends Thread {
    /*
    ** CountDown code.
    */

    public CountDown() {
      start();
    }

    public void run(){
      /*
      ** Counter functionality.
      */
    }
  }
}
```

When **btnCDown** is pressed, a new instance of **CountDown** is created, whose constructor calls **start** method and therefore **run** is also called, this way the **run** method will be executed on a dedicated Thread.

<p align="center">
<img src="./snapshots/Annotation 2020-05-12 030557.png"/>
</p>

So, as we can see in the following screenshot, the terminal shows how 3 countdowns were added, identifying each of them with an **unique id** and a different color.

<p align="center">
<img src="./snapshots/Annotation 2020-05-12 030702.png"/>
</p>

When each **countdown** is created it starts to **decrease** its counter until it reaches 0, when that happends it gets **disposed** from the program.

<p align="center">
<img src="./snapshots/Annotation 2020-05-12 030716.png"/>
</p>
