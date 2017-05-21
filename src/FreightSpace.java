import java.util.ArrayList;

/** 
 * Provides a higher level representation of the scenario that is being
 * described in the freight instructions file. Allows for better info lookups
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public class FreightSpace {

	/**
	 * FreightSpace Constructor - construct the map joblist and finalcost values
	 */
	public FreightSpace() {
		this.map = new Map();
		this.joblist = new ArrayList<Job>();
		this.setFinalcost(0);
	}

	/**
	 * Populate the map
	 * 
	 * @param cost the unloading cost of each town
	 * @param name the name of the town
	 */
	public void addTownToMap(int cost, String name) {
		map.addTown(cost, name);
	}
	
	/**
	 * 	Connect two town in both directions (undirected graph)
	 * 
	 * @param cost the travel cost between two towns
	 * @param town1 the name of the first town
	 * @param town2 the name of the second town
	 */
	public void connectTowns(int cost, String town1, String town2) {
		map.getTown(town1).addNeighbour(cost, map.getTown(town2));
		// we need to link both towns again, since the graph is undirected
		map.getTown(town2).addNeighbour(cost, map.getTown(town1));
	}

	/**
	 * Populate the job list
	 * 
	 * @param town_from
	 * @param town_to
	 */
	public void addJob(String town_from, String town_to) {
		joblist.add(new Job(map.getTown(town_from),
							map.getTown(town_to)));
	}

	
	/**
	 * Increment the final cost with a given value
	 * 
	 * @param r the routes need to be calculated
	 */
	public void addCostToFinal(FreightRoute r) {
		if(findJob(r.getTownFrom(), r.getTownTo())) {
			setFinalcost(map.getTown(r.getTownTo()).getUnloadCost());
		}
		setFinalcost(map.getTown(r.getTownTo()).getNeighourCost(map.getTown(r.getTownFrom())));
	}
	
	/**
	 * Print the route, for both job and empty loads 
	 * 
	 * @param r the route that needs to be printed
	 */
	public void printRoutes(FreightRoute r) {
		if(findJob(r.getTownFrom(), r.getTownTo())) {
			System.out.print("Job");
		} else { 
			System.out.print("Empty");
		}
		System.out.println(" " + r.getTownFrom() + " to " + r.getTownTo());
	}
	
	/**
	 * Check if the given route is a job or just a route
	 * 
	 * @param from town from
	 * @param to town to
	 * @return if the route is a job, return true
	 */
	private boolean findJob(String from, String to) {
		boolean found = false;

		for (Job j: joblist) {
			if (from.equals(j.getFrom().getName()) && to.equals(j.getTo().getName())) {
				found = true;
			}
		}
		
		return found;
	}
	
	/**
	 * Debug output for the map and the JobList Tracker
	 */
	public void printDebug() {
		System.out.println("------------------------------------------------------");
		System.out.println("MAP: " + map.numTowns());
		for (Town t: map.getTowns().values()) {
			System.out.println(t.getName() + ": " + t.numNeighbours());
			for (Town r: t.getNeighbours()) {
				System.out.println(" - " + r.getName() + 
				" with travel cost " + r.getNeighourCost(t) + 
				", with total cost: " + (t.getUnloadCost() + 
				r.getNeighourCost(t)));
			}
		}
		System.out.println("------------------------------------------------------");
		System.out.println("JOBS: " + joblist.size());
		for (Job j: joblist) {
			System.out.println(j.getFrom().getName() + ":" + j.getFrom().getUnloadCost() 
					+ " ----" + " " + j.getFrom().getNeighourCost(j.getTo()) + "  ---->  " 
					+ j.getTo().getName() + ":" + j.getTo().getUnloadCost()
			);
		}
		System.out.println("------------------------------------------------------");
	}
	
	/**
	 * Obtain the final cost of each path
	 * 
	 * @return the final cost
	 */
	public int getFinalcost() {
		return finalcost;
	}

	/**
	 * Add a value to the final cost
	 * 
	 * @param finalcost the value that needs to be added.
	 */
	public void setFinalcost(int finalcost) {
		this.finalcost += finalcost;
	}

	private int finalcost;
	private Map map = null;
	private ArrayList<Job> joblist = null;
}
