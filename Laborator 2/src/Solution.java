import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Solution class - class that specializes on solving the MDVSP problem
 * @author Nistor Sergiu
 *
 **/
public class Solution {
    /**
     * solve method - entry point for the application
     *
     * @param   problem     the problem instance
     * @return  List  list of tours for each vehicle of each depot
     **/
    public static List<Tour> solve(Problem problem) {
        List<Tour> tours = new ArrayList<Tour>();

        List<Vehicle> vehicles = problem.getVehicles();
        Tour currentTour;
        Vehicle currentVehicle;
        for (int i = 0; i < vehicles.size(); ++i) {
            currentTour = new Tour();
            tours.add(currentTour);
            currentVehicle = vehicles.get(i);

            currentTour.addTourLocation(currentVehicle.getDepot());
            currentVehicle.setTour(currentTour);
            currentTour.setVehicle(currentVehicle);
        }

        List<Client> clientsPriorityList = new ArrayList<Client>();
        clientsPriorityList.addAll(problem.getClients());
        clientsPriorityList.sort(new ClientPriorityComparator());

        int currentVehicleTurn = 0;
        for (var client : clientsPriorityList) {
            tours.get(currentVehicleTurn).addTourLocation(client);

            currentVehicleTurn = (currentVehicleTurn + 1) % tours.size();
        }

        for (var tour : tours)
            tour.addTourLocation(tour.getPath().get(0));

        return tours;
    }

    /**
     * Ascendant comparator for the Collection<Client>.sort() method
     *
     **/
    private static class ClientPriorityComparator implements Comparator<Client> {
        /**
         * Ascendant compare function
         *
         * @param   a       left left
         * @param   b       right term
         * @return  int     < 0 if a should be first, > 0 if b should be first, 0 if the order shoud not change
         **/
        @Override
        public int compare(Client a, Client b) {
            return a.getNumberOfOrder() - b.getNumberOfOrder();
        }
    }
}