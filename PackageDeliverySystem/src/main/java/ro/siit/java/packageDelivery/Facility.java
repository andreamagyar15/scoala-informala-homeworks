/*
*Construct for facility
*Pickup/Transport/Deliver a parcel
*Filter a parcel in facilities
 */
package ro.siit.java.packageDelivery;

public class Facility {
    private int amountOfPickUpCars;
    private int amountOfTransportCars;
    private int amountOfDeliveryCars;
    private String city;


    public Facility(int amountOfPickUpCars, int amountOfTransportCars, int amountOfDeliveryCars, String city) {
        this.amountOfPickUpCars = amountOfPickUpCars;
        this.amountOfTransportCars = amountOfTransportCars;
        this.amountOfDeliveryCars = amountOfDeliveryCars;
        this.city = city;
    }

    public int getAmountOfPickUpCars() {
        return amountOfPickUpCars;
    }

    public int getAmountOfTransportCars() {
        return amountOfTransportCars;
    }

    public int getAmountOfDeliveryCars() {
        return amountOfDeliveryCars;
    }

    public String getCity() {
        return city;
    }

    public String pickUp(String pickupAddress){
        String message="The parcel is picked up in: "+pickupAddress;
        return message ;
    }
    public String delivery (String deliveryAddress){
        String message="The parcel is in delivery in : "+deliveryAddress;
        return message;
    }
    public String transport(String currentAddress, String destination){
        String message="The parcel is now in: "+currentAddress+" the destination is: "+destination;
        return message;
    }
    public void filterPackages(String currentAddress, String nextDestination){
            if(currentAddress==nextDestination){
                delivery(nextDestination);
            }else {
                transport(currentAddress, nextDestination);
            }

    }
}
