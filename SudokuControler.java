package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class SudokuControler {// Sudoku Setup code is in MenuControler

    @FXML
    private GridPane gridPane;

    @FXML
    void GoBackToMenu(ActionEvent event) {
        MenuControler.sudokuStage.hide();
        Main.MenuStage.show();
    }

}
