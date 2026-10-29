
class MyTask implements Runnable {

    Object lock1;
    Object lock2;
    boolean deadlock;

    MyTask(Object lock1, Object lock2, boolean deadlock) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.deadlock = deadlock;
    }

    public void run() {

        if (deadlock) {

            synchronized (lock1) {

                System.out.println(Thread.currentThread().getName()
                        + " locked first object");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

                synchronized (lock2) {

                    System.out.println(Thread.currentThread().getName()
                            + " locked second object");
                }
            }

        } else {

            // Always lock objects in the same order
            synchronized (lock1) {

                System.out.println(Thread.currentThread().getName()
                        + " locked first object");

                synchronized (lock2) {

                    System.out.println(Thread.currentThread().getName()
                            + " locked second object");
                }
            }
        }
    }
}

public class Main10_3 {

    public static void main(String[] args) throws Exception {

        Object A = new Object();
        Object B = new Object();

        System.out.println("Deadlock demonstration:");

        Thread t1 = new Thread(new MyTask(A, B, true), "Thread-1");
        Thread t2 = new Thread(new MyTask(B, A, true), "Thread-2");

        // Daemon threads allow the program to exit after demonstration
        t1.setDaemon(true);
        t2.setDaemon(true);

        t1.start();
        t2.start();

        t1.join(500);
        t2.join(500);

        System.out.println("\nDeadlock prevention:");

        Thread t3 = new Thread(new MyTask(A, B, false), "Thread-3");
        Thread t4 = new Thread(new MyTask(A, B, false), "Thread-4");

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println("Program completed");
    }
}
