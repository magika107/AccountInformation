import mft.model.repository.PersonDA;

public class ConnectionProviderTest {
    public static void main(String[] args) throws Exception {
        try (PersonDA personDA = new PersonDA()) {
            System.out.println("dakhel");
        }
        System.out.println("kharej");
    }


}
