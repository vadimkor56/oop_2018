import java.io.File;
import java.util.Vector;

public class Parser {
    private Sections sections = new Sections();
    private Integer indexOfCurrentSection = -1;

    public Sections parsing(Vector<String> file) {
        for (String line : file) {
            if (line.contains("[") && line.contains("]") && !line.substring(0, line.indexOf("[")).contains(";")) {
                try {
                    String sectionName = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                    if (sectionName.contains(" ") || !sectionName.matches("[a-zA-Z0-9_]+"))
                        throw new Exception("В названии секции есть недопустимые символы");
                    sections.append(new Section(sectionName));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                indexOfCurrentSection += 1;
            } else if (line.charAt(0) != ';') {
                String parameterName = line.substring(0, line.indexOf("=") - 1);
                String parameterValue = line.substring(line.indexOf("=") + 2, line.contains(";") ? line.indexOf(";") - 1 : line.length());

                String tempParamValue = parameterValue.replace(".", "0");
                if (parameterValue.matches("[0-9]+")) {
                    Integer value = Integer.valueOf(parameterValue);
                    sections.elementAt(indexOfCurrentSection).append(new Parameter<Integer>(parameterName, value));
                } else if (tempParamValue.matches("[0-9]+")) {
                    Double value = Double.valueOf(parameterValue);
                    sections.elementAt(indexOfCurrentSection).append(new Parameter<Double>(parameterName, value));
                } else {
                    sections.elementAt(indexOfCurrentSection).append(new Parameter<String>(parameterName, parameterValue));
                }
            }
        }
        return sections;
    }
}
