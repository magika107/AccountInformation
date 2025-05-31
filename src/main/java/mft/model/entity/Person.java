package mft.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@SuperBuilder
public class Person implements Serializable {

    private int id;
    private String name;
    private String family;
    private LocalDate birthDate;
    private String phoneNumber;
    private String username;
    private String password;
}
