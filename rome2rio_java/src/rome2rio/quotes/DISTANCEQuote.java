package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DISTANCEQuote {
  private static int hc = 0;
  private static DISTANCEQuote instance = null;

  public DISTANCEQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static DISTANCEQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new DISTANCEQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof DISTANCEQuote;
  }

  public String toString() {

    return "<DISTANCE>";
  }
}
