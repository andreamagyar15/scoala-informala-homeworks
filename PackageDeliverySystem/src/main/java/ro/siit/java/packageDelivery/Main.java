/**
 * Main Class with initialization for facilities, parcel
 * Calculate the shortest root from source address to delivery address
 * Filter the package in every facility
 *
 */

package ro.siit.java.packageDelivery;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IllegalArgumentException, IOException {
        PackageDeliverySystem packageDeliverySystem=new PackageDeliverySystem();

        //facilities
        packageDeliverySystem.setFacilities();
        Facility[] facilities=packageDeliverySystem.getFacilities();

        //Instances of package
        //Package1
        Address sourceAddress=new Address("Romania","Cluj","Unirii","442121");
        ContactInfo sourceContact =new ContactInfo("Ana",sourceAddress,"075145245");
        Address destinationAddress=new Address("Romania","Bucuresti","Traian","443432");
        ContactInfo destinationContact =new ContactInfo("Maria",destinationAddress,"075658546");
        Parcel p1=new Parcel(sourceContact,destinationContact,12,33,21);
        int id1=packageDeliverySystem.requestDeliveryPickup(p1,sourceAddress,destinationAddress);


        TrackingInfo p1TrackInfo =new TrackingInfo();
        p1TrackInfo.createTrackingInfo(sourceContact,destinationContact,id1);

        p1.setTrackingInfo(p1TrackInfo);
       // packageDeliverySystem.addParcelToList(id1,p1);
        //Package2
        Address sourceAddress2=new Address("Romania","Brasov","Ariesului","342345");
        ContactInfo sourceContact2 =new ContactInfo("Bogdan",sourceAddress2,"07514546");
        Address destinationAddress2=new Address("Romania","Sibiu","Artelor","45467");
        ContactInfo destinationContact2 =new ContactInfo("Stefan",destinationAddress2,"075645656456");
        Parcel p2=new Parcel(sourceContact2,destinationContact2,15,83,27);
        int id2=packageDeliverySystem.requestDeliveryPickup(p2,sourceAddress2,destinationAddress2);

        TrackingInfo p2TrackInfo =new TrackingInfo();
        p2TrackInfo.createTrackingInfo(sourceContact2,destinationContact2,id2);
        p2.setTrackingInfo(p2TrackInfo);
        //packageDeliverySystem.addParcelToList(id2,p2);

        //Package3
        Address sourceAddress3=new Address("Romania","Cluj","Unirii","442134");
        ContactInfo sourceContact3 =new ContactInfo("Irina",sourceAddress3,"0751434242");
        Address destinationAddress3=new Address("Romania","Bucuresti","Plopilor","4768432");
        ContactInfo destinationContact3 =new ContactInfo("Peter",destinationAddress3,"075658987");
        Parcel p3=new Parcel(sourceContact3,destinationContact3,19,37,31);
        int id3=packageDeliverySystem.requestDeliveryPickup(p3,sourceAddress3,destinationAddress3);


        TrackingInfo p3TrackInfo =new TrackingInfo();
        p3TrackInfo.createTrackingInfo(sourceContact3,destinationContact3,id3);

        p3.setTrackingInfo(p3TrackInfo);
        //packageDeliverySystem.addParcelToList(id3,p3);

        //Package4
        Address sourceAddress4=new Address("Romania","Brasov","Unirii","442781");
        ContactInfo sourceContact4 =new ContactInfo("Andrei",sourceAddress4,"075147255");
        Address destinationAddress4=new Address("Romania","Bucuresti","Traian","443432");
        ContactInfo destinationContact4 =new ContactInfo("Maria",destinationAddress4,"075655234");
        Parcel p4=new Parcel(sourceContact4,destinationContact4,14,39,31);
        int id4=packageDeliverySystem.requestDeliveryPickup(p4,sourceAddress4,destinationAddress4);


        TrackingInfo p4TrackInfo =new TrackingInfo();
        p4TrackInfo.createTrackingInfo(sourceContact4,destinationContact4,id4);

        p4.setTrackingInfo(p4TrackInfo);
        //packageDeliverySystem.addParcelToList(id4,p4);

        //Package5
        Address sourceAddress5=new Address("Romania","Cluj","Unirii","44278");
        ContactInfo sourceContact5 =new ContactInfo("George",sourceAddress5,"075145287");
        Address destinationAddress5=new Address("Romania","Bucuresti","Dunarii","444232");
        ContactInfo destinationContact5 =new ContactInfo("Paul",destinationAddress5,"0756589800");
        Parcel p5=new Parcel(sourceContact5,destinationContact5,42,13,11);
        int id5=packageDeliverySystem.requestDeliveryPickup(p5,sourceAddress5,destinationAddress5);


        TrackingInfo p5TrackInfo =new TrackingInfo();
        p5TrackInfo.createTrackingInfo(sourceContact5,destinationContact5,id5);

        p5.setTrackingInfo(p5TrackInfo);
       // packageDeliverySystem.addParcelToList(id5,p5);

        //Print packages
        System.out.println("Packages: ");
        packageDeliverySystem.getPackages();

        //Shortest route for a packages
        System.out.println("First package shortest route: ");
        ArrayList<String> shortestRoute=packageDeliverySystem.routeCalculation(sourceAddress.getCity(),destinationAddress.getCity());
        for(int p=0;p<shortestRoute.size();p++){
            System.out.print(shortestRoute.get(p)+" ");
        }
        System.out.println();

        System.out.println("2nd package shortest route: ");
        ArrayList<String> shortestRoute2=packageDeliverySystem.routeCalculation(sourceAddress2.getCity(),destinationAddress2.getCity());
        for(int p=0;p<shortestRoute2.size();p++){
            System.out.print(shortestRoute2.get(p)+" ");
        }
        System.out.println();

        System.out.println("4th package shortest route: ");
        ArrayList<String> shortestRoute4=packageDeliverySystem.routeCalculation(sourceAddress4.getCity(),destinationAddress4.getCity());
        for(int p=0;p<shortestRoute4.size();p++){
            System.out.print(shortestRoute4.get(p)+" ");
        }
        System.out.println();
        //Add packages to pickup facility
        int facilityIndex2=facilityIndex(shortestRoute2.get(0),facilities);
        facilities[facilityIndex2].addPackageToFacility(packageDeliverySystem.getTrackingId(p2),p2);
        int facilityIndex4=facilityIndex(shortestRoute4.get(0),facilities);
        facilities[facilityIndex4].addPackageToFacility(packageDeliverySystem.getTrackingId(p4),p4);

        //delivery simulation for p1
        int facilityIndex=facilityIndex(shortestRoute.get(0),facilities);
        String currentCity;
        String nextDestination;
        String packHistory="";

        currentCity = shortestRoute.get(0);
        nextDestination = shortestRoute.get(1);
        //pickup at the package
        facilities[facilityIndex].addPackageToFacility(packageDeliverySystem.getTrackingId(p1),p1);
        packHistory= facilities[facilityIndex].pickUp(shortestRoute.get(0));
        p1TrackInfo.addInfoToHistory(packHistory,id1);
        //next destination for p1
        packHistory=facilities[facilityIndex].filterPackages(currentCity, nextDestination);
        facilities[facilityIndex].deletePackageFromFacility(facilityIndex);
        p1TrackInfo.addInfoToHistory(packHistory,id1);

        facilityIndex=facilityIndex(shortestRoute.get(1),facilities);
        facilities[facilityIndex].addPackageToFacility(packageDeliverySystem.getTrackingId(p1),p1);
        currentCity=shortestRoute.get(1);
        nextDestination=shortestRoute.get(2);
        facilities[facilityIndex].deletePackageFromFacility(facilityIndex);
        packHistory=facilities[facilityIndex].filterPackages(currentCity, nextDestination);
        p1TrackInfo.addInfoToHistory(packHistory,id1);

        facilityIndex=facilityIndex(shortestRoute.get(2),facilities);
        facilities[facilityIndex].addPackageToFacility(packageDeliverySystem.getTrackingId(p1),p1);
        currentCity=shortestRoute.get(2);
        nextDestination=shortestRoute.get(3);
        facilities[facilityIndex].deletePackageFromFacility(facilityIndex);
        packHistory=facilities[facilityIndex].filterPackages(currentCity, nextDestination);
        p1TrackInfo.addInfoToHistory(packHistory,id1);


        facilities[facilityIndex].deletePackageFromFacility(facilityIndex);
        packHistory=facilities[facilityIndex].filterPackages(currentCity, nextDestination);
        p1TrackInfo.addInfoToHistory(packHistory,id1);
        //check for delivery
        if(shortestRoute.get(1).equals(p1.getDestination().getAddress().getCity())){
            packHistory=  facilities[facilityIndex].delivery(shortestRoute.get(1));
            p1TrackInfo.addInfoToHistory(packHistory,id1);
        }

        String track=packageDeliverySystem.getParcelTrackingInfo(0);
        System.out.println(track);

        //facilty sort check
        facilities[1].sort();
        System.out.println(facilities[1].getFacilityPackages().toString());
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
