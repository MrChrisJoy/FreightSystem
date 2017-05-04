import java.util.ArrayList;
import java.util.Scanner;

/** FreightSystemCMD - links the API with the parse and other things
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	chrisjoy
 * @date	07/04/17
 */
public class FreightSystemCMD {

	private void Unloading(int cost, String town) {
		System.out.println("Unloading " + cost + " " + town);
		FS.addTownToMap(cost, town);
	}

	private void Cost(int cost, String town1, String town2) {
		System.out.println("Cost " + cost + " " + town1 + " " + town2);
		FS.connectTowns(cost, town1, town2);
	}
	
	// add job between two towns
	private void Job(String town1, String town2) {
		System.out.println("Job " + town1 + " " + town2);
		FS.addJob(town1, town2);
	}
	
	
	/**
	 * FreightSystemCMD constructor used to instantiate the FS (FreightSystem) object.
	 * This object is later used to connect the API to the inputStream (input text file).
	 * 
	 * @param handler		handler name of the API instance
	 * @param inputStream	text file containing unique commands
	 */
	public FreightSystemCMD(FreightSystemAPI handler, Scanner inputStream) {
		this.FS = handler;
		this.inputStream = inputStream;
	}
	
	/**
	 * Connect the parser and then redirect commands to their appropriate API.
	 */
	public void process() {
		while (inputStream.hasNext()) {
			// parse the inputStream string (remove spaces )
			ArrayList<String> query = parseCMD(inputStream.nextLine()); /*contains parsed string elements*/
			if (query.size() > 0) {
				switch (query.get(0)) {
				case "Unloading": // Unloading <cost> <town>
					Unloading(Integer.parseInt(query.get(1)), query.get(2));
					break;
				case "Cost": // Cost <cost> <town1> <town2>
					Cost(Integer.parseInt(query.get(1)), query.get(2), query.get(3));
					break;
				case "Job": // Job <town1> <town2>
					Job(query.get(1), query.get(2));
					break;
				default:
					throw new IllegalArgumentException("Invalid command: " + query.get(0));
				}
			}
		}
		
		FS.printDebug();
		FS.solve();
	}
	

	/**
	 * paseCMD: command line parser for FS (helper function)
	 * 			only takes in one string line at a time
	 * @param cmd command line string, containing appropriate arguments (VRS syntax)
	 * @return parsed string <List> excluding comments and extra spacing characters
	 */
	private static ArrayList<String> parseCMD(String cmd) {
		// split command on hash, then split on space
		String[] preQuery = cmd.split("#");
		preQuery = preQuery[0].split("\\s+");
		ArrayList<String> postQuery = new ArrayList<String>();

		// loop through each argument and check validity
		for (int i = 0; i != preQuery.length; i++) {
			if (preQuery.length == 0 | preQuery[0].equals("")) {
				break;
			} else {
				postQuery.add(preQuery[i]);		
			}
		}
		// this takes into account the dynamic nature of the 
		// "request" & "change" command.
		if (postQuery.size() == 10) {
			postQuery.add("0");
			postQuery.add("Unspecified");
		}

		return postQuery;
	}
	
	// Object FrieghtSystem will be used
	// is a API instance, allowing developers to
	// expand and change it's implementation
	private FreightSystemAPI FS;
	private Scanner inputStream; // text file containing commands
}
