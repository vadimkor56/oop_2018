import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Genre rock = new Genre("Rock");
        rock.append(new Genre("Punk-Rock"));
        rock.append(new Genre("Pop-Rock"));
        rock.append(new Genre("Indie-Rock"));

        Genre rap = new Genre("Rap");
        rap.append(new Genre("Hip-Hop"));
        rap.append(new Genre("Alternative Rap"));
        rap.append(new Genre("Cloud Rap"));

        Genre jazz = new Genre("Jazz");
        jazz.append(new Genre("Soul-Jazz"));
        jazz.append(new Genre("Boogie-woogie"));
        jazz.append(new Genre("Hot-Jazz"));

        Singer Drake = new Singer("Drake");
        Vector<Album> DrakeAlbums = new Vector<Album>(3);
        Album DrakeScorpionAlbum = new Album("Scorpion", Drake, rap.getSubgenres().elementAt(0), 2018);
        DrakeAlbums.add(DrakeScorpionAlbum);
        Album DrakeMoreLifeAlbum = new Album("More Life", Drake, rap.getSubgenres().elementAt(1), 2017);
        DrakeAlbums.add(DrakeMoreLifeAlbum);
        Album DrakeViewsAlbum = new Album("Views", Drake, rap.getSubgenres().elementAt(2), 2016);
        DrakeAlbums.add(DrakeViewsAlbum);
        Vector<Song> DrakeSongs = new Vector<Song>(9);

        Song DrakeNonestopSong = new Song("Nonestop", DrakeScorpionAlbum);
        DrakeSongs.add(DrakeNonestopSong);
        Song DrakeEmotionlessSong = new Song("Emotionless", DrakeScorpionAlbum);
        DrakeSongs.add(DrakeEmotionlessSong);
        Song DrakeElevateSong = new Song("Elevate", DrakeScorpionAlbum);
        DrakeSongs.add(DrakeElevateSong);
        DrakeSongs.add(new Song("Blem", DrakeMoreLifeAlbum));
        DrakeSongs.add(new Song("Passion Fruit", DrakeMoreLifeAlbum));
        DrakeSongs.add(new Song("4422", DrakeMoreLifeAlbum));
        DrakeSongs.add(new Song("9", DrakeViewsAlbum));
        DrakeSongs.add(new Song("Hype", DrakeViewsAlbum));
        DrakeSongs.add(new Song("Redemption", DrakeViewsAlbum));

        for (Song song : DrakeSongs) {
            if (song.getAlbum().getName().equals(DrakeScorpionAlbum.getName()))
                DrakeScorpionAlbum.appendSong(song);
            else if (song.getAlbum().getName().equals(DrakeMoreLifeAlbum.getName()))
                DrakeMoreLifeAlbum.appendSong(song);
            else if (song.getAlbum().getName().equals(DrakeViewsAlbum.getName()))
                DrakeViewsAlbum.appendSong(song);
        }

        Singer NikeBorzov = new Singer("Найк Борзов");
        Vector<Album> NikeBorzovAlbums = new Vector<Album>(3);
        Album NikeBorzovMoleculaAlbum = new Album("Молекула", NikeBorzov, rock.getSubgenres().elementAt(0), 2015);
        NikeBorzovAlbums.add(NikeBorzovMoleculaAlbum);
        Album NikeBorzovIznutriAlbum = new Album("Изнутри", NikeBorzov, rock.getSubgenres().elementAt(1), 2010);
        NikeBorzovAlbums.add(NikeBorzovIznutriAlbum);
        Album NikeBorzovVezdeNigdeAlbum = new Album("Везде и нигде", NikeBorzov, rock.getSubgenres().elementAt(2), 2014);
        NikeBorzovAlbums.add(NikeBorzovVezdeNigdeAlbum);
        Vector<Song> NikeBorzovSongs = new Vector<Song>(9);
        NikeBorzovSongs.add(new Song("Ева", NikeBorzovMoleculaAlbum));
        Song NikeBorzovVerhomNaZvezdeSong = new Song("Верхом на здезде", NikeBorzovMoleculaAlbum);
        NikeBorzovSongs.add(NikeBorzovVerhomNaZvezdeSong);
        Song NikeBorzovOdnaOnaSong = new Song("Одна она", NikeBorzovMoleculaAlbum);
        NikeBorzovSongs.add(NikeBorzovOdnaOnaSong);
        NikeBorzovSongs.add(new Song("Полёт", NikeBorzovIznutriAlbum));
        NikeBorzovSongs.add(new Song("Сновидения", NikeBorzovIznutriAlbum));
        NikeBorzovSongs.add(new Song("Начало дня", NikeBorzovIznutriAlbum));
        NikeBorzovSongs.add(new Song("Поток", NikeBorzovVezdeNigdeAlbum));
        NikeBorzovSongs.add(new Song("Видение", NikeBorzovVezdeNigdeAlbum));
        NikeBorzovSongs.add(new Song("Пустота", NikeBorzovVezdeNigdeAlbum));

        for (Song song : NikeBorzovSongs) {
            if (song.getAlbum().getName().equals(NikeBorzovMoleculaAlbum.getName()))
                NikeBorzovMoleculaAlbum.appendSong(song);
            else if (song.getAlbum().getName().equals(NikeBorzovIznutriAlbum.getName()))
                NikeBorzovIznutriAlbum.appendSong(song);
            else if (song.getAlbum().getName().equals(NikeBorzovVezdeNigdeAlbum.getName()))
                NikeBorzovVezdeNigdeAlbum.appendSong(song);
        }

        Catalog catalog = new Catalog();
        for (Album album : DrakeAlbums)
            catalog.appendAlbum(album);
        for (Album album : NikeBorzovAlbums)
            catalog.appendAlbum(album);

        System.out.println("Drake:");
        catalog.findSongs(Drake).forEach(song -> System.out.println(song.getName()));
        System.out.println("----");
        System.out.println("Найк Борзов:");
        catalog.findSongs(NikeBorzov).forEach(song -> System.out.println(song.getName()));
        System.out.println("-------------------");

        System.out.println("Rock:");
        catalog.findSongs(rock).forEach(song -> System.out.println(song.getName()));
        System.out.println("----");
        System.out.println("Alternative rap:");
        catalog.findSongs(rap.getSubgenres().elementAt(1)).forEach(song -> System.out.println(song.getName()));
        System.out.println("-------------------");

        System.out.println("Rap singers:");
        catalog.findSingers(rap).forEach(singer -> System.out.println(singer.getName()));
        System.out.println("-------------------");

        System.out.println("Rock albums 2015:");
        catalog.findAlbums(rock, 2015).forEach(album -> System.out.println(album.getName()));
        System.out.println("-------------------");
        System.out.println("Rap albums 2018:");
        catalog.findAlbums(rap, 2018).forEach(album -> System.out.println(album.getName()));
        System.out.println("-------------------");

        Collection mostPopularCollection = new Collection("Most Popular");
        mostPopularCollection.append(DrakeNonestopSong);
        mostPopularCollection.append(DrakeElevateSong);
        mostPopularCollection.append(DrakeEmotionlessSong);
        mostPopularCollection.append(NikeBorzovOdnaOnaSong);
        mostPopularCollection.append(NikeBorzovVerhomNaZvezdeSong);

        catalog.appendCollection(mostPopularCollection);

        System.out.println("Collections that contain rap songs released in 2018:");
        catalog.findCollection(rap, 2018).forEach(collection -> System.out.println(collection.getName()));
        System.out.println("-------------------");
        System.out.println("Collections that contain rock songs released in 1967:");
        catalog.findCollection(rock, 1967).forEach(collection -> System.out.println(collection.getName()));
        System.out.println("-------------------");

        for (Album album : DrakeAlbums) {
            System.out.println(album.getName() + ":");
            for (Song song : album.getSongs())
                System.out.println(song.getName());
            System.out.println("----");
        }
        System.out.println("-------------------");
        for (Album album : NikeBorzovAlbums) {
            System.out.println(album.getName() + ":");
            for (Song song : album.getSongs())
                System.out.println(song.getName());
            System.out.println("----");
        }
    }
}
