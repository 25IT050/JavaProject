public class Main_B_6 {
    interface Transactable { void deposit(long amount); boolean withdraw(long amount); }
    interface InterestBearing {
        double interestRate(); long getBalance();
        default double yearlyInterest() { return getBalance() * interestRate() / 100.0; }
    }
    @FunctionalInterface interface WithdrawRule { boolean allow(Account account, long amount); }
    interface Premium {}
    static class Account implements Transactable, InterestBearing, Premium {
        private long balance; private double rate;
        Account(long balance,double rate) { this.balance=balance; this.rate=rate; }
        @Override public void deposit(long amount) { if (amount > 0) balance += amount; }
        @Override public boolean withdraw(long amount) { if (amount<=0 || amount>balance) return false; balance-=amount; return true; }
        @Override public double interestRate() { return rate; }
        @Override public long getBalance() { return balance; }
    }
    public static void main(String[] args) {
        Account a = new Account(5000,4.0);
        a.deposit(1000);
        WithdrawRule anonymousRule = new WithdrawRule() {
            @Override public boolean allow(Account account,long amount) { return amount > 0 && amount <= account.getBalance(); }
        };
        WithdrawRule lambdaRule = (account, amount) -> amount > 0 && amount <= account.getBalance();
        System.out.println("Anonymous class allows: " + anonymousRule.allow(a,2000));
        System.out.println("Lambda allows: " + lambdaRule.allow(a,2000));
        System.out.println("Yearly interest: " + a.yearlyInterest());
        System.out.println("Premium customer: " + (a instanceof Premium));
    }
}
