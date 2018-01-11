package Values;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

public interface Constants {
	
	public int START_DELAY = 900;
	public int MAX_LEVEL = 10;
	public int MIN_DELAY = START_DELAY/MAX_LEVEL;

	public int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public int WINDOW_WIDTH_MID = WINDOW_WIDTH/2;
	public int WINDOW_HEIGHT_MID = WINDOW_HEIGHT/2;
	
	public int TILE_SIZE = WINDOW_HEIGHT/25;
	public int GAMEFIELD_TILES_HORIZONTAL = 10;
	public int GAMEFIELD_TILES_VERTICAL = 20;
	public int TILES_VERTICAL = GAMEFIELD_TILES_VERTICAL+4;
	public int[][] GAME_FIELD = new int[GAMEFIELD_TILES_HORIZONTAL][TILES_VERTICAL];
	
	public Point[][] GAME_FIELD_COORDS = new Point[GAMEFIELD_TILES_HORIZONTAL][TILES_VERTICAL];	
	public static void initGameFieldCoords(){
		for (int i = 0; i < GAMEFIELD_TILES_HORIZONTAL; i++) {
			for (int j = 0; j < TILES_VERTICAL; j++) {
				GAME_FIELD_COORDS[i][j] = new Point();
				GAME_FIELD_COORDS[i][j].x = i*TILE_SIZE;
				GAME_FIELD_COORDS[i][j].y = -4*TILE_SIZE+j*TILE_SIZE;
			}
		}
	}

	public Point[][] NEXT_FIGURE_PANEL_COORDS = new Point[4][4];	
	public static void initNextFigurePanelCoords(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				NEXT_FIGURE_PANEL_COORDS[i][j] = new Point();
				NEXT_FIGURE_PANEL_COORDS[i][j].x = i*TILE_SIZE;
				NEXT_FIGURE_PANEL_COORDS[i][j].y = j*TILE_SIZE;
			}
		}
	}

	public int FIGURE_SIZE = TILE_SIZE*4;
	
	public int GAME_FIELD_X = WINDOW_WIDTH_MID-TILE_SIZE*GAMEFIELD_TILES_HORIZONTAL/2;
	public int GAME_FIELD_Y = WINDOW_HEIGHT_MID-TILE_SIZE*GAMEFIELD_TILES_VERTICAL/2;
	public int GAME_FIELD_WIDTH = TILE_SIZE*GAMEFIELD_TILES_HORIZONTAL;
	public int GAME_FIELD_HEIGHT = TILE_SIZE*GAMEFIELD_TILES_VERTICAL;
	public Rectangle GAME_FIELD_RECTANGLE = new Rectangle(GAME_FIELD_X, GAME_FIELD_Y, GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
	public Rectangle GAME_FIELD_RECTANGLE_INSIDE = new Rectangle(0, 0, GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
	
	public int NEXT_FIGURE_PANEL_X = GAME_FIELD_X-TILE_SIZE-FIGURE_SIZE;
	public int NEXT_FIGURE_PANEL_Y = GAME_FIELD_Y;
	public int NEXT_FIGURE_PANEL_WIDTH = FIGURE_SIZE+1;
	public int NEXT_FIGURE_PANEL_HEIGHT = FIGURE_SIZE+1;
	
	public Color SCREEN_BACKGROUND_COLOR = Color.LIGHT_GRAY;
	public Color GAMEFIELD_BACKGROUND_COLOR = Color.WHITE;
	
	public int BUTTON_WIDTH = TILE_SIZE*4;
	public int BUTTON_HEIGHT = TILE_SIZE*2;	
	public int BUTTONS_X = WINDOW_WIDTH_MID+TILE_SIZE*GAMEFIELD_TILES_HORIZONTAL/2+TILE_SIZE;
}
