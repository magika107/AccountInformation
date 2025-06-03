package mft.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import mft.model.entity.Person;
import mft.model.repository.PersonDataAccess;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class PersonViewController implements Initializable {
    @FXML
    private TextField idTxt, nameTxt, familyTxt, phoneNumberTxt, userNameTxt, nameSearchTxt, familySearchTxt;
    @FXML
    private PasswordField passwordPas;

    @FXML
    private DatePicker birthDate;
    @FXML
    private Button saveBtn, editBtn, removeBtn, clearBtn;

    @FXML
    private TableView<Person> PersonTab;

    @FXML
    private TableColumn<Person, String> nameCol;
    @FXML
    private TableColumn<Person, String> familyCol;
    @FXML
    private TableColumn<Person, String> usernameCol;
    @FXML
    private TableColumn<Person, Integer> idCol;


    private List<Person> personList = new ArrayList<>();


    //private PersonDataAccess personDataAccess = new PersonDataAccess();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        saveBtn.setOnMouseClicked((event) -> {


            try(PersonDataAccess personDataAccess = new PersonDataAccess()) {
                Person person = Person
                        .builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .name(nameTxt.getText())
                        .family(familyTxt.getText())
                        .birthDate(birthDate.getValue())
                        .phoneNumber(phoneNumberTxt.getText())
                        .password(passwordPas.getText())
                        .username(userNameTxt.getText())
                        .build();
                personDataAccess.save(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person created successfully", ButtonType.OK);
                alert.show();
                resetForm();
                log.info("Person created successfully" + person);
//                personList.add(person);
            } catch (Exception e) {
                log.error("Person Creation Error : " + e.getMessage());
            }
        });

        editBtn.setOnAction((event) -> {
            try (PersonDataAccess personDataAccess = new PersonDataAccess()) {
                Person person =
                        Person
                                .builder()
                                .id(Integer.parseInt(idTxt.getText()))
                                .name(nameTxt.getText())
                                .family(familyTxt.getText())
                                .username(userNameTxt.getText())
                                .password(passwordPas.getText())
                                .birthDate(birthDate.getValue())
                                .build();
                personDataAccess.edit(person);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Edited Successfully", ButtonType.OK);
                alert.show();
                resetForm();
                log.info("Person Edit Successfully " + person);
            } catch (Exception e) {
                log.error("Person Editing " + e.getMessage());
            }
        });

        removeBtn.setOnAction((event) -> {
            try (PersonDataAccess personDataAccess = new PersonDataAccess()){
                int id = Integer.parseInt(idTxt.getText());
                personDataAccess.delete(id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Removed Successfully", ButtonType.OK);
                alert.show();
                resetForm();
                log.info("Person Deleted Successfully " + id);
            } catch (Exception e) {
                log.error("Person Deleting Error :" + e.getMessage());
            }
        });

        clearBtn.setOnAction((event) -> {
            resetForm();
        });

        nameSearchTxt.setOnKeyReleased((event) -> {
            try (PersonDataAccess personDataAccess = new PersonDataAccess()){
                showPersonOnTable(personDataAccess.findByNameAndFamily(nameSearchTxt.getText(), familySearchTxt.getText()));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });

        familySearchTxt.setOnKeyReleased((event) -> {
            try (PersonDataAccess personDataAccess = new PersonDataAccess()){
                showPersonOnTable(personDataAccess.findByNameAndFamily(nameSearchTxt.getText(), familySearchTxt.getText()));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
        EventHandler<Event> tableChangeEvent = (mouseEvent) -> {
            Person selectedPerson = PersonTab.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(selectedPerson.getId()));
            nameTxt.setText(selectedPerson.getName());
            familyTxt.setText(selectedPerson.getFamily());
            birthDate.setValue(selectedPerson.getBirthDate());
            phoneNumberTxt.setText(selectedPerson.getPhoneNumber());
            passwordPas.setText(selectedPerson.getPassword());
            userNameTxt.setText(selectedPerson.getUsername());
        };
        PersonTab.setOnMouseReleased(tableChangeEvent);
        PersonTab.setOnKeyReleased(tableChangeEvent);


        showPersonOnTable(personList);
        birthDate.setValue(LocalDate.now());
    }


    public void resetForm() {
        idTxt.setText(String.valueOf(personList.size() + 1));
        nameTxt.clear();
        familyTxt.clear();
        phoneNumberTxt.clear();
        passwordPas.clear();
        userNameTxt.clear();
        birthDate.setValue(LocalDate.now());

        try (PersonDataAccess personDataAccess = new PersonDataAccess()){
            showPersonOnTable(personList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void showPersonOnTable(List<Person> personList) {
        ObservableList<Person> personObservableList = FXCollections.observableArrayList(personList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyCol.setCellValueFactory(new PropertyValueFactory<>("family"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        PersonTab.setItems(personObservableList);
    }

}

