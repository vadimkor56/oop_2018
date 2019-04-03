import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        Vector<String> file = new Vector<>(100);
        Integer indexOfCurrentSection = -1;

        try {
            Files.lines(Paths.get("input.txt")).forEach(file::add);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Sections sections = parser.parsing(file);

        for (Section section : sections.getSections()) {
            System.out.println("[" + section.getName() + "]");
            for (Parameter parameter : section.getParameters()) {
                System.out.println(parameter.getName() + "    " + parameter.getValue() + "    " + parameter.getValue().getClass().getName());
            }
        }

        System.out.println(sections.findParam("COMMON", "StatisterTimeMs").getValue());
        System.out.println(sections.findParam("ADC_DEV", "Driver").getValue());
    }

}
