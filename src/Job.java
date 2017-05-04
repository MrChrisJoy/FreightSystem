
public class Job {
	private Town from;
	private Town to;

	public Job(Town from, Town to) {
		this.setFrom(from);
		this.setTo(to);
	}
	
	public Town getFrom() {
		return from;
	}
	public void setFrom(Town from) {
		this.from = from;
	}
	public Town getTo() {
		return to;
	}
	public void setTo(Town to) {
		this.to = to;
	}
}
