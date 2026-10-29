import java.util.concurrent.*;
import java.util.*;

public class Main_B_10 {
    static class TransactionProcessor {
        private ExecutorService executor = Executors.newFixedThreadPool(4);
        void submit(Runnable task) { executor.execute(task); }
        void stop() {
            executor.shutdown();
            try { if (!executor.awaitTermination(10,TimeUnit.SECONDS)) executor.shutdownNow(); }
            catch (InterruptedException e) { executor.shutdownNow(); Thread.currentThread().interrupt(); }
        }
    }
    static class TransactionBuffer {
        private final Queue<String> queue = new ArrayDeque<>();
        synchronized void add(String item) throws InterruptedException { while(queue.size()>=3) wait(); queue.add(item); notifyAll(); }
        synchronized String take() throws InterruptedException { while(queue.isEmpty()) wait(); String item=queue.remove(); notifyAll(); return item; }
    }
    static class Account {
        final String accountNumber; long balance;
        Account(String n,long b) { accountNumber=n; balance=b; }
        void deposit(long x) { balance+=x; }
    }
    static void safeTransfer(Account from,Account to,long amount) {
        Account first = from.accountNumber.compareTo(to.accountNumber)<0 ? from : to;
        Account second = first==from ? to : from;
        synchronized(first) { synchronized(second) { if(from.balance>=amount) { from.balance-=amount; to.balance+=amount; } } }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1. Thread pool transaction batch");
        TransactionProcessor processor = new TransactionProcessor();
        for(int i=1;i<=8;i++) { final int id=i; processor.submit(() -> System.out.println("Transaction " + id + " processed by " + Thread.currentThread().getName())); }
        processor.stop();

        System.out.println("\n2. Producer-consumer");
        TransactionBuffer buffer = new TransactionBuffer();
        Thread producer = new Thread(() -> { try { for(int i=1;i<=5;i++) buffer.add("TX-"+i); } catch(InterruptedException e) { Thread.currentThread().interrupt(); } });
        Thread consumer = new Thread(() -> { try { for(int i=1;i<=5;i++) System.out.println("Processed " + buffer.take()); } catch(InterruptedException e) { Thread.currentThread().interrupt(); } });
        producer.start(); consumer.start(); producer.join(); consumer.join();

        System.out.println("\n3. Transfer with consistent lock order");
        Account a=new Account("AC0001",5000), b=new Account("AC0002",3000);
        Thread t1=new Thread(() -> safeTransfer(a,b,500));
        Thread t2=new Thread(() -> safeTransfer(b,a,300));
        t1.start(); t2.start(); t1.join(); t2.join();
        System.out.println(a.accountNumber+" balance: "+a.balance);
        System.out.println(b.accountNumber+" balance: "+b.balance);
    }
}
