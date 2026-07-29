/**
 * Car represents a car available for rental. It implements both the
 * general Vehicle contract and the car-specific CarVehicle contract,
 * so it must provide make/model/year details as well as door count
 * and fuel type.
 */
public class Car implements Vehicle, CarVehicle {

    private String make;
    private String model;
    private int year;
    private int numDoors;
    private String fuelType;

    /**
     * Constructs a Car with its basic vehicle details. Door count and
     * fuel type are set afterwards via setNumDoors/setFuelType, since
     * those come from the CarVehicle interface.
     *
     * @param make  the manufacturer, must not be null or blank
     * @param model the model name, must not be null or blank
     * @param year  the year of manufacture, must be a reasonable positive year
     */
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public int getNumDoors() {
        return numDoors;
    }

    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Returns a human-readable summary of the car's details.
     */
    @Override
    public String toString() {
        return String.format(
                "Car -> Make: %s | Model: %s | Year: %d | Doors: %d | Fuel Type: %s",
                make, model, year, numDoors, fuelType);
    }
}
