package rome2rio;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Edge {
	public VDMSet edgeType;
	public Node sourceNode;
	public Node targetNode;

	public void cg_init_Edge_1(final EdgeType type, final Node sN, final Node tN) {

		edgeType = SetUtil.set(type);
		sourceNode = sN;
		targetNode = tN;
		return;
	}

	public Edge(final EdgeType type, final Node sN, final Node tN) {

		cg_init_Edge_1(type, sN, tN);
	}

	public void addEdgeType(final EdgeType edgeT) {

		edgeType = SetUtil.union(Utils.copy(edgeType), SetUtil.set(edgeT));
	}

	public Edge() {
	}

	public String toString() {

		return "Edge{" + "edgeType := " + Utils.toString(edgeType) + ", sourceNode := " + Utils.toString(sourceNode)
				+ ", targetNode := " + Utils.toString(targetNode) + "}";
	}
}
