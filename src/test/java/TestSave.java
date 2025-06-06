import mft.model.entity.Person;
import mft.model.repository.PersonDA;

import java.time.LocalDate;

public class TestSave {
    public static void main(String[] args) {
        try (PersonDA personDA = new PersonDA()) {
            Person person = Person.builder()
                  //.id(67)
                    .name("karimali")
                    .family("safariiii")
                    .username("karim90")
                    .password("safari987")
                    .birthDate(LocalDate.of(2011, 1, 1))
                    .phoneNumber("09121230000")
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

