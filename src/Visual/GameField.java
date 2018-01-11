package Visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import Mech.GameTimer;
import Values.Constants;
import Values.KeyDictionary;

public class GameField extends JPanel implements Constants, KeyListener{
	private static final long serialVersionUID = 1L;
	
	private GameTimer gameTimer;
	public Screen scr;
	public int tiles[][] = new int[GAMEFIELD_TILES_HORIZONTAL][TILES_VERTICAL];
	

	public GameField(Screen scr) {
		this.scr = scr;
		setBounds(GAME_FIELD_X, GAME_FIELD_Y, GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
	}
	public GameField() {
	}

	@Override
	public void paint(Graphics g) {
		setBackground(GAMEFIELD_BACKGROUND_COLOR);
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, GAME_FIELD_WIDTH-1, GAME_FIELD_HEIGHT-1);
		drawAllTiles(g);
	}

	private void drawAllTiles(Graphics g) {
		for (int i = 0; i < GAMEFIELD_TILES_HORIZONTAL; i++)
			for (int j = 0; j < TILES_VERTICAL; j++) {
				if (tiles[i][j] == 1) {
					drawTile(i, j, g);
				}
			}
	}

	public void addTileToTiles(int i, int j){
		tiles[i][j] = 1;
	}
		public void removeTileFromTiles(int i, int j){
			tiles[i][j] = 0;
		}

	private void drawTile(int i, int j, Graphics g) {
		Point p = GAME_FIELD_COORDS[i][j];
		g.setColor(Color.BLACK);
		g.fillRect(p.x+1, p.y+1, TILE_SIZE-1, TILE_SIZE-1);
	}
	
	public void startNewTimer(){
		tiles = new int[GAMEFIELD_TILES_HORIZONTAL][TILES_VERTICAL];
		repaint(GAME_FIELD_RECTANGLE_INSIDE);
		gameTimer = new GameTimer(START_DELAY/scr.mySlider.valueToInteger(), null, this);
		gameTimer.addActionListener(gameTimer);
		gameTimer.start();
	}
		public void resumeTimer(){
			gameTimer.start();
		}
			public void stopTimer(){
				gameTimer.stop();
			}

	@Override
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		char c = KeyDictionary.translate(e.getKeyChar());
		switch (c) {
			case 'd': 
				gameTimer.moveFigureRight();
				break;
			case 'a':
				gameTimer.moveFigureLeft();
				break;
			case 's':
				gameTimer.dropFigureDown();
				break;
			case 'w':
				gameTimer.rotateFigure();
				break;
		}
	}
}
