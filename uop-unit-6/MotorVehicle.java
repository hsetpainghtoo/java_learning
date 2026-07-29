/**
 * The MotorVehicle interface defines behaviour specific to motorcycles:
 * setting and retrieving the number of wheels and the motorcycle type.
 */
public interface MotorVehicle {

    /**
     * Sets the number of wheels on the motorcycle.
     * 
     * @param numWheels the number of wheels (usually 2 or 3)
     */
    void setNumWheels(int numWheels);

    /**
     * Returns the number of wheels on the motorcycle.
     * 
     * @return the number of wheels
     */
    int getNumWheels();

    /**
     * Sets the type of motorcycle.
     * 
     * @param motorcycleType one of "Sport", "Cruiser", or "Off-road"
     */
    void setMotorcycleType(String motorcycleType);

    /**
     * Returns the type of motorcycle.
     * 
     * @return the motorcycle type
     */
    String getMotorcycleType();
}
