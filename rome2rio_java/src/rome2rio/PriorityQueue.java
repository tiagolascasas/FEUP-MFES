package rome2rio;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PriorityQueue {
  public VDMSet elements;

  public void cg_init_PriorityQueue_1() {

    elements = SetUtil.set();
    return;
  }

  public PriorityQueue() {

    cg_init_PriorityQueue_1();
  }

  public void addElement(final Node element) {

    elements = SetUtil.union(Utils.copy(elements), SetUtil.set(element));
  }

  public Node pop() {

    Node res = null;
    Number min = 9999999999L;
    for (Iterator iterator_15 = elements.iterator(); iterator_15.hasNext(); ) {
      Node e = (Node) iterator_15.next();
      if (e.priority.doubleValue() < min.doubleValue()) {
        res = e;
        min = e.priority;
      }
    }
    elements = SetUtil.diff(Utils.copy(elements), SetUtil.set(res));
    return res;
  }

  public Boolean isEmpty() {

    return Utils.equals(elements.size(), 0L);
  }

  public void clear() {

    elements = SetUtil.set();
  }

  public String toString() {

    return "PriorityQueue{" + "elements := " + Utils.toString(elements) + "}";
  }
}
