@FunctionalInterface
interface Notifier {
    void send(String message);
}

// Marker interface: contains no methods
interface Urgent {
}

public class NotificationDemo {
    public static void main(String[] args) {

        Notifier email = (message) -> {
            System.out.println("Email: " + message);
        };

        Notifier sms = (message) -> {
            System.out.println("SMS: " + message);
        };

        // Email sender is marked as urgent
        Notifier urgentEmail = (Notifier & Urgent) (message) -> {
            System.out.println("Urgent Email: " + message);
        };

        Notifier[] senders = {email, sms, urgentEmail};

        String message = "Lab session starts at 10 AM";

        System.out.println("Broadcasting message:");

        for (int i = 0; i < senders.length; i++) {
            senders[i].send(message);
        }

        System.out.println("\nSending urgent notifications again:");

        for (int i = 0; i < senders.length; i++) {

            if (senders[i] instanceof Urgent) {
                senders[i].send(message);
                senders[i].send(message);
            }
        }
    }
}
