package Visual;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import Values.Constants;

public class MyLabel extends JLabel implements Constants{
	private static final long serialVersionUID = 1L;
	
	public MyLabel(String str, int x) {
		setText(str);
		setOpaque(true);
		setBounds(x, GAME_FIELD_Y-TILE_SIZE, BUTTON_WIDTH, TILE_SIZE);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
	
	public int getIntegerValue(){
		return Integer.valueOf(getText());
	}
		public void setIntegerValue(int newValue){
			setText(Integer.toString(newValue));
		}
}
