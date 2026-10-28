# MiniBank - Java Console Banking System

## Project Overview

MiniBank is a menu-driven console banking application developed in Java for educational purposes. It demonstrates object-oriented programming, exception handling, file handling, collections, generics, annotations, and multithreading.

The application allows users to create bank accounts, deposit and withdraw money, transfer funds between accounts, view account statements, list accounts, and generate a transaction report.

## Features

- **Open an account:** Create Savings, Current, or Fixed Deposit (FD) accounts.
- **Deposit money:** Add money to an existing account.
- **Withdraw money:** Withdraw funds when the account has a sufficient balance.
- **Transfer money:** Transfer funds between two accounts.
- **Account statement:** Display an account number, owner, balance, and account type.
- **List accounts:** Display accounts sorted by balance, from highest to lowest.
- **Transaction report:** Generate an end-of-day report from log files.
- **Persistent account data:** Save account details to a file and load them when the application starts.
- **Transaction logging:** Record account openings and transactions in a log file.
- **Thread-pool demonstration:** Run multiple batch tasks using an `ExecutorService`.
- **Producer-consumer demonstration:** Demonstrate thread communication using `wait()`, `notify()`, and `notifyAll()`.
- **Input validation:** Check formats for mobile numbers, email addresses, PAN numbers, IFSC codes, and transaction amounts.
- **Exception handling:** Handle invalid amounts, missing accounts, and insufficient funds.

## Technologies Used

- Java
- Java Collections Framework (`ArrayList`, `HashMap`, `Queue`, `ConcurrentHashMap`)
- Java I/O and NIO file APIs
- Serialization
- Generics and reflection
- Custom annotations
- Exception handling
- Multithreading and concurrency utilities

## Requirements

- JDK 16 or later is recommended because the code uses Java records and modern switch expressions.
- A terminal or command prompt.
- A text editor or Java IDE, such as VS Code, IntelliJ IDEA, or Eclipse.

Check your Java installation:

```bash
java -version
javac -version
```

## How to Compile and Run

1. Save the source file as **`MiniBank.java`**. The filename must match the public class name.
2. Open a terminal in the folder containing `MiniBank.java`.
3. Compile the program:

   ```bash
   javac MiniBank.java
   ```

4. Run the program:

   ```bash
   java MiniBank
   ```

## Menu Options

When the program starts, it displays the following menu:

| Option | Operation |
|---|---|
| 1 | Open account |
| 2 | Deposit |
| 3 | Withdraw |
| 4 | Transfer |
| 5 | Account statement |
| 6 | List accounts by balance |
| 7 | Generate end-of-day report |
| 8 | Run transaction thread-pool demo |
| 9 | Run producer-consumer demo |
| 10 | Save accounts |
| 0 | Save accounts and exit |

Follow the prompts shown in the terminal to complete an operation.

## Data Files

The program creates a `data` directory in the current working directory as needed.

- `data/accounts.dat` — serialized account data.
- `data/logs/transactions.log` — transaction and account-opening log.
- `data/report.txt` — generated end-of-day report.

Keep these files if you want to retain the saved account data and logs between runs. The files are created relative to the directory from which you run the program.

## Main Java Concepts Demonstrated

- **OOP:** Abstract classes, inheritance, encapsulation, interfaces, and polymorphism.
- **Collections:** Lists, maps, queues, sorting, and concurrent collections.
- **Generics:** A generic repository class and a numeric average method.
- **Exceptions:** A custom exception hierarchy for banking errors.
- **File handling:** Reading, writing, serialization, and transaction logs.
- **Annotations and reflection:** Custom annotations and runtime field inspection.
- **Multithreading:** Executor service, synchronized methods, and producer-consumer coordination.
- **Functional programming:** A functional interface and lambda expressions.
- **Resource management:** `AutoCloseable` and try-with-resources.
- **Records and enums:** Structured data and named constants.

## Important Notes

- This is an **educational demonstration**, not production banking software.
- It does not connect to a real bank or process real payments.
- The program stores account data using Java serialization and does not implement production-grade security.
- Account files and logs are created relative to the working directory used to launch the program.
- Use test data only.

## Author

**Bhargavi Luva**  
**Student ID:** 25IT050

## License

This project is intended for educational and academic use. Add a specific license if you plan to distribute or reuse it publicly.
