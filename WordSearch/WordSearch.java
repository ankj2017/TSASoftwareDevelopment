package puzzles.wordsearch;

import java.util.*;

public class WordSearch {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final double probBackwards = 0.5d;
    private static final double probDiagonal = 0.3d;

    private int size, numWords;
    private String theme;
    private String[] wordBank;

    private char[][] grid;
    private String[] words;


    public WordSearch(int size, int numWords, String theme, String[] wordBank) {
//		super();
        this.size = size;
        this.numWords = (wordBank.length > numWords) ? numWords : wordBank.length;
        this.theme = theme;
        this.wordBank = wordBank;

        grid = new char[size][size];
        words = new String[this.numWords];

        wordBank = shuffleArray(wordBank);
        words = Arrays.copyOfRange(wordBank, 0, this.numWords);

        //Finds position, orientation, and direction for every word
        //And then places it in the grid

        for(String word : words) {
            System.out.println(word);
            int wordLength = word.length();

            int startX, startY, orientation;
            boolean dir;

            while(true) {

                //To break out of loop
                boolean canBreak = false;

                //Breaks when random unoccupied position is found
                while(true) {
                    startX = (int)(Math.random()*size);
                    startY = (int)(Math.random()*size);
                    System.out.println("startX " + startX);
                    System.out.println("startY " + startY);
                    System.out.println(!isOccupied(startX, startY, grid));
                    if(!isOccupied(startX, startY, grid)) {
                        break;
                    }
                }

                //Selects random orientation and direction
                //If word can be placed, loop is broken
                //Else, a new orientation and direction are chosen

                //Variables to break out of loop once every possible orientation has been tried
                int numOrientations = 0;
                boolean[][] triedOrientations = new boolean[2][4];

                while(true) {
                    orientation = selectOrientation();
                    dir = selectDir();
                    System.out.println(word);
                    System.out.println("startX " + startX);
                    System.out.println("startY " + startY);
                    System.out.println("orientation " + orientation);
                    System.out.println("dir " + dir);
                    int d = (dir) ? 1 : -1;
                    switch(orientation) {
                        case 0:
                            for(int i = 0; i < wordLength; i++) {
                                if(isOccupied(startX + d*i, startY, grid)) {
                                    canBreak = false;
                                    break;
                                }
                                canBreak = true;
                            }
                            if(!triedOrientations[dir ? 0 : 1][orientation]) {
                                numOrientations++;
                            }
                            break;
                        case 1:
                            for(int i = 0; i < wordLength; i++) {
                                if(isOccupied(startX, startY + d*i, grid)) {
                                    canBreak = false;
                                    break;
                                }
                                canBreak = true;
                            }
                            if(!triedOrientations[dir ? 0 : 1][orientation]) {
                                numOrientations++;
                            }
                            break;
                        case 2:
                            if(dir) {
                                for(int i = 0; i < wordLength; i++) {
                                    if(isOccupied(startX + d*i, startY + d*i, grid)) {
                                        canBreak = false;
                                        break;
                                    }
                                    canBreak = true;
                                }
                            } else {
                                for(int i = 0; i < wordLength; i++) {
                                    if(isOccupied(startX - d*i, startY - d*i, grid)) {
                                        canBreak = false;
                                        break;
                                    }
                                    canBreak = true;
                                }
                            }
                            if(!triedOrientations[dir ? 0 : 1][orientation]) {
                                numOrientations++;
                            }
                            break;
                        case 3:
                            if(dir) {
                                for(int i = 0; i < wordLength; i++) {
                                    if(isOccupied(startX + d*i, startY - d*i, grid)) {
                                        canBreak = false;
                                        break;
                                    }
                                    canBreak = true;
                                }
                            } else {
                                for(int i = 0; i < wordLength; i++) {
                                    if(isOccupied(startX - d*i, startY + d*i, grid)) {
                                        canBreak = false;
                                        break;
                                    }
                                    canBreak = true;
                                }
                            }
                            if(!triedOrientations[dir ? 0 : 1][orientation]) {
                                numOrientations++;
                            }
                            break;
                        default:

                    }
                    if(canBreak || numOrientations >= 8) {
                        break;
                    }
                }

                if(canBreak) {
                    break;
                }
            }

            //Inserts word into grid

            int d_ = (dir) ? 1 : -1;
            switch(orientation) {
                case 0:
                    for(int i = 0; i < wordLength; i++) {
                        grid[startX+d_*i][startY] = word.charAt(i);
                    }
                    break;
                case 1:
                    for(int i = 0; i < wordLength; i++) {
                        grid[startX][startY + d_*i] = word.charAt(i);
                    }
                    break;
                case 2:
                    if(dir) {
                        for(int i = 0; i < wordLength; i++) {
                            grid[startX + d_*i][startY + d_*i] = word.charAt(i);
                        }
                    } else {
                        for(int i = 0; i < wordLength; i++) {
                            grid[startX - d_*i][startY - d_*i] = word.charAt(i);
                        }
                    }
                    break;
                case 3:
                    if(dir) {
                        for(int i = 0; i < wordLength; i++) {
                            grid[startX + d_*i][startY - d_*i] = word.charAt(i);
                        }
                    } else {
                        for(int i = 0; i < wordLength; i++) {
                            grid[startX - d_*i][startY + d_*i] = word.charAt(i);
                        }
                    }
                    break;
                default:
            }

        }

        //Populated remaining cells with random letters
        grid = populateGrid(grid);
    }

