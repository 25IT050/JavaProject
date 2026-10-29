class fileresource implements AutoCloseable {

    public void close() {
        System.out.println("resource closed");
    }
}

public class Main83 {
    public static void main(String[] args) {

        try (fileresource f1 = new fileresource()) {
            System.out.println("resource is being used");
            throw new Exception("original error");
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        finally{
            System.out.println("successfull");
        }
    }
}