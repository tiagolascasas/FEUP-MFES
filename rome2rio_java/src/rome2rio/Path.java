package rome2rio;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Path {
	public VDMSeq path;
	public Object travelType;
	public Object criteria;
	public Number cost;

	public void cg_init_Path_1(final Object tt, final Object crit, final Number c) {

		path = SeqUtil.seq();
		travelType = tt;
		criteria = crit;
		cost = c;
	}

	public Path(final Object tt, final Object crit, final Number c) {

		cg_init_Path_1(tt, crit, c);
	}

	public void addNode(final Node node) {

		path = SeqUtil.conc(SeqUtil.seq(node), Utils.copy(path));
	}

	public Boolean isPossible() {

		return path.size() > 1L;
	}

	public void print() {

		Number i = 1L;
		Boolean whileCond_3 = true;
		while (whileCond_3) {
			whileCond_3 = i.longValue() <= path.size();
			if (!(whileCond_3)) {
				break;
			}

			{
				IO.println(((Node) Utils.get(path, i)).location);
				i = i.longValue() + 1L;
			}
		}
	}

	public Path() {
	}

	public String toString() {

		return "Path{" + "path := " + Utils.toString(path) + ", travelType := " + Utils.toString(travelType)
				+ ", criteria := " + Utils.toString(criteria) + ", cost := " + Utils.toString(cost) + "}";
	}
}
