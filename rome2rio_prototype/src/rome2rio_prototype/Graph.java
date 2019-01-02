package rome2rio_prototype;

import java.util.*;

public class Graph
{
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private PriorityQueue<Node> pq;

    public Graph()
    {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        pq = new PriorityQueue<>();
    }

    public void addNode(String local, float c1, float c2, String target, EdgeType type)
    {
        nodes.add(new Node(local, c1, c2));
        if (target != null)
            addEdge(local, target, type);
    }

    public void addEdge(String source, String target, EdgeType type)
    {
        Edge edge = new Edge(source, target, type);
        edges.add(edge);
        Node sourceNode = findNode(source);
        sourceNode.addEdge(edge);
    }

    public void removeNode(String local)
    {
        for (Edge edge : edges)
        {
            if (edge.getSource().equals(local) || edge.getTarget().equals(local))
                edges.remove(edge);
        }
        for (Node node : nodes)
        {
            if (node.getLocal().equals(local))
            {
                nodes.remove(node);
                return;
            }
        }
    }

    public ArrayList<Node> findPath(String source, String target, TravelTypes travelType, CriterionTypes criterion)
    {
        boolean foundTarget = false;

        ArrayList<Node> nodes = new ArrayList<>();

        resetGraphInformation();

        Node startingNode = findNode(source);
        startingNode.setDistance(0);

        pq.add(startingNode);

        while (!pq.isEmpty())
        {
            Node currentNode = pq.peek();
            pq.remove();

            if (currentNode.getLocal().equals(target))
            {
                foundTarget = true;
                break;
            }

            for (Edge edge : currentNode.getOutwardEdges())
            {
                for (EdgeType type : edge.getTypes())
                {
                    if (type.getTravelMethod() == travelType || travelType == TravelTypes.ANY)
                    {
                        float weight = 0;
                        switch (criterion)
                        {
                            case TIME:
                                weight = type.getTime();
                                break;
                            case DISTANCE:
                                weight = type.getDistance();
                                break;
                            case PRICE:
                                weight = type.getPrice();
                                break;
                        }

                        Node dest = findNode(edge.getTarget());

                        if (currentNode.getDistance() + weight < dest.getDistance())
                        {
                            dest.setDistance(currentNode.getDistance() + weight);
                            dest.setPath(currentNode);
                            if (!dest.isProcessed())
                            {
                                dest.setProcessed(true);
                                pq.add(dest);
                            }
                        }
                    }
                }
            }
        }

        if (foundTarget)
            nodes = getPathTargetToSource(findNode(target), startingNode);
        return nodes;
    }

    private ArrayList<Node> getPathTargetToSource(Node target, Node source)
    {
        ArrayList<Node> path = new ArrayList<>();
        do {
            path.add(target);
            target = target.getPath();
        } while (target != source);
        Collections.reverse(path);
        return path;
    }

    private Node findNode(String source)
    {
        for (Node node : nodes)
            if (node.getLocal().equals(source))
                return node;
        return null;
    }

    private void resetGraphInformation()
    {
        for (Node node : nodes)
        {
            node.setDistance(Float.MAX_VALUE);
            node.setProcessed(false);
            node.setPath(null);
        }
    }
}
