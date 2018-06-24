/**
 * Parcel object constructor
 */
package ro.siit.java.packageDelivery;

public class Parcel {

    private ContactInfo sender;
    private ContactInfo destination;
    private int height;
    private int width;
    private int weight;
    public Parcel(  ContactInfo sender,ContactInfo destination, int height,int width, int weight){

        this.sender=sender;
        this.destination=destination;
        this.height=height;
        this.width=width;
        this.weight=weight;
    }

    public ContactInfo getSender() {
        return sender;
    }

    public ContactInfo getDestination() {
        return destination;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getWeight() {
        return weight;
    }

    public void setDelivery (TrackingInfo trackingInfo, ContactInfo sender, ContactInfo destination){

    }

}
