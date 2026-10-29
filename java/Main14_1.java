import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String name;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}

class BankService {
    private ArrayList<Account> accounts = new ArrayList<>();

    public void addAccount(String name, double balance) {
        if (name == null || name.trim().isEmpty()
                || balance < 0) {
            throw new IllegalArgumentException("Invalid account");
        }

        accounts.add(new Account(name, balance));
    }

    public int getCount() {
        return accounts.size();
    }

    public void performAction(int action) {
        if (action < 1 || action > 3) {
            throw new IllegalArgumentException("Invalid action");
        }

        if (action == 1) {
            System.out.println("Add account selected");
        } else if (action == 2) {
            System.out.println("View count selected");
        } else {
            System.out.println("Exit selected");
        }
    }
}

public class Main14_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();

        while (true) {
            System.out.println("\n1. Add Account");
            System.out.println("2. View Account Count");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                service.performAction(choice);

                if (choice == 1) {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter balance: ");
                    double balance = sc.nextDouble();
                    sc.nextLine();

                    service.addAccount(name, balance);
                    System.out.println("Account added successfully");
                } else if (choice == 2) {
                    System.out.println("Total accounts: "
                            + service.getCount());
                } else {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
