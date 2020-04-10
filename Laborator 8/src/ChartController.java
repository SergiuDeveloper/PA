import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ChartController {
    public static void create(String name, List<ChartAlbum> chartAlbums) throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance();
        PreparedStatement chartInsertionStatement = musicAlbums.getStatement("INSERT INTO Charts(Name) VALUES(?)");
        chartInsertionStatement.setString(1, name);
        chartInsertionStatement.execute();
        chartInsertionStatement.close();

        PreparedStatement getInsertedChartID = musicAlbums.getStatement("SELECT ID FROM Charts WHERE Name = ? ORDER BY ID DESC");
        getInsertedChartID.setString(1, name);
        int chartID = getInsertedChartID.executeQuery().getInt(1);
        getInsertedChartID.close();

        musicAlbums.setAutoCommit(false);
        Statement insertAlbumStatement = musicAlbums.getStatement();
        String albumInsertionQuery;
        for (var chartAlbum: chartAlbums) {
            albumInsertionQuery = String.format("INSERT INTO ChartAlbums(Chart_ID, Album_ID, Place) VALUES(%d, %d, %d)", chartID, chartAlbum.getAlbum().getID(), chartAlbum.getPlace());
            insertAlbumStatement.addBatch(albumInsertionQuery);
        }
        insertAlbumStatement.executeBatch();
        insertAlbumStatement.close();
        musicAlbums.setAutoCommit(true);
    }

    public static Chart getChartByID(int chartID) throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance();

        PreparedStatement getChartStatement = musicAlbums.getStatement("SELECT Name FROM Charts WHERE ID = ?");
        getChartStatement.setInt(1, chartID);
        ResultSet chartRows = getChartStatement.executeQuery();

        if (!chartRows.next())
            return null;

        Chart chart = new Chart(chartID, chartRows.getString(1));
        chartRows.close();
        getChartStatement.close();

        PreparedStatement getChartAlbumsStatement = musicAlbums.getStatement("SELECT Album_ID, Place FROM ChartAlbums WHERE Chart_ID = ?");
        getChartAlbumsStatement.setInt(1, chartID);
        ResultSet chartAlbumRows = getChartAlbumsStatement.executeQuery();

        List<ChartAlbum> chartAlbumList = new ArrayList<>();
        PreparedStatement getAlbumStatement = musicAlbums.getStatement("SELECT Name, Artist_ID, Release_Year FROM Albums WHERE ID = ?");
        ResultSet albumRows = null;
        Album queriedAlbum;
        int albumID;
        while (chartAlbumRows.next()) {
            albumID = chartAlbumRows.getInt(1);
            getAlbumStatement.setInt(1, albumID);
            albumRows = getAlbumStatement.executeQuery();
            if (!albumRows.next())
                continue;

            queriedAlbum = new Album(albumID, albumRows.getString(1), albumRows.getInt(2), albumRows.getInt(3));
            chartAlbumList.add(new ChartAlbum(queriedAlbum, chartAlbumRows.getInt(2)));
        }

        if (albumRows != null && !albumRows.isClosed())
            albumRows.close();
        getAlbumStatement.close();
        chartAlbumRows.close();
        getChartAlbumsStatement.close();

        chartAlbumList.sort(Comparator.comparingInt(ChartAlbum::getPlace));

        for (var chartAlbum: chartAlbumList)
            chart.addAlbumToChart(chartAlbum);

        return chart;
    }

    public static void printAllCharts() throws SQLException {
        MusicAlbums musicAlbums = MusicAlbums.getInstance();

        PreparedStatement getChartIDsStatement = musicAlbums.getStatement("SELECT ID FROM Charts");
        ResultSet chartRows = getChartIDsStatement.executeQuery();

        Chart currentChart;
        Artist currentArtist;
        while (chartRows.next()) {
            currentChart = ChartController.getChartByID(chartRows.getInt(1));
            assert currentChart != null;

            System.out.println("The " + currentChart.getName() + " chart:");
            for (var chartAlbum: currentChart.getChartAlbumList()) {
                currentArtist = ArtistController.findByID(chartAlbum.getAlbum().getArtistID()).get(0);
                System.out.println(chartAlbum.getPlace() + ". " + chartAlbum.getAlbum().getName() + ", released by " + currentArtist.getName() + "(" + currentArtist.getCountry() + "), in " +
                        +chartAlbum.getAlbum().getReleaseYear());
                System.out.println();
            }
        }

        chartRows.close();
        getChartIDsStatement.close();
    }
}
