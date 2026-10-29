import java.util.Scanner;

public class Main_B_1 {
    record BankInfo(String name, String branch) {}
    enum MenuOption { OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankInfo bank = new BankInfo("MiniBank", "CHARUSAT Branch");
        System.out.println("===== " + bank.name() + " | " + bank.branch() + " =====");
        int choice;
        do {
            System.out.println("\n1. Open Account\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Exit");
            System.out.print("Enter choice: ");
            while (!sc.hasNextInt()) { System.out.print("Enter a number: "); sc.next(); }
            choice = sc.nextInt();
            MenuOption option = switch (choice) {
                case 1 -> MenuOption.OPEN_ACCOUNT;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.EXIT;
                default -> null;
            };
            if (option == null) System.out.println("Invalid choice.");
            else switch (option) {
                case OPEN_ACCOUNT -> System.out.println("Open Account — to be implemented in a later practical.");
                case DEPOSIT -> System.out.println("Deposit — to be implemented in a later practical.");
                case WITHDRAW -> System.out.println("Withdraw — to be implemented in a later practical.");
                case TRANSFER -> System.out.println("Transfer — to be implemented in a later practical.");
                case EXIT -> System.out.println("Thank you for using MiniBank. Goodbye!");
            }
        } while (choice != 5);
        sc.close();
    }
}
