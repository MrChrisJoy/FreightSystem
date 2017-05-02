/** FreightSystemAPI - kind of acts like an interface 
 * for FreightSystemCMD (but not really an interface).
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	chrisjoy
 * @date	07/04/17
 */
public class FreightSystemAPI {
	private Map map = null;
	
	// initialise graph objects etc here
	// 
	public FreightSystemAPI() {
		this.map = new Map();
	}
	
	public void addTownToMap(int cost, String name) {
		map.addTown(cost, name);
	}
	
	// connect two town, both directions
	public void addRoute(int cost, String town1, String town2) {
		map.getTown(town1).addRoute(cost, map.getTown(town2));
		map.getTown(town2).addRoute(cost, map.getTown(town1));
	}
	
	
	public void printDebug() {
		System.out.println("--------------------------");
		System.out.println("TOWNS LIST: " + this.map.numTowns());
		for (Town t: this.map.getTowns().values()) {
			System.out.println(t.getName() + " has unloading cost of " + t.getCost());
		}
		System.out.println("--------------------------");
		System.out.println("ROUTE LIST: ");
		for (Town t: this.map.getTowns().values()) {
			System.out.println(t.getName() + ": " + t.getDegree());
			for (Route r: t.getRoutes()) {
				System.out.println(" - " + r.getTown().getName() + " with travel cost " + r.getCost() + ", with total cost: " + (t.getCost() + r.getCost()));				
			}
		}
		
	}

	
}