/** 
 * FreightRoute Class implementing a Routes - Representing an object with 
 * the town from and to, and appropriate attributes to quantify that route.
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public class FreightRoute implements Route {

	/**
	 * Freight Route Constructor- construct freight object
	 * 
	 * @param townFrom the town from
	 * @param townTo the town to
 	 * @param travelCost the cost to travel between towns
	 * @param unloadCost the unloading cost of each town
	 */
	public FreightRoute (String townFrom, String townTo, int travelCost, int unloadCost) {
		this.townFrom = townFrom;
		this.townTo = townTo;
		this.unloadCost = unloadCost;
		this.travelCost = travelCost;
	}

	/**
	 * Checks if the given route is the same as this this route
	 * 
	 * @param freightRoute given route
	 * @return yes if it's the same, vice versa
	 */
	public boolean isSameRoute(Route freightRoute) {
		boolean equals = false;
		if (this.getTownFrom().equals(freightRoute.getTownFrom()))
			if (this.getTownTo().equals(freightRoute.getTownTo())) 
				equals = true;
		return equals;
	}

	/**
	 * Get the name of town from
	 * 
	 * @return town from
	 */
	public String getTownFrom() {
		return townFrom;
	}

	/**
	 * Get the name of town to
	 * 
	 * @return town to
	 */
	public String getTownTo() {
		return townTo;
	}

	/**
	 * Get the travel cost
	 * 
	 * @return the travel cost
	 */
	public int getTravelCost() {
		return travelCost;
	}

	/**
	 * Set the travel cost
	 * 
	 * @param travelCost the travel cost that needs to be added
	 */
	public void setTravelCost(int travelCost) {
		this.travelCost = travelCost;
	}

	/**
	 * Get the unloading cost
	 * 
	 * @return the unloading cost
	 */
	public int getUnloadingCost() {
		return unloadCost;
	}

	private String townFrom;
	private String townTo;
	private int travelCost;
	private int unloadCost;
}
