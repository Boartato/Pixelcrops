/**
 * 
 */
package ca.sevenless.pixelcrops.display;

import java.io.IOException;

import ca.sevenless.pixelcrops.display.GraphicsHolder.GraphicNotFoundException;

/**
 * @author Sevenless
 *
 */
public class GraphicsLoaderTest extends GraphicsLoader{

	
	public static void main(String[] args){
		
		GraphicsHolder required1 = new GraphicsHolder();
		GraphicsLoader test = new GraphicsLoader();
		try {
			test.loadGraphics("res", required1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			required1.find("yellowcircle.png");
			System.out.println("circle graphic properly loaded");
		} catch (GraphicNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
