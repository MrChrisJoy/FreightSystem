import java.util.ArrayList;
import java.util.HashMap;

public class Town implements Comparable<Town>{

	public Town (int cost, String name) {
		this.setUnloadCost(cost);
		this.setName(name);
		this.neighbours = new ArrayList<Town>();
		this.neighbourcosts = new HashMap<Town, Integer>();
	}
	

	public void addNeighbourCost(Town town, int cost) {
		neighbourcosts.put(town, cost);
	}
	public int getNeighourCost(Town town) {
		return neighbourcosts.get(town);
	}
	
	
	// NEIGHBOUR FUNCTIONS
	// add neighbour
	public void addNeighbour(int cost, Town town) {
		neighbours.add(town);
		addNeighbourCost(town, cost);
	}
	// get all neighbours
	public ArrayList<Town> getNeighbours() {
		return neighbours;	
	}
	// find and get single neighbour, given the name of the neighbour
	public Town getNeighbour(Town town) {
		Town found = null;
		for (Town n: neighbours) {
			if (n.getName().equals(town.getName())) {
				found = n;
			}
		}
		return found; 
	}
	
	
	// get number of connected towns
	public int numNeighbours() {
		return neighbours.size();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnloadCost() {
		return unloadCost;
	}
	public void setUnloadCost(int cost) {
		this.unloadCost = cost;
	}
	
	public String toString() {
		return this.name;
	}
	

	@Override
	public int compareTo(Town o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.priority, o.priority);
	}

	private String name;
	private int unloadCost;
	private ArrayList<Town> neighbours = null;
	private HashMap<Town, Integer> neighbourcosts = null;
	public Town parent = null;
	public int priority = 0;
}
