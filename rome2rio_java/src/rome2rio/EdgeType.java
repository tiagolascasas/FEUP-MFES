package rome2rio;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class EdgeType {
  public Object travelType;
  public Number time;
  public Number distance;
  public Number price;

  public void cg_init_EdgeType_1(
      final Object tt, final Number tm, final Number dist, final Number p) {

    travelType = tt;
    time = tm;
    distance = dist;
    price = p;
    return;
  }

  public EdgeType(final Object tt, final Number tm, final Number dist, final Number p) {

    cg_init_EdgeType_1(tt, tm, dist, p);
  }

  public void SetTravelType(final Object tt) {

    travelType = tt;
  }

  public Object GetTravelType() {

    return travelType;
  }

  public void SetTime(final Number tm) {

    time = tm;
  }

  public Number GetTime() {

    return time;
  }

  public void SetDistance(final Number dist) {

    distance = dist;
  }

  public Number GetDistance() {

    return distance;
  }

  public void SetPrice(final Number p) {

    price = p;
  }

  public Number GetPrice() {

    return price;
  }

  public EdgeType() {}

  public String toString() {

    return "EdgeType{"
        + "travelType := "
        + Utils.toString(travelType)
        + ", time := "
        + Utils.toString(time)
        + ", distance := "
        + Utils.toString(distance)
        + ", price := "
        + Utils.toString(price)
        + "}";
  }
}
