import java.util.Vector;

public class Album {
    private String name;
    private Vector<Song> songs;
    private Singer singer;
    private Genre genre;
    private Integer year;

    Album(String name_, Singer singer_, Vector<Song> songs_, Genre genre_, Integer year_) {
        name = name_;
        singer = singer_;
        songs = songs_;
        genre = genre_;
        year = year_;
    }

    Album(String name_, Singer singer_, Genre genre_, Integer year_) {
        name = name_;
        singer = singer_;
        genre = genre_;
        year = year_;
        songs = new Vector<Song>(15);
    }

    public void appendSong(Song song) {
        songs.add(song);
    }

    public Singer getSinger() {
        return singer;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Integer getYear() {
        return year;
    }

    public Vector<Song> getSongs() {
        return songs;
    }
}
