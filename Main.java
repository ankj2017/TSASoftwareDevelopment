
public class Main {
	
	static final long startTime = System.nanoTime();
	
	static String theme = "Colors";
	static String[] wordBank = {"red", "blue", "orange", "purple", "black", "white", "gray",
			"green", "yellow", "pink"};
	static int size = 16;
	static int numWords = 20;
	
	public static void main(String[] args) {
		WordSearch wordSearch = new WordSearch(size, numWords, theme, wordBank);
		wordSearch.display();
		
		final long duration = System.nanoTime() - startTime;
		final double duration_s = duration/Math.pow(10,  9);
		System.out.println("\n\n");
		System.out.println("-------------COMPLETED INADFASFASDFASDFASDF " + duration_s + "s---------------");
	}

}
