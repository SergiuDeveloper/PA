/**
 * Car class - extends Vehicle
 * @author Nistor Sergiu
 *
 **/
public class Car extends Vehicle {
    /**
     * Car class constructor
     *
     * @param   name    the car's name
     * @param   depot   the depot that contains the car
     **/
    public Car(String name, Depot depot) {
        super(name, depot);
        this.setVehicleType(Vehicle.VehicleType.CAR);
    }
}