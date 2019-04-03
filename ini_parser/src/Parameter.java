public class Parameter<T> {
    private String name;
    private T value;

    Parameter(String name_, T value_) {
        name = name_;
        value = value_;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }
}
