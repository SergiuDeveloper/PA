/**
 * Truck class - extends Vehicle
 * @author Nistor Sergiu
 *
 **/
public class Truck extends Vehicle {
    /**
     * Truck class constructor
     *
     * @param   name    the truck's name
     * @param   depot   the depot that contains the truck
     **/
    public Truck(String name, Depot depot) {
        super(name, depot);
        this.setVehicleType(Vehicle.VehicleType.TRUCK);
    }
}
