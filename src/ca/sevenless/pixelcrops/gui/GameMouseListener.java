/**
 * 
 */
package ca.sevenless.pixelcrops.gui;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.*;

import ca.sevenless.pixelcrops.gui.util.*;
import ca.sevenless.pixelcrops.init.GameInitialization;


/**
 * @author Sevenless
 *
 */
public class GameMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener{

	private GameInitialization main;
	private ButtonManager user;

	public GameMouseListener(GameInitialization _main, ButtonManager _user){
		main = _main;
		user = _user;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		user.clickEvent(arg0.getX(), arg0.getY());
		

		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}
