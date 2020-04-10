import java.util.ArrayList;
import java.util.List;

/**
 * Tour class - defines the path of a vehicle
 * @author Nistor Sergiu
 *
 **/
public class Tour {
    /**
     * Tour class constructor - construct an empty Tour object
     *
     **/
    public Tour() {
        this.path = new ArrayList<TourLocation>();
    }

    /**
     * TourLocation class - defines a location in a tour
     * @author Nistor Sergiu
     *
     **/
    public static class TourLocation {
        /**
         * TourLocation class constructor
         *
         * @param   name represents the location's name
         **/
        public TourLocation(String name) {
            this.name = name;
        }

        protected String name;
        public String getName() {
            return this.name;
        }
        private void setName(String name) {
            this.name = name;
        }
    }

    private Vehicle vehicle;
    public Vehicle getVehicle() {
        return this.vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    private List<TourLocation> path;
    public List<TourLocation> getPath() {
        return this.path;
    }
    private void setPath(List<TourLocation> path) {
        this.path = path;
    }

    /**
     * addTourLocation method - used to add a tour location to the respective tour
     *
     * @param   tourLocation tour location to add
     **/
    public void addTourLocation(TourLocation tourLocation) {
        this.path.add(tourLocation);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "path=" + path +
                '}';
    }
}