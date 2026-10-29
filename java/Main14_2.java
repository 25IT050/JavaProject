import java.io.*;
import java.util.ArrayList;

class SavedAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    double balance;

    SavedAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String toString() {
        return name + " - " + balance;
    }
}

class SavedBankService {
    private ArrayList<SavedAccount> accounts = new ArrayList<>();

    public void addAccount(String name, double balance) {
        if (name == null || name.trim().isEmpty()
                || balance < 0) {
            throw new IllegalArgumentException("Invalid account");
        }

        accounts.add(new SavedAccount(name, balance));
    }

    public ArrayList<SavedAccount> getAccounts() {
        return accounts;
    }

    public void save(String filename) throws IOException {
        try (ObjectOutputStream out =
                new ObjectOutputStream(
                    new FileOutputStream(filename))) {
            out.writeObject(accounts);
        }
    }

    @SuppressWarnings("unchecked")
    public void load(String filename)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in =
                new ObjectInputStream(
                    new FileInputStream(filename))) {
            accounts = (ArrayList<SavedAccount>) in.readObject();
        }
    }
}

public class Main14_2 {
    public static void main(String[] args) {
        try {
            SavedBankService bank = new SavedBankService();

            bank.addAccount("Bhargavi", 1000);
            bank.addAccount("Rutvi", 2000);

            bank.save("accounts.dat");
            System.out.println("Accounts saved.");

            SavedBankService loadedBank = new SavedBankService();
            loadedBank.load("accounts.dat");

            System.out.println("Accounts after reloading:");

            for (SavedAccount account : loadedBank.getAccounts()) {
                System.out.println(account);
            }

            System.out.println("Total accounts: "
                    + loadedBank.getAccounts().size());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
