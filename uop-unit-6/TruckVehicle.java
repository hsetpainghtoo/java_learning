/**
 * The TruckVehicle interface defines behaviour specific to trucks:
 * setting and retrieving the cargo capacity and the transmission type.
 */
public interface TruckVehicle {

    /**
     * Sets the truck's cargo capacity.
     * 
     * @param cargoCapacity the cargo capacity, in tons
     */
    void setCargoCapacity(double cargoCapacity);

    /**
     * Returns the truck's cargo capacity.
     * 
     * @return the cargo capacity, in tons
     */
    double getCargoCapacity();

    /**
     * Sets the truck's transmission type.
     * 
     * @param transmissionType one of "Manual" or "Automatic"
     */
    void setTransmissionType(String transmissionType);

    /**
     * Returns the truck's transmission type.
     * 
     * @return the transmission type
     */
    String getTransmissionType();
}
