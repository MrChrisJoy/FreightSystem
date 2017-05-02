import java.util.LinkedHashMap;

public class Map {
	private LinkedHashMap<String, Town> towns = null;
	
	public Map() {
		this.towns = new LinkedHashMap<String, Town>();
	}
	
	// get list of loaded towns
	public LinkedHashMap<String, Town> getTowns() {
		return this.towns;
	}
	// get single town
	public Town getTown(String town) {
		return towns.get(town);
	}
	
	// add town to map
	public void addTown(int cost, String name) {
		towns.put(name, new Town(cost, name));
	}
	
	// number of towns in map
	public int numTowns() {
		return towns.size();
	}
}
