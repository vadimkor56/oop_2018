import java.util.Vector;

public class Polynom {
    Polynom(SetOfFractions fractions, double x_) {
        coeffs = fractions.coeffs;
        degree = fractions.coeffs.size() - 1;
        x = x_;
    }

    public double summ() {
        double res = 0;
        for (int i = 0; i <= degree; ++i) {
            res += Math.pow(x, degree - i) * coeffs.elementAt(i);
        }
        return res;
    }

    public void output() {
        for (int i = 0; i <= degree; ++i) {
            System.out.print(coeffs.elementAt(i) + "*x^" + (degree - i));
            if (i != degree)
                System.out.print(" + ");
        }
    }

    private Vector<Double> coeffs;
    private int degree;
    private double x;
}
