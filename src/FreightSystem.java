import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**FreightSystem: Main system class file.
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	chrisjoy
 * @date	28/04/17
 */
public class FreightSystem {

	/**
	 * Main function, entry to the system.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileReader(args[0]));
			// create a new API handler
			FreightSystemAPI handler = new FreightSystemAPI();
			// create a new system, containing the API handles and input stream
			FreightSystemCMD FS = new FreightSystemCMD(handler, inputStream);
			// process the input stream using the API handles
			FS.process();
		}
		catch (FileNotFoundException e) {
			System.out.println(e);
		}
		finally {
			if (inputStream != null)
				inputStream.close();
		}
	}
	
}
