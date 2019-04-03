public class  Song {
    private String name;
    private Album album;
    private Singer singer;

    Song(String name_, Album album_) {
        name = name_;
        album = album_;
        singer = album_.getSinger();
    }

    public Album getAlbum() {
        return album;
    }

    public String getName() {
        return name;
    }
}
