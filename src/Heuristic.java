<<<<<<< HEAD
import java.util.List;

/** 
 * Heuristic Interface
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public interface Heuristic {
	/**
	 * Calculates the heuristic path cost for a given State.
	 * @param curr the state that the heuristic is being calculated on.
	 * @param routes The list of Routes required to be made.
	 * @param scalar The weighting magnitude that which h(x) will be multiplied by
	 * @return An integer value for the heuristic, multiplied by the scalar.
	 */
	public int calcHeur(FreightState curr, List<FreightRoute> routes, int scalar);
=======

public class Heuristic {

>>>>>>> 40b20acef9692c0bb8c45368dbb0927549c31dcc
}