    //Prints maze to the console

    public void display() {
        System.out.println("-----------WORD SEARCH GENERATED-----------");
        System.out.println("");
        System.out.println(theme.toUpperCase());
        System.out.println("");
        for(char[] row : grid) {
            for(char c : row) {
                System.out.print(c + " ");
            }
            System.out.print("\n");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("-----------WORD BANK-----------");
        for(String word : words) {
            System.out.println(word);
        }
    }

    //Selects a random orientation for a word
    //0 = horizontal, 1 = vertical, 2 = top-left to bottom right (forwards), 3 = bottom-left to top-right (forwards)

    private static int selectOrientation() {
        int or = (Math.random() < probDiagonal) ? (Math.random() < 0.5d ? 2 : 3)
                : (Math.random() < 0.5d ? 0 : 1);
        return or;
    }

    //Selects a random direction for a word
    //true = forwards, false = backwards

    private static boolean selectDir() {
        boolean dir = (Math.random() > probBackwards) ? true : false;
        return dir;
    }

    //Determines whether a cell is occupied

    private static boolean isOccupied(int x, int y, char[][] grid_) {
        if(x >= grid_.length || x < 0 || y >= grid_.length || y < 0) {
            return true;
        }
        else {
            return (grid_[x][y] != '\0') ? true : false;
        }
    }

    //Fills unoccupied cells in the grid with random characters

    private static char[][] populateGrid(char[][] grid_){
        char[][] grid_copy = grid_;
        for(int i = 0; i < grid_.length; i++) {
            for(int  j = 0; j < grid_[i].length; j++) {
                if(grid_[i][j] == '\0') {
                    grid_copy[i][j] = randChar();
                }
            }
        }
        return grid_copy;
    }

    //Returns a random permutation of an array

    private static <T> T[] shuffleArray(T[] arr) {
        ArrayList<T> list = new ArrayList<>();
        for(T elem : arr) {
            list.add(elem);
        }
        Collections.shuffle(list);
        T[] arr2 = list.toArray(arr);
        return arr2;
    }

    //Returns a random alphabetic character

    private static char randChar() {
        Random r = new Random();

        char c = alphabet.charAt(r.nextInt(alphabet.length()));
        return c;
    }

    //Accessor methods

    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }


    public int getNumWords() {
        return numWords;
    }


    public void setNumWords(int numWords) {
        this.numWords = numWords;
    }


    public String getTheme() {
        return theme;
    }


    public void setTheme(String theme) {
        this.theme = theme;
    }


    public String[] getWordBank() {
        return wordBank;
    }


    public void setWordBank(String[] wordBank) {
        this.wordBank = wordBank;
    }

    public char[][] getGrid(){
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }
}