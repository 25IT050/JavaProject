import java.util.Scanner;
enum Move {
    ROCK, PAPER, SCISSORS, LIZARD, SPOCK
}
public class P1A3 {
    static int winner(Move a, Move b) {
        if (a == b)
            return 0;
        return switch (a) {
            case ROCK ->
                (b == Move.SCISSORS || b == Move.LIZARD) ? 1 : -1;
            case PAPER ->
                (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;
            case SCISSORS ->
                (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;
            case LIZARD ->
                (b == Move.SPOCK || b == Move.PAPER) ? 1 : -1;
            case SPOCK ->
                (b == Move.SCISSORS || b == Move.ROCK) ? 1 : -1;
        };
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int playerScore = 0;
        int computerScore = 0;
        Move[] moves = Move.values();
        for (int i = 1; i <= 5; i++) {
            System.out.println("Round " + i);
            System.out.print("Enter your move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK): ");
            Move player = Move.valueOf(sc.next().toUpperCase());
            Move computer = moves[(int) (Math.random() * 5)];
            System.out.println("Computer Move: " + computer);
            int result = winner(player, computer);
            if (result == 1) {
                System.out.println("You win this round.");
                playerScore++;
            } else if (result == -1) {
                System.out.println("Computer wins this round.");
                computerScore++;
            } else {
                System.out.println("Round Tie.");
            }
            System.out.println();
        }
        System.out.println("Final Score");
        System.out.println("You: " + playerScore);
        System.out.println("Computer: " + computerScore);

        if (playerScore > computerScore) {
            System.out.println("You win " + playerScore + "-" + computerScore);
        } 
        else if (computerScore > playerScore) {
            System.out.println("Computer wins " + computerScore + "-" + playerScore);
        }
         else {
            System.out.println("Match Tie");
        }

        sc.close();
    }
}