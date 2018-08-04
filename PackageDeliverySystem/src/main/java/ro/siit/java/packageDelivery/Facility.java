/**
*Represents a facility
*Pickup/Transport/Deliver a parcel
*Filter a parcel in facilities
 *
 */
package ro.siit.java.packageDelivery;

import java.util.ArrayList;
import java.util.List;

public class Facility {
    // TODO - CODE REVIEW - why not use collactions of vehicles?
    private int amountOfPickUpCars;
    private int amountOfTransportCars;
    private int amountOfDeliveryCars;
    private String city;
    private List<Integer> facilityPackages;

    /** Creates a facility */
    public Facility(int amountOfPickUpCars, int amountOfTransportCars, int amountOfDeliveryCars, String city, ArrayList<Integer> facilityPackages) {
        this.amountOfPickUpCars = amountOfPickUpCars;
        this.amountOfTransportCars = amountOfTransportCars;
        this.amountOfDeliveryCars = amountOfDeliveryCars;
        this.city = city;
        this.facilityPackages=new ArrayList<Integer>();
        if(this.amountOfDeliveryCars<=0 || this.amountOfPickUpCars<=0||this.amountOfTransportCars<=0){
            throw new IllegalArgumentException("Not correct amount argument");
        }
        if(this.city==null||this.city.isEmpty()){
            throw  new IllegalArgumentException("City is null or empty");
        }
    }

    public int getAmountOfPickUpCars() {

        return amountOfPickUpCars;
    }

    public int getAmountOfTransportCars()
    {
        return amountOfTransportCars;
    }

    public int getAmountOfDeliveryCars()
    {
        return amountOfDeliveryCars;
    }

    public String getCity() {
        return city;
    }

    public List<Integer> getFacilityPackages() {
        return this.facilityPackages;
    }
    /** Add an element (package id) to list with the packages in a certain facility */
    public void addPackageToFacility(Integer packageIndex) {
        this.facilityPackages.add(packageIndex);

    }
    public void deletePackageFromFacility(Integer packageIndex){
        this.facilityPackages.remove(packageIndex);
    }
    /** Pick up a package */
    public String pickUp(String pickupAddress){
        String message="The parcel is picked up in: "+pickupAddress;
        return message ;
    }
    /**Deliver a package */
    public String delivery (String deliveryAddress){
        String message="The parcel is in delivery in : "+deliveryAddress;
        return message;
    }
    /**Transport a package from current address to a destination address */
    public String transport(String currentAddress, String destination){
        String message="The parcel is now in: "+currentAddress+" the destination is: "+destination;
        return message;
    }
    /**Filter a package */
    public String filterPackages(String currentAddress, String nextDestination){
            if(currentAddress==nextDestination){
               return  delivery(nextDestination);
            }else {
              return   transport(currentAddress, nextDestination);
            }

    }
}
