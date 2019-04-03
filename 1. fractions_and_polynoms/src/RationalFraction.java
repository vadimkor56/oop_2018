public class RationalFraction {
    private int n;
    private int m;
    private double val;

    RationalFraction(int n_, int m_) {
        n = n_;
        m = m_;
        val = ((double) n) / m;
    }

    RationalFraction() {
    }

    public double getVal() {
        return val;
    }

}