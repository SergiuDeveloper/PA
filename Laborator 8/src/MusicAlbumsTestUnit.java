import java.sql.SQLException;
import java.util.List;

public class MusicAlbumsTestUnit {
    public static void Test1() throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance("-", "-", "-");
        musicAlbums.initializeDatabase();

        ArtistController.create("Sergiu Nistor", "Romania");
        ArtistController.create("Negura Bunget", "Romania");
        ArtistController.create("Nhor", "UK");

        List<Artist> artists1 = ArtistController.findByName("Sergiu Nistor");
        List<Artist> artists2 = ArtistController.findByName("Negura Bunget");
        List<Artist> artists3 = ArtistController.findByName("Nhor");

        assert artists1.size() == 1 && artists2.size() == 1 && artists3.size() == 1;

        AlbumController.create("Forevermore", artists1.get(0).getID(), 2018);
        AlbumController.create("Om", artists2.get(0).getID(), 2010);
        AlbumController.create("Innarborat Kosmos", artists2.get(0).getID(), 2002);
        AlbumController.create("Nhor album 1", artists3.get(0).getID(), 2006);

        List<Album> albums1 = AlbumController.findByArtist(artists1.get(0).getID());
        List<Album> albums2 = AlbumController.findByArtist(artists2.get(0).getID());
        List<Album> albums3 = AlbumController.findByArtist(artists3.get(0).getID());

        assert albums1.size() == 1 && albums2.size() == 2 && albums3.size() == 1;

        musicAlbums.deinitializeConnection();
    }

    public static void Test2() throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance("-", "-", "-");
        musicAlbums.initializeConnection();

        ArtistController.create("Sergiu Nistor2", "Romania");
        ArtistController.create("Negura Bunget2", "Romania");
        ArtistController.create("Nhor2", "UK");

        List<Artist> artists1 = ArtistController.findByName("Sergiu Nistor");
        List<Artist> artists2 = ArtistController.findByName("Negura Bunget");
        List<Artist> artists3 = ArtistController.findByName("Nhor");

        assert artists1.size() >= 1 && artists2.size() >= 1 && artists3.size() >= 1;

        AlbumController.create("Forevermore2", artists1.get(0).getID(), 2018);
        AlbumController.create("Om2", artists2.get(0).getID(), 2010);
        AlbumController.create("Innarborat Kosmos2", artists2.get(0).getID(), 2002);
        AlbumController.create("Nhor album 2", artists3.get(0).getID(), 2006);

        List<Album> albums1 = AlbumController.findByArtist(artists1.get(0).getID());
        List<Album> albums2 = AlbumController.findByArtist(artists2.get(0).getID());
        List<Album> albums3 = AlbumController.findByArtist(artists3.get(0).getID());

        assert albums1.size() >= 1 && albums2.size() >= 2 && albums3.size() >= 1;

        musicAlbums.deinitializeConnection();
    }
}
