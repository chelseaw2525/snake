package src.snake;

import java.awt.Graphics2D;

import src.utilities.GDV5;
import src.utilities.Sound;

public class Game extends GDV5 {

	private Board board = new Board();
	private Screens screen = new Screens();
	private leSnek snek;
	private elKens kens;
	private Tile head;
	private Tile deah;
	private Yums fruit;
	
	private boolean mirror = false;
	private boolean splash = true;
	private boolean gameOver = false;
	private boolean renew = false;
	private int count = 0;
	private int len = 0;
	public boolean groovin;

	public Game() {
		super();
		init();
	}

	public void init() {
		board.makeBoard();
		snek = new leSnek(2);
		kens = new elKens(2);
		head = snek.getSnekPart(0);
		deah = kens.getKensPart(0);
		fruit = new Yums(board, board.bSize(), snek, kens, false);
	}

	public static void main(String[] args) {
		Game snake = new Game();
		snake.start();
		Sound.loop("tetris_for_some_reason.wav");
	}

	@Override
	public void update() {	
		count++;
		if (mirror) {
			deah.mirrorHeadDirection(KeysPressed);
			deah.updateDirection(board);
		}
			head.setHeadDirection(KeysPressed);
			head.updateDirection(board);
		if (count % 15 == 0 && !splash) {
			snek.moveSnek(board, KeysPressed); 
			kens.moveKens(board, KeysPressed);
		}
		if ((fruit.eatFruit(head) || fruit.eatFruit(deah)) && !gameOver) {
			Sound.playSound("nom.wav");
			snek.growSnek(board);
			kens.growKens(board);
			fruit = new Yums (board, board.bSize(), snek, kens, true);
		}
	}
	
	@Override
	public void draw(Graphics2D draw) {
		if (splash) {
			screen.drawSplash(draw);
			splash = screen.changeScreen(KeysPressed);
			mirror = screen.mirror(KeysPressed, draw);
		}
		else {
			if (mirror) groovin = snek.alive(kens);
			else groovin = snek.alive();
			if (groovin && !gameOver) {
				screen.initGame(draw, board, snek, fruit, kens, mirror); 
				len = snek.snekLen();
			}
			else if (!renew && len < board.bSize()) {
				screen.drawGameOver(draw, len);
				gameOver = screen.changeScreen(KeysPressed);
				renew = gameOver;
			}
			else {
				screen.drawVictory(draw);
				gameOver = screen.changeScreen(KeysPressed);
			}
			if (renew){
				renew = false;
				init();
			}
		}
	}
}

