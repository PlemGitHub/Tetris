package Visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

import Entities.figure.Figure;
import Values.Constants;

public class NextFigurePanel extends JPanel implements Constants{
	private static final long serialVersionUID = 1L;
	
	private int[][] figure;
	
	public NextFigurePanel() {
		setBounds(NEXT_FIGURE_PANEL_X, NEXT_FIGURE_PANEL_Y, NEXT_FIGURE_PANEL_WIDTH, NEXT_FIGURE_PANEL_HEIGHT);
		setBackground(GAMEFIELD_BACKGROUND_COLOR);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		if (figure != null)
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (figure[i][j] == 1){
						drawTile(i, j, Color.BLACK, g);
					}else
						drawTile(i, j, Color.WHITE, g);
				}
			}
	}
	
	private void drawTile(int i, int j, Color color, Graphics g) {
		Point p = NEXT_FIGURE_PANEL_COORDS[i][j];
		g.setColor(color);
		g.fillRect(p.x+1, p.y+1, TILE_SIZE-1, TILE_SIZE-1);
	}

	public void updateFigure(Figure newFigure){
		figure = newFigure.figure;
		repaint();
	}
}
