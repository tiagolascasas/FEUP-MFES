package rome2rio;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Graph {
	public VDMSet node;
	public PriorityQueue priorityQueue;
	public VDMSet edge;

	public void cg_init_Graph_1() {

		node = SetUtil.set();
		priorityQueue = new PriorityQueue();
		edge = SetUtil.set();
		return;
	}

	public Graph() {

		cg_init_Graph_1();
	}

	public void addNode(final String location, final Number coordinateNS, final Number coordinateEW) {

		Node n = new Node(coordinateNS, coordinateEW, location);
		node = SetUtil.union(SetUtil.set(n), Utils.copy(node));
	}

	public void addEdge(final String source, final String target, final EdgeType type) {

		Node s = findNode(source);
		Node t = findNode(target);
		Edge e = new Edge(type, s, t);
		edge = SetUtil.union(SetUtil.set(e), Utils.copy(edge));
		s.addEdge(e);
	}

	public void addEdgeType(final String source, final String target, final EdgeType type) {

		Edge e = findEdge(source, target);
		e.addEdgeType(type);
	}

	public Node findNode(final String location) {

		Node res = null;
		for (Iterator iterator_10 = node.iterator(); iterator_10.hasNext(); ) {
			Node n = (Node) iterator_10.next();
			if (Utils.equals(n.location, location)) {
				res = n;
			}
		}
		return res;
	}

	public Edge findEdge(final String source, final String target) {

		Edge res = null;
		for (Iterator iterator_11 = edge.iterator(); iterator_11.hasNext(); ) {
			Edge e = (Edge) iterator_11.next();
			Boolean andResult_7 = false;

			if (Utils.equals(e.sourceNode.location, source)) {
				if (Utils.equals(e.targetNode.location, target)) {
					andResult_7 = true;
				}
			}

			if (andResult_7) {
				res = e;
			}
		}
		return res;
	}

	public Boolean existsEdge(final String source, final String target) {

		Boolean existsExpResult_5 = false;
		VDMSet set_7 = Utils.copy(edge);
		for (Iterator iterator_7 = set_7.iterator(); iterator_7.hasNext() && !(existsExpResult_5); ) {
			Edge e = ((Edge) iterator_7.next());
			Boolean andResult_8 = false;

			if (Utils.equals(e.sourceNode.location, source)) {
				if (Utils.equals(e.targetNode.location, target)) {
					andResult_8 = true;
				}
			}

			existsExpResult_5 = andResult_8;
		}
		return existsExpResult_5;
	}

	public VDMSet FindEdgesWithNode(final String location) {

		VDMSet res = null;
		res = SetUtil.set();
		for (Iterator iterator_12 = edge.iterator(); iterator_12.hasNext(); ) {
			Edge e = (Edge) iterator_12.next();
			Boolean orResult_1 = false;

			if (Utils.equals(e.sourceNode.location, location)) {
				orResult_1 = true;
			} else {
				orResult_1 = Utils.equals(e.targetNode.location, location);
			}

			if (orResult_1) {
				res = SetUtil.union(Utils.copy(res), SetUtil.set(e));
			}
		}
		return Utils.copy(res);
	}

	public void removeNode(final String location) {

		Node n = findNode(location);
		VDMSet edgesWithNode = FindEdgesWithNode(location);
		edge = SetUtil.diff(Utils.copy(edge), Utils.copy(edgesWithNode));
		node = SetUtil.diff(Utils.copy(node), SetUtil.set(n));
	}

	public void resetGraphInformation() {

		priorityQueue.clear();
		for (Iterator iterator_12 = node.iterator(); iterator_12.hasNext(); ) {
			Node n = (Node) iterator_12.next();
			n.setPriority(Node.MIN_PRIORITY);
			n.setProcessed(false);
			n.setPath(n);
		}
	}

	public Path findPath(
			final String source, final String target, final Object criterion, final Object travelType) {

		Boolean foundTarget = false;
		Node startingNode = findNode(source);
		resetGraphInformation();
		startingNode.setPriority(0L);
		priorityQueue.addElement(startingNode);
		Boolean whileCond_1 = true;
		while (whileCond_1) {
			Boolean andResult_11 = false;

			if (!(Utils.equals(priorityQueue.isEmpty(), true))) {
				if (!(Utils.equals(foundTarget, true))) {
					andResult_11 = true;
				}
			}

			whileCond_1 = andResult_11;

			if (!(whileCond_1)) {
				break;
			}

			{
				Node currentNode = priorityQueue.pop();
				if (Utils.equals(currentNode.location, target)) {
					foundTarget = true;
				} else {
					for (Iterator iterator_14 = currentNode.outwardEdges.iterator();
							iterator_14.hasNext();
							) {
						Edge outwardEdge = (Edge) iterator_14.next();
						for (Iterator iterator_15 = outwardEdge.edgeType.iterator(); iterator_15.hasNext(); ) {
							EdgeType type = (EdgeType) iterator_15.next();
							Number weight = 0L;
							Node destNode = outwardEdge.targetNode;
							Boolean orResult_2 = false;

							if (Utils.equals(type.travelType, travelType)) {
								orResult_2 = true;
							} else {
								orResult_2 = Utils.equals(travelType, rome2rio.quotes.ANYQuote.getInstance());
							}

							if (orResult_2) {
								if (Utils.equals(criterion, rome2rio.quotes.TIMEQuote.getInstance())) {
									weight = type.time;
								} else if (Utils.equals(criterion, rome2rio.quotes.DISTANCEQuote.getInstance())) {
									weight = type.distance;
								} else {
									weight = type.price;
								}

								if (currentNode.priority.doubleValue() + weight.doubleValue()
								< destNode.priority.doubleValue()) {
									destNode.setPriority(currentNode.priority.doubleValue() + weight.doubleValue());
									destNode.setPath(currentNode);
									if (Utils.equals(destNode.processed, false)) {
										destNode.setProcessed(true);
										priorityQueue.addElement(destNode);
									}
								}
							}
						}
					}
				}
			}
		}

		if (Utils.equals(foundTarget, true)) {
			return getPathSourceToTarget(source, target, ((Object) travelType), ((Object) criterion));

		} else {
			return new Path(((Object) travelType), ((Object) criterion), -1L);
		}
	}

	public Path getPathSourceToTarget(
			final String source, final String target, final Object type, final Object criterion) {

		Node currentNode = findNode(target);
		Path path = new Path(((Object) type), ((Object) criterion), currentNode.priority);
		Boolean whileCond_2 = true;
		while (whileCond_2) {
			whileCond_2 = !(Utils.equals(currentNode.location, source));
			if (!(whileCond_2)) {
				break;
			}

			{
				path.addNode(currentNode);
				currentNode = currentNode.path;
			}
		}

		path.addNode(currentNode);
		return path;
	}

	public String toString() {

		return "Graph{"
				+ "node := "
				+ Utils.toString(node)
				+ ", priorityQueue := "
				+ Utils.toString(priorityQueue)
				+ ", edge := "
				+ Utils.toString(edge)
				+ "}";
	}
}
