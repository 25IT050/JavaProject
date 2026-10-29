class Booking {
    int seatsLeft = 5;
    public synchronized void book(String name) {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            seatsLeft--;
            System.out.println(name + " booked a seat");
        }
    }
}
public class MainS92 {
    public static void main(String[] args) throws Exception {
        Booking b = new Booking();
        Thread[] threads = new Thread[10];
        for (int i=0;i<10;i++) {
            int n =i+1;
            threads[i] = new Thread(() -> {
                b.book("Person " + n);
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Seats left: " + b.seatsLeft);
    }
}