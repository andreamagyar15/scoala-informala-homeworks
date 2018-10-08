package ro.siit.java.packageDelivery;

import java.util.LinkedList;

public class DeliveryVehicle implements Vehicle {
    private int carId;
    private int capacity;
    private boolean available;
    private LinkedList<Parcel> packageList;

    public DeliveryVehicle(int carId, int capacity, boolean available, LinkedList<Parcel> packageList) {
        this.carId = carId;
        this.capacity = capacity;
        this.available = available;
        this.packageList = packageList;
    }

    @Override
    public int getCarId() {
        return this.carId;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void addElementToList(Parcel p){
        packageList.add(p);
    }
    public void removeElementFromList(Parcel p){
        packageList.remove(p);
    }
}
