package ro.siit.java.packageDelivery;

public class PickUpVehicle implements Vechicle {
    private int carId;

    public PickUpVehicle(int carId) {
        this.carId = carId;
    }

    public int getCarId() {
        return carId;
    }
}
