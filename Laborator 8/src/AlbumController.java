import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    public static void create(String name, int artistID, int releaseYear) throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance();
        PreparedStatement albumInsertionStatement = musicAlbums.getStatement("INSERT INTO Albums(Name, Artist_ID, Release_Year) VALUES(?, ?, ?)");
        albumInsertionStatement.setString(1, name);
        albumInsertionStatement.setInt(2, artistID);
        albumInsertionStatement.setInt(3, releaseYear);
        albumInsertionStatement.execute();
        albumInsertionStatement.close();
    }

    public static List<Album> findByArtist(int artistID) throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance();
        PreparedStatement getAlbumsStatement = musicAlbums.getStatement("SELECT ID, Name, Release_Year FROM Albums WHERE Artist_ID = ?");
        getAlbumsStatement.setInt(1, artistID);

        ResultSet albumRows = getAlbumsStatement.executeQuery();

        List<Album> albums = new ArrayList<>();
        while (albumRows.next())
            albums.add(new Album(albumRows.getInt(1), albumRows.getString(2), artistID, albumRows.getInt(3)));
        albumRows.close();
        getAlbumsStatement.close();

        return albums;
    }
}
