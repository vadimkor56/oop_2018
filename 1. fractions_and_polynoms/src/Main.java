import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        SetOfFractions fractions = new SetOfFractions();
        Vector<String> file = new Vector<>(1000);

        Files.lines(Paths.get("input.txt")).forEach(file::add);
        for (String item: file) {
            int n = Integer.parseInt(item.substring(0, item.indexOf("/")));
            int m = Integer.parseInt(item.substring(item.indexOf("/") + 1, item.length()));
            fractions.append(new RationalFraction(n, m));
        }

        System.out.println(fractions.min().getVal());
        System.out.println(fractions.max().getVal());
        System.out.println(fractions.numberOfSmaller(new RationalFraction(1, 1)));
        System.out.println(fractions.numberOfGreater(new RationalFraction(1, 1)));

        Polynom polynom = new Polynom(fractions, 2.0);
        System.out.println(polynom.summ());
        polynom.output();
    }

}
