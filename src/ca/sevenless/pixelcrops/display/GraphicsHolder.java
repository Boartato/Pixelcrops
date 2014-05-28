/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Acts as a gateway object to all the graphics currently being held in memory for this instance of Pixelcrops
 * and provides simplistic searching ability via names of the currently held graphics.
 * 
 * @author Sevenless
 *
 */
public class GraphicsHolder {
	
	ArrayList<BufferedImage> graphics;
	ArrayList<String> names;

	public GraphicsHolder(){
		this.names = new ArrayList<String>();
		this.graphics = new ArrayList<BufferedImage>();
	}
	
	/**
	 * Adds the graphic and its name to the appropriate lists.
	 * @param graphic
	 * @param name
	 * @throws DuplicateGraphicNameException 
	 */
	public void addGraphic(BufferedImage graphic, String name) throws DuplicateGraphicNameException{
		
		try {
			find(name);
		} catch (GraphicNotFoundException e) {
			//If the graphic being added isn't a duplicate name, continue as normal.
			graphics.add(graphic);
			names.add(name);
			return;
		}
		throw new DuplicateGraphicNameException("That graphic name is already in use");
	}
	
	/**
	 * Searches the name list until the name is found and returns the graphic at the cooresponding location in
	 * the graphic list. GraphicNotFoundException is thrown if no such name exists on the list.
	 * @param graphicName
	 * @return
	 * @throws GraphicNotFoundException
	 */
	public BufferedImage find (String graphicName) throws GraphicNotFoundException{
		if (names.size() > 0){
			int location = 0;
			for (String currentName : names){
				if (currentName.equals(graphicName))
					return graphics.get(location);
				location +=1;
			}
		}
		throw new GraphicNotFoundException("Unable to find requested graphic");
	}

	/**
	 * Inner class for giving DuplicateGraphicNameException a specific catch warning
	 * @author Sevenless
	 *
	 */
	public class DuplicateGraphicNameException extends Exception{
		private static final long serialVersionUID = 1L;

		public DuplicateGraphicNameException (String message){
			super(message);
		}
	}
	
	/**
	 * Inner class for giving GraphicNotFoundExceptions a specific catch warning
	 * @author Sevenless
	 *
	 */
	public class GraphicNotFoundException extends Exception{
		private static final long serialVersionUID = 1L;

		public GraphicNotFoundException(String message) {
			super(message);
		}
		
	}
	
}
