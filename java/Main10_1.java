
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyTask implements Runnable {

    private int id;

    MyTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Task " + id + " started by "
                + Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("Task " + id + " completed");
    }
}

public class Main10_1 {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            executor.submit(new MyTask(i));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println(e);
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
