package heca;

import java.util.Comparator;

public class CostComparator implements Comparator<Attribute>{

	// fractional knapsack comparator having only Weight(weight per unit) but all items are unbounded..same Value...Hence value ignored
    @Override
    public int compare(Attribute o1, Attribute o2) {
        return ((o1.limit -o1.consumed)*(int)o1.perUnitWeight )      -	((o2.limit -o2.consumed)*(int)o2.perUnitWeight ) > 0 ? 1:0  ;
    }

}
