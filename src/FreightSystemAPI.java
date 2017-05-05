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
		joblist.add(new Job(map.getTown(town_from),
							map.getTown(town_to)));
	}
	
	
	
	
	// find the optimum job list path given the map & joblist 
	public void solve() {
		Town start = map.getTown("Sydney");
		Town goal = map.getTown("Dubbo");

		AStar findPath = new AStar(start, goal);
		System.out.print(findPath.getPath());
		System.out.println(findPath.getNodesExpanded());
		System.out.println(findPath.getCost());
		
//		Town start = map.getTown("Sydney");
//		int n = 0;
//		for (Town t: map.getTowns().values()) {
//			for (Town f: map.getTowns().values()) {
//				AStar fromPathFind = new AStar(t, f);
//				fromPathFind.search();
//				System.out.println(fromPathFind.getPath());
//				n += fromPathFind.getNodesExpanded();
//			}
//		}
//		System.out.println("node expanded: " + n);
//
	
	
	}
	

	
	// map overview & joblist
	public void printDebug() {
		System.out.println("------------------------------------------------------");
		System.out.println("MAP: " + map.numTowns());
		for (Town t: map.getTowns().values()) {
			System.out.println(t.getName() + ": " + t.numNeighbours());
			for (Town r: t.getNeighbours()) {
				System.out.println(" - " + r.getName() + 
				" with travel cost " + r.getNeighourCost(t) + 
				", with total cost: " + (t.getUnloadCost() + 
				r.getNeighourCost(t)));
			}
		}
		System.out.println("------------------------------------------------------");
		System.out.println("JOBS: " + joblist.size());
		for (Job j: joblist) {
			System.out.println(j.getFrom().getName() + ":" + j.getFrom().getUnloadCost() 
					+ " ----" + " " + j.getFrom().getNeighourCost(j.getTo()) + "  ---->  " 
					+ j.getTo().getName() + ":" + j.getTo().getUnloadCost()
			);
		}
		System.out.println("------------------------------------------------------");
	}
	private Map map = null;
	private ArrayList<Job> joblist = null;
}