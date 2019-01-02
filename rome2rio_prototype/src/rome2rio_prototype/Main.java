package rome2rio_prototype;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Graph graph = new Graph();
        graph.addNode("L1", 1, 1, null, null);
        graph.addNode("L2", 2, 2, "L1", new EdgeType(TravelTypes.CAR, 20, 1240, 12323));
        graph.addNode("L3", 2, 2, "L1", new EdgeType(TravelTypes.CAR, 200, 32, 5125));
        graph.addNode("L4", 2, 2, "L1", new EdgeType(TravelTypes.PLANE, 120, 315, 51245));
        graph.addNode("L5", 2, 2, "L2", new EdgeType(TravelTypes.PLANE, 555, 120, 6341));
        graph.addNode("L6", 2, 2, "L2", new EdgeType(TravelTypes.FERRY, 42, 320, 1231));
        graph.addNode("L7", 2, 2, "L3", new EdgeType(TravelTypes.FERRY, 212, 6230, 16588));
        graph.addEdge("L1", "L7", new EdgeType(TravelTypes.TRAIN, 19924, 13454, 9923));
        graph.addEdge("L1", "L6", new EdgeType(TravelTypes.TRAIN, 66123, 1353454, 3123));
        graph.addEdge("L1", "L7", new EdgeType(TravelTypes.TRAIN, 65523, 1844, 1288));
        graph.addEdge("L2", "L1", new EdgeType(TravelTypes.TRAIN, 25431, 11235,12890));
        graph.addEdge("L2", "L3", new EdgeType(TravelTypes.TRAIN, 2541, 1252235, 9809));
        graph.addEdge("L7", "L2", new EdgeType(TravelTypes.TRAIN, 2541, 1252235, 9809));

        ArrayList<Node> path;

        path = graph.findPath("L1", "L2", TravelTypes.ANY, CriterionTypes.DISTANCE);
        for (Node node : path)
            System.out.println(node.getLocal());

        path = graph.findPath("L1", "L3", TravelTypes.ANY, CriterionTypes.DISTANCE);
        for (Node node : path)
            System.out.println(node.getLocal());

        path = graph.findPath("L5", "L2", TravelTypes.ANY, CriterionTypes.DISTANCE);
        for (Node node : path)
            System.out.println(node.getLocal());

        path = graph.findPath("L1", "L4", TravelTypes.ANY, CriterionTypes.DISTANCE);
        for (Node node : path)
            System.out.println(node.getLocal());
    }
}