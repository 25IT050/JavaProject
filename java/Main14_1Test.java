import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Main14_1Test {

    @Test
    public void testAddingAccountIncreasesCount() {
        BankService service = new BankService();

        assertEquals(0, service.getCount());

        service.addAccount("Bhargavi", 1000);

        assertEquals(1, service.getCount());
    }

    @Test
    public void testAddingTwoAccounts() {
        BankService service = new BankService();

        service.addAccount("Bhargavi", 1000);
        service.addAccount("Rutvi", 2000);

        assertEquals(2, service.getCount());
    }

    @Test
    public void testInvalidActionThrowsException() {
        BankService service = new BankService();

        assertThrows(IllegalArgumentException.class,
                () -> service.performAction(5));
    }
}
