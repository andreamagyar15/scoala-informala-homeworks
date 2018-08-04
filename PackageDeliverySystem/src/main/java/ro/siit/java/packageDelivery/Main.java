/**
 * Main Class with initialization for facilities, parcel
 * Calculate the shortest root from source address to delivery address
 * Filter the package in every facility
 *
 */

package ro.siit.java.packageDelivery;

import java.util.*;

public class Main {
    public static void main(String[] args) throws IllegalArgumentException {
        PackageDeliverySystem packageDeliverySystem=new PackageDeliverySystem();

        //facilities
        packageDeliverySystem.setFacilities();
        Facility[] facilities=packageDeliverySystem.getFacilities();

        //Instances of package
        Address sourceAddress=new Address("Romania","Cluj","Unirii","442121");
        ContactInfo sourceContact =new ContactInfo("Ana",sourceAddress,"075145245");
        Address destinationAddress=new Address("Romania","Bucuresti","Traian","443432");
        ContactInfo destinationContact =new ContactInfo("Maria",destinationAddress,"075658546");
        int id1=packageDeliverySystem.requestDeliveryPickup();
        Parcel p1=new Parcel(sourceContact,destinationContact,12,33,21);

        TrackingInfo p1TrackInfo =new TrackingInfo();
        p1TrackInfo.createTrackingInfo(sourceContact,destinationContact);
        p1.setTrackingInfo(p1TrackInfo);
        packageDeliverySystem.addParcelToList(id1,p1);

        Address sourceAddress2=new Address("Romania","Brasov","Ariesului","342345");
        ContactInfo sourceContact2 =new ContactInfo("Bogdan",sourceAddress2,"07514546");
        Address destinationAddress2=new Address("Romania","Sibiu","Artelor","45467");
        ContactInfo destinationContact2 =new ContactInfo("Stefan",destinationAddress2,"075645656456");
        int id2=packageDeliverySystem.requestDeliveryPickup();
        Parcel p2=new Parcel(sourceContact2,destinationContact2,15,83,27);

        TrackingInfo p2TrackInfo =new TrackingInfo();
        p2TrackInfo.createTrackingInfo(sourceContact2,destinationContact2);
        p2.setTrackingInfo(p2TrackInfo);
        packageDeliverySystem.addParcelToList(id2,p2);

        //Print packages
        System.out.println("Packages: ");
        packageDeliverySystem.getPackages();

        //Shortest route for a package
        System.out.println("First package shortest route: ");
        ArrayList<String> shortestRoute=packageDeliverySystem.routeCalculation(sourceAddress.getCity(),destinationAddress.getCity());
        for(int p=0;p<shortestRoute.size();p++){
            System.out.print(shortestRoute.get(p)+" ");
        }
        System.out.println();

        //delivery simulation for p1
        int facilityIndex=facilityIndex(shortestRoute.get(0),facilities);
        String currentCity;
        String nextDestination;
        String packHistory="";

        currentCity = shortestRoute.get(0);
        nextDestination = shortestRoute.get(1);
        //pickup address
        facilities[facilityIndex].addPackageToFacility(packageDeliverySystem.getTrackingId(p1));
        packHistory= facilities[facilityIndex].pickUp(shortestRoute.get(0));
        p1TrackInfo.addInfoToHistory(packHistory);
        //next destination
        packHistory=facilities[facilityIndex].filterPackages(currentCity, nextDestination);
        facilities[facilityIndex].deletePackageFromFacility(facilityIndex);
        p1TrackInfo.addInfoToHistory(packHistory);

        facilityIndex=facilityIndex(shortestRoute.get(1),facilities);
        facilities[facilityIndex].addPackageToFacility(packageDeliverySystem.getTrackingId(p1));

        //check for delivery
        if(shortestRoute.get(1).equals(p1.getDestination().getAddress().getCity())){
            packHistory=  facilities[facilityIndex].delivery(shortestRoute.get(1));
            p1TrackInfo.addInfoToHistory(packHistory);
        }

        String track=packageDeliverySystem.getParcelTrackingInfo(0);
        System.out.println(track);
    }
    public static int facilityIndex(String facility,Facility[] facilities){
        for (int j=0;j<facilities.length;j++) {
            if (facility.equals(facilities[j].getCity())) {
                return j;
            }
        }
        return -1;
    }
}
