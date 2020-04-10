/**
 * Drone class - extends Vehicle
 * @author Nistor Sergiu
 *
 **/
public class Drone extends Vehicle {
    /**
     * Drone class constructor
     *
     * @param   name    the drone's name
     * @param   depot   the depot that contains the drone
     **/
    public Drone(String name, Depot depot) {
        super(name, depot);
        this.setVehicleType(Vehicle.VehicleType.DRONE);
    }
}
