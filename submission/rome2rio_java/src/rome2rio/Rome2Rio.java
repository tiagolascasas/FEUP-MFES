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

		if (pass.length() > 8) {
			adminCode = pass;
			return true;
		}
		return false;
	}

	public Boolean addLocation(final String location, final String country, final Number coordinateNS,
			final Number coordinateEW) {

		if (graph.findNode(location) == null) {
			graph.addNode(location, country, coordinateNS, coordinateEW);
			return true;
		}
		return false;
	}

	public Boolean addWayBetweenLocations(final String source, final String target, final Object travelType,
			final Number time, final Number distance, final Number price) {

		if (Utils.equals(graph.existsEdge(source, target), false)) {
			graph.addEdge(source, target, new EdgeType(((Object) travelType), time, distance, price));
			return true;
		}
		return false;
	}

	public Boolean addNewTransportationType(final String source, final String target, final Object type,
			final Number time, final Number distance, final Number price) {

		EdgeType et = new EdgeType(((Object) type), time, distance, price);
		graph.addEdgeType(source, target, et);

		return true;
	}

	public boolean removeLocation(final String loc) {

		graph.removeNode(loc);
		return true;
	}

	public void changeToClient() {

		status = rome2rio.quotes.ClientQuote.getInstance();
	}

	public Number listLocations() {

		Number i = 0L;
		for (Iterator iterator_16 = graph.node.iterator(); iterator_16.hasNext();) {
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
	
	public ArrayList<ArrayList<String>> listLocationsStrings() {

		Number i = 0L;
		ArrayList<ArrayList<String>> locations = new ArrayList<ArrayList<String>>();

		for (Iterator iterator_16 = graph.node.iterator(); iterator_16.hasNext();) {
			Node n = (Node) iterator_16.next();
			ArrayList<String> countryCity = new ArrayList<String>();
			countryCity.add(n.location);
			countryCity.add(n.country);
			locations.add(countryCity);
			i = i.longValue() + 1L;
		}

		Collections.sort(locations, new Comparator<ArrayList<String>>() {
			@Override
			public int compare(ArrayList<String> o1, ArrayList<String> o2) {
				return o1.get(0).compareTo(o2.get(0));
			}
		});

		return locations;
	}

	public Path getRouteWithCriteria(final String source, final String target, final Object type,
			final Object criterion) {

		return graph.findPath(source, target, ((Object) criterion), ((Object) type));
	}

	public VDMSet getBestRoutesForAllCriteria(final String source, final String target) {

		VDMSet paths = SetUtil.set();
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.PRICEQuote.getInstance(), rome2rio.quotes.ANYQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.DISTANCEQuote.getInstance(), rome2rio.quotes.ANYQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.TIMEQuote.getInstance(), rome2rio.quotes.ANYQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.PRICEQuote.getInstance(), rome2rio.quotes.CARQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.DISTANCEQuote.getInstance(), rome2rio.quotes.CARQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.TIMEQuote.getInstance(), rome2rio.quotes.CARQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.PRICEQuote.getInstance(), rome2rio.quotes.BUSQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.DISTANCEQuote.getInstance(), rome2rio.quotes.BUSQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.TIMEQuote.getInstance(), rome2rio.quotes.BUSQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.PRICEQuote.getInstance(), rome2rio.quotes.PLANEQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.DISTANCEQuote.getInstance(), rome2rio.quotes.PLANEQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.TIMEQuote.getInstance(), rome2rio.quotes.PLANEQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.PRICEQuote.getInstance(), rome2rio.quotes.FERRYQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.DISTANCEQuote.getInstance(), rome2rio.quotes.FERRYQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.TIMEQuote.getInstance(), rome2rio.quotes.FERRYQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.PRICEQuote.getInstance(), rome2rio.quotes.TRAINQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.DISTANCEQuote.getInstance(), rome2rio.quotes.TRAINQuote.getInstance())));
		paths = SetUtil.union(Utils.copy(paths), SetUtil.set(graph.findPath(source, target,
				rome2rio.quotes.TIMEQuote.getInstance(), rome2rio.quotes.TRAINQuote.getInstance())));
		for (Iterator iterator_19 = paths.iterator(); iterator_19.hasNext();) {
			Path p = (Path) iterator_19.next();
			if (!(p.isPossible())) {
				paths = SetUtil.diff(Utils.copy(paths), SetUtil.set(p));
			}
		}
		return Utils.copy(paths);
	}

	public String toString() {

		return "Rome2Rio{" + "graph := " + Utils.toString(graph) + ", status := " + Utils.toString(status)
				+ ", adminCode := " + Utils.toString(adminCode) + "}";
	}
}
