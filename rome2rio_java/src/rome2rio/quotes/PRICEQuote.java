package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PRICEQuote {
  private static int hc = 0;
  private static PRICEQuote instance = null;

  public PRICEQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static PRICEQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new PRICEQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof PRICEQuote;
  }

  public String toString() {

    return "<PRICE>";
  }
}
