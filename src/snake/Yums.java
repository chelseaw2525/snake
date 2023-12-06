package src.snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import src.utilities.Images;

public class Yums {
	private static Color col = new Color(0xff4466);
	private ArrayList<Tile> fruit = new ArrayList<Tile>();
	Images image = new Images();

	public Yums(Board b, int t, leSnek snek, elKens kens, boolean start) {
		int[] coord = generate(b, t);
		for (int i = 0; i < snek.snekLen(); i++) {
			while (coord[0] == snek.getSnekPart(i).x && coord[1] == snek.getSnekPart(i).y && 
					coord[0] == kens.getKensPart(i).x && coord[1] == kens.getKensPart(i).y && start) {
				coord = generate(b, t);
			}
		}
		fruit.add(new Tile(coord[0], coord[1], col, 0));
	}
	
	public int[] generate(Board b, int t) {
		int n = (int)(Math.random() * t);
		int x = (int) (b.tile(n).getX());
		int y = (int) (b.tile(n).getY());
		int[] loc = {x, y};
		return loc;
	}
	
	public boolean eatFruit(Tile t) {
		for (int i = 0; i < fruit.size(); i++) {
			if (t.intersects(fruit.get(i))) {
				fruit.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void drawYums(Graphics2D win) {
		for (Tile t : fruit) {
			t.draw(win, true);
		}
	}
	
	public Tile getYum() {
		return fruit.get(0);
	}
}

