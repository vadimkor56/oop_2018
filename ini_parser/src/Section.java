import java.util.Vector;

public class Section {
    private String name;
    private Vector<Parameter> parameters;

    Section(String name_) {
        name = name_;
        parameters = new Vector<>(100);
    }

    public void append(Parameter parameter) {
        parameters.add(parameter);
    }

    public String getName() {
        return name;
    }

    public Vector<Parameter> getParameters() {
        return parameters;
    }
}
