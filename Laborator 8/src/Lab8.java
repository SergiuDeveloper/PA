import java.sql.SQLException;

public class Lab8 {
    public static void main(String[] args) throws SQLException {
        MusicAlbumsTestUnit.Test1();
        MusicAlbumsTestUnit.Test2();

        MusicAlbums musicAlbums = MusicAlbums.getInstance("-", "-", "-");
        musicAlbums.initializeConnection();

        ChartController.printAllCharts();

        musicAlbums.deinitializeConnection();
    }
}