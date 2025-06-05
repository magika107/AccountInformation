import mft.model.entity.Person;
import mft.model.repository.PersonDA;

import java.time.LocalDate;

public class TestSave {
    public static void main(String[] args) {
        try (PersonDA personDA = new PersonDA()) {
//            Person person = Person.builder()
//                    .id(45)
//                    .name("mohsen")
//                    .family("akbari")
//                    .username("mohsen878")
//                    .password("Psijo")
//                    .birthDate(LocalDate.of(2000, 1, 1))
//                    .phoneNumber("09121239119")
//                    .build();

           // personDA.delete(45);
            //for(Person personDA1:personDA.findAll())


            System.out.println(personDA.findByNameAndFamily("mehdi", "ghasemi"));


            System.out.println("okkkkkk");

                //System.out.println(personDA.findById(4));
            System.out.println("👤 اطلاعات شخص ذخیره شد.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

