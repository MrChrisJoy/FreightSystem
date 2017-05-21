/** 
 * Neighbour Class - links two towns together (similar to an edge)
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public class Neighbour {
	/**
	 * Neighbour constructor - create an edge between two towns
	 * 
	 * @param cost the cost between two towns
	 * @param town town to be linked to
	 */
	public Neighbour(int cost, Town town) {
		this.setCost(cost);
		this.setTown(town);
	}
	
	/**
	 * Get the travel cost between two towns
	 * 
	 * @return travel cost
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Set the cost between two towns
	 * 
	 * @param cost the cost between two towns
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Get the town object
	 * 
	 * @return the town object
	 */
	public Town getTown() {
		return town;
	}

	/**
	 * Set the town object
	 * 
	 * @param town the town object
	 */
	public void setTown(Town town) {
		this.town = town;
	}
	
	private int cost;
	private Town town;
}
