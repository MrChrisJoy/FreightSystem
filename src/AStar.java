import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

public class AStar {

	AStar(Town from, Town to) {
		this.from = from;
		this.to = to;
		this.path = new ArrayList<Town>();
		this.nodes_expanded = 0;
		this.cost = 0;
		this.search();
	}
		
	public void search() {
		Town start = from;
		PriorityQueue<Town> frontier = new PriorityQueue<Town>();
		frontier.offer(start);
		HashMap<Town, Integer> cost_so_far = new LinkedHashMap<Town, Integer>();
		HashMap<Town, Town> came_from = new LinkedHashMap<Town, Town>();
		came_from.put(start, null);
		cost_so_far.put(start, 0);
		
		while (!frontier.isEmpty()) {
			Town current = frontier.poll();
			nodes_expanded++;

			if (current == to) {
				break;
			}

			for (Town next: current.getNeighbours()) {
				int new_cost = cost_so_far.get(current) + current.getNeighourCost(next);
				if (!cost_so_far.containsKey(next) || new_cost < cost_so_far.get(next)) {
					cost_so_far.put(next, new_cost);
					next.priority = new_cost;
					frontier.offer(next);
					came_from.put(next, current);
				}
			}
		}
		
		// reconstruct the path
		ArrayList<Town> path = new ArrayList<Town>();
		Town curr = to;
		path.add(curr);		
		while (curr != from) {
			curr = came_from.get(curr);
			path.add(curr);
		}
		Collections.reverse(path);
		this.path.addAll(path);
		
        for(int i=0; i<path.size(); i++){
        	if (i < path.size()-1 && path.get(i+1) != null) {
                cost += path.get(i).getNeighourCost(path.get(i+1)); //total cost + path.get(i+1).getUnloadCost();
        	}
        }
		
	}
	
	
	public ArrayList<Town> getPath() {
		return path;
	}
	
	public int getNodesExpanded() {
		return nodes_expanded;
	}
	public int getCost() {
		return this.cost;
	}
	
	private int nodes_expanded;
	private Town from = null;
	private Town to = null;
	private ArrayList<Town> path = null;
	private int cost;
}
