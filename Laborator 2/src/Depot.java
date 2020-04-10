import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Depot class - is a tour location
 * @author Nistor Sergiu
 *
 **/
public class Depot extends Tour.TourLocation {
    /**
     * Depot class constructor
     *
     * @param   name    the depot's name
     **/
    public Depot(String name) {
        super(name);
        this.vehicles = new ArrayList<Vehicle>();
    }

    private List<Vehicle> vehicles;
    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }
    private void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * addVehicle method - adds a vehicle, if it does not exist in the depot instance
     *
     * @param   vehicle     the vehicle to add
     * @return  boolean     true if the vehicle was added, false otherwise
     **/
    public boolean addVehicle(Vehicle vehicle) {
        if (this.vehicles.contains(vehicle))
            return false;

        this.vehicles.add(vehicle);
        return true;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "vehicles=" + vehicles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Depot)) return false;
        Depot depot = (Depot) o;
        return Objects.equals(vehicles, depot.vehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicles);
    }
}
