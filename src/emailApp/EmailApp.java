package emailApp;

public class EmailApp {

    public static void main(String[] args) {

        Email em1 = new Email();
        System.out.println(em1.getInfo());
        System.out.println("Generated Password: " + em1.getPassword());
    }
}
