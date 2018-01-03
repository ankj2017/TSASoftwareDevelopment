package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WordPane extends StackPane {
	
	private char text;
	private Text gui_text;
	private int x, y;
	public boolean keepsColor;
	
	public WordPane(char text, int x, int y) {
		super();
		this.text = text;
		this.x = x;
		this.y = y;
		gui_text = new Text(String.valueOf(text));
		gui_text.setFont(Font.font("Verdana", 24));
		StackPane.setAlignment(gui_text, Pos.CENTER);
		keepsColor = false;
		this.getChildren().add(gui_text);
	}
	
	public String getText() {
		return String.valueOf(text);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
