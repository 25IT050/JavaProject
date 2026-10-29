import java.util.Scanner;

record Vehicle(String number, String type){}

public class P1A2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;
        int bike = 0;
        int car = 0;
        int truck = 0;
        while (true) {
            String number = sc.next();
            if (number.equals("done")) {
                break;
            }
            String type = sc.next();
            Vehicle v = new Vehicle(number, type);
            int toll = switch (v.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };
            total += toll;
            switch (v.type()) {
                case "bike" -> bike++;
                case "car" -> car++;
                case "truck" -> truck++;
            }
        }

        System.out.println("Total toll: " + total);

        if (bike >= car && bike >= truck) {
            System.out.println("Most frequent: bike");
        } else if (car >= bike && car >= truck) {
            System.out.println("Most frequent: car");
        } else {
            System.out.println("Most frequent: truck");
        }
    }
}