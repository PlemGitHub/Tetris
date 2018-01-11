package Entities.figure;

import java.util.Random;
import Values.Constants;
import Visual.GameField;

public class Figure implements IJLOSTZ, Constants{
	private GameField gf;
	public int currentFigureNumber;
	public int differentVariants;
	public int rotationVar;
	public int nextRotationVar;
	
	// top-left figure's indexes it tiles[][] of gamefield.
	public int i;
	public int j;
	// coordinates of figure on gamefield.
	public int x;
	public int y;
	
	public int[][] figure;

	public Figure(int i, int j, GameField gf) {
		this.gf = gf;
		this.i = i;
		this.j = j;
		chooseRandomFigure();
		updateGamefieldWithFiguresTiles();
		updateXY();
	}
	
	public Figure(int i, int j, GameField gf, Figure nextFigure) {
		this.gf = gf;
		this.i = i;
		this.j = j;
		retoreProperties(nextFigure);
		updateGamefieldWithFiguresTiles();
		updateXY();
	}
	
	private void retoreProperties(Figure nextFigure) {
		figure = nextFigure.figure;
		currentFigureNumber = nextFigure.currentFigureNumber;
		differentVariants = nextFigure.differentVariants;
		rotationVar = nextFigure.rotationVar;
		nextRotationVar = nextFigure.nextRotationVar;
		j = nextFigure.j;
	}

	private void updateXY() {
		x = i*TILE_SIZE;
		y = -4*TILE_SIZE+j*TILE_SIZE;
	}

	private void chooseRandomFigure() {
		Random rnd = new Random();
		currentFigureNumber = rnd.nextInt(7);
			differentVariants = FGR[currentFigureNumber].length;
				rotationVar = rnd.nextInt(differentVariants);
				defineNextRotationVar();
			figure = FGR[currentFigureNumber][rotationVar];
			moveFigureRightAboveGameField();
	}

	private void moveFigureRightAboveGameField() {
		for (int n = 3; n >= 0; n--) {
			for (int k = 0; k < 4; k++){
				if (figure[k][n] == 1){
					j += 3-n;
					return;
				}
			}
		}
	}

	public void moveFigureDown(){
		clearFiguresTilesFromGamefieldBeforeMove();
		j++;
		updateGamefieldWithFiguresTiles();
		updateXY();
	}
	
	public void moveFigureRight(){
		clearFiguresTilesFromGamefieldBeforeMove();
		i++;
		updateGamefieldWithFiguresTiles();
		updateXY();
	}
	
	public void moveFigureLeft(){
		clearFiguresTilesFromGamefieldBeforeMove();
		i--;
		updateGamefieldWithFiguresTiles();
		updateXY();
	}
	
	private void clearFiguresTilesFromGamefieldBeforeMove() {
		for (int k = 0; k < 4; k++) {
			for (int n = 0; n < 4; n++) {
				if (figure[k][n] == 1)
					gf.removeTileFromTiles(k+i, n+j);	
			}
		}
	}

	private void updateGamefieldWithFiguresTiles() {
		for (int k = 0; k < 4; k++) {
			for (int n = 0; n < 4; n++) {
				if (figure[k][n] == 1)
					gf.addTileToTiles(k+i, n+j);
			}
		}
	}

	public void defineNextRotationVar(){
		nextRotationVar = rotationVar+1;
		if (nextRotationVar == differentVariants) {
			nextRotationVar = 0;
		}
	}
	
	public void doRotate(){
		clearFiguresTilesFromGamefieldBeforeMove();
		figure = FGR[currentFigureNumber][nextRotationVar];
			rotationVar = nextRotationVar;
			defineNextRotationVar();
		updateGamefieldWithFiguresTiles();
	}
	
		public int[][] getNextRotationVarOfCurrentFigure(){
			return FGR[currentFigureNumber][nextRotationVar];
		}
}
