public class Main_B_5 {
    static abstract class Account {
        protected String accountNumber, ownerName; protected long balance;
        Account(String no, String owner, long balance) { this.accountNumber=no; this.ownerName=owner; this.balance=balance; }
        abstract double interestRate();
        abstract boolean canWithdraw(long amount);
        public long getBalance() { return balance; }
    }
    static class SavingsAccount extends Account {
        private long minBalance;
        SavingsAccount(String n,String o,long b,long min) { super(n,o,b); minBalance=min; }
        @Override double interestRate() { return 4.0; }
        @Override boolean canWithdraw(long amount) { return amount > 0 && balance-amount >= minBalance; }
    }
    static class CurrentAccount extends Account {
        private long overdraftLimit;
        CurrentAccount(String n,String o,long b,long limit) { super(n,o,b); overdraftLimit=limit; }
        @Override double interestRate() { return 0.0; }
        @Override boolean canWithdraw(long amount) { return amount > 0 && balance-amount >= -overdraftLimit; }
    }
    static class FixedDepositAccount extends Account {
        FixedDepositAccount(String n,String o,long b) { super(n,o,b); }
        @Override double interestRate() { return 7.0; }
        @Override boolean canWithdraw(long amount) { return false; }
    }
    public static void main(String[] args) {
        Account[] accounts = { new SavingsAccount("AC0001","Riya",5000,1000), new CurrentAccount("AC0002","Aarav",2000,3000), new FixedDepositAccount("AC0003","Neha",10000) };
        for (Account a : accounts) {
            System.out.println(a.accountNumber + " interest rate: " + a.interestRate() + "%");
            System.out.println("Can withdraw Rs. 1500: " + a.canWithdraw(1500));
            if (a instanceof SavingsAccount s) System.out.println("Savings minimum balance: " + s.minBalance);
        }
    }
}
