package src.snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

	public class elKens {
	
	private static ArrayList<Tile> kens;
	private Color bCol = new Color (hexCol);
	private static int hexCol = 0xffffff;
	
	public elKens(int length) {
		hexCol = 0xffffff;
		int x = Board.midCol(0).x;
		int y = Board.midCol(18).y;
		kens = new ArrayList<Tile>();
		kens.add(new Tile(x, y, new Color(0xffffff), 2));
		for (int i = 0; i < length; i++) {
			x -= Tile.tSize();
			kens.add(new Tile(x, y, changeColor(), 2));
		}
	}
	
	public Color changeColor() {  //make the color the new shade
		hexCol -= 0x11;
		bCol = new Color(hexCol, false);
		return bCol;
	}
	public static void updateBodyDir(Board board) {
		for (int i = 0; i < board.bSize(); i++) {
			for (int j = 1; j < kens.size(); j++) {
				if (kens.get(j).getX() == board.tile(i).getX() && kens.get(j).getY() == board.tile(i).getY()) {
					kens.get(j).setDirection(board.tile(i).getDir());
				}
			}
		}
	}
	
	public void moveKens (Board board, boolean[] KeysPressed) {
		updateBodyDir(board);
			for (Tile t : kens)		{
				t.moveTile(KeysPressed);
			}
	}
	
	public void growKens(Board board) {
		int x = (int) kens.get(kens.size()-1).getX();
		int y = (int) kens.get(kens.size()-1).getY();
		int dir = kens.get(kens.size()-1).getDir();
		if (dir == 0) 	x += Tile.tSize();
		if (dir == 1) 	y += Tile.tSize();
		if (dir == 2) 	x -= Tile.tSize();
		if (dir == 3)	y -= Tile.tSize();
		Tile t = new Tile(x, y, changeColor(), dir);
		kens.add(t);	
	}
		
	public Tile getKensPart(int i) {
			return kens.get(i);
	}
		
	public int kensLen() {
		return kens.size();
	}
		
	public void drawKens(Graphics2D win) {
		for (Tile t :kens) {
			t.draw(win, true); 
		}
	}
}


