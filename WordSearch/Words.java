package puzzles.wordsearch;

public class Words {

    public static final Words colors = new Words("Colors",
            new String[] {"red", "blue", "orange", "purple", "black", "white", "gray",
                    "green", "yellow", "pink"});

    private String theme;
    private String[] wordBank;

    public Words(String theme, String[] wordBank) {
        this.theme = theme;
        this.wordBank = wordBank;
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

}