import java.util.List;

/**
 * Main class - entry point for the application
 * @author Nistor Sergiu
 *
 **/
public class Main {
    /**
     * Main method - entry point for the application
     *
     * @param   args command-line arguments
     **/
    public static void main(String[] args) {
        Depot d1 = new Depot("D1");
        Depot d2 = new Depot("D2");
        Vehicle v1 = new Truck("V1", d1);
        Vehicle v2 = new Truck("V2", d1);
        Vehicle v3 = new Truck("V3", d2);
        Client c1 = new Client("C1", 1);
        Client c2 = new Client("C2", 1);
        Client c3 = new Client("C3", 2);
        Client c4 = new Client("C4", 2);
        Client c5 = new Client("C5", 3);

        Problem problem = new Problem(List.of(d1, d2), List.of(c1, c2, c3, c4, c5));

        List<Tour> tours = Solution.solve(problem);
        List<Tour.TourLocation> tourLocations;
        for (var tour : tours) {
            System.out.print(tour.getVehicle().getName() + ": ");
            tourLocations = tour.getPath();
            for (int i = 0; i < tourLocations.size(); ++i)
                System.out.print(tourLocations.get(i).getName() + (i == tourLocations.size() - 1 ? "" : " -> "));
            System.out.println();
        }
    }
}
