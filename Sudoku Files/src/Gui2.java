import java.awt.*; // Uses AWT's Layout Managers
import java.awt.event.*; // Uses AWT's Event Handlers
import javax.swing.*; // Uses Swing's Container/Components
/**
* The Sudoku game.
* To solve the number puzzle, each row, each column, and each of the
* nine 3×3 sub-grids shall contain all of the digits from 1 to 9
*/
public class Gui2 extends JFrame {
/**
*
https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_Sudoku.html
*/
private static final long serialVersionUID = 1L;
// Name-constants for the game properties
public static final int GRID_SIZE = 10; // Size of the board
public static final int SUBGRID_SIZE = 3; // Size of the sub-grid
// Name-constants for UI control (sizes, colors and fonts)
public static final int CELL_SIZE = 60; // Cell width/height in pixels
public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
// Board width/height in pixels
public static final Color OPEN_CELL_BGCOLOR = Color.YELLOW;
public static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0); //RGB
public static final Color OPEN_CELL_TEXT_NO = Color.RED;
public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240);
// RGB
public static final Color CLOSED_CELL_TEXT = Color.BLACK;
public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD,
20);
// The game board composes of 9x9 JTextFields,
// each containing String "1" to "9", or empty String
private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
private int[][] puzzle =new int[10][10];
/**
* Constructor to setup the game and the UI Components
*/
public Gui2(int[][] board) {
Container cp = getContentPane();
cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE)); // 9x9 GridLayout
puzzle=board;
// Construct 9x9 JTextFields and add to the content-pane
for (int row = 0; row < GRID_SIZE; ++row) {
for (int col = 0; col < GRID_SIZE; ++col) {
tfCells[row][col] = new JTextField(); // Allocate element of array
cp.add(tfCells[row][col]); // ContentPane adds JTextField
if (puzzle[row][col]==0&&(row!=0&&col!=0)){
tfCells[row][col].setText(""); // set to empty string

tfCells[row][col].setEditable(true);
tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);}

else {
tfCells[row][col].setText(puzzle[row][col] + "");
tfCells[row][col].setEditable(false);
tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
}
// Beautify all the cells
tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
tfCells[row][col].setFont(FONT_NUMBERS);
}
}
// Set the size of the content-pane and pack all the components
// under this container.
cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
pack();
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window closing
setTitle("Sudoku");
setVisible(true);
}
public void constructBoard()
{
for (int row = 1; row < GRID_SIZE; ++row) {
for (int col = 1; col < GRID_SIZE; ++col) {

if (puzzle[row][col]==0){
tfCells[row][col].setText(""); // set to empty string
tfCells[row][col].setEditable(true);
tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);}

else {
tfCells[row][col].setText(puzzle[row][col] + "");
tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
}
}
}
}


public void updateBoard(SudokuChecker check)
{
for (int row=1;row<10;row++)
{
for (int col=1;col<10;col++)
{
String text=tfCells[row][col].getText();
if (!text.equals(""))
{
int num = Integer.parseInt(text);
if (check.checkRow(row,

num)&&check.checkColumn(col, num)&&check.checkBox(row, col, num))

puzzle[row][col]=num;
}

}
}
constructBoard();
}
/** The entry main() entry method */
public static void main(String[] args) {

}

}