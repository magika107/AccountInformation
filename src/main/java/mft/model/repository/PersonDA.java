package mft.model.repository;

import lombok.extern.log4j.Log4j2;
import mft.controller.exception.UserNotFoundException;
import mft.model.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class PersonDA implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PersonDA() throws SQLException {
        log.info("Connect");
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    public void save(Person person) throws SQLException {
        preparedStatement = connection.prepareStatement("insert into PERSON values (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getUsername());
        preparedStatement.setString(5, person.getPassword());
        preparedStatement.setDate(6, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(7, person.getPhoneNumber());
        preparedStatement.execute();
        log.info("Person Saved " + person);
    }

    public void edit(Person person) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "update PERSON set  name=?, family=?, username=?, password=?, birth_date=?, phonenumber=? where id=?"
        );

        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setString(3, person.getUsername());
        preparedStatement.setString(4, person.getPassword());
        preparedStatement.setDate(5, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(6, person.getPhoneNumber());
        preparedStatement.setInt(7, person.getId());
        preparedStatement.execute();
        log.info("Person Edited " + person);
    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "delete from PERSON where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Person Removed " + id);
    }

    public List<Person> findAll() throws SQLException {
        List<Person> personList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from PERSON");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                            .phoneNumber(resultSet.getString("PHONENUMBER"))

                            .build();
            personList.add(person);
        }
        log.info("Find All Persons");
        return personList;
    }

    public Person findById(int id) throws SQLException, UserNotFoundException {
        Person person = null;
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from PERSON where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .password(resultSet.getString("PHONENUMBER"))

                    .build();
        }
        if (person == null) {
            log.error("No User With Id " + id);
            throw new UserNotFoundException("No User With Id " + id);
        }
        log.info("Find Person By Id " + id);
        return person;
    }

    public List<Person> findByNameAndFamily(String name, String family) throws SQLException, UserNotFoundException {
        List<Person> personList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from PERSON where name like ? and family like ?");
        preparedStatement.setString(1, name + "%");
        preparedStatement.setString(2, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                            .phoneNumber(resultSet.getString("PHONENUMBER"))

                            .build();
            personList.add(person);
        }
        if (personList.isEmpty()) {
            log.error("No User With name and family (starts with) : " + name + " " + family);
            throw new UserNotFoundException("No User With name and family (starts with) : " + name + " " + family);
        }
        log.info("Find All Persons By Name and Family " + name + " " + family);
        return personList;
    }

    public Person login(String username, String password) throws UserNotFoundException, SQLException {
        Person person = null;
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from PERSON where username=? and password=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .phoneNumber(resultSet.getString("PHONENUMBER"))

                    .build();
        }
        if (person == null) {
            log.error("Login - No User With username/password " + username + ":" + password);
            throw new UserNotFoundException("No User With username/password");
        }
        log.info("Login " + person);
        return person;
    }

    @Override
    public void close() throws Exception {
        log.info("Disconnect");
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}
