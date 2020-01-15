package DiGraph_A5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class DiGraph implements DiGraphInterface {
	// in here go all your data and methods for the graph
	HashMap<String, Node> NodeMap = new HashMap<String, Node>(); // label, node
	HashSet<Long> NodeID = new HashSet<Long>(); // node ID
	HashSet<Long> EdgeID = new HashSet<Long>(); // edge ID

	public DiGraph ( ) { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if(idNum < 0 || label == null) {
			return false;
		} else if (!NodeID.contains(idNum) && !NodeMap.containsKey(label)) {
			Node newNode = new Node(idNum, label);
			NodeID.add(idNum);
			NodeMap.put(label, newNode);
			return true;
		}
		return false;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (idNum < 0 || EdgeID.contains(idNum)) {
			return false;
		} else if(!NodeMap.containsKey(sLabel) || !NodeMap.containsKey(dLabel)) {
			return false;
		} else {
			Node source = NodeMap.get(sLabel);
			Node dest = NodeMap.get(dLabel);
			if(source.outEdge.containsKey(dLabel) && dest.inEdge.containsKey(sLabel)) {
				return false;				
			} else {
				Edge newEdge = new Edge(idNum, weight, eLabel, dLabel, sLabel);
				EdgeID.add(idNum);
				source.outEdge.put(dLabel, newEdge);
				dest.inEdge.put(sLabel, newEdge);
				dest.inDegree++;
				return true;
			}
		}
	}

	@Override
	public boolean delNode(String label) {
		if (!NodeMap.containsKey(label)) {
			return false;
		} else {
			Node removeNode = NodeMap.get(label);
			
			Iterator<String> iteratein = removeNode.inEdge.keySet().iterator();
			Iterator<String> iterateout = removeNode.outEdge.keySet().iterator();
			
			while (iteratein.hasNext()) {
				delEdge(iteratein.next(), removeNode.label);
			} while (iterateout.hasNext()) {
				delEdge(removeNode.label, iterateout.next());
			}
			
			NodeMap.remove(label, removeNode);
			NodeID.remove(removeNode.idNum);
			return true;
		}
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if (!NodeMap.containsKey(sLabel) || !NodeMap.containsKey(dLabel)) {
			return false;
		} else {
			Node source = NodeMap.get(sLabel);
			Node dest = NodeMap.get(dLabel);
			if(source.outEdge.containsKey(dLabel) && dest.inEdge.containsKey(sLabel)) {
				Edge removeEdge = NodeMap.get(sLabel).outEdge.get(dLabel);
				EdgeID.remove(removeEdge.idNum);
				source.outEdge.remove(dLabel, removeEdge);
				dest.inEdge.remove(sLabel, removeEdge);
				dest.inDegree--;
				return true;
			}
			return false;
		}
	}

	@Override
	public long numNodes() {
		return NodeID.size();
	}

	@Override
	public long numEdges() {
		return EdgeID.size();
	}
		   
	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		int size = NodeID.size();
		ShortestPathInfo[] sp = new ShortestPathInfo[size];
		MinBinHeap pq = new MinBinHeap();
		EntryPair s = new EntryPair(label, 0); // put start node s in table with dist of 0
		pq.insert(s); // put (0, s) on pq
		sp[0] = new ShortestPathInfo(label, 0);
		int i = 0;
		while (pq.size() > 0) { // loop until pq is empty
			Node n = NodeMap.get(pq.getMin().value);
			long d = pq.getMin().priority;
			pq.delMin();
			if (n.known) { // check if n is known, then loop back to get another pq
				continue;
			} else {
				sp[i] = new ShortestPathInfo(n.label, d);
				n.known = true; //mark n as known 
			}
			Iterator<Edge> adjacent = n.outEdge.values().iterator(); 
			while(adjacent.hasNext()) {
				Node a = NodeMap.get(adjacent.next().dest);
				if (!a.known) { // for each unknown node a adjacent to n
					long dist = d + n.outEdge.get(a.label).weight;
					if (a.d == 0 || a.d > dist) { // if a.dist > d+edge.weight
						a.d = dist; // update a.dist in table to be d+edge weight
						EntryPair apair = new EntryPair(a.label, a.d);
						pq.insert(apair);// add a to pq with priority d+edge.weight
					}
				}
			}
			i++;
		}
		if (i<size) { // test 4, catch dist -1
			for (Node n: NodeMap.values()) {
				if (!n.known) {
					n.d--;
					sp[i] = new ShortestPathInfo(n.label, n.d);
					n.known = true;
					i++;
				}
			}
		}
		return sp;
	}

}