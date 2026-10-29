
class Booking {
    int seatsLeft = 5;
    int booked = 0;

    // Without synchronization
    void book() {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }

            seatsLeft--;
            booked++;

            System.out.println("Seat booked");
        }
    }

    // With synchronization
    synchronized void bookSync() {
        if (seatsLeft > 0) {
            seatsLeft--;
            booked++;

            System.out.println("Seat booked");
        } else {
            System.out.println("No seats available");
        }
    }
}

class MyThread extends Thread {
    Booking b;
    boolean sync;

    MyThread(Booking b, boolean sync) {
        this.b = b;
        this.sync = sync;
    }

    public void run() {
        if (sync) {
            b.bookSync();
        } else {
            b.book();
        }
    }
}

public class Main9_2 {
    public static void main(String[] args) throws Exception {

        Booking b = new Booking();

        System.out.println("Without Synchronization:");

        MyThread[] t = new MyThread[10];

        for (int i = 0; i < 10; i++) {
            t[i] = new MyThread(b, false);
            t[i].start();
        }

        for (int i = 0; i < 10; i++) {
            t[i].join();
        }

        System.out.println("Total seats booked: " + b.booked);
        System.out.println("Seats left: " + b.seatsLeft);

        // Reset values
        b.seatsLeft = 5;
        b.booked = 0;

        System.out.println("\nWith Synchronization:");

        MyThread[] t2 = new MyThread[10];

        for (int i = 0; i < 10; i++) {
            t2[i] = new MyThread(b, true);
            t2[i].start();
        }

        for (int i = 0; i < 10; i++) {
            t2[i].join();
        }

        System.out.println("Total seats booked: " + b.booked);
        System.out.println("Seats left: " + b.seatsLeft);
    }
}
