/**
*Represents a facility
*Pickup/Transport/Deliver a parcel
*Filter a parcel in facilities
 *
 */
package ro.siit.java.packageDelivery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Facility {
    private ArrayList <PickUpVehicle> pickUpCars;
    private ArrayList <TransportVehicle> transportCars;
    private ArrayList <DeliveryVehicle> deliveryCars;
    private String city;
    private List<Parcel> facilityPackages;

    /** Creates a facility */
    public Facility(ArrayList<PickUpVehicle> pickUpCars, ArrayList<TransportVehicle> transportCars, ArrayList<DeliveryVehicle> deliveryCars, String city, ArrayList<Parcel> facilityPackages) {
        this.pickUpCars = pickUpCars;
        this.transportCars = transportCars;
        this.deliveryCars = deliveryCars;
        this.city = city;
        this.facilityPackages=new ArrayList<Parcel>();
        if(this.deliveryCars.size()<=0 || this.pickUpCars.size()<=0||this.transportCars.size()<=0){
            throw new IllegalArgumentException("Not correct amount argument");
        }
        if(this.city==null||this.city.isEmpty()){
            throw  new IllegalArgumentException("City is null or empty");
        }
    }

    public ArrayList<PickUpVehicle> getPickUpCars() {

        return pickUpCars;
    }

    public ArrayList<TransportVehicle> getTransportCars()
    {
        return transportCars;
    }

    public ArrayList<DeliveryVehicle> getDeliveryCars()
    {
        return deliveryCars;
    }

    public String getCity() {
        return city;
    }

    public List<Parcel> getFacilityPackages() {
        return this.facilityPackages;
    }
    /** Add an element (package id) to list with the packages in a certain facility */
    public void addPackageToFacility(Integer packageIndex,Parcel parcel) {
        this.facilityPackages.add(parcel);

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
    public void sort(){
        sortPackages(facilityPackages,0,facilityPackages.size()-1);
    }
    public  <T>  void sortPackages(List<T> list, int start, int end) {
        if (start < end) {
            int pivot = partition(list, start, end);
            sortPackages(list, start,pivot - 1);
            sortPackages(list, pivot + 1, end);
        }

    }

    public <T>  int partition(List<T> list, int start, int end) {
        int pivotIndex = (end+start)/2;
        swap(list,pivotIndex,end);
        int partitionIndex = start;
        Comparator comp =new DestinationComparator();
        for (int i = start; i < end; i++) {
            if(comp.compare(list.get(i),list.get(end))<=0) {
                swap(list, partitionIndex,i);
                partitionIndex++;
            }
        }
        swap(list,partitionIndex,end);
        return partitionIndex;
    }

    public   <T> void swap(List<T> list,int a, int b) {
        T c=list.get(a);
        list.set(a,list.get(b));
        list.set(b,c);
    }
    public void vehicleProcessing(final Integer indexAddParcel, final Parcel parcelAdd, final Integer indexRemoveParcel) throws InterruptedException{

        Runnable r1 = new Runnable() {
            public void run() {
                synchronized (facilityPackages) {
                    addPackageToFacility(indexAddParcel,parcelAdd);
                }
            }
        };
        Runnable r2 = new Runnable() {
            public void run() {
                synchronized (facilityPackages) {
                    deletePackageFromFacility(indexRemoveParcel);
                };
            };
        };
        Thread t1 =new Thread(r1);
        Thread t2=new Thread(r2);
        t1.start();
        t2.start();
    }


}
