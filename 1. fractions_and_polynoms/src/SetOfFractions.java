import java.util.*;

public class SetOfFractions {

    private Vector<RationalFraction> fractions = new Vector<RationalFraction>(1000);
    Vector<Double> coeffs = new Vector<Double>(1000);
    private RationalFraction min = new RationalFraction((int) Double.POSITIVE_INFINITY, 1);
    private RationalFraction max = new RationalFraction((int) Double.NEGATIVE_INFINITY, 1);

    private Map<Double, Integer> numberOfGreaterDict = new HashMap<Double, Integer>(1000);
    private Map<Double, Integer> numberOfSmallerDict = new HashMap<Double, Integer>(1000);

    SetOfFractions(Vector<RationalFraction> fractions_) {
        fractions = fractions_;
        for (int i = 0; i < fractions.size(); ++i) {
            coeffs.add(fractions.elementAt(i).getVal());
        }
    }

    SetOfFractions() {
    }


    public void append(RationalFraction f) {
        fractions.add(f);
        if (f.getVal() > max.getVal())
            max = f;
        if (f.getVal() < min.getVal())
            min = f;
        coeffs.add(f.getVal());

        for (Map.Entry<Double, Integer> item : numberOfGreaterDict.entrySet()) {
            if (f.getVal() > item.getKey())
                numberOfGreaterDict.replace(item.getKey(), item.getValue() + 1);
        }

        for (Map.Entry<Double, Integer> item : numberOfSmallerDict.entrySet()) {
            if (f.getVal() < item.getKey())
                numberOfGreaterDict.replace(item.getKey(), item.getValue() + 1);
        }

    }

    public RationalFraction min() {
        return min;
    }

    public RationalFraction max() {
        return max;
    }

    public int numberOfSmaller(RationalFraction f) {
        if (numberOfSmallerDict.containsKey(f.getVal()))
            return numberOfSmallerDict.get(f.getVal());
        else {
            int res = 0;
            for (int i = 0; i < fractions.size(); ++i)
                if (fractions.elementAt(i).getVal() < f.getVal())
                    res += 1;
            numberOfSmallerDict.put(f.getVal(), res);
            return res;
        }
    }

    public int numberOfGreater(RationalFraction f) {
        if (numberOfGreaterDict.containsKey(f.getVal()))
            return numberOfGreaterDict.get(f.getVal());
        else {
            int res = 0;
            for (int i = 0; i < fractions.size(); ++i)
                if (fractions.elementAt(i).getVal() > f.getVal())
                    res += 1;
            numberOfGreaterDict.put(f.getVal(), res);
            return res;
        }
    }

}