package rome2rio_prototype;

import java.util.*;

public class Node implements Comparable<Node>
{
    private String local;
    private float coordNS;
    private float coordEW;
    private float distance = Float.MAX_VALUE;
    private boolean processed = false;
    private ArrayList<Edge> outwardEdges;
    private Node path = null;

    public Node(String local, float coordNS, float coordEW)
    {
        this.local = local;
        this.coordEW = coordEW;
        this.coordNS = coordNS;
        this.outwardEdges = new ArrayList<>();
    }

    @Override
    public int compareTo(Node y)
    {
        if (this.getDistance() < y.getDistance())
            return 1;
        if (this.getDistance() > y.getDistance())
            return -1;
        return 0;
    }

    public void addEdge(Edge edge)
    {
        this.outwardEdges.add(edge);
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public float getCoordNS() {
        return coordNS;
    }

    public void setCoordNS(float coordNS) {
        this.coordNS = coordNS;
    }

    public float getCoordEW() {
        return coordEW;
    }

    public void setCoordEW(float coordEW) {
        this.coordEW = coordEW;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public ArrayList<Edge> getOutwardEdges() {
        return outwardEdges;
    }

    public void setOutwardEdges(ArrayList<Edge> outwardEdges) {
        this.outwardEdges = outwardEdges;
    }

    public Node getPath() {
        return path;
    }

    public void setPath(Node path) {
        this.path = path;
    }
}
