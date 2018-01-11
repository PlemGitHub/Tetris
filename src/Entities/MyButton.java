package Entities;

import javax.swing.JButton;

import Mech.ButtonsLogic;
import Values.Constants;

public class MyButton extends JButton implements Constants{
	private static final long serialVersionUID = 1L;

	public MyButton(ButtonsLogic btnLgc, String text, int y) {
		setText(text);
		setBounds(BUTTONS_X, y, BUTTON_WIDTH, BUTTON_HEIGHT);
		setFocusable(false);
		addActionListener(btnLgc);
	}
}
