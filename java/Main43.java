abstract class Media {
    String title;
    int lateDays;

    Media(String title, int lateDays) {
        this.title = title;
        this.lateDays = lateDays;
    }

    abstract double lateFee();

    void display() {
        System.out.println(title + " - Late Fee: " + lateFee());
    }
}

class Book extends Media {

    Book(String title, int lateDays) {
        super(title, lateDays);
    }

    double lateFee() {
        return lateDays * 2.0;
    }
}

class DVD extends Media {

    DVD(String title, int lateDays) {
        super(title, lateDays);
    }

    double lateFee() {
        return lateDays * 5.0;
    }
}

class Magazine extends Media {

    Magazine(String title, int lateDays) {
        super(title, lateDays);
    }

    double lateFee() {
        return lateDays * 1.0;
    }
}

public class Main43 {
    public static void main(String[] args) {

        Media[] media = {
            new Book("Java Programming", 3),
            new DVD("Avengers", 2),
            new Magazine("Tech Today", 5),
            new Book("Data Structures", 4)
        };

        double totalFee = 0;

        for (Media m : media) {
            m.display();
            totalFee += m.lateFee();
        }

        System.out.println("-------------------");
        System.out.println("Total Late Fee: " + totalFee);
    }
}