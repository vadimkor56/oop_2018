import java.util.Vector;

public class Singer {
    private String name;
    private Vector<Album> albums;

    Singer(String name_, Vector<Album> albums_) {
        name = name_;
        albums = albums_;
    }

    Singer(String name_) {
        name = name_;
    }

    public String getName() {
        return name;
    }

    public void appendAlbum(Album album) {
        if (album.getSinger().getName().equals(name))
            albums.add(album);
        else
            System.out.println("Singer error");
    }
}
