/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.awt.image.BufferedImage;
import java.io.IOException;

import ca.sevenless.pixelcrops.display.util.GraphicsHolder;
import ca.sevenless.pixelcrops.util.ImageLoader;

/**
 * @author Sevenless
 *
 */
public class GraphicsHolderTest extends GraphicsHolder{

	static BufferedImage test;
	
	public static void main(String[] args){
		try {
			test = ImageLoader.createImageIO("yellowcircle.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GraphicsHolder testHolder = new GraphicsHolder();
		try {
			testHolder.addGraphic(test,"testName1");
			testHolder.addGraphic(test,"testName2");
			System.out.println("Graphics added successfully");
		} catch (DuplicateGraphicNameException e) {
			// TODO Auto-generated catch block
			System.out.println("Duplicate name");
		}
		
		try {
			testHolder.find("testName1");
			System.out.println("Find succeeded");
		} catch (GraphicNotFoundException e) {
			System.out.println("Find failed");
		}
		
		try {
			testHolder.addGraphic(test,"testName2");
		} catch (DuplicateGraphicNameException e) {
			// TODO Auto-generated catch block
			System.out.println("Duplicate name properly caught");
		}
		
		try {
			testHolder.find("testName3");
		} catch (GraphicNotFoundException e) {
			System.out.println("Find failed properly");
		}
		
	}
	
	
}
