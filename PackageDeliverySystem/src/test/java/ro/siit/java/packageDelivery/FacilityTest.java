package ro.siit.java.packageDelivery;

import org.junit.Test;

import static org.junit.Assert.*;

public class FacilityTest {
    @Test
    public void delivery() {

    }

    @Test
    public void transport() {
    }
    @Test
    public void testFilterPackages(){
        Facility facility =new Facility(2,3,3,"Cluj",null);
        String  expected="The parcel is now in: "+"Cluj"+" the destination is: "+"Brasov";
        facility.filterPackages("Cluj","Brasov");

    }



}