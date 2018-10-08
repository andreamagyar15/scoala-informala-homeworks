package ro.siit.java.packageDelivery;

import java.util.Comparator;

public class DestinationComparator implements Comparator<Parcel> {
    public int compare(Parcel o1,Parcel o2) {
        o1.getDestination().getAddress().getCity();
        return o1.getDestination().getAddress().getCity().compareTo(o2.getDestination().getAddress().getCity());
    }

}
