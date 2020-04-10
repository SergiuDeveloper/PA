public class ChartAlbum {
    public ChartAlbum(Album album, int place) {
        this.album = album;
        this.place = place;
    }

    private ChartAlbum() { }

    private Album album;
    public Album getAlbum() { return this.album; }

    private int place;
    public int getPlace() { return this.place; }
}
