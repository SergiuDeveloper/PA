import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Database {
    private Database(String server, String schema, String username, String password) {
        this.server = server;
        this.schema = schema;
        this.username = username;
        this.password = password;

        Database.databaseList.add(this);
    }

    private String server;
    public String getServer() { return this.server; }

    private String schema;
    public String getSchema() { return this.schema; }

    private String username;
    public String getUsername() { return this.username; }

    private String password;
    public String getPassword() { return this.password; }

    private Connection connection;

    private static List<Database> databaseList = new ArrayList<>();

    public void initializeConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://" + this.server + (this.schema == null ? "" : ("/" + this.schema)) + "?user=" + this.username + "&password=" + this.password);
    }

    public void deinitializeConnection() throws SQLException {
        this.connection.close();
    }

    public PreparedStatement getStatement(String query) throws SQLException {
        return this.connection.prepareStatement(query);
    }

    public Statement getStatement() throws SQLException {
        return this.connection.createStatement();
    }

    public void setAutoCommit(boolean autoCommitValue) throws SQLException {
        this.connection.setAutoCommit(autoCommitValue);
    }

    public static Database getDatabase(String server, String schema, String username, String password) {
        Database.databaseList.removeIf(Objects::isNull);
        for (var database: Database.databaseList)
            if (database.server.equals(server) && database.schema.equals(schema) && database.username.equals(username) && database.password.equals(password))
                return database;
        return new Database(server, schema, username, password);
    }

    public static Database getDatabase(String server, String username, String password) {
        return Database.getDatabase(server, null, username, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Database)) return false;
        Database database = (Database) o;
        return server.equals(database.server) &&
                Objects.equals(schema, database.schema) &&
                username.equals(database.username) &&
                password.equals(database.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, schema, username, password);
    }
}
