import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MusicAlbums {
    private MusicAlbums(Database database) {
        this.database = database;
        MusicAlbums.singletonInstance = this;
    }

    private Database database;

    private static MusicAlbums singletonInstance;

    public void initializeDatabase() throws SQLException {
        this.database.initializeConnection();

        this.database.setAutoCommit(false);

        Statement databaseInitializationStatement = this.database.getStatement();
        for (var databaseInitializationQueryPart: MusicAlbums.databaseInitializationQueryParts)
            databaseInitializationStatement.addBatch(databaseInitializationQueryPart);
        databaseInitializationStatement.executeBatch();
        databaseInitializationStatement.close();

        this.database.setAutoCommit(true);
    }

    public void initializeConnection() throws SQLException {
        this.database.initializeConnection();

        PreparedStatement useSchemaStatement = (PreparedStatement) this.database.getStatement(MusicAlbums.useSchemaQuery);
        useSchemaStatement.execute();
        useSchemaStatement.close();
    }

    public void deinitializeConnection() throws SQLException {
        this.database.deinitializeConnection();
    }

    public PreparedStatement getStatement(String query) throws SQLException {
        return this.database.getStatement(query);
    }

    public Statement getStatement() throws SQLException {
        return this.database.getStatement();
    }

    public void setAutoCommit(boolean autoCommitValue) throws SQLException {
        this.database.setAutoCommit(autoCommitValue);
    }

    public static MusicAlbums getInstance(String dbServer, String dbUsername, String dbPassword) {
        if (MusicAlbums.singletonInstance == null) {
            Database database = Database.getDatabase(dbServer, dbUsername, dbPassword);
            MusicAlbums.singletonInstance = new MusicAlbums(database);
        }
        return MusicAlbums.singletonInstance;
    }

    public static MusicAlbums getInstance() {
        return MusicAlbums.singletonInstance;
    }

    private static String[] databaseInitializationQueryParts = {
            "DROP SCHEMA IF EXISTS MusicAlbums",
            "CREATE SCHEMA MusicAlbums",
            "USE MusicAlbums",

            "DROP TABLE IF EXISTS Artists",
            "CREATE TABLE Artists(" +
                    "ID 		INT 			NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Name       VARCHAR(128) 	NOT NULL," +
                    "Country    VARCHAR(128) 	NOT NULL" +
                    ")",

            "DROP TABLE IF EXISTS Albums",
            "CREATE TABLE Albums(" +
                    "ID 			INT 			NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Name 			VARCHAR(128) 	NOT NULL," +
                    "Artist_ID 		INT 			NOT NULL REFERENCES Artists.ID," +
                    "Release_Year 	INT 			NOT NULL REFERENCES Artists ON DELETE RESTRICT" +
                    ")",

            "DROP TABLE IF EXISTS Charts",
            "CREATE TABLE Charts(" +
                    "ID 	INT 			NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Name 	VARCHAR(128) 	NOT NULL" +
                    ")",

            "DROP TABLE IF EXISTS ChartAlbums",
            "CREATE TABLE ChartAlbums(" +
                    "ID 		INT 	NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Chart_ID 	INT     NOT NULL REFERENCES Charts.ID," +
                    "Album_ID 	INT 	NOT NULL REFERENCES Albums.ID," +
                    "Place      INT     NOT NULL" +
                    ")"
    };

    private static String useSchemaQuery = "USE MusicAlbums";
}