import java.util.Objects;

public class Main_B_3 {
    static class Account {
        private final String accountNumber; private String ownerName; private long balance;
        Account(String number, String owner, long balance) { this.accountNumber=number; this.ownerName=owner; this.balance=balance; }
        @Override public String toString() { return accountNumber + " | " + ownerName + " | " + balance; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Account a)) return false;
            return accountNumber.equals(a.accountNumber);
        }
        @Override public int hashCode() { return Objects.hash(accountNumber); }
    }
    static class Customer implements Cloneable {
        public static class Address {
            private String line, city, pincode;
            Address(String line, String city, String pincode) { this.line=line; this.city=city; this.pincode=pincode; }
            public String getLine() { return line; } public String getCity() { return city; } public String getPincode() { return pincode; }
        }
        private String name; private Address address;
        Customer(String name, Address address) { this.name=name; this.address=address; }
        public Address getAddress() { return address; }
        @Override public Customer clone() {
            try { return (Customer) super.clone(); }
            catch (CloneNotSupportedException e) { throw new AssertionError(e); }
        }
    }
    public static void main(String[] args) {
        Account a1 = new Account("AC0001", "Riya", 4000);
        Account a2 = new Account("AC0001", "Riya", 9000);
        System.out.println(a1);
        System.out.println("Accounts equal: " + a1.equals(a2));
        Object obj = a1;
        System.out.println("Object is Account: " + (obj instanceof Account));
        Customer c = new Customer("Riya", new Customer.Address("Main Road", "Anand", "388001"));
        Customer copy = c.clone();
        System.out.println("Customer cloned: " + (c != copy));
        System.out.println("City: " + c.getAddress().getCity());
    }
}
