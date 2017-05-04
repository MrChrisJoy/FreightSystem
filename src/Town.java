import java.util.ArrayList;

public class Town {
	private String name;
	private int unloadCost;
	private ArrayList<Neighbour> neighbours = null;

	public Town (int cost, String name) {
		this.setUnloadCost(cost);
		this.setName(name);
		this.neighbours = new ArrayList<Neighbour>();
	}
	
	
	
	// NEIGHBOUR FUNCTIONS
	// add neighbour
	public void addNeighbour(int cost, Town town) {
		neighbours.add(new Neighbour(cost, town));
	}
	// get all neighbours
	public ArrayList<Neighbour> getNeighbours() {
		return neighbours;	
	}
	// find and get single neighbour, given the name of the neighbour
	public Neighbour getNeighbour(String town) {
		Neighbour found = null;
		for (Neighbour n: this.neighbours) {
			if (n.getTown().getName().equals(town))
				found = n;
		}
		return found; 
	}
	// get number of connected towns
	public int numNeighbours() {
		return neighbours.size();
	}
	
	public int getTravelCostToNeighbour(Town neighbour) {
		return this.getNeighbour(neighbour.getName()).getCost();
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
	
}
