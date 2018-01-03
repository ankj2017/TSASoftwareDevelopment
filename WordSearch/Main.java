package gui;

import word_search.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.prism.paint.Color;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;

public class Main extends Application {

	private int numWords = 20;
	private int size = 16;
	
	private WordPane[][] wordPanes = new WordPane[size][size];
	
	private int currentX = 0;
	private int currentY = 0;
	
	private boolean clicked = false;
	private WordPane clickedPane;
	
	private String theme = Words.colors.getTheme();
	private String[] wordBank = Words.colors.getWordBank();
	
	private List<String> foundWords = new ArrayList<>();
	
	private BackgroundFill redFill = new BackgroundFill(Paint.valueOf("#FF2222"), CornerRadii.EMPTY, Insets.EMPTY);
	private BackgroundFill greenFill = new BackgroundFill(Paint.valueOf("#22FF22"), CornerRadii.EMPTY, Insets.EMPTY);
	private BackgroundFill blueFill = new BackgroundFill(Paint.valueOf("#3344FF"), CornerRadii.EMPTY, Insets.EMPTY);
	private BackgroundFill whiteFill = new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		VBox root = new VBox(40);
		root.setAlignment(Pos.CENTER);
		TilePane tilePane = new TilePane(Orientation.VERTICAL);
		tilePane.setPrefRows(size);
		tilePane.setPrefColumns(size);
//		tilePane.setHgap(5);
//		tilePane.setVgap(5);
		tilePane.setAlignment(Pos.CENTER);
		
		Text title = new Text("Word Search");
		title.setFont(Font.font("Verdana", 48));
		
		
		WordSearch wordSearch = new WordSearch(size, numWords, theme, wordBank);
		char[][] grid = wordSearch.getGrid();
		for(char[] char_ : grid) {
			for(char c : char_) {
				c = Character.toUpperCase(c);
			}
		}
//		StringBuilder sb = new StringBuilder();
		for(char[] char_ : grid) {
			for(char c : char_) {
				WordPane wordPane = new WordPane(c, currentX, currentY);
				wordPane.setOnMouseClicked((MouseEvent event) -> {
					if(clicked){
						clicked = false;
						clickedPane.keepsColor = false;
						int dx = wordPane.getX() - clickedPane.getX();
						int dy = wordPane.getY() - clickedPane.getY();
						System.out.println("dx " + dx);
						System.out.println("dy " + dy);
						if((dx==0 ^ dy==0) ^ (dx==dy) ^ (dx==-dy)) {
							String word = "";
							int b = (Math.abs(dx) > Math.abs(dy))? Math.abs(dx) : Math.abs(dy);
							System.out.println(b);
							WordPane[] activePanes = new WordPane[b+1];
							for(int i = b; i >= 0; i--) {
								WordPane activePane = wordPanes[wordPane.getX()-i*dx/b][wordPane.getY()-i*dy/b]; 
								activePanes[i] = activePane;
								System.out.println("Active X " + activePane.getX());
								System.out.println("Active Y " + activePane.getY());
								word += activePane.getText();
								System.out.println(word);
							}
							StringBuilder sb = new StringBuilder();
							sb.append(word.toLowerCase());
							if(Arrays.asList(wordBank).contains(sb.toString()) ||
									Arrays.asList(wordBank).contains(sb.reverse().toString())) {
								if(!foundWords.contains(word)) {
									foundWords.add(word);
								}
								if(foundWords.size() == wordBank.length) {
									foundWords.clear();
//									try {Thread.sleep(3000);} catch(InterruptedException exc) {}
//									VBox root2 = new VBox(40);
//									Text text_box2 = new Text("Congratulations! You solved the puzzle!");
//									text_box2.setFont(Font.font("Verdana", 64));
//									root2.getChildren().add(text_box2);
//									primaryStage.getScene().setRoot(root2);
//									try {Thread.sleep(5000);} catch(InterruptedException exc) {}
									restart(primaryStage);
								}
								for(WordPane thePane : activePanes) {
									thePane.setBackground(new Background(greenFill));
									thePane.keepsColor = true;
								}
//								if()
							} else {
								for(WordPane thePane : activePanes) {
//									System.out.println("Executed!");
									thePane.setBackground(new Background(redFill));
//									System.out.println(thePane.getBackground().getFills().get(0).equals(redFill));
									thePane.keepsColor = true;
								}
								Thread thread = new Thread() {
									public void run() {
										try{Thread.sleep(3000);} catch(InterruptedException exc) {}
										for(WordPane thePane : activePanes) { 
											thePane.setBackground(new Background(whiteFill));
											System.out.println(thePane.getBackground());
											thePane.keepsColor = false;
										}
									}
								};
								thread.start();
							}
						} else {
							clickedPane.setBackground(new Background(whiteFill));
						}
					} else {
						clicked = true;
						clickedPane = wordPane;
						wordPane.setBackground(new Background(blueFill));
						wordPane.keepsColor = true;
					}	
				});
				wordPane.setOnMouseEntered((MouseEvent event) -> {
					if(!wordPane.keepsColor) {	
						try {Thread.sleep(100);} catch(InterruptedException exc) {}
						wordPane.setBackground(new Background(greenFill));
					}	
				});
				wordPane.setOnMouseExited((MouseEvent event) -> {
					if(!wordPane.keepsColor) {
						wordPane.setBackground(new Background(whiteFill));
					}	
				});
				//pane.setOn
				wordPanes[currentX][currentY] = wordPane;
				tilePane.getChildren().add(wordPane);
				currentY++;
				currentY = currentY % size;
			}
			currentX++;
			currentX = currentX % size;
//			sb.append("\n");
		}
//		String str_grid = sb.toString();
//		Text word_search = new Text(str_grid);
		
