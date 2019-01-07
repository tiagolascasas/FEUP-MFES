package gui;

import java.util.ArrayList;
import java.util.Iterator;

import org.overture.codegen.runtime.Utils;

import rome2rio.Edge;
import rome2rio.EdgeType;
import rome2rio.Node;
import rome2rio.Path;

public class PathUtils {

	public PathUtils() {
	};

	public static ArrayList<EdgeType> getEdgeTypesOfPath(Path path) {
		ArrayList<EdgeType> routeParts = new ArrayList<EdgeType>();

		switch (path.travelType.toString()) {
		case "<BUS>":
		case "<CAR>":
		case "<FERRY>":
		case "<PLANE>":
		case "<TRAIN>":
			return getEdgeTypesOfPathGeneral(path, path.travelType.toString());
		case "<ANY>":
			return getEdgeTypesOfPathAny(path);
		}

		return routeParts;
	}

	private static ArrayList<EdgeType> getEdgeTypesOfPathGeneral(Path path, String Transport) {

		ArrayList<EdgeType> routeParts = new ArrayList<EdgeType>();

		Number i = 2L;
		Boolean whileCond_3 = true;
		while (whileCond_3) {
			whileCond_3 = i.longValue() <= path.path.size();
			if (!(whileCond_3)) {
				break;
			}
			{
				Number j = i.longValue() - 1L;

				EdgeType aux = null;

				for (Iterator<?> iterator_16 = ((Node) Utils.get(path.path, j)).outwardEdges.iterator(); iterator_16
						.hasNext();) {
					Edge edge = ((Edge) iterator_16.next());
					if (edge.targetNode == ((Node) Utils.get(path.path, i))) {
						for (Iterator<?> iterator_17 = edge.edgeType.iterator(); iterator_17.hasNext();) {
							EdgeType type = (EdgeType) iterator_17.next();
							switch (path.criteria.toString()) {
							case "<TIME>":
								if ((aux == null || (aux.time.intValue() > type.time.intValue()))
										&& type.travelType.toString() == Transport)
									aux = type;
							case "<DISTANCE>":
								if ((aux == null || (aux.distance.intValue() > type.distance.intValue()))
										&& type.travelType.toString() == Transport)
									aux = type;
							case "<PRICE>":
								if ((aux == null || (aux.price.intValue() > type.price.intValue()))
										&& type.travelType.toString() == Transport)
									aux = type;
							}
						}
					}
				}

				routeParts.add(aux);

				i = i.longValue() + 1L;
			}
		}

		return routeParts;
	}

	private static ArrayList<EdgeType> getEdgeTypesOfPathAny(Path path) {

		ArrayList<EdgeType> routeParts = new ArrayList<EdgeType>();

		Number i = 2L;
		Boolean whileCond_3 = true;
		while (whileCond_3) {
			whileCond_3 = i.longValue() <= path.path.size();
			if (!(whileCond_3)) {
				break;
			}
			{
				Number j = i.longValue() - 1L;

				EdgeType aux = null;

				for (Iterator<?> iterator_16 = ((Node) Utils.get(path.path, j)).outwardEdges.iterator(); iterator_16
						.hasNext();) {
					Edge edge = ((Edge) iterator_16.next());
					if (edge.targetNode == ((Node) Utils.get(path.path, i))) {
						for (Iterator<?> iterator_17 = edge.edgeType.iterator(); iterator_17.hasNext();) {
							EdgeType type = (EdgeType) iterator_17.next();
							switch (path.criteria.toString()) {
							case "<TIME>":
								if ((aux == null || (aux.time.intValue() > type.time.intValue())))
									aux = type;
							case "<DISTANCE>":
								if ((aux == null || (aux.distance.intValue() > type.distance.intValue())))
									aux = type;
							case "<PRICE>":
								if ((aux == null || (aux.price.intValue() > type.price.intValue())))
									aux = type;
							}
						}
					}
				}

				routeParts.add(aux);

				i = i.longValue() + 1L;
			}
		}

		return routeParts;
	}

	public static String calculateTotalPrice(Path path) {

		Number totalPrice = 0L;

		ArrayList<EdgeType> routes = getEdgeTypesOfPath(path);

		for (int i = 0; i < routes.size(); i++) {
			totalPrice = totalPrice.longValue() + routes.get(i).price.longValue();
		}

		return totalPrice.toString();
	}

	public static String calculateTotalDistance(Path path) {

		Number totalDistance = 0L;

		ArrayList<EdgeType> routes = getEdgeTypesOfPath(path);

		for (int i = 0; i < routes.size(); i++) {
			totalDistance = totalDistance.longValue() + routes.get(i).distance.longValue();
		}

		return totalDistance.toString();
	}

	public static String calculateTotalTime(Path path) {

		Number totalTime = 0L;

		ArrayList<EdgeType> routes = getEdgeTypesOfPath(path);

		for (int i = 0; i < routes.size(); i++) {
			totalTime = totalTime.longValue() + routes.get(i).time.longValue();
		}

		return totalTime.toString();
	}

	public static String pathToString(Path path) {

		String res = "";
		Number i = 1L;
		Boolean whileCond_3 = true;
		while (whileCond_3) {
			whileCond_3 = i.longValue() <= path.path.size();
			if (!(whileCond_3)) {
				break;
			}
			if (i.longValue() != 1L)
				res += " -> ";
			{
				res += ((Node) Utils.get(path.path, i)).location;
				i = i.longValue() + 1L;
			}
		}

		return res;
	}

}
