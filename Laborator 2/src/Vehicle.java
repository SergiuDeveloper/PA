import java.util.Objects;

/**
 * Vehicle class - Vehicle abstract class
 * @author Nistor Sergiu
 *
 **/
public abstract class Vehicle {
    /**
     * Vehicle class constructor
     *
     * @param   name    the vehicle's name
     * @param   depot   the depot that contains the vehicle
     **/
    public Vehicle(String name, Depot depot) {
        this.name = name;
        this.depot = depot;
        this.depot.addVehicle(this);
    }

    public static enum VehicleType {
        CAR, TRUCK, DRONE
    };

    private String name;
    public String getName() {
        return this.name;
    }
    private void setName(String name) {
        this.name = name;
    }

    private Depot depot;
    public Depot getDepot() {
        return this.depot;
    }
    private void setDepot(Depot depot) {
        this.depot = depot;
    }

    private Tour tour;
    private Tour getTour() {
        return this.tour;
    }
    public void setTour(Tour tour) {
        this.tour = tour;
        this.tour.setVehicle(this);
    }

    private VehicleType vehicleType;
    protected VehicleType getVehicleType() {
        return this.vehicleType;
    }
    protected void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "depot=" + depot +
                ", tour=" + tour +
                ", vehicleType=" + vehicleType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(depot, vehicle.depot) &&
                Objects.equals(tour, vehicle.tour) &&
                vehicleType == vehicle.vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depot, tour, vehicleType);
    }
}
