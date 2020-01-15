package DiGraph_A5;

import java.util.HashMap;

public class Node {
	long idNum;
	String label;
	int inDegree;
	HashMap<String, Edge> inEdge; // source label
	HashMap<String, Edge> outEdge; // destination label 
	long d; // min dist from start node 
	boolean known;
	
	public Node(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
		this.inDegree = 0;
		this.inEdge = new HashMap();
		this.outEdge = new HashMap();
		this.known = false;
	}
	
	public String getLabel() {
		return label;
	}
	public long getID() {
		return idNum;
	}
}
