/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ca.sevenless.pixelcrops.display.GraphicsHolder.DuplicateGraphicNameException;
import ca.sevenless.pixelcrops.util.ImageLoader;

/**
 * @author Sevenless
 *
 */
public class GraphicsLoader {

	
	/**
	 * Loads accepted image formated files stored directly inside the res folder, and ignores any other files/directories inside.
	 * @param resourceDirectory
	 * @param graphicsHolder
	 * @throws IOException 
	 */
	public void loadGraphics(String resourceDirectory, GraphicsHolder graphicsHolder) throws IOException{
		
		File[] possibleGraphics = new File(resourceDirectory).listFiles();
		for (File thisFile : possibleGraphics){
			String name = thisFile.getName();
			if (isAcceptedImageFormat(name)){
				BufferedImage newGraphic;
				try {
					newGraphic = ImageLoader.createImageIO(name);
					graphicsHolder.addGraphic(newGraphic, name);
				} catch (DuplicateGraphicNameException e) {
					//Until this can read multiple layers of folders, this should never happen
					e.printStackTrace();
				}
			}
		}
	}
	
	private boolean isAcceptedImageFormat(String fileName){
		if (fileName.contains(".png") || fileName.contains(".jpg"))
			return true;
		
		return false;
	}
	
}
