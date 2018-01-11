package Mech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Visual.Screen;

public class ButtonsLogic implements ActionListener {
	private Screen scr;
	private boolean gamePaused;
	
	public ButtonsLogic(Screen scr) {
		this.scr = scr;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(scr.newGameBtn)) {
			scr.createGameField();
			scr.newGameBtn.setVisible(false);
			scr.startGameBtn.setVisible(true);
			scr.scoreLbl.setIntegerValue(0);
		}
		
		if (e.getSource().equals(scr.startGameBtn)) {
			scr.startGameBtn.setVisible(false);
			scr.pauseGameBtn.setVisible(true);
			scr.stopGameBtn.setVisible(true);
			scr.mySlider.setEnabled(false);
			scr.gf.startNewTimer();
		}
		
		if (e.getSource().equals(scr.stopGameBtn)) {
			scr.newGameBtn.setVisible(true);
			scr.pauseGameBtn.setVisible(false);
				gamePaused = false;
				scr.pauseGameBtn.setText("Pause");				
			scr.stopGameBtn.setVisible(false);
			scr.mySlider.setEnabled(true);
			scr.gf.stopTimer();
		}
		
		if (e.getSource().equals(scr.pauseGameBtn)) {
			if (gamePaused) {
				scr.pauseGameBtn.setText("Pause");
				scr.gf.resumeTimer();
				gamePaused = false;
			}else{
				scr.pauseGameBtn.setText("Continue");
				scr.gf.stopTimer();
				gamePaused = true;
			}
		}
	}

}
