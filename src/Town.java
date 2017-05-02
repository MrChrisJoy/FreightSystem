import java.util.ArrayList;

public class Town {
	private String name;
	private int cost;
	private ArrayList<Route> routes = null;

	public Town (int cost, String name) {
		this.setCost(cost);
		this.setName(name);
		this.routes = new ArrayList<Route>();
	}
	
	// add route
	public void addRoute(int cost, Town town) {
		routes.add(new Route(cost, town));
	}
	// get routes
	public ArrayList<Route> getRoutes() {
		return routes;	
	}
	// get degree (number of routes)
	public int getDegree() {
		return routes.size();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
