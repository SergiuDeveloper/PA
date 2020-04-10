import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistController {
    public static void create(String name, String country) throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance();
        PreparedStatement artistInsertionStatement = musicAlbums.getStatement("INSERT INTO Artists(Name, Country) VALUES(?, ?)");
        artistInsertionStatement.setString(1, name);
        artistInsertionStatement.setString(2, country);
        artistInsertionStatement.execute();
        artistInsertionStatement.close();
    }

    public static List<Artist> findByName(String name) throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance();
        PreparedStatement getArtistsStatement = musicAlbums.getStatement("SELECT ID, Country FROM Artists WHERE Name = ?");
        getArtistsStatement.setString(1, name);

        ResultSet artistRows = getArtistsStatement.executeQuery();

        List<Artist> artists = new ArrayList<>();
        while (artistRows.next())
            artists.add(new Artist(artistRows.getInt(1), name, artistRows.getString(2)));
        artistRows.close();
        getArtistsStatement.close();

        return artists;
    }

    public static List<Artist> findByID(int id) throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance();
        PreparedStatement getArtistsStatement = musicAlbums.getStatement("SELECT Name, Country FROM Artists WHERE ID = ?");
        getArtistsStatement.setInt(1, id);

        ResultSet artistRows = getArtistsStatement.executeQuery();

        List<Artist> artists = new ArrayList<>();
        while (artistRows.next())
            artists.add(new Artist(id, artistRows.getString(1), artistRows.getString(2)));
        artistRows.close();
        getArtistsStatement.close();

        return artists;
    }
}
