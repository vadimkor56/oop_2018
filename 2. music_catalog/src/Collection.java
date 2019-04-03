import java.util.Vector;

public class Collection {
    private String name;
    private Vector<Song> songs;

    Collection(String name_, Vector<Song> songs_) {
        name = name_;
        songs = songs_;
    }

    Collection(String name_) {
        name = name_;
        songs = new Vector<Song>(20);
    }

    public void append(Song song) {
        songs.add(song);
    }

    public Vector<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }
}
