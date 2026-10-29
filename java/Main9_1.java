
class Counter {
    int count = 0;

    // Without synchronization
    void increment() {
        count++;
    }

    // With synchronization
    synchronized void incrementSync() {
        count++;
    }
}

class MyThread extends Thread {
    Counter c;
    boolean sync;

    MyThread(Counter c, boolean sync) {
        this.c = c;
        this.sync = sync;
    }

    public void run() {
        for (int i = 0; i < 100000; i++) {
            if (sync) {
                c.incrementSync();
            } else {
                c.increment();
            }
        }
    }
}

public class Main9_1 {
    public static void main(String[] args) throws Exception {

        Counter c = new Counter();

        // Without synchronization
        MyThread t1 = new MyThread(c, false);
        MyThread t2 = new MyThread(c, false);
        MyThread t3 = new MyThread(c, false);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Without Synchronization: " + c.count);

        // With synchronization
        c.count = 0;

        MyThread t4 = new MyThread(c, true);
        MyThread t5 = new MyThread(c, true);
        MyThread t6 = new MyThread(c, true);

        t4.start();
        t5.start();
        t6.start();

        t4.join();
        t5.join();
        t6.join();

        System.out.println("With Synchronization: " + c.count);
    }
}
