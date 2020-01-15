package DiGraph_A5;

public class Edge {
	String source;
	String dest;
	String edge;
	long idNum;
	long weight;

	public Edge(long idNum, long weight, String label, String dest, String source) {
		this.idNum = idNum;
		this.weight = weight;
		this.edge = label;
		this.dest = dest;
		this.source = source;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	public String getDest() {
		return dest;
	}
	
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getEdge() {
		return edge;
	}
	
	public void setEdge(String label) {
		this.edge = label;
	}
	public long getID() {
		return idNum;
	}
	
	public void setID(long id) {
		this.idNum = id;
	}
	public long getWeight() {
		return weight;
	}
	
	public void setWeight(long weight) {
		this.weight = weight;
	}
}
