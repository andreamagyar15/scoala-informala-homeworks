/**
 * Main Class with initialization for facilities, parcel
 * Calculate the shortest root from source address to delivery address
 * Filter the package in every facility until arrive in destination city
 *
 */

package ro.siit.java.packageDelivery;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        //facilities
        Facility[] facilities = {
                new Facility(3,4,1, "Cluj"),
                new Facility(2,3,4, "Brasov"),
                new Facility(2,1,3, "Bucuresti"),
                new Facility(3,2,2, "Sibiu"),
                new Facility(2,3,1, "Deva")
        };

        Address sourceAdrees=new Address("Romania","Cluj","Unirii","442121");
        ContactInfo sourceContact =new ContactInfo("Ana",sourceAdrees,"075145245");

        Address destinationAdrees=new Address("Romania","Bucuresti","Traian","443432");
        ContactInfo destinationContact =new ContactInfo("Maria",destinationAdrees,"075658546");

        Set<Parcel> parcel =new HashSet<Parcel>() ;
        parcel.add(new Parcel(sourceContact,destinationContact,12,33,21));

        PackageDeliverySystem packageDeliverySystem=new PackageDeliverySystem();

        ArrayList<String> result=packageDeliverySystem.requestDeliveryPickup(sourceContact,destinationContact);
        for(int p=0;p<result.size();p++){
            System.out.print(result.get(p)+" ");
        }
        System.out.println();
        int facilityIndex=0;
        String currentCity;
        String nextDestination;
        //delivery simulation
        for(int i=0;i<result.size()-1;i++){
            for (int j=0;j<facilities.length;j++) {
                if (result.get(i).equals(facilities[i].getCity())) {
                    facilityIndex = i;
                    break;
                }
            }
            currentCity = result.get(i);
            nextDestination = result.get(i + 1);
            if(i==0){
                facilities[facilityIndex].pickUp(result.get(i));
            }
            facilities[facilityIndex].filterPackages(currentCity, nextDestination);
            if(result.get(i + 1).equals(result.get(result.size()-1))){
                currentCity=nextDestination;
                facilities[facilityIndex].filterPackages(currentCity,nextDestination);
            }

        }








    }
}
