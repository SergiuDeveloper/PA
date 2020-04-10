public class Artist {
    public Artist(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    private Artist() { }

    private int id;
    public int getID() { return this.id; }

    private String name;
    public String getName() { return this.name; }

    private String country;
    public String getCountry() { return this.country; }
}
