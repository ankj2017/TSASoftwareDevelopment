package program;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuControler {
    public static Stage sudokuStage = new Stage();
    @FXML
    void startSudoku(ActionEvent event) {

        Scene sudokuScene = null;
        try
        {
            sudokuScene = new Scene(FXMLLoader.load(MenuControler.class.getResource("Sudoku.fxml")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        sudokuStage.setScene(sudokuScene);
        sudokuStage.show();
        Main.MenuStage.hide();
    }

    @FXML
    void startWordSearch(ActionEvent event) {

    }

}
