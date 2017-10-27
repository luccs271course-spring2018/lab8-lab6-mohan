package edu.luc.cs271.arrayqueue;

import java.util.Scanner;

public class SingleQueueService {

  /** Service time per customer in ms. */
  static final int SERVICE_TIME = 6000;


  public static void main(final String[] args)
      throws
          InterruptedException { // Done read successive input lines until EOF and try to add them
    // to the queue

    // queue for customer names
    final SimpleQueue<String> queue = new FixedArrayQueue<>(5);

    // lock object for thread safety
    final Object lock = new Object();

    // background activity for serving customers
    final Thread consumer =
        new Thread(
            () -> {
              while (true) {
                String current;
                int remaining;
                synchronized (lock) {
                  current = queue.poll(); // Done
                  remaining = queue.size();
                }
                if (current == null) {
                  System.out.println("You are up!");
                } else {
                  System.out.println(current + " is being served, " + remaining + " still waiting");
                }
                try {
                  Thread.sleep(SERVICE_TIME);
                } catch (final InterruptedException ex) {
                  return;
                }
              }
            });
    consumer.setDaemon(true);
    consumer.start();
    final Scanner input = new Scanner(System.in);
    System.out.print("enter next customer: ");
    while (input.hasNextLine()) {
      final String name = input.nextLine();
      boolean result;
      synchronized (lock) {
        result = queue.offer(name);
      }
      if (result) {
        System.out.println(name + " has joined the queue");
      } else {
        System.out.println("queue full, " + name + " unable to join");
      }
    }
  }
}
