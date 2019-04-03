import java.util.Vector;

public class Catalog {
    private Vector<Album> albums;
    private Vector<Collection> collections;

    Catalog(Vector<Genre> genres_, Vector<Album> albums_, Vector<Collection> collections_) {
        albums = albums_;
        collections = collections_;
    }

    Catalog() {
        albums = new Vector<>(10);
        collections = new Vector<>(10);
    }

    public void appendAlbum(Album album) {
        albums.add(album);
    }

    public void appendCollection(Collection collection) {
        collections.add(collection);
    }

    public Vector<Song> findSongs(Singer singer) {
        Vector<Song> res = new Vector<Song>(100);
        for (Album album: albums) {
            if (album.getSinger() == singer)
                res.addAll(album.getSongs());
        }
        return res;
    }

    public Vector<Song> findSongs(Genre genre) {
        Vector<Song> res = new Vector<Song>(100);
        for (Album album: albums) {
            if (album.getGenre() == genre || genre.getSubgenres().contains(album.getGenre()))
                res.addAll(album.getSongs());
        }
        return res;
    }

    public Vector<Singer> findSingers(Genre genre) {
        Vector<Singer> res = new Vector<Singer>(10);
        for (Album album: albums) {
            if ((album.getGenre() == genre || genre.getSubgenres().contains(album.getGenre()) && !res.contains(album.getSinger())))
                res.add(album.getSinger());
        }
        return res;
    }

    public Vector<Album> findAlbums(Genre genre, Integer year) {
        Vector<Album> res = new Vector<Album>(10);
        for (Album album: albums) {
            if ((album.getGenre() == genre || genre.getSubgenres().contains(album.getGenre()) && album.getYear().equals(year)))
                res.add(album);
        }
        return res;
    }

    public Vector<Collection> findCollection(Genre genre, Integer year) {
        Vector<Collection> res = new Vector<Collection>(10);
        for (Collection collection: collections) {
            for (Song song: collection.getSongs())
                if ((song.getAlbum().getGenre() == genre || genre.getSubgenres().contains(song.getAlbum().getGenre())) &&
                        song.getAlbum().getYear().equals(year)) {
                    res.add(collection);
                    break;
                }
        }
        return res;
    }



}
