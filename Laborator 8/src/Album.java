public class Album {
    public Album(int id, String name, int artistID, int releaseYear) {
        this.id = id;
        this.name = name;
        this.artistID = artistID;
        this.releaseYear = releaseYear;
    }

    private Album() { }

    private int id;
    public int getID() { return this.id; }

    private String name;
    public String getName() { return this.name; }

    private int artistID;
    public int getArtistID() { return this.artistID; }

    private int releaseYear;
    public int getReleaseYear() { return this.releaseYear; }
}
