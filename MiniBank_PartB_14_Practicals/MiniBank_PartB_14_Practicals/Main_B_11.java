import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main_B_11 {
    static class Account implements Serializable {
        private static final long serialVersionUID=1L;
        private String accountNumber, ownerName; private long balance;
        Account(String n,String o,long b) { accountNumber=n; ownerName=o; balance=b; }
        @Override public String toString() { return accountNumber+" | "+ownerName+" | "+balance; }
    }
    static class StatePersister {
        static void save(Account[] accounts,Path file) throws IOException {
            Files.createDirectories(file.getParent());
            try(ObjectOutputStream out=new ObjectOutputStream(Files.newOutputStream(file))) { out.writeObject(accounts); }
        }
        static Account[] load(Path file) throws IOException,ClassNotFoundException {
            try(ObjectInputStream in=new ObjectInputStream(Files.newInputStream(file))) { return (Account[])in.readObject(); }
        }
    }
    static class TransactionLog {
        static void append(Path file,String line) throws IOException {
            Files.createDirectories(file.getParent());
            Files.write(file,(line+System.lineSeparator()).getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        }
    }
    static class ReportGenerator {
        static String generate(Path folder,Path report) throws IOException {
            long deposits=0, withdrawals=0; StringBuilder details=new StringBuilder();
            if(Files.exists(folder)) try(DirectoryStream<Path> stream=Files.newDirectoryStream(folder,"*.log")) {
                for(Path file:stream) {
                    for(String line:Files.readAllLines(file)) {
                        String[] parts=line.trim().split("\\s+");
                        if(parts.length>=3) try { long amount=Long.parseLong(parts[2]); if(parts[0].equals("DEPOSIT")) deposits+=amount; else if(parts[0].equals("WITHDRAW")) withdrawals+=amount; } catch(NumberFormatException ignored) {}
                    }
                    BasicFileAttributes attr=Files.readAttributes(file,BasicFileAttributes.class);
                    details.append(file.getFileName()).append(" size=").append(attr.size()).append(" bytes, modified=").append(attr.lastModifiedTime()).append('\n');
                }
            }
            String result="Total deposits: "+deposits+"\nTotal withdrawals: "+withdrawals+"\nNet change: "+(deposits-withdrawals)+"\n"+details;
            Files.writeString(report,result,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
            return result;
        }
    }
    public static void main(String[] args) throws Exception {
        Path data=Paths.get("data"), accountsFile=data.resolve("accounts.dat"), logs=data.resolve("logs");
        Account[] accounts={new Account("AC0001","Riya",5000),new Account("AC0002","Aarav",2000),new Account("AC0003","Neha",9000)};
        StatePersister.save(accounts,accountsFile);
        TransactionLog.append(logs.resolve("transactions.log"),"DEPOSIT AC0001 500");
        TransactionLog.append(logs.resolve("transactions.log"),"WITHDRAW AC0002 200");
        System.out.println("Reloaded accounts:");
        for(Account a:StatePersister.load(accountsFile)) System.out.println(a);
        System.out.println("\n"+ReportGenerator.generate(logs,data.resolve("daily-report.txt")));
    }
}
