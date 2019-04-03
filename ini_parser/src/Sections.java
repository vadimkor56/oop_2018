import java.util.Vector;

public class Sections {
    private Vector<Section> sections;

    public void append(Section section) {
        sections.add(section);
    }

    Sections() {
        sections = new Vector<>(10);
    }

    public Section elementAt(Integer index) {
        return sections.elementAt(index);
    }

    public Parameter findParam(String sectionName, String paramName) {
        try {
            for (Section section : sections) {
                if (section.getName().equals(sectionName)) {
                    for (Parameter parameter : section.getParameters()) {
                        if (parameter.getName().equals(paramName))
                            return parameter;
                    }
                }
            }
            throw new Exception("Заданной пары СЕКЦИЯ ПАРАМЕТР нет в конфигурационном файле");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Vector<Section> getSections() {
        return sections;
    }
}
