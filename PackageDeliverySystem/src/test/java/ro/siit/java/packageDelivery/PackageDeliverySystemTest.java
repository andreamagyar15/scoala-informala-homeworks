package ro.siit.java.packageDelivery;

import org.junit.Test;
import ro.siit.java.packageDelivery.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PackageDeliverySystemTest {
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
