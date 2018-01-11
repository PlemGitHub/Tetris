package Visual;

import java.awt.Component;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Entities.MyButton;
import Mech.ButtonsLogic;
import Values.Constants;

public class Screen implements Constants{
	private JFrame fr;
	private JPanel mp;
	private ButtonsLogic btnLgc;
	public GameField gf;
	public NextFigurePanel nextFigurePanel;
	public MySlider mySlider;
	public MyButton newGameBtn;
	public MyButton startGameBtn;
	public MyButton pauseGameBtn;
	public MyButton stopGameBtn;
	public MyLabel lvlLbl;
	public MyLabel scoreLbl;
	
	
	public Screen() {
		Constants.initGameFieldCoords();
		Constants.initNextFigurePanelCoords();
		mp = new JPanel();
		mp.setBackground(SCREEN_BACKGROUND_COLOR);
		mp.setLayout(null);
		
		createButtons();
		createMySlider();
		createLabels();
		
		fr = new JFrame("Tetris");
		fr.setContentPane(mp);
		fr.setExtendedState(Frame.MAXIMIZED_BOTH);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createButtons() {
		btnLgc = new ButtonsLogic(this);
//		createFocusButton();
		createNewGameButton();
		createStartGameButton();
		createPauseGameButton();
		createStopGameButton();
	}

	private void createNewGameButton() {
		newGameBtn = new MyButton(btnLgc, "New Game", GAME_FIELD_Y);
		mp.add(newGameBtn);
	}

	private void createStartGameButton() {
		startGameBtn = new MyButton(btnLgc, "Start", GAME_FIELD_Y);
		startGameBtn.setVisible(false);
		mp.add(startGameBtn);
	}

	private void createPauseGameButton() {
		pauseGameBtn = new MyButton(btnLgc, "Pause", GAME_FIELD_Y);
		pauseGameBtn.setVisible(false);
		mp.add(pauseGameBtn);
	}

	private void createStopGameButton() {
		stopGameBtn = new MyButton(btnLgc, "Stop", GAME_FIELD_Y+BUTTON_HEIGHT+TILE_SIZE);
		stopGameBtn.setVisible(false);
		mp.add(stopGameBtn);
	}
	
	private void createMySlider() {
		mySlider = new MySlider(1, MAX_LEVEL, this);
		mySlider.setBounds(GAME_FIELD_X, GAME_FIELD_Y-TILE_SIZE, GAME_FIELD_WIDTH, TILE_SIZE);
		mp.add(mySlider);
	}

	private void createLabels() {
		lvlLbl = new MyLabel("Уровень 1", BUTTONS_X);
		mp.add(lvlLbl);
		
		scoreLbl = new MyLabel("0", BUTTONS_X+TILE_SIZE+BUTTON_WIDTH);
		mp.add(scoreLbl);
	}

	public void createGameField() {
		for (Component c : mp.getComponents()) {
			if (c instanceof GameField
				|| c instanceof NextFigurePanel) {
				mp.remove(c);
			}
		}
		gf = new GameField(this);
		fr.addKeyListener(gf);
		mp.add(gf);
		
		nextFigurePanel = new NextFigurePanel();
		mp.add(nextFigurePanel);
		
		mp.repaint();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Screen scr = new Screen();
	}
}