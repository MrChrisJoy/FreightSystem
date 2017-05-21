/** 
 * Route interface -Representing an object with the town from and to, and appropriate 
 * attributes to quantify that route.
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public interface Route {
	/**
	 * Checks if the given route is the same as this this route
	 * 
	 * @param freightRoute given route
	 * @return yes if it's the same, vice versa
	 */

	public boolean isSameRoute(Route freightRoute);
	
	/**
	 * Get the name of town from
	 * 
	 * @return town from
	 */
	public String getTownFrom();

	/**
	 * Get the name of town to
	 * 
	 * @return town to
	 */
	public String getTownTo();
	
	/**
	 * Get the travel cost
	 * 
	 * @return the travel cost
	 */
	public int getTravelCost();
	
	/**
	 * Set the travel cost
	 * 
	 * @param travelCost the travel cost that needs to be added
	 */
	public void setTravelCost(int travelCost);
	
	/**
	 * Get the unloading cost
	 * 
	 * @return the unloading cost
	 */
	public int getUnloadingCost();
}