package ca.sevenless.pixelcrops.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

public class ImageLoader {
	
	/**Loads image from source files and returns it as a BufferedImage
	 * 
	 * @param imageFileName Name of the image file found within the source files
	 * @return Returns the desired image as a BufferedImage
	 * @throws IOException Thrown if there is an error reading the image from memory
	 */
    public static BufferedImage createImageIO(String imageFileName) throws IOException {
        
    	URL imageURL;
    	BufferedImage image = null;
    	
    	try{
    	
	    	String path = imageFileName;
	    	imageURL = ClassLoader.getSystemResource(path);
	        image = ImageIO.read(imageURL);
			
    	} catch (IOException e){
			throw e;
		}
		
        return image;
    }   
    
}
