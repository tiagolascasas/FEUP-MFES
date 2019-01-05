package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TRAINQuote {
  private static int hc = 0;
  private static TRAINQuote instance = null;

  public TRAINQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static TRAINQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new TRAINQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof TRAINQuote;
  }

  public String toString() {

    return "<TRAIN>";
  }
}
