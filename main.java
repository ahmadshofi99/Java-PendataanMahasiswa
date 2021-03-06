package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //menghubungkan main ke sample.fxml
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Mengatur tittle pada window
        primaryStage.setTitle("PENDATAAN MAHASISWA");

        //mengatur size window
        primaryStage.setScene(new Scene(root, 500, 500));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
