package rome2rio;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Node {
	public static final Number MIN_PRIORITY = 9999999999L;
	public Number priority;
	public Number coordinateNS;
	public Number coordinateEW;
	public String location;
	public String country;
	public VDMSet outwardEdges;
	public Node path;
	public Boolean processed;

	public void cg_init_Node_1(final Number cdNS, final Number cdEW, final String loc, final String con) {

		priority = Node.MIN_PRIORITY;
		coordinateNS = cdNS;
		coordinateEW = cdEW;
		location = loc;
		country = con;
		outwardEdges = SetUtil.set();
		processed = false;
		path = this;
		return;
	}

	public Node(final Number cdNS, final Number cdEW, final String loc, final String con) {

		cg_init_Node_1(cdNS, cdEW, loc, con);
	}

	public void setPriority(final Number pri) {

		priority = pri;
	}

	public void setPath(final Node n) {

		path = n;
	}

	public void setProcessed(final Boolean p) {

		processed = p;
	}

	public void addEdge(final Edge edge) {

		outwardEdges = SetUtil.union(SetUtil.set(edge), Utils.copy(outwardEdges));
	}

	public Node() {
	}

	public String toString() {

		return "Node{" + "MIN_PRIORITY = " + Utils.toString(MIN_PRIORITY) + ", priority := " + Utils.toString(priority)
				+ ", coordinateNS := " + Utils.toString(coordinateNS) + ", coordinateEW := "
				+ Utils.toString(coordinateEW) + ", location := " + Utils.toString(location) + ", country := "
				+ Utils.toString(country) + ", outwardEdges := " + Utils.toString(outwardEdges) + ", path := "
				+ Utils.toString(path) + ", processed := " + Utils.toString(processed) + "}";
	}
}
