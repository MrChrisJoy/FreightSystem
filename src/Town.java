import java.util.ArrayList;
import java.util.HashMap;

/** 
 * Towns - Nodes that have a name, unloading cost and a set of neighbours
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public class Town {

	/**
	 * Town constructor
	 * 
	 * @param cost the unloading cost of the town
	 * @param name the name of the town, string format
	 */
	public Town (int cost, String name) {
		this.setUnloadCost(cost);
		this.setName(name);
		this.neighbours = new ArrayList<Town>();
		this.neighbourcosts = new HashMap<Town, Integer>();
	}
	
	/**
	 * Add a neighbor to this town
	 * 
	 * @param neighbour  town object 
	 * @param cost travel cost between two neighbours
	 */
	public void addNeighbourCost(Town town, int cost) {
		neighbourcosts.put(town, cost);
	}
	
	/**
	 * Get the neighbour cost
	 * 
	 * @param town neighbour town object
	 * @return the integer cost between towns
	 */
	public int getNeighourCost(Town town) {
		return neighbourcosts.get(town);
	}
	
	
	/**
	 * Add a neighbour with a cost
	 * 
	 * @param cost travel cost between neighbours
	 * @param town the town object being linked
	 */
	public void addNeighbour(int cost, Town town) {
		neighbours.add(town);
		addNeighbourCost(town, cost);
	}
	
	/**
	 * Get an array of neighbours
	 * 
	 * @return an array of neighbours
	 */
	public ArrayList<Town> getNeighbours() {
		return neighbours;	
	}
	
	/**
	 * Get a specific neighbour
	 * 
	 * @param town town object of neighbour
	 * @return the town object of neighbour
	 */
	public Town getNeighbour(Town town) {
		Town found = null;
		for (Town n: neighbours) {
			if (n.getName().equals(town.getName())) {
				found = n;
			}
		}
		return found; 
	}
	
	/**
	 * Get number of neighbours
	 * 
	 * @return degree of vertexes (i.e. neighbours)
	 */
	public int numNeighbours() {
		return neighbours.size();
	}

	/**
	 * Get the name of the town
	 * 
	 * @return the name of the town
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the twon
	 * 
	 * @param name a string value of the name of the town
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the unload cost
	 * 
	 * @return an integer of the unload cost
	 */
	public int getUnloadCost() {
		return unloadCost;
	}
	
	/**
	 * Set the unload cost
	 * 
	 * @param cost an integer of unload cost
	 */
	public void setUnloadCost(int cost) {
		this.unloadCost = cost;
	}
	
	/**
	 * When this object is called as a string, the name is returned
	 */
	public String toString() {
		return this.name;
	}

	private String name;
	private int unloadCost;
	private ArrayList<Town> neighbours = null;
	private HashMap<Town, Integer> neighbourcosts = null;
	public Town parent = null;
	public int priority = 0;
}
