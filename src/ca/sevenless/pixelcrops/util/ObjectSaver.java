package ca.sevenless.pixelcrops.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Allows serializable objects to be saved and loaded through a given filepath. Throws appropriate errors
 * and handles resource cleanup.
 * 
 * @author Sevenless
 *
 */
public class ObjectSaver {
	
	/**
	 * Saves an object and all objects it holds direct or indirect pointers to into a bin file.
	 * @param savedObject
	 * @param filePath
	 * @throws IOException
	 */
	public static void saveObject(Object savedObject, String filePath) throws IOException {
		
		ObjectOutputStream outputStream = null;
		//Opens a new output stream, attempts to save the object
		try{
			outputStream = new ObjectOutputStream( new FileOutputStream(filePath));
			outputStream.writeObject(savedObject);
		}
		catch(IOException e){ //In case of error, cleans up resources and throws the generated exception
			outputStream.flush();
			outputStream.close();
			throw e; 
		}
		//Now that the try block has finished successfully, clean up the resources that were used.
		finally{
			try{
				if(outputStream != null){
					outputStream.flush();
					outputStream.close();
				}
			}
			catch(IOException e){
				throw e;
			}
		}
	}
	
	public static Object loadObject(String filePath) throws IOException, FileNotFoundException, ClassNotFoundException{
		
		//Initialized to null so they can be used in the catch blocks
		FileInputStream fileIn = null; 
		ObjectInputStream in = null; 
		
		//Attempt to read the object from memory and return it
		try{
			fileIn = new FileInputStream(filePath);
			in = new ObjectInputStream(fileIn);
			Object savedObject = in.readObject();
			fileIn.close();
			in.close();
			return savedObject;
		}
		/*
		 * In case of errors, throw the exception to the surrounding program to handle
		 */
		catch(FileNotFoundException e){//Incorrect filepath or missing filepath
			if (fileIn != null)
				fileIn.close();
			if (in != null)
				in.close();
			throw e;
		}
		catch(ClassNotFoundException e){//Class loaded from memory does not match any source code available to the project
			if (fileIn != null)
				fileIn.close();
			if (in != null)
				in.close();
			throw e;
		}
		catch(Exception e){
			if (fileIn != null)
				fileIn.close();
			if (in != null)
				in.close();
			throw e;
		}
	}

	public static void main(String[] args){
		Integer testObject = new Integer(10);
		
		try {
			saveObject(testObject, "testObject");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Object loadTest = loadObject("testObject");
			System.out.println(loadTest);
		} catch (ClassNotFoundException e){
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
