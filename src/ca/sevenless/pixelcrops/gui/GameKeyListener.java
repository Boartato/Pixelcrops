/**
 * 
 */
package ca.sevenless.pixelcrops.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ca.sevenless.pixelcrops.init.GameInitialization;


/**
 * @author Sevenless
 *
 */
public class GameKeyListener implements KeyListener{

	GameInitialization main;
	
	public GameKeyListener(GameInitialization _main){
		main = _main;
	}
	
	private void endProgram(){
		main.endProgram();
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()){
		case KeyEvent.VK_ESCAPE:
			endProgram();
			break;	
	
		}
	
		System.out.println(e.getKeyChar());
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
