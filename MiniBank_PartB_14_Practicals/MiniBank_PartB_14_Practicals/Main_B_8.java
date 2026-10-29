public class Main_B_8 {
    static class BankException extends Exception { BankException(String m) { super(m); } }
    static class InsufficientFundsException extends BankException {
        private long shortfall;
        InsufficientFundsException(long shortfall) { super("Insufficient funds. Short by " + shortfall); this.shortfall=shortfall; }
        long getShortfall() { return shortfall; }
    }
    static class AccountNotFoundException extends BankException { AccountNotFoundException(String m) { super(m); } }
    static class InvalidAmountException extends BankException { InvalidAmountException(String m) { super(m); } }
    static class Account {
        private String accountNumber; private long balance;
        Account(String n,long b) { accountNumber=n; balance=b; }
        void deposit(long amount) throws InvalidAmountException { if (amount<=0) throw new InvalidAmountException("Deposit must be positive"); balance+=amount; }
        void withdraw(long amount) throws InsufficientFundsException,InvalidAmountException {
            if (amount<=0) throw new InvalidAmountException("Withdrawal must be positive");
            if (amount>balance) throw new InsufficientFundsException(amount-balance);
            balance-=amount;
        }
        void transfer(Account to,long amount) throws BankException {
            try { withdraw(amount); to.deposit(amount); }
            catch (BankException e) { throw e; }
            finally { System.out.println("Transfer operation finished."); }
        }
        long getBalance() { return balance; }
    }
    static class BankFile implements AutoCloseable {
        BankFile() { System.out.println("Resource opened."); }
        @Override public void close() { System.out.println("Resource closed."); }
    }
    public static void main(String[] args) {
        Account a = new Account("AC0001",1000);
        try { a.withdraw(5000); }
        catch (InsufficientFundsException e) { System.out.println("Withdrawal failed: short by " + e.getShortfall()); }
        catch (InvalidAmountException e) { System.out.println(e.getMessage()); }
        finally { System.out.println("Current balance: " + a.getBalance()); }
        try (BankFile file = new BankFile()) { System.out.println("Working with bank resource."); }
        catch (Exception e) { System.out.println(e.getMessage()); }
    }
}
