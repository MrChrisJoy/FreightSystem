
public class Neighbour {
	private int cost;
	private Town town;
	
	public Neighbour(int cost, Town town) {
		this.setCost(cost);
		this.setTown(town);
	}
	
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}
	
}
