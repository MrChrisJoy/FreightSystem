import java.util.List;

/** 
 * FreightHeuristicZero Class - Used to perform an UCS to find a goal state.
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public class FreightHeuristicZero implements Heuristic {
	@Override
	/**
	 * Calculates the heuristic path cost for a given State.
	 * 
	 * @param curr the state that the heuristic is being calculated on.
	 * @param routes The list of Routes required to be made.
	 * @param scalar The weighting magnitude that which h(x) will be multiplied by
	 * @return 0 to perform a UCS.
	 */
	public int calcHeur(FreightState curr, List<FreightRoute> routes, int scalar) {
		return 0;
	}
}
