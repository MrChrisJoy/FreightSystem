import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;

/** 
 * FreightSystemAPI that implements AStar search, this is used to 
 * create and search through the tree of states
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public class FreightSystemAPI implements AStar {

	/**
	 * FreightSystemAPI constructor to construct the API object, containing 
	 * the strategy heuristic, the cost of all routes and the routes required
	 * 
	 * @param strategy custom heuristic that takes into account the current state, required 
	 * routes and a scalar value
	 */
	public FreightSystemAPI(Heuristic strategy) {
		this.strategy = strategy;
		this.allCosts = 0;
		this.routeAll = new LinkedList<FreightRoute>();
		this.routeReq = new LinkedList<FreightRoute>();
		this.joblist = new LinkedList<FreightRoute>();
		this.nodes_expanded = 0;
	}

	/**
	 * A* Search to find optimum route between two states, given its cost. 
	 * 
	 * @param start initial state
	 * @param goal required state
	 * @return optimum route between two states
	 */
	public FreightState search(FreightState start, FreightState goal) {
		PriorityQueue<FreightState> open = new PriorityQueue<FreightState>();
		List<FreightState> closed = new LinkedList<FreightState>();
		open.add(start);

		while (!open.isEmpty()) {
			FreightState curr = open.poll();
			addNode();
			if (compareGoal(curr, goal)) {
				int delay = accountUnloadCost(curr);
				this.setAllCost(curr.getGoalCost() - delay);
				return curr;
			}
			if (sameState(closed, curr)) continue;
			for (FreightState childState: this.childStates(curr)) {
				if (initJob(childState) || (completeRoute(childState))) {
					open.add(childState);
				}
			}
			closed.add(curr);
		}

		return null;
	}

	/**
	 * Takes in a State in order to determine whether the State begins a required route.
	 * 
	 * @param curr The State being checked for starting town.
	 * @return True if the State's current town begins a required route.
	 */
	public boolean initJob(FreightState curr) {
		boolean found = false;  

		for (FreightRoute route : this.joblist){
			if (route.getTownFrom().equals(curr.getCurrTown())){
				found = true;
			}
		}
		return found;
	}

	/**
	 * Has a state finished a required route?
	 * 
	 * @param current the state that needs to be checked
	 * @return if the state has complemented a required route, then return true
	 */
	public boolean completeRoute(FreightState current) {
		boolean found = false;
		int reqSize = current.getRoutes().size();
		FreightRoute completedRoutes = null;

		if (reqSize == 0) completedRoutes = current.getRoutes().get(0);
		else completedRoutes = current.getRoutes().get(reqSize - 1);
		for (FreightRoute curr: this.joblist) {
			if (curr.isSameRoute(completedRoutes)) {
				found = true;
			}
		}
		return found;
	}	



	/**
	 * Add a route to the routeAll list
	 * 
	 * @param townName the name of the town
	 * @param unloadingCost the unloading cost of the town
	 */
	public void addTown(String townName, int unloadingCost) {
		boolean found = false;
		if (routeAll != null) {
			for (FreightRoute route: routeAll) {
				if (route.getTownTo().equals(townName)) {
					found = true;
					break;
				}
			}
		}
		if (!found) {
			routeAll.add(new FreightRoute(null, townName, 0, unloadingCost));
		}
	}

	/**
	 * Add a route between two towns
	 * 
	 * @param townFrom the name of the first town
	 * @param townTo the name of the second town
	 * @param travelCost the cost between these two towns
	 */
	public void addRoute(String townFrom, String townTo, int travelCost) {
		boolean found = false;
		if (routeReq != null) {
			for (FreightRoute newRoute: routeReq) {
				if ((newRoute.getTownFrom().equals(townFrom))
						&& (newRoute.getTownTo().equals(townTo))) {
					for (FreightRoute town: routeReq) { // traverse town and check if it exists
						if ((town.getTownFrom().equals(townTo)) 
								&& (town.getTownTo().equals(townFrom))){
							found = true;
						}
					}
					if (found == false) {
						routeReq.add(new FreightRoute(townTo, townFrom, travelCost, 0));
						found = true;
						break;
					}
				} else if ((newRoute.getTownFrom().equals(townTo)) 
						&& (newRoute.getTownTo().equals(townFrom))) {
					routeReq.add(new FreightRoute(townFrom, townTo, travelCost, 0));
					found = true;
					break;
				}
			}
		}
		if (!found) {
			// add routes, since this is an undirected graph, 
			// we step it up in both directions
			routeReq.add(new FreightRoute(townFrom, townTo, travelCost, 0));
			routeReq.add(new FreightRoute(townTo, townFrom, travelCost, 0));
		}
	}

	/**
	 * Add a route between two towns to the required routes list
	 * 
	 * @param townFrom name of the first town
	 * @param townTo name of the second town
	 */
	public void addRequiredRoute(String townFrom, String townTo) {
		boolean exists = false;
		if (joblist != null) { // find and add a route if it doesn't exist
			for (FreightRoute route: this.joblist) {
				if ((route.getTownFrom().equals(townFrom)) 
						&& (route.getTownTo().equals(townTo))) {
					exists = true;
					break;
				}
			}
		}
		if (!exists) { // if the job doesn't exist
			FreightRoute req = new FreightRoute(townFrom, townTo, 0, 0);
			for (FreightRoute a: this.routeReq) {
				if (a.isSameRoute(req)) {
					req.setTravelCost(a.getTravelCost());
				}
			}
			joblist.add(req);
		}
	}

	/**
	 * Hard copy of a route (can be manipulated)
	 * 
	 * @param curr the route that needs to be copied
	 * @return the pointer to the clone
	 */
	public List<FreightRoute> cloneRoute (FreightState curr) {
		List<FreightRoute> clone = new LinkedList<FreightRoute>();
		clone.addAll(curr.getRoutes());
		return clone;
	}

	/**
	 * Finds the neighbours of a given state
	 * 
	 * @param parent the parent state
	 * @return  a list of states contain it's child states
	 */
	public List<FreightState> childStates(FreightState parent) {
		List<FreightState> children = new LinkedList<FreightState>();
		int unload = 0;

		for (FreightRoute curr: routeReq) {
			if (curr.getTownFrom().equals(parent.getCurrTown())) {
				FreightState child = new FreightState();
				child.setCurrTown(curr.getTownTo());
				unload = 0;

				for (FreightRoute b: routeAll) {
					if (b.getTownTo().equals(child.getCurrTown())) {
						unload = b.getUnloadingCost();
					}
				}

				child.setHomeCost(strategy.calcHeur(child, joblist, 3));
				child.setGoalCost(parent.getGoalCost() + curr.getTravelCost() + unload);
				child.setRoutes(cloneRoute(parent));
				child.addRoute(parent.getCurrTown(), child.getCurrTown(), curr.getTravelCost(), unload);
				child.setFinalCost(child.getGoalCost() + child.getHomeCost());
				children.add(child);
			}
		}
		return children;
	}

	/**
	 * Take into account unloading costs when calulating the total cost
	 * 
	 * @param currentState the current state
	 * @return integer cost of the unloading costs associate with the state
	 */
	public int accountUnloadCost (FreightState currentState) {
		int cost = 0;
		for (FreightRoute curr: routeAll) {
			if (curr.getTownTo().equals(currentState.getCurrTown())) {
				cost = curr.getUnloadingCost();
			}
		}
		return cost;
	}
	
	/**
	 * Compare two goals (current and goal state)
	 * 
	 * @param current state that is being compared
	 * @param goal the state being compared to (from required states)
	 * @return
	 */
	public boolean compareGoal (FreightState current, FreightState goal) {
		boolean found = false;
		int numFound = 0;
		for (FreightRoute curr: goal.getRoutes()) {
			for (FreightRoute curr2: current.getRoutes()) {
				if (curr.isSameRoute(curr2)) {
					numFound++;
					break;
				}
			}	
		}
		if (numFound == goal.getRoutes().size()) {
			found = true;
		}
		return found;
	}

	/**
	 * Check if state has a route
	 * 
	 * @param compare list of states that needs to be checked
	 * @param goal the state that is being checked
	 * @return
	 */
	public boolean sameState(List<FreightState> compare, FreightState goal) {
		boolean found = false;
		for (FreightState curr : compare) {
			if (curr.getCurrTown().equals(goal.getCurrTown())) {		
				if(curr.hasRoutes(goal, joblist)) found = true;
			}
		}
		return found;
	}

	/**
	 * Get list of routes that that's in the joblist
	 * 
	 * @return the joblist
	 */
	public List<FreightRoute> getJoblist() {
		return joblist;
	}

	/**
	 * Set all associate costs (override)
	 * 
	 * @param cost the overide cost value
	 */
	public void setAllCost(int cost) {
		this.allCosts = cost;
	}

	/**
	 * Get all the associate costs
	 * 
	 * @return allCosts
	 */
	public int getAllCost() {
		return allCosts;
	}

	/**
	 * Increment nodes expanded when the polled of the queue
	 */
	public void addNode() {
		nodes_expanded++;
	}
	
	/**
	 * Fetch total nodes that's been explored
	 * 
	 * @return the total number of expanded nodes
	 */
	public int getTotalNodes() {
		return nodes_expanded;
	}

	private Heuristic strategy;
	private List<FreightRoute> routeAll;
	private List<FreightRoute> routeReq;
	private List<FreightRoute> joblist;
	private int allCosts;
	private int nodes_expanded;

}
