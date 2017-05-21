<<<<<<< HEAD
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
=======

public class Neighbour {
	private int cost;
	private Town town;
	
>>>>>>> 40b20acef9692c0bb8c45368dbb0927549c31dcc
	public Neighbour(int cost, Town town) {
		this.setCost(cost);
		this.setTown(town);
	}
	
<<<<<<< HEAD
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
=======
	
	public int getCost() {
		return cost;
	}
>>>>>>> 40b20acef9692c0bb8c45368dbb0927549c31dcc
	public void setCost(int cost) {
		this.cost = cost;
	}

<<<<<<< HEAD
	/**
	 * Get the town object
	 * 
	 * @return the town object
	 */
=======
>>>>>>> 40b20acef9692c0bb8c45368dbb0927549c31dcc
	public Town getTown() {
		return town;
	}

<<<<<<< HEAD
	/**
	 * Set the town object
	 * 
	 * @param town the town object
	 */
=======
>>>>>>> 40b20acef9692c0bb8c45368dbb0927549c31dcc
	public void setTown(Town town) {
		this.town = town;
	}
	
<<<<<<< HEAD
	private int cost;
	private Town town;
=======
>>>>>>> 40b20acef9692c0bb8c45368dbb0927549c31dcc
}
