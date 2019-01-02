package rome2rio_prototype;

import java.util.*;

public class Edge
{
    private String source;
    private String target;
    private ArrayList<EdgeType> types;

    public Edge(String source, String target, EdgeType type)
    {
        this.types = new ArrayList<>();
        this.source = source;
        this.target = target;
        this.types.add(type);
    }

    boolean addNewType(EdgeType newType)
    {
        for (EdgeType type : types)
        {
            if (type.getTravelMethod() == newType.getTravelMethod())
                return false;
        }
        types.add(newType);
        return true;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ArrayList<EdgeType> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<EdgeType> types) {
        this.types = types;
    }
}
