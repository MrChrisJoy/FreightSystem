/** 
 * AStar Interface - Strategy Pattern
 * Allows devs to add different search strategies.
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public interface AStar {
	/**
	 * Execute the AStar search on a given state to reach a goal state.
	 * 
	 * @param start current state that needs to expanded
	 * @param goal destination that that needs to be reached
	 * @return a state that will hang on the tree.
	 */
	public FreightState search(FreightState start, FreightState goal);
}
