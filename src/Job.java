/** 
 * Job Class - used to represent jobs in the FreightSpace
 * 
 * COMP2911 Assignment 2 - 2017 Semester 1 [UNSW CSE]
 * @author	Chris Joy
 * @date	05/05/17
 */
public class Job {

	/**
	 * Construct a job, given a town to and from
	 * 
	 * @param from
	 * @param to
	 */
	public Job(Town from, Town to) {
		this.setFrom(from);
		this.setTo(to);
	}
	
	/**
	 * Get town from
	 * 
	 * @return town from
	 */
	public Town getFrom() {
		return from;
	}
	
	/**
	 * Set town from
	 * 
	 * @param from town from
	 */
	public void setFrom(Town from) {
		this.from = from;
	}
	
	/**
	 * Get town to
	 * 
	 * @return get town to
	 */
	public Town getTo() {
		return to;
	}
	
	/**
	 * Set town to
	 * 
	 * @param to town to
	 */
	public void setTo(Town to) {
		this.to = to;
	}

	private Town from;
	private Town to;
}
