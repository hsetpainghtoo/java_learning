/**
 * The CarVehicle interface defines behaviour specific to cars: setting
 * and retrieving the number of doors and the fuel type. A class that
 * implements both Vehicle and CarVehicle is guaranteeing it can behave
 * like a general vehicle AND provide car-specific details.
 */
public interface CarVehicle {

    /**
     * Sets the number of doors on the car.
     * 
     * @param numDoors the number of doors (expected to be positive)
     */
    void setNumDoors(int numDoors);

    /**
     * Returns the number of doors on the car.
     * 
     * @return the number of doors
     */
    int getNumDoors();

    /**
     * Sets the car's fuel type.
     * 
     * @param fuelType one of "Petrol", "Diesel", or "Electric"
     */
    void setFuelType(String fuelType);

    /**
     * Returns the car's fuel type.
     * 
     * @return the fuel type
     */
    String getFuelType();
}
