package program;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.imageio.ImageIO;

public class Main extends Application {
    public static Stage MenuStage;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Platform.setImplicitExit(false);
        MenuStage = primaryStage;
        AnchorPane Ap = FXMLLoader.load(Main.class.getResource("Menu.fxml"));
        primaryStage.setScene(new Scene(Ap));
        primaryStage.setTitle("TSA Project");
        primaryStage.getIcons().add(new Image("/program/AKlogo.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
