import java.util.*;

public class Main_B_14 {
    static class BankException extends Exception { BankException(String m) { super(m); } }
    static class InvalidAmountException extends BankException { InvalidAmountException(String m) { super(m); } }
    static class InsufficientFundsException extends BankException { InsufficientFundsException(String m) { super(m); } }
    static class Account {
        private final String accountNumber; private final String ownerName; private long balance;
        Account(String n,String owner,long b) { accountNumber=n; ownerName=owner; balance=b; }
        String getAccountNumber() { return accountNumber; } String getOwnerName() { return ownerName; } long getBalance() { return balance; }
        void deposit(long amount) throws InvalidAmountException { if(amount<=0) throw new InvalidAmountException("Amount must be positive"); balance+=amount; }
        void withdraw(long amount) throws BankException { if(amount<=0) throw new InvalidAmountException("Amount must be positive"); if(amount>balance) throw new InsufficientFundsException("Insufficient balance"); balance-=amount; }
        @Override public String toString() { return accountNumber+" | "+ownerName+" | Rs. "+balance; }
    }
    static class Repository<T> {
        private final Map<String,T> data=new LinkedHashMap<>();
        void save(String id,T item) { data.put(id,item); }
        T findById(String id) { return data.get(id); }
        Collection<T> findAll() { return data.values(); }
    }
    static class Validator {
        static boolean validAccountNumber(String s) { return s!=null && s.matches("AC[0-9]{4}"); }
        static boolean validAmount(long amount) { return amount>0; }
    }
    static class BankService {
        private final Repository<Account> repository=new Repository<>();
        void openAccount(String number,String name,long opening) throws BankException {
            if(!Validator.validAccountNumber(number)) throw new BankException("Account number must look like AC0001");
            if(repository.findById(number)!=null) throw new BankException("Account already exists");
            Account a=new Account(number,name,0); if(opening>0) a.deposit(opening); else if(opening<0) throw new InvalidAmountException("Opening amount cannot be negative");
            repository.save(number,a); System.out.println("Account opened: "+a);
        }
        void deposit(String number,long amount) throws BankException { Account a=require(number); a.deposit(amount); System.out.println("Deposit successful. Balance: "+a.getBalance()); }
        void withdraw(String number,long amount) throws BankException { Account a=require(number); a.withdraw(amount); System.out.println("Withdrawal successful. Balance: "+a.getBalance()); }
        void transfer(String from,String to,long amount) throws BankException {
            Account source=require(from), target=require(to);
            source.withdraw(amount);
            try { target.deposit(amount); }
            catch(BankException e) { source.deposit(amount); throw e; }
            System.out.println("Transfer completed.");
        }
        void statement(String number) throws BankException { System.out.println(require(number)); }
        void listAccounts() { for(Account a:repository.findAll()) System.out.println(a); }
        private Account require(String number) throws BankException { Account a=repository.findById(number); if(a==null) throw new BankException("Account not found: "+number); return a; }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in); BankService service=new BankService();
        try { service.openAccount("AC0001","Riya",5000); service.openAccount("AC0002","Aarav",2000); }
        catch(BankException e) { System.out.println(e.getMessage()); }
        int choice=0;
        while(choice!=6) {
            System.out.println("\n===== MINIBANK =====\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Statement\n5. List Accounts\n6. Exit");
            System.out.print("Choice: ");
            if(!sc.hasNextInt()) { System.out.println("Enter a number."); sc.next(); continue; }
            choice=sc.nextInt();
            try {
                switch(choice) {
                    case 1 -> { System.out.print("Account number and amount: "); service.deposit(sc.next(),sc.nextLong()); }
                    case 2 -> { System.out.print("Account number and amount: "); service.withdraw(sc.next(),sc.nextLong()); }
                    case 3 -> { System.out.print("From, to and amount: "); service.transfer(sc.next(),sc.next(),sc.nextLong()); }
                    case 4 -> { System.out.print("Account number: "); service.statement(sc.next()); }
                    case 5 -> service.listAccounts();
                    case 6 -> System.out.println("Thank you for using MiniBank.");
                    default -> System.out.println("Invalid choice.");
                }
            } catch(BankException e) { System.out.println("Transaction failed: "+e.getMessage()); }
        }
        sc.close();
    }
}
