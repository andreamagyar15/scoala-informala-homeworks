package ro.siit.java.packageDelivery;

import java.util.ArrayList;

/**Interface for vehicles */
public interface Vehicle {
    public int getCarId();
    public int getCapacity();
    public boolean isAvailable();
    public void setAvailable(boolean available);
    public void addElementToList(Parcel p);
    public void removeElementFromList(Parcel p);

}
