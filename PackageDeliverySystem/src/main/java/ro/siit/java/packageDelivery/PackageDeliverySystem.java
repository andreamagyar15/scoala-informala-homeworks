/**
 * Package Delivery System
 * Route calculation . Return the shortest route between the source and destination
 * The map is defined
 */
package ro.siit.java.packageDelivery;

import java.util.*;

public class PackageDeliverySystem {

    private Map<Integer,Parcel> packages=new HashMap<Integer,Parcel>();
    private Facility[] facilities=new Facility[5];
    private int trackingId=0;

    public int requestDeliveryPickup() {
        return trackingId++;
    }
    public void addParcelToList(int id, Parcel parcel) {
        try {
            packages.put(id, parcel);
        }catch (Exception e){
            System.out.println("Something went wrong. Please check the input data");
        }
    }
    public String getParcelTrackingInfo(int trackingId){
        TrackingInfo trackingInfo=new TrackingInfo();
        try {
            trackingInfo = packages.get(trackingId).getTrackingInfo();
            return trackingInfo.displayTrackingInfo();
        }catch(Exception e){
            return "Package not found";
        }
    }

    public Integer getTrackingId(Parcel parcel) {
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
       this.facilities = new Facility[]{
                new Facility(3, 4, 1, "Cluj", null),
                new Facility(2, 3, 4, "Brasov", null),
                new Facility(2, 1, 3, "Bucuresti", null),
                new Facility(3, 2, 2, "Sibiu", null),
                new Facility(2, 3, 1, "Deva", null)
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
    /** Calculates the shortest route from pickup address to delivery address */
    public ArrayList<String> routeCalculation(String pickupAddress, String deliveryAddress){
        ArrayList <Edge> edgeList =new ArrayList <Edge>();

        edgeList.add(new Edge("Cluj","Brasov",6));
        edgeList.add(new Edge("Brasov","Bucuresti",5));
        edgeList.add(new Edge("Cluj","Deva",1));
        edgeList.add(new Edge("Brasov","Deva",2));
        edgeList.add(new Edge("Brasov","Sibiu",2));
        edgeList.add(new Edge("Deva","Sibiu",1));
        edgeList.add(new Edge("Sibiu","Bucuresti",5));

        //unvisited array with all nodes
        ArrayList<String> unvisited=new ArrayList<String>();
        for (int j=0;j<edgeList.size();j++){
            if (!unvisited.contains(edgeList.get(j).getA())){
                unvisited.add(edgeList.get(j).getA());
            }
            if (!unvisited.contains(edgeList.get(j).getB())){
                unvisited.add(edgeList.get(j).getB());
            }
        }
        // number of nodes
        int len=unvisited.size();

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
        boolean found=false;
        int ind=unvisited.indexOf(pickupAddress);
        x[ind]=0;
        int indvis=0;
        int next=-1;
        prev[ind]=pickupAddress;
        String neighbor="";
        int neighborIndex = -1;
        Edge edge;

        while(!found){
            //check the unvisited neighbors
            for (int l = 0; l < edgeList.size(); l++) {
                edge=edgeList.get(l);
                if ((pickupAddress.equals(edge.getA()) && (!visited.contains(edge.getA()))) || (pickupAddress.equals(edge.getB()) && (!visited.contains(edge.getB())))                  ) {
                    neighborIndex=getNeighborIndex(pickupAddress,edgeList,unvisited,l);
                    if (neighborIndex > -1) {
                        neighbor=getNeighbor(pickupAddress,edgeList,neighborIndex);
                        if (x[neighborIndex] > edgeList.get(l).getDist() + x[ind]) {
                            x[neighborIndex] = edgeList.get(l).getDist() + x[ind];
                            prev[neighborIndex] = neighbor;
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
                indvis++;
                ind = unvisited.indexOf(pickupAddress);
            }
        }
        return visited;
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
