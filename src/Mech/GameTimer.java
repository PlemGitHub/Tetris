package Mech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import Entities.figure.Figure;
import Values.Constants;
import Visual.GameField;

public class GameTimer extends Timer implements ActionListener, Constants{
	private static final long serialVersionUID = 1L;
	
	private GameField gf;
	public Figure currentFigure;
	public Figure nextFigure;
	public int score;

	public GameTimer(int delay, ActionListener listener, GameField gf) {
		super(delay, listener);
		this.gf = gf;
		currentFigure = new Figure(4, 0, gf);
		generateAndDisplayNextFigure();
	}

	private void generateAndDisplayNextFigure() {
		nextFigure = new Figure(4, 0, new GameField());
		gf.scr.nextFigurePanel.updateFigure(nextFigure);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (canFigureMoveDown())
			moveFigureDown();
		else{
			if (isRunning()){
				checkAndDeleteCompletedLines();
				currentFigure = new Figure(4, 0, gf, nextFigure);
					generateAndDisplayNextFigure();
				moveFigureDown();
			}
		}
	}

	private void moveFigureDown(){
		currentFigure.moveFigureDown();
		gf.repaint(currentFigure.x, currentFigure.y-TILE_SIZE, FIGURE_SIZE, FIGURE_SIZE+TILE_SIZE);
	}		
	
		private boolean canFigureMoveDown(){
			for (int i = 0; i < 4; i++)
				for (int j = 3; j >= 0; j--) {
					if (currentFigure.figure[i][j] == 1){
						// figure moves down to the bottom.
						if (j+currentFigure.j == TILES_VERTICAL-1)
							return false;
						// figure moves down to another tile.
						if (gf.tiles[i+currentFigure.i][j+currentFigure.j+1] == 1){
							checkIfOverStack();
							return false;
						}
						j -= 4;
					}
				}
			return true;
		}
		
			private void checkIfOverStack() {
				for (int i = 0; i < GAMEFIELD_TILES_HORIZONTAL; i++)
					if (gf.tiles[i][3] == 1){
						doGameOver();
						return;
					}
			}
			
				private void doGameOver() {
					JOptionPane.showMessageDialog(gf, "Game over");
					gf.scr.stopGameBtn.doClick();
				}
				
	private void checkAndDeleteCompletedLines() {
		int deletedLines = 0;
		for (int j = TILES_VERTICAL-1; j >= 4; j--)
			for (int i = 0; i < GAMEFIELD_TILES_HORIZONTAL; i++) {
				if (gf.tiles[i][j] == 0){
					break;
				}
				if (i == GAMEFIELD_TILES_HORIZONTAL-1){
					deleteLine(j);
					j++;
					deletedLines++;
				}
			}
		increaseScore(deletedLines);
		
	}

		private void deleteLine(int j) {
			for (int n = j; n > 4; n--) {
				for (int i = 0; i < GAMEFIELD_TILES_HORIZONTAL; i++) {
					gf.tiles[i][n] = gf.tiles[i][n-1];
				}
			}
			gf.repaint(GAME_FIELD_RECTANGLE_INSIDE);
		}
		
			private void increaseScore(int q) {
				switch (q) {
					case 1: score += 100;
						break;
					case 2: score += 300;
						break;
					case 3: score += 700;
						break;
					case 4: score += 1500;
						break;
				}
				gf.scr.scoreLbl.setIntegerValue(score);
				setNewDelay();
			}

				private void setNewDelay() {
					int newLevel = score/10000 + 1;
					if (newLevel > 10)
						return;
					int newDelay = START_DELAY/(newLevel);
					setDelay(newDelay);
					gf.scr.mySlider.setValue(newLevel);
					gf.scr.lvlLbl.setText("Уровень "+gf.scr.mySlider.valueToString());
				}

	public void moveFigureRight(){
		if (canFigureMoveRight()){
			currentFigure.moveFigureRight();
			gf.repaint(currentFigure.x-TILE_SIZE, currentFigure.y, FIGURE_SIZE+TILE_SIZE, FIGURE_SIZE);
		}
	}
	
