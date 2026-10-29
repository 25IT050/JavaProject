public class Main_B_9 {
    static class Account {
        private long balance;
        Account(long balance) { this.balance=balance; }
        public synchronized void deposit(long amount) { balance += amount; }
        public synchronized void withdraw(long amount) { if (amount>0 && amount<=balance) balance-=amount; }
        public synchronized long getBalance() { return balance; }
    }
    static class AccountWorker implements Runnable {
        private Account account; private int times; private long amount;
        AccountWorker(Account a,int times,long amount) { account=a; this.times=times; this.amount=amount; }
        @Override public void run() {
            System.out.println(Thread.currentThread().getName() + " started");
            for (int i=0;i<times;i++) account.deposit(amount);
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);
        Thread[] threads = new Thread[10];
        for (int i=0;i<threads.length;i++) { threads[i]=new Thread(new AccountWorker(account,1000,1),"Worker-"+(i+1)); threads[i].start(); }
        for (Thread t : threads) t.join();
        System.out.println("Final balance: " + account.getBalance());
        System.out.println("Expected balance: 10000");
    }
}
