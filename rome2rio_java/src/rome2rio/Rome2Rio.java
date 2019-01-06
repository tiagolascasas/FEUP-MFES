package rome2rio;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Rome2Rio {
	public Graph graph;
	public Object status;
	public String adminCode = "default";

	public void cg_init_Rome2Rio_1() {

		graph = new Graph();
		status = rome2rio.quotes.ClientQuote.getInstance();
		return;
	}

	public Rome2Rio() {

		cg_init_Rome2Rio_1();
	}

	public Boolean changeToAdmin(final String pass) {

		if (Utils.equals(pass, adminCode)) {
			status = rome2rio.quotes.AdministratorQuote.getInstance();
			return true;

		} else {
			return false;
		}
	}

	public boolean setAdministratorPassword(final String pass) {
		
		if(pass.length() > 8) {
			adminCode = pass;
			return true;
		}
		return false;
	}

	public void addLocation(
			final String location, final Number coordinateNS, final Number coordinateEW) {

		graph.addNode(location, coordinateNS, coordinateEW);
	}

	public void addWayBetweenLocations(
			final String source,
			final String target,
			final Object travelType,
			final Number time,
			final Number distance,
			final Number price) {

		if (Utils.equals(graph.ExistsEdge(source, target), false)) {
			graph.addEdge(source, target, new EdgeType(((Object) travelType), time, distance, price));
		} else {
			graph.addEdgeType(source, target, new EdgeType(((Object) travelType), time, distance, price));
		}
	}

	public void addNewTransportationType(
			final String source,
			final String target,
			final Object type,
			final Number time,
			final Number distance,
			final Number price) {

		EdgeType et = new EdgeType(((Object) type), time, distance, price);
		graph.addEdgeType(source, target, et);
	}

	public void changeToClient() {

		status = rome2rio.quotes.ClientQuote.getInstance();
	}

	public Number listLocations() {

		Number i = 0L;
		for (Iterator iterator_16 = graph.node.iterator(); iterator_16.hasNext(); ) {
			Node n = (Node) iterator_16.next();
			IO.print(n.location);
			IO.print(" at (");
			IO.print(n.coordinateNS);
			IO.print(", ");
			IO.print(n.coordinateEW);
			IO.println(")");
			i = i.longValue() + 1L;
		}
		return i;
	}

	public Path getRouteWithCriteria(
			final String source, final String target, final Object type, final Object criterion) {

		return graph.findPath(source, target, ((Object) criterion), ((Object) type));
	}

	public Path getBestRouteForCriterion(
			final String source, final String target, final Object criterion) {

		VDMSet paths = SetUtil.set();
		Path path =
				graph.findPath(
						source, target, ((Object) criterion), rome2rio.quotes.ANYQuote.getInstance());
		if (path.isPossible()) {
			paths = SetUtil.union(Utils.copy(paths), SetUtil.set(path));
		}

		path =
				graph.findPath(
						source, target, ((Object) criterion), rome2rio.quotes.CARQuote.getInstance());
		if (path.isPossible()) {
			paths = SetUtil.union(Utils.copy(paths), SetUtil.set(path));
		}

		path =
				graph.findPath(
						source, target, ((Object) criterion), rome2rio.quotes.PLANEQuote.getInstance());
		if (path.isPossible()) {
			paths = SetUtil.union(Utils.copy(paths), SetUtil.set(path));
		}

		path =
				graph.findPath(
						source, target, ((Object) criterion), rome2rio.quotes.TRAINQuote.getInstance());
		if (path.isPossible()) {
			paths = SetUtil.union(Utils.copy(paths), SetUtil.set(path));
		}

		path =
				graph.findPath(
						source, target, ((Object) criterion), rome2rio.quotes.FERRYQuote.getInstance());
		if (path.isPossible()) {
			paths = SetUtil.union(Utils.copy(paths), SetUtil.set(path));
		}

		path =
				graph.findPath(
						source, target, ((Object) criterion), rome2rio.quotes.BUSQuote.getInstance());
		if (path.isPossible()) {
			paths = SetUtil.union(Utils.copy(paths), SetUtil.set(path));
		}

		for (Iterator iterator_17 = paths.iterator(); iterator_17.hasNext(); ) {
			Path p = (Path) iterator_17.next();
			if (p.getTotalCost().doubleValue() < path.getTotalCost().doubleValue()) {
				path = p;
			}
		}
		return path;
	}

	public String toString() {

		return "Rome2Rio{"
				+ "graph := "
				+ Utils.toString(graph)
				+ ", status := "
				+ Utils.toString(status)
				+ ", adminCode := "
				+ Utils.toString(adminCode)
				+ "}";
	}
}
