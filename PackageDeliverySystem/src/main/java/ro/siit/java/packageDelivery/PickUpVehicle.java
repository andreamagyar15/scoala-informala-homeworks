package ro.siit.java.packageDelivery;
/** Pick up vehicle  */
public class PickUpVehicle implements Vehicle {
    private int carId;

    public PickUpVehicle(int carId) {
        this.carId = carId;
    }

    public int getCarId() {
        return carId;
    }
}
