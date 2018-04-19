package program;
import javafx.scene.Scene;
import java.util.Random;
public class Sudoku {
    private int[][] board;

    public Sudoku() {
        board = new int[10][10];
    }

    private void populateBoard()
    {
        board[1][1] = 1;
        board[1][2] = 3;
        board[1][3] = 5;
        board[1][4] = 6;
        board[1][5] = 2;
        board[1][6] = 8;
        board[1][7] = 7;
        board[1][8] = 9;
        board[1][9] = 4;
        board[2][1] = 4;
        board[2][2] = 6;
        board[2][3] = 9;
        board[2][4] = 7;
        board[2][5] = 5;
        board[2][6] = 1;
        board[2][7] = 3;
        board[2][8] = 2;
        board[2][9] = 8;
        board[3][1] = 7;
        board[3][2] = 8;
        board[3][3] = 2;
        board[3][4] = 4;
        board[3][5] = 3;
        board[3][6] = 9;
        board[3][7] = 1;
        board[3][8] = 6;
        board[3][9] = 5;
        board[4][1] = 2;
        board[4][2] = 9;
        board[4][3] = 6;
        board[4][4] = 8;
        board[4][5] = 1;
        board[4][6] = 4;
        board[4][7] = 5;
        board[4][8] = 3;
        board[4][9] = 7;
        board[5][1] = 3;
        board[5][2] = 1;

        board[5][3] = 8;
        board[5][4] = 5;
        board[5][5] = 9;
        board[5][6] = 7;
        board[5][7] = 6;
        board[5][8] = 4;
        board[5][9] = 2;
        board[6][1] = 5;
        board[6][2] = 4;
        board[6][3] = 7;
        board[6][4] = 3;
        board[6][5] = 6;
        board[6][6] = 2;
        board[6][7] = 8;
        board[6][8] = 1;
        board[6][9] = 9;
        board[7][1] = 8;
        board[7][2] = 7;
        board[7][3] = 1;
        board[7][4] = 2;
        board[7][5] = 4;
        board[7][6] = 3;
        board[7][7] = 9;
        board[7][8] = 5;
        board[7][9] = 6;
        board[8][1] = 9;
        board[8][2] = 5;
        board[8][3] = 4;
        board[8][4] = 1;
        board[8][5] = 7;
        board[8][6] = 6;
        board[8][7] = 2;
        board[8][8] = 8;
        board[8][9] = 3;
        board[9][1] = 6;
        board[9][2] = 2;
        board[9][3] = 3;
        board[9][4] = 9;
        board[9][5] = 8;
        board[9][6] = 5;
        board[9][7] = 4;
        board[9][8] = 7;
        board[9][9] = 1;
        shufflePuzzle();
        for (int k = 0; k < 10; k++) {
            board[0][k] = k;
        }
        for (int p = 1; p < 10; p++) {
            board[p][0] = p;
        }
    }
    private void shufflePuzzle()
    {
        int[][] puzzle2 = board;
        int count = 0;
        Random rand = new Random();
        int randomChoice = 0;
        int randomRow = 0;
        while (count < 20) {
            randomChoice = rand.nextInt(4) + 1;
            switch (randomChoice) {
                case 1:
                    puzzle2 = shuffleBigRow(puzzle2);
                case 2:
                    puzzle2 = shuffleBigColumn(puzzle2);
                case 3:
                    randomRow = rand.nextInt(9) + 1;
                    puzzle2 = shuffleSmallRow(puzzle2, randomRow);
                case 4:
                    randomRow = rand.nextInt(9) + 1;
                    puzzle2 = shuffleSmallColumn(puzzle2, randomRow);
            }
            count++;
        }

        board = puzzle2;
    }
    private int[][] shuffleBigRow(int[][] puzzle)
    {
        int[][] puzzleShuffle = new int[10][10];
        Random rand = new Random();
        int random = rand.nextInt(2);
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 10; j++) {
                puzzleShuffle[i + 3 * (random + 1)][j] = puzzle[i][j];
            }
        }

        for (int i = 1; i < 4; i++)

        {
            for (int j = 1; j < 10; j++) {
                puzzleShuffle[i][j] = puzzle[i + 3 * (random + 1)][j];
            }
        }

        if (random == 0) {
            for (int i = 7; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    puzzleShuffle[i][j] = puzzle[i][j];
                }
            }
        } else {
            for (int i = 4; i < 7; i++) {
                for (int j = 1; j < 10; j++) {
                    puzzleShuffle[i][j] = puzzle[i][j];
                }
            }
        }
        return puzzleShuffle;
    }
    private int[][] shuffleBigColumn(int[][] puzzle)
    {
        int[][] puzzleShuffle = new int[10][10];
        Random rand = new Random();
        int random = rand.nextInt(2);
        for (int i=1;i<10;i++)
        {
            for (int j=1;j<4;j++)
            {

                puzzleShuffle[i][j+3*(random+1)]=puzzle[i][j];
            }
        }
        for (int i=1;i<10;i++)
        {
            for (int j=1;j<4;j++)
            {
                puzzleShuffle[i][j]=puzzle[i][j+3*(random+1)];
            }
        }
        if(random==0)
        {
            for (int i=1;i<10;i++)
            {
                for (int j=7;j<10;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }
            }
        }
        else
        {
            for (int i=1;i<10;i++)
            {
                for (int j=4;j<7;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }
            }
        }
        return puzzleShuffle;
    }
    private int[][] shuffleSmallColumn(int[][] puzzle, int bigColumn)
    {
        int[][] puzzleShuffle = new int[10][10];
        Random rand = new Random();
        int random = rand.nextInt(2);
        if (bigColumn==1||bigColumn==4||bigColumn==7)
        {
            for (int i=1;i<10;i++)
            {
                puzzleShuffle[bigColumn+random+1][i]=puzzle[bigColumn][i];
            }
            for (int i=1;i<10;i++)
            {
                puzzleShuffle[bigColumn][i]=puzzle[bigColumn+random+1][i];
            }
            if (random==1)
            {
                for (int i=1;i<10;i++)
                {

                    puzzleShuffle[bigColumn+1][i]=puzzle[bigColumn+1][i];
                }
            }
            else
            {
                for (int i=1;i<10;i++)
                {

                    puzzleShuffle[bigColumn+2][i]=puzzle[bigColumn+2][i];
                }
            }

        }
        else if (bigColumn==2||bigColumn==5||bigColumn==8)
        {
            if (random==1)
            {
                for (int i=1;i<10;i++)
                {

                    puzzleShuffle[bigColumn+random][i]=puzzle[bigColumn][i];
                }
                for (int i=1;i<10;i++)
                {

                    puzzleShuffle[bigColumn][i]=puzzle[bigColumn+random][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigColumn-1][i]=puzzle[bigColumn-
                            1][i];
                }
            }
            else
            {
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigColumn-1][i]=puzzle[bigColumn][i];

                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigColumn][i]=puzzle[bigColumn-1][i];
                }
                for (int i=1;i<10;i++)
                {

                    puzzleShuffle[bigColumn+1][i]=puzzle[bigColumn+1][i];
                }
            }
        }
        else
        {
            if (random==1)
            {
                for (int i=1;i<10;i++)
                {

                    puzzleShuffle[bigColumn-
                            random][i]=puzzle[bigColumn][i];

                }
                for (int i=1;i<10;i++)
                {

                    puzzleShuffle[bigColumn][i]=puzzle[bigColumn-
                            random][i];

                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigColumn-2][i]=puzzle[bigColumn-
                            2][i];
                }
            }
            else
            {
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigColumn-2][i]=puzzle[bigColumn][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigColumn][i]=puzzle[bigColumn-2][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigColumn-1][i]=puzzle[bigColumn-
                            1][i];
                }
            }
        }
        if (bigColumn==1||bigColumn==2||bigColumn==3)

        {
            for (int i=4;i<10;i++)
            {
                for (int j=1;j<10;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }

            }
        }
        else if (bigColumn==4||bigColumn==5||bigColumn==6)
        {
            for (int i=1;i<4;i++)
            {
                for (int j=1;j<10;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }
            }
            for (int i=7;i<10;i++)
            {
                for (int j=1;j<10;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }
            }
        }
        else
        {
            for (int i=1;i<7;i++)
            {
                for (int j=1;j<10;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }
            }
        }

        return puzzleShuffle;
    }
    private int[][] shuffleSmallRow(int[][] puzzle, int bigRow)
    {
        int[][] puzzleShuffle = new int[10][10];
        Random rand = new Random();
        int random = rand.nextInt(2);
        if (bigRow==1||bigRow==4||bigRow==7)
        {
            for (int i=1;i<10;i++)
            {
                puzzleShuffle[bigRow+random+1][i]=puzzle[bigRow][i];
            }
            for (int i=1;i<10;i++)
            {
                puzzleShuffle[bigRow][i]=puzzle[bigRow+random+1][i];
            }
            if (random==1)
            {
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow+1][i]=puzzle[bigRow+1][i];
                }
            }
            else
            {
                for (int i=1;i<10;i++)

                {
                    puzzleShuffle[bigRow+2][i]=puzzle[bigRow+2][i];
                }
            }

        }
        else if (bigRow==2||bigRow==5||bigRow==8)
        {

            if (random==1)
            {
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow+random][i]=puzzle[bigRow][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow][i]=puzzle[bigRow+random][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow-1][i]=puzzle[bigRow-1][i];
                }
            }
            else
            {
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow-1][i]=puzzle[bigRow][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow][i]=puzzle[bigRow-1][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow+1][i]=puzzle[bigRow+1][i];
                }
            }
        }
        else
        {
            if (random==1)
            {
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow-random][i]=puzzle[bigRow][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow][i]=puzzle[bigRow-random][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow-2][i]=puzzle[bigRow-2][i];
                }
            }

            else
            {
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow-2][i]=puzzle[bigRow][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow][i]=puzzle[bigRow-2][i];
                }
                for (int i=1;i<10;i++)
                {
                    puzzleShuffle[bigRow-1][i]=puzzle[bigRow-1][i];
                }
            }
        }
        if (bigRow==1||bigRow==2||bigRow==3)
        {
            for (int i=4;i<10;i++)
            {
                for (int j=1;j<10;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }
            }
        }
        else if (bigRow==4||bigRow==5||bigRow==6)
        {
            for (int i=1;i<4;i++)
            {
                for (int j=1;j<10;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }
            }
            for (int i=7;i<10;i++)
            {
                for (int j=1;j<10;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }
            }
        }
        else
        {
            for (int i=1;i<7;i++)
            {
                for (int j=1;j<10;j++)
                {
                    puzzleShuffle[i][j]=puzzle[i][j];
                }
            }
        }

        return puzzleShuffle;
    }
}
