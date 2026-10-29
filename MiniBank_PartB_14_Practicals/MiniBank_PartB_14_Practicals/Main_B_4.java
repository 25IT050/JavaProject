import java.util.regex.Pattern;

public class Main_B_4 {
    static class Validator {
        private static final Pattern MOBILE = Pattern.compile("^[6-9][0-9]{9}$");
        private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        private static final Pattern PAN = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
        private static final Pattern IFSC = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");
        static boolean isValidMobile(String s) { return s != null && MOBILE.matcher(s).matches(); }
        static boolean isValidEmail(String s) { return s != null && EMAIL.matcher(s).matches(); }
        static boolean isValidPan(String s) { return s != null && PAN.matcher(s).matches(); }
        static boolean isValidIfsc(String s) { return s != null && IFSC.matcher(s).matches(); }
    }
    enum TransactionType { DEPOSIT, WITHDRAW, TRANSFER }
    record Command(TransactionType type, String accountNumber, long amount) {}
    static class CommandParser {
        static Command parse(String line) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 3) throw new IllegalArgumentException("Enter: TYPE ACCOUNT_NUMBER AMOUNT");
            return new Command(TransactionType.valueOf(parts[0].toUpperCase()), parts[1], Long.parseLong(parts[2]));
        }
    }
    static class Account {
        private String accountNumber, ownerName; private long balance;
        Account(String n, String o, long b) { accountNumber=n; ownerName=o; balance=b; }
        String getAccountNumber() { return accountNumber; } String getOwnerName() { return ownerName; } long getBalance() { return balance; }
    }
    static class StatementFormatter {
        static String buildStatement(Account a) {
            StringBuilder sb = new StringBuilder();
            sb.append("----- ACCOUNT STATEMENT -----\n").append("Account: ").append(a.getAccountNumber())
              .append("\nOwner: ").append(a.getOwnerName()).append("\nBalance: Rs. ").append(a.getBalance());
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        System.out.println("Mobile valid: " + Validator.isValidMobile("9876543210"));
        System.out.println("Mobile invalid: " + Validator.isValidMobile("12345"));
        System.out.println("Email valid: " + Validator.isValidEmail("abc@example.com"));
        System.out.println("PAN valid: " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("IFSC valid: " + Validator.isValidIfsc("ABCD0123456"));
        Command c = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println(c);
        System.out.println(StatementFormatter.buildStatement(new Account("AC0001", "Riya", 5000)));
    }
}
