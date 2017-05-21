import java.util.LinkedList;
import java.util.List;

/** 
 * FreightState - Used to represent the probability tree for each given path. 
 * This is an implementation of a comparable (in order to sort using the PriorityQueue), which is
 * needed when executing searching algorithms.
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public class FreightState implements Comparable<FreightState> {

	/**
	 * FreightState Constructor - set the routes and appropriate costs the state object
	 */
	public FreightState() {
		this.freightRoutes = new LinkedList<FreightRoute>();
		this.goalCost = 0;
		this.homeCost = 0;
		this.finalCost = 0;
	}

	/**
	 * Add Route to the state.
	 * 
	 * @param townFrom the town from
	 * @param townTo the town too
	 * @param travelCost the travel cost (road length) - undirected
	 * @param unloadCost the unloading cost of each town
	 */
	public void addRoute(String townFrom, String townTo, int travelCost, int unloadCost) {
		freightRoutes.add(new FreightRoute(townFrom, townTo, travelCost, unloadCost));
	}

	/**
	 * Get a list of routes to visit.
	 * 
	 * @param freightRoutes the current route
	 * @return neighbor routes that need to be explored
	 */
	public List<FreightRoute> routesToGo(List<FreightRoute> freightRoutes) {
		List<FreightRoute> todo = new LinkedList<FreightRoute>();
		for (FreightRoute from: freightRoutes) {
			for (FreightRoute to: getRoutes()) {
				if (from.isSameRoute(to)) {
					todo.add(new FreightRoute(from.getTownFrom(), from.getTownTo(), 0, 0));
					break;
				}
			}
		}
		return todo;
	}

	/**
	 * Check if one route state contains another, used to remove redundant 
	 * tree branches
	 * 
	 * @param freightState the state that needs to be checked
	 * @param freightRoutes a list of routes
	 * @return is the route is contained in the state, then true is returned 
	 */
	public boolean hasRoutes(FreightState freightState, List<FreightRoute> freightRoutes){
		boolean found = false;
		List<FreightRoute> path1  = routesToGo(freightRoutes);
		List<FreightRoute> path2  = freightState.routesToGo(freightRoutes);
		if (path1.size() != path2.size())
			found = false;
		else if (path1.containsAll(path2) && path2.containsAll(path1)
				&& getFinalCost() < freightState.getFinalCost())
			found = true;
		return found;
	}


	/**
	 * Get the current town name
	 * 
	 * @return the town name string
	 */
	public String getCurrTown() {
		return currTown;
	}

	/**
	 * Set or change the current town name
	 * 
	 * @param currTown the town name that needs to be set to
	 */
	public void setCurrTown(String currTown) {
		this.currTown = currTown;
	}

	/**
	 * Get a list of routes
	 * 
	 * @return a list of freight routes
	 */
	public List<FreightRoute> getRoutes() {
		return freightRoutes;
	}

	/**
	 * Set the freight routes for the given state
	 * 
	 * @param freightRoutes a list of freight routes
	 */
	public void setRoutes(List<FreightRoute> freightRoutes) {
		this.freightRoutes = freightRoutes;
	}

	/**
	 * Get the goal cost number
	 * 
	 * @return an integer representing the goal cost
	 */
	public int getGoalCost() {
		return goalCost;
	}

	/**
	 * Set the goal cost
	 * 
	 * @param goalCost integer value of goal cost
	 */
	public void setGoalCost(int goalCost) {
		this.goalCost = goalCost;
	}

	/**
	 * Get the final cost of the state
	 * 
	 * @return an integer value containing the final cost of each state
	 */
	public int getFinalCost() {
		return finalCost;
	}

	/**
	 * Set the final for the the state
	 * 
	 * @param finalCost integer value of the final cost
	 */
	public void setFinalCost(int finalCost) {
		this.finalCost = finalCost;
	}

	/**
	 * Get the home cost of the state
	 * 
	 * @return the home cost
	 */
	public int getHomeCost() {
		return homeCost;
	}

	/**
	 * Set the home cost of the state
	 * 
	 * @param homeCost the home cost
	 */
	public void setHomeCost(int homeCost) {
		this.homeCost = homeCost;
	}

	/**
	 * Used for order the states in the priority queue.
	 * 
	 * @param o the state to be compared to.
	 */
	public int compareTo(FreightState o) {
		return this.getFinalCost() - o.getFinalCost();
	}

	private int goalCost;
	private int finalCost;
	private int homeCost;
	private String currTown;
	private List<FreightRoute> freightRoutes;
}