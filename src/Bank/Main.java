package Bank;

import Bank.count.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connectivity connectivity = new Connectivity();
        //connectivity.createTableForPerson();
        //connectivity.createTableForAccounts();
        //connectivity.createTableForCards();
        //connectivity.createTableForChecks();
        //connectivity.addPerson(new Person("someone", "else", "123"));
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
