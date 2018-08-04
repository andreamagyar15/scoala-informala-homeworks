package ro.siit.java.packageDelivery;

import org.junit.Test;
import org.junit.runner.RunWith;
import ro.siit.java.packageDelivery.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PackageDeliverySystemTest {
    @Test
    public void testRequestDeliveryPickup(){
        Address sourceAddress=new Address("Romania","Cluj","Unirii","442121");
        ContactInfo sourceContact =new ContactInfo("Ana",sourceAddress,"075145245");
        Address destinationAddress=new Address("Romania","Bucuresti","Traian","443432");
        ContactInfo destinationContact =new ContactInfo("Maria",destinationAddress,"075658546");
        Parcel p1=new Parcel(sourceContact,destinationContact,12,33,21);

        PackageDeliverySystem packageDeliverySystem= new PackageDeliverySystem();
        int expected=0;
        int result=packageDeliverySystem.requestDeliveryPickup(p1,sourceAddress,destinationAddress);
        assertEquals(expected,result);
        expected=1;
        result=packageDeliverySystem.requestDeliveryPickup(p1,sourceAddress,destinationAddress);
        assertEquals(expected,result);
    }
    @Test
    public  void testGetParcelTrackingInfo(){
        PackageDeliverySystem packageDeliverySystem= new PackageDeliverySystem();
        String expected="Package not found";
        String result =packageDeliverySystem.getParcelTrackingInfo(1);
        assertEquals(expected,result);
    }

    @Test
    public void testRouteCalculation(){
        PackageDeliverySystem packageDeliverySystem= new PackageDeliverySystem();
        ArrayList<String> result=new ArrayList<String>();
        result=packageDeliverySystem.routeCalculation("Cluj","Bucuresti");
        ArrayList<String> expected =new ArrayList<String>();
        expected.add("Cluj");
        expected.add("Deva");
        expected.add("Sibiu");
        expected.add("Brasov");
        expected.add("Bucuresti");
        assertEquals(expected,result);
    }


}
