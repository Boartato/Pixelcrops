/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.io.IOException;

import ca.sevenless.pixelcrops.display.util.GraphicsHolder;
import ca.sevenless.pixelcrops.display.util.GraphicsLoader;
import ca.sevenless.pixelcrops.display.util.GraphicsHolder.GraphicNotFoundException;

/**
 * @author Sevenless
 *
 */
public class GraphicsLoaderTest extends GraphicsLoader{

	
	public static void main(String[] args){
		
		GraphicsHolder required1 = new GraphicsHolder();
		GraphicsLoader test = new GraphicsLoader();
		test.addImageFormat(".png");
		try {
			test.loadGraphics("res", required1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoAcceptedImageFormatsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			required1.find("yellowcircle.png");
			System.out.println("circle graphic properly loaded");
			required1.find("FuckingCasual.png");
			System.out.println("Badasspug found");
		} catch (GraphicNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
