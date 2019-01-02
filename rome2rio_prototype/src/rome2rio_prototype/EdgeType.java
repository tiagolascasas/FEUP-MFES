package rome2rio_prototype;

public class EdgeType
{
    private TravelTypes travelMethod;
    private float price;
    private int distance;
    private int time;

    public EdgeType(TravelTypes type, float price, int distance, int time)
    {
        this.travelMethod = type;
        this.price = price;
        this.distance = distance;
        this.time = time;
    }

    public TravelTypes getTravelMethod() {
        return travelMethod;
    }

    public void setTravelMethod(TravelTypes travelMethod) {
        this.travelMethod = travelMethod;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
