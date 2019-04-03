import java.util.Vector;

public class Genre {
    private String name;
    private Vector<Genre> subgenres;

    Genre(String name_, Vector<Genre> subgenres_) {
        name = name_;
        subgenres = subgenres_;
    }

    Genre(String name_) {
        name = name_;
        subgenres = new Vector<Genre>(10);
    }

    public Vector<Genre> getSubgenres() {
        return subgenres;
    }

    public void append(Genre subgenre) {
        subgenres.add(subgenre);
    }
}
