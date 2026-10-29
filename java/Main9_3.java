
class Total {
    int sum = 0;

    // Without synchronization
    void add(int value) {
        sum = sum + value;
    }

    // With synchronization
    synchronized void addSync(int value) {
        sum = sum + value;
    }
}

class MyThread extends Thread {
    int[] a;
    int start, end;
    Total t;
    boolean sync;

    MyThread(int[] a, int start, int end, Total t, boolean sync) {
        this.a = a;
        this.start = start;
        this.end = end;
        this.t = t;
        this.sync = sync;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            if (sync) {
                t.addSync(a[i]);
            } else {
                t.add(a[i]);
            }
        }
    }
}

public class Main9_3 {
    public static void main(String[] args) throws Exception {

        int[] a = new int[100000];

        for (int i = 0; i < a.length; i++) {
            a[i] = 1;
        }

        Total t = new Total();

        // Without synchronization
        MyThread t1 = new MyThread(a, 0, 25000, t, false);
        MyThread t2 = new MyThread(a, 25000, 50000, t, false);
        MyThread t3 = new MyThread(a, 50000, 75000, t, false);
        MyThread t4 = new MyThread(a, 75000, 100000, t, false);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Without Synchronization: " + t.sum);

        // Reset sum
        t.sum = 0;

        // With synchronization
        MyThread t5 = new MyThread(a, 0, 25000, t, true);
        MyThread t6 = new MyThread(a, 25000, 50000, t, true);
        MyThread t7 = new MyThread(a, 50000, 75000, t, true);
        MyThread t8 = new MyThread(a, 75000, 100000, t, true);

        t5.start();
        t6.start();
        t7.start();
        t8.start();

        t5.join();
        t6.join();
        t7.join();
        t8.join();

        System.out.println("With Synchronization: " + t.sum);
    }
}
