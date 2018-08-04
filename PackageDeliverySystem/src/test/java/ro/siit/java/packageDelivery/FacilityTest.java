package ro.siit.java.packageDelivery;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class FacilityTest {
    @Test
    public void delivery() {

    }

    @Test
    public void transport() {
    }
    @Test
    public void testFilterPackages(){
        ArrayList<PickUpVehicle> pickupCar1 =new ArrayList<>();
        LinkedList<Parcel> packageList=new LinkedList<>();
        pickupCar1.add(new PickUpVehicle(12,3,true, packageList));
        pickupCar1.add(new PickUpVehicle(13,3,true, packageList));
        ArrayList<TransportVehicle> transportCar1= new ArrayList<>();
        transportCar1.add(new TransportVehicle(31,2,true, packageList));
        transportCar1.add(new TransportVehicle(32,2,true, packageList));
        ArrayList<DeliveryVehicle> deliveryCar1=new ArrayList<>();
        deliveryCar1.add(new DeliveryVehicle(41,3,true, packageList));
        deliveryCar1.add(new DeliveryVehicle(42,2,true, packageList));
        deliveryCar1.add(new DeliveryVehicle(43,4,true, packageList));
        Facility facility =new Facility(pickupCar1,transportCar1,deliveryCar1,"Cluj",null);
        String  expected="The parcel is now in: "+"Cluj"+" the destination is: "+"Brasov";
        facility.filterPackages("Cluj","Brasov");

    }



}