public class Main_B_2 {
    static class Customer {
        private static long customerCounter = 100;
        private final String customerId;
        private String name, email, mobile;
        private static String generateCustomerId() { return "CUST" + (++customerCounter); }
        Customer(String name, String email, String mobile) {
            this.customerId = generateCustomerId(); this.name = name; this.email = email; this.mobile = mobile;
        }
        public String getCustomerId() { return customerId; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getMobile() { return mobile; }
    }

    static class Account {
        private static int counter = 1;
        private final String accountNumber;
        private String ownerName;
        private long balance;
        private boolean active = true;
        Account(String ownerName, long openingBalance) {
            this.accountNumber = String.format("AC%04d", counter++);
            this.ownerName = ownerName; this.balance = Math.max(0, openingBalance);
        }
        Account(String ownerName) { this(ownerName, 0); }
        public void deposit(long amount) { if (amount > 0) balance += amount; }
        public boolean withdraw(long amount) {
            if (amount <= 0 || balance < amount) return false;
            balance -= amount; return true;
        }
        public String getAccountNumber() { return accountNumber; }
        public String getOwnerName() { return ownerName; }
        public long getBalance() { return balance; }
        public boolean isActive() { return active; }
    }

    public static void main(String[] args) {
        Account[] accounts = { new Account("Riya", 5000), new Account("Aarav"), new Account("Neha", 3000) };
        accounts[0].deposit(2000);
        accounts[0].withdraw(3000);
        boolean result = accounts[0].withdraw(10000);
        for (Account a : accounts)
            System.out.println(a.getAccountNumber() + " | " + a.getOwnerName() + " | Balance: " + a.getBalance());
        System.out.println("Large withdrawal successful: " + result);
        Customer c = new Customer("Riya", "riya@example.com", "9876543210");
        System.out.println(c.getCustomerId() + " | " + c.getName());
    }
}
