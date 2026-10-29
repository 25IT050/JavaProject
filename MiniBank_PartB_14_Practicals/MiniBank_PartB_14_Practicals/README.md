# MiniBank – Part B (Practical 1–14)

Each practical is a separate, self-contained Java 17 program. Supporting classes are included inside the corresponding `Main_B_N` class to make each practical easy to compile and run independently.

## Files
- `Main_B_1.java` — Practical 1
- `Main_B_2.java` — Practical 2
- `Main_B_3.java` — Practical 3
- `Main_B_4.java` — Practical 4
- `Main_B_5.java` — Practical 5
- `Main_B_6.java` — Practical 6
- `Main_B_7.java` — Practical 7
- `Main_B_8.java` — Practical 8
- `Main_B_9.java` — Practical 9
- `Main_B_10.java` — Practical 10
- `Main_B_11.java` — Practical 11
- `Main_B_12.java` — Practical 12
- `Main_B_13.java` — Practical 13
- `Main_B_14.java` — Practical 14

## Compile and run

Open a terminal in this folder and run the commands for the practical you want:

```bash
javac Main_B_1.java
java Main_B_1
```

Replace `1` with the practical number (1 to 14). Use JDK 17 or later. Practical 11 creates a `data` folder containing its sample saved accounts, transaction log and report.

## Notes

- Every file has its own `public class Main_B_N` and its own `main()` method.
- Practical 6 demonstrates the interfaces and lambda requirements in a single-file layout. The source list asks you to organise the finished application into `model`, `util` and `service` packages and build a runnable JAR; those packaging steps are project-structure steps rather than required for testing this self-contained version.
- Practical 9 uses synchronized account methods so the final balance is consistently 10000. This demonstrates the corrected version requested in the practical.
- Practical 10 demonstrates the thread pool, producer-consumer coordination and safe lock ordering.
- Practical 14 is a menu-driven integration example with in-memory account storage.
