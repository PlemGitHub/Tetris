package Values;

import java.awt.event.KeyEvent;

public interface KeyDictionary {
	
	public static char translate(char c){
		char cc=' ';
		
			switch (c) {
	
			case 'a':
			case 'A':
			case 'ф':
			case 'Ф':
			case KeyEvent.VK_LEFT:
				cc = 'a';
				break;
				
			case 'd':
			case 'D':
			case 'в':
			case 'В':
			case KeyEvent.VK_RIGHT:
				cc = 'd';
				break;
				
			case 's':
			case 'S':
			case 'ы':
			case 'Ы':
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_SPACE:
				cc = 's';
				break;	
				
			case 'w':
			case 'W':
			case 'ц':
			case 'Ц':
			case KeyEvent.VK_UP:
			case KeyEvent.VK_ENTER:
				cc = 'w';
				break;			
			}
			
		return cc;
	}
}
