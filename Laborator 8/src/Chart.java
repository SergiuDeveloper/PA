import java.util.List;

public class Chart {
    public Chart(int id, String name, List<ChartAlbum> chartAlbumList) {
        this.id = id;
        this.name = name;
        this.chartAlbumList = chartAlbumList;
    }

    public Chart(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private Chart() { }

    private int id;
    public int getID() { return this.id; }

    private String name;
    public String getName() { return this.name; }

    private List<ChartAlbum> chartAlbumList;
    public List<ChartAlbum> getChartAlbumList() { return this.chartAlbumList; }

    public void addAlbumToChart(ChartAlbum chartAlbum) {
        this.chartAlbumList.add(chartAlbum);
    }
}
