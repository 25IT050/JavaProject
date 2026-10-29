import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main_B_12 {
    static class Account implements Comparable<Account> {
        private final String accountNumber; private String ownerName; private long balance;
        Account(String n,String o,long b) { accountNumber=n; ownerName=o; balance=b; }
        String getAccountNumber() { return accountNumber; } long getBalance() { return balance; }
        @Override public int compareTo(Account other) { return accountNumber.compareTo(other.accountNumber); }
        @Override public String toString() { return accountNumber+" | "+ownerName+" | "+balance; }
    }
    static class AccountStore {
        private final HashMap<String,Account> accounts=new HashMap<>();
        private final ArrayList<String> history=new ArrayList<>();
        void add(Account a) { accounts.put(a.getAccountNumber(),a); history.add("ADDED "+a.getAccountNumber()); }
        Account get(String number) { return accounts.get(number); }
        ArrayList<Account> getAll() { return new ArrayList<>(accounts.values()); }
        ArrayList<Account> sortedByBalance() {
            ArrayList<Account> result=getAll();
            result.sort(Comparator.comparingLong(Account::getBalance).reversed());
            return result;
        }
        ArrayList<String> getHistory() { return new ArrayList<>(history); }
    }
    static class TransactionProcessor {
        private final ConcurrentHashMap<String,Account> sharedAccounts=new ConcurrentHashMap<>();
        void add(Account a) { sharedAccounts.put(a.getAccountNumber(),a); }
        Account get(String no) { return sharedAccounts.get(no); }
    }
    public static void main(String[] args) {
        AccountStore store=new AccountStore();
        store.add(new Account("AC0001","Riya",5000));
        store.add(new Account("AC0002","Aarav",2000));
        store.add(new Account("AC0003","Neha",9000));
        System.out.println("Lookup AC0002: "+store.get("AC0002"));
        System.out.println("Accounts sorted by balance (high to low):");
        for(Account a:store.sortedByBalance()) System.out.println(a);
        System.out.println("History: "+store.getHistory());
        TransactionProcessor processor=new TransactionProcessor();
        for(Account a:store.getAll()) processor.add(a);
        System.out.println("Thread-safe map lookup: "+processor.get("AC0001"));
    }
}
