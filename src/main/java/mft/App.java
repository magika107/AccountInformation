package mft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import mft.model.entity.Person;
import mft.model.repository.PersonDA;

import java.sql.SQLException;

@Log4j2
public class App extends Application {
    public void saveAdmin() throws SQLException {
        Person person =new Person();


//                         Person
//                        .name("Mehdi")
//                        .family("Ghasemi")
//                        .username("Mehdi")
//                        .password("mehdi132")



        PersonDA personDataA = new PersonDA();
        personDataA.save(person);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Scene scene = new Scene(
                    FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))
            );
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            log.info("App Started");
            primaryStage.show();
        } catch (Exception e) {
            log.error("Start App: " + e.getMessage(), e);
        }
    }

}
