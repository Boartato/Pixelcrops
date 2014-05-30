/**
 * 
 */
package ca.sevenless.pixelcrops.display.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ca.sevenless.pixelcrops.display.util.GraphicsHolder.DuplicateGraphicNameException;
import ca.sevenless.pixelcrops.util.ImageLoader;

/**
 * @author Sevenless
 *
 */
public class GraphicsLoader {

	ArrayList<String> acceptedImageFormats;
	
	/**
	 * Initiates a graphics loader with an initially empty acceptedImageFormatList
	 */
	public GraphicsLoader(){
		acceptedImageFormats = new ArrayList<String>();
	}
	
	/**
	 * Takes any iterable collection filled with Strings and adds them to the acceptedImageFormats list
	 * @param acceptedImageFormats
	 */
	public GraphicsLoader(Iterable<String> acceptedImageFormats){
		this.acceptedImageFormats = new ArrayList<String>();
		
		for (String acceptedFormat : acceptedImageFormats)
			this.acceptedImageFormats.add(acceptedFormat);
		
	}
	
	/**
	 * Adds a single accepted image format to the list
	 * @param acceptedFormat
	 */
	public void addImageFormat(String acceptedFormat){
		acceptedImageFormats.add(acceptedFormat);
	}
	
	/**
	 * Loads accepted image formated files stored directly inside the res folder, and ignores any other files/directories inside.
	 * @param topLevelDirectory
	 * @param graphicsHolder
	 * @throws IOException 
	 * @throws NoAcceptedImageFormatsException 
	 */
	public void loadGraphics(String topLevelDirectory, GraphicsHolder graphicsHolder) throws IOException, NoAcceptedImageFormatsException{
		
		File[] possibleGraphics = new File(topLevelDirectory).listFiles();
		for (File thisFile : possibleGraphics){
			String name = thisFile.getName();
			if (isAcceptedImageFormat(name)){
				BufferedImage newGraphic;
				try {
					newGraphic = ImageLoader.createImageFromFile(thisFile);
					graphicsHolder.addGraphic(newGraphic, name);
				} catch (DuplicateGraphicNameException e) {
					//Until this can read multiple layers of folders, this should never happen
					e.printStackTrace();
				}
			}
			else if (thisFile.isDirectory()){
				loadGraphics(thisFile, graphicsHolder);
			}
		}
	}
	
	/**
	 * @param thisFile
	 * @param graphicsHolder
	 * @throws IOException 
	 * @throws NoAcceptedImageFormatsException 
	 */
	private void loadGraphics(File graphicsDirectory, GraphicsHolder graphicsHolder) throws IOException, NoAcceptedImageFormatsException {
		

		File[] possibleGraphics = graphicsDirectory.listFiles();
		for (File thisFile : possibleGraphics){
			String name = thisFile.getName();
			if (isAcceptedImageFormat(name)){
				BufferedImage newGraphic;
				try {
					newGraphic = ImageLoader.createImageFromFile(thisFile);
					graphicsHolder.addGraphic(newGraphic, name);
				} catch (DuplicateGraphicNameException e) {
					//Until this can read multiple layers of folders, this should never happen
					e.printStackTrace();
				}
			}
			else if (thisFile.isDirectory()){
				loadGraphics(thisFile, graphicsHolder);
			}
		}
		
	}

	private boolean isAcceptedImageFormat(String fileName) throws NoAcceptedImageFormatsException{
		if (acceptedImageFormats.size() == 0)
			throw new NoAcceptedImageFormatsException("No image formats have been added as acceptable in this GraphicsLoader object");
		
		for (String thisFormat : acceptedImageFormats)
			if (fileName.endsWith(thisFormat))
				return true;
		
		return false;
	}
	
	/**
	 * Gives explicit name to an exception thrown by this class alone
	 * @author Sevenless
	 *
	 */
	public class NoAcceptedImageFormatsException extends Exception{
		private static final long serialVersionUID = 1L;

		public NoAcceptedImageFormatsException(String message) {
			super(message);
		}
	}
	
}
