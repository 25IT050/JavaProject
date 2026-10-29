import java.util.ArrayList;
import java.util.List;

class PasswordChecker {
    public static boolean hasMinLength(String pw) {
        return pw != null && pw.length() >= 8;
    }
    public static boolean hasUppercase(String pw) {
        return pw != null && pw.matches(".*[A-Z].*");
    }
    public static boolean hasDigit(String pw) {
        return pw != null && pw.matches(".*[0-9].*");
    }
    public static boolean hasSpecialChar(String pw) {
        return pw != null && pw.matches(".*[^a-zA-Z0-9].*");
    }
    public static int countPassedRules(String pw) {
        int count = 0;
        if (hasMinLength(pw)) count++;
        if (hasUppercase(pw)) count++;
        if (hasDigit(pw)) count++;
        if (hasSpecialChar(pw)) count++;
        return count;
    }
    public static String strength(String pw) {
        int passedCount = countPassedRules(pw);

        if (passedCount <= 1) {
            return "Weak";
        } else if (passedCount <= 3) {
            return "Medium";
        } else {
            return "Strong";
        }
    }
    public static List<String> getPassedRulesBreakdown(String pw) {
        List<String> rules = new ArrayList<>();
        rules.add("Length >= 8: " + (hasMinLength(pw) ? "PASS" : "FAIL"));
        rules.add("Uppercase Letter: " + (hasUppercase(pw) ? "PASS" : "FAIL"));
        rules.add("Digit: " + (hasDigit(pw) ? "PASS" : "FAIL"));
        rules.add("Special Character: " + (hasSpecialChar(pw) ? "PASS" : "FAIL"));
        return rules;
    }
}
public class Driver1 {

    public static void main(String[] args) {
        String[] testPasswords = {
                "abc",
                "password",
                "Password1",
                "Abcd1234!"
        };

        for (String pw : testPasswords) {
            System.out.println("Testing Password: \"" + pw + "\"");
            System.out.println("------------------------------------------");
            for (String ruleStatus : PasswordChecker.getPassedRulesBreakdown(pw)) {
                System.out.println("  [ ] " + ruleStatus);
            }
            int passed = PasswordChecker.countPassedRules(pw);
            String result = PasswordChecker.strength(pw);

            System.out.println("  --> Rules Passed: " + passed + "/4");
            System.out.println("  --> Result Strength: " + result);
            System.out.println();
        }
    }
}