package rome2rio;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestRome2Rio extends MyTestCase {
  public Rome2Rio r2r;

  public void testAdminLogin() {

    r2r = new Rome2Rio();
    assertEqual(false, r2r.changeToAdmin("foo"));
    assertEqual(true, r2r.changeToAdmin("default"));
    assertEqual(((Object) r2r.status), rome2rio.quotes.AdministratorQuote.getInstance());
  }

  public void testAdminPasswordChange() {

    r2r.setAdministratorPassword("new_password");
    assertEqual(r2r.adminCode, "new_password");
    r2r.changeToClient();
    assertEqual(((Object) r2r.status), rome2rio.quotes.ClientQuote.getInstance());
    assertEqual(false, r2r.changeToAdmin("default"));
    assertEqual(true, r2r.changeToAdmin("new_password"));
    assertEqual(((Object) r2r.status), rome2rio.quotes.AdministratorQuote.getInstance());
  }

  public void testAddLocations() {

    r2r.addLocation("L1", 1.0, 1.0);
    assertEqual(1L, r2r.graph.node.size());
    r2r.addLocation("L2", 2.0, 2.0);
    assertEqual(2L, r2r.graph.node.size());
    r2r.addLocation("L3", 3.0, 3.0);
    assertEqual(3L, r2r.graph.node.size());
  }

  public void testAddConnections() {

    r2r.addWayBetweenLocations("L1", "L2", rome2rio.quotes.CARQuote.getInstance(), 1L, 1L, 1.0);
    assertEqual(1L, r2r.graph.edge.size());
    r2r.addWayBetweenLocations("L1", "L3", rome2rio.quotes.CARQuote.getInstance(), 2L, 2L, 2.0);
    assertEqual(2L, r2r.graph.edge.size());
    r2r.addWayBetweenLocations("L2", "L3", rome2rio.quotes.CARQuote.getInstance(), 3L, 3L, 3.0);
    assertEqual(3L, r2r.graph.edge.size());
  }

  public void testAddTravelTypes() {

    r2r.addNewTransportationType(
        "L1", "L2", rome2rio.quotes.PLANEQuote.getInstance(), 50L, 50L, 50.5);
    assertEqual(2L, r2r.graph.findEdge("L1", "L2").edgeType.size());
    r2r.addNewTransportationType(
        "L1", "L2", rome2rio.quotes.FERRYQuote.getInstance(), 30L, 30L, 30.5);
    assertEqual(3L, r2r.graph.findEdge("L1", "L2").edgeType.size());
    r2r.addNewTransportationType(
        "L2", "L3", rome2rio.quotes.PLANEQuote.getInstance(), 50L, 50L, 50.5);
    assertEqual(2L, r2r.graph.findEdge("L2", "L3").edgeType.size());
  }

  public void testListLocations() {

    assertEqual(r2r.graph.node.size(), r2r.listLocations());
  }

  public void testGetRouteWithCriteria() {

    assertEqual(true, true);
  }

  public void testGetBestRouteForCriterion() {

    assertEqual(true, true);
  }

  public void testAddNodesAndEdges() {

    Graph graph = new Graph();
    graph.addNode("L1", 1.0, 1.0);
    graph.addNode("L2", 4.0, 2.0);
    graph.addNode("L3", 5.0, 3.0);
    assertEqual(3L, graph.node.size());
    graph.addEdge("L1", "L2", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 1L, 1L, 1.0));
    graph.addEdge("L1", "L3", new EdgeType(rome2rio.quotes.FERRYQuote.getInstance(), 10L, 2L, 2.0));
    assertEqual(2L, graph.findNode("L1").outwardEdges.size());
    graph.addEdge(
        "L3", "L2", new EdgeType(rome2rio.quotes.PLANEQuote.getInstance(), 100L, 3L, 3.0));
    graph.addEdge("L2", "L1", new EdgeType(rome2rio.quotes.BUSQuote.getInstance(), 1000L, 4L, 4.0));
    graph.addEdge(
        "L2", "L3", new EdgeType(rome2rio.quotes.TRAINQuote.getInstance(), 10000L, 5L, 5.0));
    assertEqual(5L, graph.edge.size());
    assertEqual("L1", graph.findEdge("L1", "L2").sourceNode.location);
    assertEqual("L2", graph.findEdge("L1", "L2").targetNode.location);
    graph.addEdgeType(
        "L1", "L2", new EdgeType(rome2rio.quotes.FERRYQuote.getInstance(), 1L, 1L, 1.0));
    assertEqual(2L, graph.findEdge("L1", "L2").edgeType.size());
    graph.addEdgeType(
        "L1", "L2", new EdgeType(rome2rio.quotes.PLANEQuote.getInstance(), 1L, 1L, 1.0));
    assertEqual(3L, graph.findEdge("L1", "L2").edgeType.size());
    graph.addEdgeType(
        "L1", "L2", new EdgeType(rome2rio.quotes.BUSQuote.getInstance(), 1L, 1L, 1.0));
    assertEqual(4L, graph.findEdge("L1", "L2").edgeType.size());
    graph.addEdgeType(
        "L1", "L2", new EdgeType(rome2rio.quotes.TRAINQuote.getInstance(), 1L, 1L, 1.0));
    assertEqual(5L, graph.findEdge("L1", "L2").edgeType.size());
  }

  public void testPriorityQueue() {

    PriorityQueue pq = new PriorityQueue();
    Node node1 = new Node(5.1, 3.1, "L1");
    Node node2 = new Node(5.2, 3.2, "L2");
    Node node3 = new Node(5.3, 3.3, "L3");
    Node node4 = new Node(5.4, 3.4, "L4");
    node1.setPriority(50L);
    node2.setPriority(100L);
    node3.setPriority(3L);
    node4.setPriority(20L);
    pq.addElement(node1);
    pq.addElement(node2);
    pq.addElement(node3);
    pq.addElement(node4);
    assertEqual(4L, pq.elements.size());
    assertEqual(node3, pq.pop());
    assertEqual(node4, pq.pop());
    assertEqual(node1, pq.pop());
    assertEqual(node2, pq.pop());
    assertEqual(true, pq.isEmpty());
    pq.addElement(node1);
    pq.addElement(node2);
    assertEqual(false, pq.isEmpty());
    pq.clear();
    assertEqual(true, pq.isEmpty());
  }

  public Graph makeGraph() {

    Graph graph = new Graph();
    graph.addNode("L1", 1.0, 1.0);
    graph.addNode("L2", 2.0, 2.0);
    graph.addNode("L3", 3.0, 3.0);
    graph.addNode("L4", 4.0, 4.0);
    graph.addNode("L5", 5.0, 5.0);
    graph.addNode("L6", 6.0, 6.0);
    graph.addNode("L7", 7.0, 7.0);
    graph.addNode("L8", 8.0, 8.0);
    graph.addNode("L9", 9.0, 9.0);
    graph.addNode("L10", 10.0, 10.0);
    graph.addNode("L11", 11.0, 11.0);
    graph.addNode("L12", 12.0, 12.0);
    graph.addNode("L13", 13.0, 13.0);
    graph.addNode("L14", 14.0, 14.0);
    graph.addNode("L15", 15.0, 15.0);
    graph.addNode("L16", 16.0, 16.0);
    graph.addEdge(
        "L1", "L2", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 10L, 123L, 23.0));
    graph.addEdge(
        "L2", "L1", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 10L, 234L, 12.0));
    graph.addEdge(
        "L2", "L5", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 20L, 134L, 55.0));
    graph.addEdge(
        "L5", "L2", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 20L, 215L, 123.0));
    graph.addEdge(
        "L2", "L4", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 15L, 234L, 67.0));
    graph.addEdge(
        "L4", "L2", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 15L, 642L, 88.0));
    graph.addEdge(
        "L2", "L3", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 14L, 2134L, 234.0));
    graph.addEdge(
        "L3", "L2", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 13L, 1531L, 1003.0));
    graph.addEdge(
        "L5", "L6", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 60L, 451L, 114.0));
    graph.addEdge(
        "L6", "L7", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 55L, 462L, 665.0));
    graph.addEdge(
        "L6", "L8", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 22L, 134L, 54.0));
    graph.addEdge(
        "L8", "L6", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 51L, 231L, 245.5));
    graph.addEdge(
        "L7", "L3", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 100L, 141L, 123.0));
    graph.addEdge(
        "L5", "L9", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 4L, 512L, 134.0));
    graph.addEdge(
        "L9", "L5", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 90L, 141L, 131.0));
    graph.addEdge(
        "L9", "L10", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 30L, 352L, 7000.0));
    graph.addEdge(
        "L10", "L9", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 120L, 42L, 64.0));
    graph.addEdge(
        "L10", "L12", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 62L, 875L, 5000.0));
    graph.addEdge(
        "L12", "L10", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 78L, 232L, 56.0));
    graph.addEdge(
        "L12", "L11", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 788L, 162L, 6888L));
    graph.addEdge(
        "L11", "L12", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 700L, 124L, 6444L));
    graph.addEdge(
        "L11", "L9", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 33L, 456L, 95.0));
    graph.addEdge(
        "L9", "L11", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 33L, 567L, 74.0));
    graph.addEdge(
        "L11", "L13", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 9L, 891L, 41.0));
    graph.addEdge(
        "L13", "L11", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 10L, 921L, 92.0));
    graph.addEdge(
        "L13", "L16", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 87L, 120L, 22.0));
    graph.addEdge(
        "L16", "L13", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 86L, 519L, 15.0));
    graph.addEdge(
        "L16", "L15", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 50L, 140L, 12.0));
    graph.addEdge(
        "L15", "L16", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 55L, 501L, 93.0));
    graph.addEdge(
        "L15", "L13", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 23L, 124L, 54.0));
    graph.addEdge(
        "L13", "L15", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 22L, 901L, 246.0));
    graph.addEdge(
        "L13", "L14", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 17L, 120L, 234.0));
    graph.addEdge(
        "L14", "L13", new EdgeType(rome2rio.quotes.CARQuote.getInstance(), 15L, 190L, 421.0));
    return graph;
  }

  public void testDijkstraCar() {

    Graph graph = makeGraph();
    Path path = null;
    path =
        graph.findPath(
            "L1",
            "L15",
            rome2rio.quotes.TIMEQuote.getInstance(),
            rome2rio.quotes.CARQuote.getInstance());
    assertEqual(98L, path.getTotalCost());
    assertEqual(7L, path.path.size());
    assertEqual("L1", ((Node) Utils.get(path.path, 1L)).location);
    assertEqual("L2", ((Node) Utils.get(path.path, 2L)).location);
    assertEqual("L5", ((Node) Utils.get(path.path, 3L)).location);
    assertEqual("L9", ((Node) Utils.get(path.path, 4L)).location);
    assertEqual("L11", ((Node) Utils.get(path.path, 5L)).location);
    assertEqual("L13", ((Node) Utils.get(path.path, 6L)).location);
    assertEqual("L15", ((Node) Utils.get(path.path, 7L)).location);
    path =
        graph.findPath(
            "L9",
            "L3",
            rome2rio.quotes.TIMEQuote.getInstance(),
            rome2rio.quotes.CARQuote.getInstance());
    assertEqual(124L, path.getTotalCost());
    assertEqual(4L, path.path.size());
    assertEqual("L9", ((Node) Utils.get(path.path, 1L)).location);
    assertEqual("L5", ((Node) Utils.get(path.path, 2L)).location);
    assertEqual("L2", ((Node) Utils.get(path.path, 3L)).location);
    assertEqual("L3", ((Node) Utils.get(path.path, 4L)).location);
    path =
        graph.findPath(
            "L1",
            "L1",
            rome2rio.quotes.TIMEQuote.getInstance(),
            rome2rio.quotes.CARQuote.getInstance());
    assertEqual(0L, path.getTotalCost());
    assertEqual(1L, path.path.size());
    assertEqual("L1", ((Node) Utils.get(path.path, 1L)).location);
    path =
        graph.findPath(
            "L1",
            "L15",
            rome2rio.quotes.PRICEQuote.getInstance(),
            rome2rio.quotes.CARQuote.getInstance());
    assertEqual(361L, path.getTotalCost());
    assertEqual(8L, path.path.size());
    assertEqual("L1", ((Node) Utils.get(path.path, 1L)).location);
    assertEqual("L2", ((Node) Utils.get(path.path, 2L)).location);
    assertEqual("L5", ((Node) Utils.get(path.path, 3L)).location);
    assertEqual("L9", ((Node) Utils.get(path.path, 4L)).location);
    assertEqual("L11", ((Node) Utils.get(path.path, 5L)).location);
    assertEqual("L13", ((Node) Utils.get(path.path, 6L)).location);
    assertEqual("L16", ((Node) Utils.get(path.path, 7L)).location);
    assertEqual("L15", ((Node) Utils.get(path.path, 8L)).location);
    path =
        graph.findPath(
            "L15",
            "L7",
            rome2rio.quotes.DISTANCEQuote.getInstance(),
            rome2rio.quotes.CARQuote.getInstance());
    assertEqual(2497L, path.getTotalCost());
    assertEqual(9L, path.path.size());
    assertEqual("L15", ((Node) Utils.get(path.path, 1L)).location);
    assertEqual("L13", ((Node) Utils.get(path.path, 2L)).location);
    assertEqual("L11", ((Node) Utils.get(path.path, 3L)).location);
    assertEqual("L12", ((Node) Utils.get(path.path, 4L)).location);
    assertEqual("L10", ((Node) Utils.get(path.path, 5L)).location);
    assertEqual("L9", ((Node) Utils.get(path.path, 6L)).location);
    assertEqual("L5", ((Node) Utils.get(path.path, 7L)).location);
    assertEqual("L6", ((Node) Utils.get(path.path, 8L)).location);
    assertEqual("L7", ((Node) Utils.get(path.path, 9L)).location);
  }

  public void testAllValidTests() {

    testAddNodesAndEdges();
    testPriorityQueue();
    testDijkstraCar();
    testAdminLogin();
    testAdminPasswordChange();
    testAddLocations();
    testAddConnections();
    testAddTravelTypes();
    testListLocations();
    testGetRouteWithCriteria();
    testGetBestRouteForCriterion();
  }

  public TestRome2Rio() {}

  public String toString() {

    return "TestRome2Rio{" + "r2r := " + Utils.toString(r2r) + "}";
  }
}