		private boolean canFigureMoveRight(){
			for (int j = 3; j >=0; j--)
				for (int i = 3; i >= 0; i--) {
					if (currentFigure.figure[i][j] == 1){
						// figure moves right to the gamefield's side.
						if (i+currentFigure.i == GAMEFIELD_TILES_HORIZONTAL-1)
							return false;
						// figure moves right to another tile.
						if (gf.tiles[i+currentFigure.i+1][j+currentFigure.j] == 1)
							return false;
						i -= 4;
					}
				}
			return true;
		}

	public void moveFigureLeft(){
		if (canFigureMoveLeft()){
			currentFigure.moveFigureLeft();
			gf.repaint(currentFigure.x, currentFigure.y, FIGURE_SIZE+TILE_SIZE, FIGURE_SIZE);
		}
	}
	
		private boolean canFigureMoveLeft(){
			for (int j = 3; j >=0; j--)
				for (int i = 0; i < 4; i++) {
					if (currentFigure.figure[i][j] == 1){
						// figure moves left to the gamefield's side.
						if (i+currentFigure.i == 0)
							return false;
						// figure moves left to another tile.
						if (gf.tiles[i+currentFigure.i-1][j+currentFigure.j] == 1)
							return false;
						i += 4;
					}
				}
			return true;
		}
	
	public void dropFigureDown(){
		int minDJ = TILES_VERTICAL;
		for (int k = 0; k < 3; k++) {
			for (int n = 3; n >= 0; n--) {
				if (currentFigure.figure[k][n] == 1){
					int i = currentFigure.i + k;
					int j = currentFigure.j + n;
					int topTile = findIndexOfTopTileInColumn(i, j);
					int dJ = topTile - j - 1;
					if (dJ < minDJ)
						minDJ = dJ;
					n -= 4;
				}
			}
		}
		
		for (int i = 0; i < minDJ; i++) {
			currentFigure.moveFigureDown();
			gf.repaint(currentFigure.x, currentFigure.y-TILE_SIZE, FIGURE_SIZE, FIGURE_SIZE+TILE_SIZE);
		}
	}

		private int findIndexOfTopTileInColumn(int i, int j) {
			for (int n = j+1; n < TILES_VERTICAL; n++) {
				if (n < TILES_VERTICAL
					&& gf.tiles[i][n] == 1)
					return n;
			}
			return TILES_VERTICAL;
		}
	
	public void rotateFigure(){
		if (canFigureRotate()) {
			currentFigure.doRotate();
			gf.repaint(currentFigure.x, currentFigure.y, FIGURE_SIZE, FIGURE_SIZE);
		}
	}
		/**
		 * Return false, if rotated figure collides with another gamefield's tile.<br>
		 * Checks all figure's tiles (4x4).<br>
		 * Figure's tile must be empty, rotated tile - not empty, gamefield's tile - empty.
		 * That means this tile does not have problem to rotate operation. <br>
		 * If no tile has problem - figure can rotates.
		 */
		public boolean canFigureRotate(){
			int[][] rotatedFigure = currentFigure.getNextRotationVarOfCurrentFigure();
			if (rotatedFigureOutOfGamefield(rotatedFigure))
				return false;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
						if (currentFigure.figure[i][j] == 0
							&& rotatedFigure[i][j] == 1
							&& gf.tiles[i+currentFigure.i][j+currentFigure.j] == 1)
								return false;
				}
			}
			return true;
		}

		/**
		 * Returns true if rotated figure appears out of gamefield, so no rotation atm are available.
		 */
		private boolean rotatedFigureOutOfGamefield(int[][] rotatedFigure) {
			for (int k = 0; k < 4; k++) {
				for (int n = 0; n < 4; n++) {
					int i = k+currentFigure.i;
					int j = n+currentFigure.j;
					if (rotatedFigure[k][n] == 1
						&& (i < 0
							|| i >= GAMEFIELD_TILES_HORIZONTAL
							|| j >= TILES_VERTICAL))
						return true;
				}
			}
			return false;
		}
}
