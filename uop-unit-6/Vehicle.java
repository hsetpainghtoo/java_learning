/**
 * The Vehicle interface defines the common contract that every vehicle
 * type in the rental system must follow. Any class that implements this
 * interface guarantees it can report its make, model, and year of
 * manufacture, regardless of what specific kind of vehicle it is.
 *
 * Using an interface here (rather than a shared superclass) means Car,
 * Motorcycle, and Truck can each also implement their own specialised
 * interface (CarVehicle, MotorVehicle, TruckVehicle) at the same time,
 * since Java allows a class to implement multiple interfaces but extend
 * only one class.
 */
public interface Vehicle {

    /**
     * Returns the manufacturer of the vehicle.
     * 
     * @return the vehicle's make (e.g. "Toyota")
     */
    String getMake();

    /**
     * Returns the model name of the vehicle.
     * 
     * @return the vehicle's model (e.g. "Corolla")
     */
    String getModel();

    /**
     * Returns the year the vehicle was manufactured.
     * 
     * @return the year of manufacture
     */
    int getYear();
}
