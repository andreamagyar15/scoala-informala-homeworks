/**
 * Package Delivery System
 * Creates a pick up, add a Parcel to the list with all parcels
 * Route calculation . Return the shortest route between the source and destination
 * The map with facilities is defined
 */
package ro.siit.java.packageDelivery;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class PackageDeliverySystem {
 /**
  * @key : tracking id of the Parcel
  * @value: Parcel
  * **/
    private Map<Integer,Parcel> packages=new HashMap<Integer,Parcel>();

    // Array with facilities, hard-code array, never change during the lifetime of the application
    private Facility[] facilities=new Facility[5];
    private int trackingId=0;

    public int requestDeliveryPickup(Parcel parcel,Address sender, Address destination) {
        boolean foundSender=false;
        boolean foundDestination=false;
        for(int i=0;i<facilities.length;i++){
            if(facilities[i].getCity()==sender.getCity()){
                foundSender=true;
            }
            if(facilities[i].getCity()==sender.getCity()){
                foundDestination=true;
            }
        }
        if (!foundSender || !foundDestination) {
            return -1;
        }
        addParcelToList(trackingId++,parcel);
        return trackingId++;
    }
    private void addParcelToList(int id, Parcel parcel) {
        /**
         * Add a parcel to the parcel list
         */

        try {
            packages.put(id, parcel);
            addParcelToFile(id,parcel);
        }catch (Exception e){
            System.out.println("Something went wrong. Please check the input data");
        }
    }
    public void addParcelToFile(int id,Parcel parcel){
        /**
         * Add a parcel to the CSV file
         * **/
        try {
            ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("packages.csv"));
            ob.writeObject(parcel);
            ob.close();
            /* Check the file
            ObjectInputStream obTest=new ObjectInputStream(new FileInputStream("packages.csv"));
            Parcel p=(Parcel) obTest.readObject();
            String s=p.getSender().getContactName();
            System.out.println(s+" ");
            obTest.close();
             **/
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getParcelTrackingInfo(int trackingId){
        /**
         * @return :The tracking information of a parcel
         * **/
        TrackingInfo trackingInfo=new TrackingInfo();
        try {
            trackingInfo = packages.get(trackingId).getTrackingInfo();
            return trackingInfo.displayTrackingInfo();
        }catch(Exception e){
            return "Package not found";
        }
    }

    public Integer getTrackingId(Parcel parcel) {
        /**
         * @return Tracking id of the parcel
         * **/

        for(Integer key: packages.keySet()){
            if(packages.get(key).equals(parcel)){
               return key;
            }
        }
        return -1;
    }

    public void getPackages(){
        displayPackages(packages);
    }

    public void setFacilities() {
        //vehicles
        //vehicles for 1st facility
        ArrayList<PickUpVehicle> pickupCar1 =new ArrayList<>();
        pickupCar1.add(new PickUpVehicle(12,3,true, null));
        pickupCar1.add(new PickUpVehicle(13,3,true, null));
        ArrayList<TransportVehicle> transportCar1= new ArrayList<>();
        transportCar1.add(new TransportVehicle(31,2,true, null));
        transportCar1.add(new TransportVehicle(32,2,true, null));
        ArrayList<DeliveryVehicle> deliveryCar1=new ArrayList<>();
        deliveryCar1.add(new DeliveryVehicle(41,3,true, null));
        deliveryCar1.add(new DeliveryVehicle(42,2,true, null));
        deliveryCar1.add(new DeliveryVehicle(43,4,true, null));
        //vehicles for 2nd facility
        ArrayList<PickUpVehicle> pickupCar2 =new ArrayList<>();
        pickupCar2.add(new PickUpVehicle(14,2,true, null));
        pickupCar2.add(new PickUpVehicle(15,2,true, null));
        pickupCar2.add(new PickUpVehicle(16,3,true, null));
        ArrayList<TransportVehicle> transportCar2= new ArrayList<>();
        transportCar2.add(new TransportVehicle(34,2,true, null));
        transportCar2.add(new TransportVehicle(35,2,true, null));
        transportCar2.add(new TransportVehicle(36,2,true, null));
        ArrayList<DeliveryVehicle> deliveryCar2=new ArrayList<>();
        deliveryCar2.add(new DeliveryVehicle(44,3,true, null));
        deliveryCar2.add(new DeliveryVehicle(45,2,true, null));
        deliveryCar2.add(new DeliveryVehicle(46,4,true, null));
        //vehicles for 3rd facility
        ArrayList<PickUpVehicle> pickupCar3 =new ArrayList<>();
        pickupCar3.add(new PickUpVehicle(18,4,true, null));
        pickupCar3.add(new PickUpVehicle(19,2,true, null));
        ArrayList<TransportVehicle> transportCar3= new ArrayList<>();
        transportCar3.add(new TransportVehicle(37,2,true, null));
        transportCar3.add(new TransportVehicle(38,4,true, null));
        transportCar3.add(new TransportVehicle(39,1,true, null));
        ArrayList<DeliveryVehicle> deliveryCar3=new ArrayList<>();
        deliveryCar3.add(new DeliveryVehicle(47,3,true, null));
        deliveryCar3.add(new DeliveryVehicle(48,2,true, null));
        deliveryCar3.add(new DeliveryVehicle(49,4,true, null));
        //vehicles for 4th facility
        ArrayList<PickUpVehicle> pickupCar4 =new ArrayList<>();
        pickupCar4.add(new PickUpVehicle(11,2,true, null));
        ArrayList<TransportVehicle> transportCar4= new ArrayList<>();
        transportCar4.add(new TransportVehicle(51,2,true, null));
        transportCar4.add(new TransportVehicle(52,1,true, null));
        transportCar4.add(new TransportVehicle(53,2,true, null));
        ArrayList<DeliveryVehicle> deliveryCar4=new ArrayList<>();
        deliveryCar4.add(new DeliveryVehicle(57,1,true, null));
        deliveryCar4.add(new DeliveryVehicle(54,2,true, null));
        deliveryCar4.add(new DeliveryVehicle(58,1,true, null));
        //vehicles for 5th facility
        ArrayList<PickUpVehicle> pickupCar5 =new ArrayList<>();
        pickupCar5.add(new PickUpVehicle(60,2,true, null));
        pickupCar5.add(new PickUpVehicle(62,3,true, null));
        ArrayList<TransportVehicle> transportCar5= new ArrayList<>();
        transportCar5.add(new TransportVehicle(61,2,true, null));
        transportCar5.add(new TransportVehicle(62,3,true, null));
        transportCar5.add(new TransportVehicle(63,2,true, null));
        ArrayList<DeliveryVehicle> deliveryCar5=new ArrayList<>();
        deliveryCar5.add(new DeliveryVehicle(64,1,true, null));
        deliveryCar5.add(new DeliveryVehicle(67,2,true, null));
        deliveryCar5.add(new DeliveryVehicle(68,2,true, null));
       this.facilities = new Facility[]{
                new Facility(pickupCar1,transportCar1,deliveryCar1,"Cluj", null),
                new Facility(pickupCar2,transportCar2,deliveryCar2,"Brasov", null),
                new Facility(pickupCar3,transportCar3,deliveryCar3,"Bucuresti", null),
                new Facility(pickupCar4,transportCar4,deliveryCar4,"Sibiu", null),
                new Facility(pickupCar4,transportCar5,deliveryCar5,"Deva", null)
        };
    }

    public Facility[] getFacilities() {
        return facilities;
    }

    private void displayPackages(Map<Integer, Parcel> packages) {
        for(Map.Entry<Integer,Parcel> pack:packages.entrySet()) {
            System.out.println("ID: " +pack.getKey() + " From: " + pack.getValue().getSender().getAddress().getCity() + " To: " + pack.getValue().getDestination()              .getAddress().getCity());
        }
    }
    //TODO : class for routeCalcualtion
    /** Calculates the shortest route from pickup address to delivery address */
    public ArrayList<String> routeCalculation(String pickupAddress, String deliveryAddress){
        ArrayList<Edge> edgeList = getEdges();

        //unvisited array with all nodes
        ArrayList<String> unvisited=new ArrayList<String>();
        ArrayList<String> destinationList=new ArrayList<>();
        for (int j=0;j<edgeList.size();j++){
            if (!unvisited.contains(edgeList.get(j).getA())){
                unvisited.add(edgeList.get(j).getA());
                destinationList.add(edgeList.get(j).getA());
            }
            if (!unvisited.contains(edgeList.get(j).getB())){
                unvisited.add(edgeList.get(j).getB());
                destinationList.add(edgeList.get(j).getB());
            }
        }
        // number of nodes
        int len=unvisited.size();

        //route array:
        ArrayList<String> route=new ArrayList<String>();
        //previous vertex
        String[] prev=new String[len];

        //visited nodes
        ArrayList<String> visited=new ArrayList<String>(len);

        //array with distances from start node
        int[] x =new int[len];
        for (int k=0;k<len;k++){
            x[k]=500;
        }

        //initialization
        String pickUp=pickupAddress;
        boolean found=false;
        int ind=unvisited.indexOf(pickupAddress);
        x[ind]=0;
        int next=-1;
        prev[ind]=pickupAddress;
        int neighborIndex = -1;
        Edge edge;

        while(!found){
            //check the unvisited neighbors
            for (int l = 0; l < edgeList.size(); l++) {
                edge=edgeList.get(l);
                if ((pickupAddress.equals(edge.getA()) && (!visited.contains(edge.getA()))) || (pickupAddress.equals(edge.getB()) && (!visited.contains(edge.getB())))                  ) {
                    neighborIndex=getNeighborIndex(pickupAddress,edgeList,unvisited,l);
                    if (neighborIndex > -1) {
                        if (x[neighborIndex] > edgeList.get(l).getDist() + x[ind]) {
                            x[neighborIndex] = edgeList.get(l).getDist() + x[ind];
                            prev[neighborIndex] = pickupAddress;
                        }
                    }
                }
            }
            //set the unvisited and the visited arrays
            unvisited.set(ind,null);
            visited.add(pickupAddress);
            if(visited.contains(deliveryAddress)){
                found=true;
            }

            if(visited.size()<len) {
                //found the next unvisited neighbor with the smallest distance
                next = foundSmallestUnvNeighbor(x, unvisited, edgeList);
                pickupAddress = unvisited.get(next);
                ind = unvisited.indexOf(pickupAddress);
            }
        }
        route =createRoute(prev,deliveryAddress,pickUp,destinationList);
        return route;
    }

    private ArrayList<Edge> getEdges() {
        ArrayList <Edge> edgeList =new ArrayList <Edge>();
        edgeList.add(new Edge("Cluj","Brasov",6));
        edgeList.add(new Edge("Brasov","Bucuresti",5));
        edgeList.add(new Edge("Cluj","Deva",1));
        edgeList.add(new Edge("Brasov","Deva",2));
        edgeList.add(new Edge("Brasov","Sibiu",2));
        edgeList.add(new Edge("Deva","Sibiu",1));
        edgeList.add(new Edge("Sibiu","Bucuresti",5));
        return edgeList;
    }

    private ArrayList<String> createRoute(String[] prev, String deliveryAddress,String pickUp,ArrayList<String> destinationList) {
        ArrayList<String> route=new ArrayList<String>();
        int ind=0;
        route.add(deliveryAddress);
        while(!(route.get(route.size()-1).equals(pickUp))){
          ind=destinationList.indexOf(deliveryAddress);
          route.add(prev[ind]);
          deliveryAddress=prev[ind];
        }
        for(int k=0;k<route.size()/2;k++){
           Collections.swap(route,k,route.size()-1-k);
        }
        return route;
    }

    public void creatTrackingInfo(ContactInfo sender,ContactInfo destination){

    }
    private static String getNeighbor(String start, ArrayList<Edge> edgeList, int neighborIndex) {
        String neighbor=(start.equals(edgeList.get(neighborIndex).getA())) ? edgeList.get(neighborIndex).getB() : edgeList.get(neighborIndex).getA();
        return neighbor;
    }

    private static int getNeighborIndex(String start, ArrayList<Edge> edgeList, ArrayList<String> unvisited, int l) {
        int index= (start.equals(edgeList.get(l).getA())) ? unvisited.indexOf(edgeList.get(l).getB()) : unvisited.indexOf(edgeList.get(l).getA());
        return index;
    }

    private static int foundSmallestUnvNeighbor(int[] x, ArrayList<String> unvisited, ArrayList<Edge> edgeList) {
        int min=Integer.MAX_VALUE;
        int minInd=-1;
        for (int i=0;i<unvisited.size();i++){
            if((x[i] < min) && unvisited.get(i)!=null){
                min=x[i];
                minInd=i;
            }
        }
        return minInd;
    }

}
