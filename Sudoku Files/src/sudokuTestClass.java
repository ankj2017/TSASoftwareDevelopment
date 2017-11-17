import java.util.Scanner;

public class sudokuTestClass {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BuildBoard a = new BuildBoard();
		a.populateBoard();
		System.out.println("Choose how many squares you want filled in");
		int fillSquare = Integer.parseInt(in.nextLine());
				a.populateRandomNumbers(fillSquare);
				SudokuChecker check = new SudokuChecker(a.getBoard());
				Gui2 gui = new Gui2(a.getBoard());
				while (!check.checkFinish()) {
					gui.updateBoard(check);
				}
				
		System.out.println("Congratulatons, you have won the game!");
		System.out.println("Do you want to play another game: Y or N?");
			String response = in.nextLine();
		
		while(response.equals("Y")){
				a.populateBoard();
				
				System.out.println("Choose how many squares you want filled in");
				int squareNum = Integer.parseInt(in.nextLine());
						a.populateRandomNumbers(squareNum);
						 check = new SudokuChecker(a.getBoard());
						 gui = new Gui2(a.getBoard());
						while (!check.checkFinish()) {
							gui.updateBoard(check);
						}
				System.out.println("Congratulations, you have won the game!\nDo you want to play another game: Y or N?");
				response = in.nextLine();
			}
	}

}
