import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * FreightSystem - contains static instances of commands that needs to be 
 * passed onto the API and the FreightSpace controller.
 * 
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
=======
import java.util.Scanner;

/**FreightSystem: Main system class file.
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	chrisjoy
 * @date	28/04/17
>>>>>>> 40b20acef9692c0bb8c45368dbb0927549c31dcc
 */
public class FreightSystem {

	/**
<<<<<<< HEAD
	 * Freight system constructor - creates an object of the heuristic strategy to be used
	 * by the main class. The setup allows developer to implement different strategies and 
	 * launch multiple instances of the system.
	 */
	public FreightSystem() {
		heuristic = new FreightHeuristic();
		australian_company = new FreightSystemAPI(heuristic);	
		// create another instance with another strategy
		// chinese_company = new FreightSystemAPI(new ChineseFreightHurisitic);
		FS = new FreightSpace(); // create new FrieghtSpace for map and town & job representation
	}

	/**
	 * Set the unloading cost and the town in the freight space and australian_company instance.
	 * 
	 * @param cost integer amount of the cost need to unload
	 * @param town the name of the town
	 */
	private static void Unloading(int cost, String town) {
		australian_company.addTown(town, cost);
		FS.addTownToMap(cost, town);
	}

	/**
	 * Set the Cost between two towns in the freight space and australian_company instance.
	 * 
	 * @param cost the cost between neighboring towns
	 * @param town1 name of first town
	 * @param town2 name of second town
	 */
	private static void Cost(int cost, String town1, String town2) {
		australian_company.addRoute(town1, town2, cost);
		FS.connectTowns(cost, town1, town2);
	}

	/**
	 * Add a job to the job list in the freight space and australian_company instance.
	 * 
	 * @param town1 job start
	 * @param town2 job end
	 */
	private static void Job(String town1, String town2) {
		australian_company.addRequiredRoute(town1, town2);
		FS.addJob(town1, town2);
	}

	/**
	 * Process commands from a file
	 * 
	 * @param file scanner object containing the file contents
	 */
	private static void process(Scanner file) {
		while (file.hasNext()) {
			// parse the inputStream string (remove spaces )
			ArrayList<String> query = parseCMD(file.nextLine()); /*contains parsed string elements*/			
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

		FreightState goal = new FreightState();
		goal.setRoutes(australian_company.getJoblist());
		FreightState start = new FreightState();
		start.setCurrTown("Sydney");
		FreightState results = australian_company.search(start, goal);

		System.out.println(australian_company.getTotalNodes() + " nodes expanded");		

		// Re-map and calculate route costs""
		if (results.getRoutes().size() != 0) {
			for (FreightRoute r: results.getRoutes())
				FS.addCostToFinal(r);
		}
		System.out.println("cost = " + FS.getFinalcost());

		// Re-map and print routes
		if (results.getRoutes().size() == 0) {
			System.out.println("No solution.");
		}
		else {
			for (FreightRoute r: results.getRoutes())
				FS.printRoutes(r);
		}

		// 		Uncomment for debugging output
		//		FS.printDebug();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner file = null;
		new FreightSystem(); // create new FrieghtSystem
		try {
			file = new Scanner(new FileReader(args[0]));
			process(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			if (file != null) { file.close(); }
		}
	}

	/**
	 * Parse commands from a given string
	 * (Remove comments and unnecessary spaces)
	 * 
	 * @param cmd the string command
	 * @return array of commands that can be processed 
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

		return postQuery;
	}

	private static FreightSpace FS = null;
	private static Heuristic heuristic = null;
	private static FreightSystemAPI australian_company = null;
=======
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
	
>>>>>>> 40b20acef9692c0bb8c45368dbb0927549c31dcc
}