		root.getChildren().addAll(title, tilePane);
		
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setTitle("Word Search");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {launch(args);}

	public void restart(Stage primaryStage) {
		VBox root = new VBox(40);
		root.setAlignment(Pos.CENTER);
		TilePane tilePane = new TilePane(Orientation.VERTICAL);
		tilePane.setPrefRows(size);
		tilePane.setPrefColumns(size);
//		tilePane.setHgap(5);
//		tilePane.setVgap(5);
		tilePane.setAlignment(Pos.CENTER);
		
		Text title = new Text("Word Search");
		title.setFont(Font.font("Verdana", 48));
		
		
		WordSearch wordSearch = new WordSearch(size, numWords, theme, wordBank);
		char[][] grid = wordSearch.getGrid();
//		StringBuilder sb = new StringBuilder();
		for(char[] char_ : grid) {
			for(char c : char_) {
				WordPane wordPane = new WordPane(c, currentX, currentY);
				wordPane.setOnMouseClicked((MouseEvent event) -> {
					if(clicked){
						clicked = false;
						clickedPane.keepsColor = false;
						int dx = wordPane.getX() - clickedPane.getX();
						int dy = wordPane.getY() - clickedPane.getY();
						System.out.println("dx " + dx);
						System.out.println("dy " + dy);
						if((dx==0 ^ dy==0) ^ (dx==dy) ^ (dx==-dy)) {
							String word = "";
							int b = (Math.abs(dx) > Math.abs(dy))? Math.abs(dx) : Math.abs(dy);
							System.out.println(b);
							WordPane[] activePanes = new WordPane[b+1];
							for(int i = b; i >= 0; i--) {
								WordPane activePane = wordPanes[wordPane.getX()-i*dx/b][wordPane.getY()-i*dy/b]; 
								activePanes[i] = activePane;
								System.out.println("Active X " + activePane.getX());
								System.out.println("Active Y " + activePane.getY());
								word += activePane.getText();
								System.out.println(word);
							}
							StringBuilder sb = new StringBuilder();
							sb.append(word.toLowerCase());
							if(Arrays.asList(wordBank).contains(sb.toString()) ||
									Arrays.asList(wordBank).contains(sb.reverse().toString())) {
								if(!foundWords.contains(word)) {
									foundWords.add(word);
								}
								if(foundWords.size() == wordBank.length) {
									foundWords.clear();
									try {Thread.sleep(3000);} catch(InterruptedException exc) {}
									VBox root2 = new VBox(40);
									Text text_box2 = new Text("Congratulations! You solved the puzzle!");
									text_box2.setFont(Font.font("Verdana", 64));
									root2.getChildren().add(text_box2);
									primaryStage.getScene().setRoot(root2);
									try {Thread.sleep(5000);} catch(InterruptedException exc) {}
									restart(primaryStage);
								}
								for(WordPane thePane : activePanes) {
									thePane.setBackground(new Background(greenFill));
									thePane.keepsColor = true;
								}
//								if()
							} else {
								for(WordPane thePane : activePanes) {
//									System.out.println("Executed!");
									thePane.setBackground(new Background(redFill));
//									System.out.println(thePane.getBackground().getFills().get(0).equals(redFill));
									thePane.keepsColor = true;
								}
								Thread thread = new Thread() {
									public void run() {
										try{Thread.sleep(3000);} catch(InterruptedException exc) {}
										for(WordPane thePane : activePanes) { 
											thePane.setBackground(new Background(whiteFill));
											System.out.println(thePane.getBackground());
											thePane.keepsColor = false;
										}
									}
								};
								thread.start();
							}
						}	
					} else {
						clicked = true;
						clickedPane = wordPane;
						wordPane.setBackground(new Background(blueFill));
						wordPane.keepsColor = true;
					}	
				});
				wordPane.setOnMouseEntered((MouseEvent event) -> {
					try {Thread.sleep(100);} catch(InterruptedException exc) {}
					wordPane.setBackground(new Background(greenFill));
				});
				wordPane.setOnMouseExited((MouseEvent event) -> {
					if(!wordPane.keepsColor)
						wordPane.setBackground(new Background(whiteFill));
				});
				//pane.setOn
				wordPanes[currentX][currentY] = wordPane;
				tilePane.getChildren().add(wordPane);
				currentY++;
				currentY = currentY % size;
			}
			currentX++;
			currentX = currentX % size;
//			sb.append("\n");
		}
//		String str_grid = sb.toString();
//		Text word_search = new Text(str_grid);
		
		root.getChildren().addAll(title, tilePane);
		
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setTitle("Word Search");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

//ISSUES:
//	1. Panes offset one row
//	2. Incorrect attempts not highlighting red
//	3. Puzzle not changing after fully solved
