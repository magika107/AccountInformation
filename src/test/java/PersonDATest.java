import mft.model.entity.Person;
import mft.model.repository.PersonDA;


public class PersonDATest {
    public static void main(String[] args) throws Exception {
        Person person =
                Person
                        .builder()
                        .name()
                        .build();

        try(PersonDA personDA = new PersonDA()){
            personDA.save(person);
        }
    }
}
