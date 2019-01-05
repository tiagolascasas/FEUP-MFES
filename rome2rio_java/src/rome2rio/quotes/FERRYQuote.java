package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FERRYQuote {
  private static int hc = 0;
  private static FERRYQuote instance = null;

  public FERRYQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static FERRYQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new FERRYQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof FERRYQuote;
  }

  public String toString() {

    return "<FERRY>";
  }
}
