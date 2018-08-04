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
    private TrackingInfo trackingInfo;

    public Parcel(ContactInfo sender, ContactInfo destination, int height, int width, int weight){
        this.sender=sender;
        this.destination=destination;
        this.height=height;
        this.width=width;
        this.weight=weight;
        this.trackingInfo=null;
        if(this.height<=0|| this.width<=0|| this.weight<=0){
            throw  new IllegalArgumentException("The package parameters are wrong");
        }
    }

    public TrackingInfo getTrackingInfo() {
        return trackingInfo;
    }

    public void setTrackingInfo(TrackingInfo trackingInfo) {
        this.trackingInfo = trackingInfo;
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

    @Override
    public String toString() {
        return "Parcel{" +
                "sender=" + sender +
                ", destination=" + destination +
                ", height=" + height +
                ", width=" + width +
                ", weight=" + weight +
                ", trackingInfo=" + trackingInfo +
                '}';
    }
}
