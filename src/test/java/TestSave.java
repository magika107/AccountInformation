import mft.model.entity.Person;
import mft.model.repository.PersonDA;

import java.time.LocalDate;

public class TestSave {
    public static void main(String[] args) {
        try (PersonDA personDA = new PersonDA()) {
            Person person = Person.builder()
                  //.id(27)
                    .name("hamed")
                    .family("moradi")
                    .username("shakeri")
                    .password("saber")
                    .birthDate(LocalDate.of(2019, 1, 10))
                    .phoneNumber("09121235555")
                    .build();

            // personDA.delete(45);
            //for(Person personDA1:personDA.findAll())
            System.out.println(person);

            //System.out.println(personDA.findByNameAndFamily("ali", "alipour"));


            personDA.save(person);

            //System.out.println(personDA.findById(4));
            System.out.println("👤 اطلاعات شخص ذخیره شد.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

