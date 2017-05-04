import java.util.ArrayList;

/** FreightSystemAPI - kind of acts like an interface 
 * for FreightSystemCMD (but not really an interface).
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	chrisjoy
 * @date	07/04/17
 */
public class FreightSystemAPI {
	// Initialize the joblist
	public FreightSystemAPI() {
		this.map = new Map();
		this.joblist = new ArrayList<Job>();
	}
	// populate the map
	public void addTownToMap(int cost, String name) {
		map.addTown(cost, name);
	}
	// connect two town in both directions (undirected graph)
	public void connectTowns(int cost, String town1, String town2) {
		map.getTown(town1).addNeighbour(cost, map.getTown(town2));
		map.getTown(town2).addNeighbour(cost, map.getTown(town1));
	}	
	// populate the job list
	public void addJob(String town_from, String town_to) {
		joblist.add(new Job(this.map.getTown(town_from),
							this.map.getTown(town_to)));		
	}
	
	
	
	
	// find the optimum job list path given the map & joblist 
	public void solve() {
		Town start = this.map.getTown("Sydney");
		
		
		
	}
	
	
	
	
	// map overview & joblist
	public void printDebug() {
		System.out.println("--------------------------");
		System.out.println("MAP: " + this.map.numTowns());
		for (Town t: this.map.getTowns().values()) {
			System.out.println(t.getName() + ": " + t.numNeighbours());
			for (Neighbour r: t.getNeighbours()) {
				System.out.println(" - " + r.getTown().getName() + " with travel cost " + r.getCost() + ", with total cost: " + (t.getUnloadCost() + r.getCost()));				
			}
		}
		System.out.println("--------------------------");
		System.out.println("JOBS: " + this.joblist.size());
		for (Job j: this.joblist) {
			System.out.println(j.getFrom().getName() + ":" + j.getFrom().getUnloadCost() 
					+ " ----" + " " + j.getTo().getTravelCostToNeighbour(j.getFrom()) + "  ---->  " 
					+ j.getTo().getName() + ":" + j.getTo().getUnloadCost()
			);
		}
	}
	private Map map = null;
	private ArrayList<Job> joblist = null;
}