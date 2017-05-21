import java.util.List;

/** 
 * FreightHeuristic Class - Adds up the travel cost of each state that is similar to 
 * the current state and multiplies by an scalar value to give it a weighting.
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public class FreightHeuristic implements Heuristic {
	@Override
	/**
	 * Calculates the heuristic path cost for a given State.
	 * 
	 * @param curr the state that the heuristic is being calculated on.
	 * @param routes The list of Routes required to be made.
	 * @param scalar The weighting magnitude that which h(x) will be multiplied by
	 * @return An integer value for the heuristic, multiplied by the scalar.
	 */
	public int calcHeur(FreightState curr, List<FreightRoute> routes, int scalar) {
		int h = 0;
		for (Route parent: routes) {
			boolean found = false;
			// traverse all the child routes and get routes
			for (Route child: curr.routesToGo(curr.getRoutes())) {
				if (parent.isSameRoute(child)) {
					// check when the path has been reached
					found = true;
					break;
				}
			}
			if (found == false) {
				// when we find a similar state, add it to the total h(x) value
				h = Math.round((h + parent.getTravelCost())*1/scalar) ;
			}
		}
		return h;

	}
}