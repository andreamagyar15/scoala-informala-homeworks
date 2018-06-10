package ro.siit.java.packageDelivery;

public class Parcel {

    private TrackingInfo trackingInfo;
    private ContactInfo sender;
    private ContactInfo destination;
    private int[] dimensions;
    private int weight;
    public Parcel( TrackingInfo trackingInfo, ContactInfo sender,ContactInfo destination, int[] dimensions, int weight){
        this.trackingInfo=trackingInfo;
        this.sender=sender;
        this.destination=destination;
        this.dimensions=dimensions;
        this.weight=weight;
    }
    public void setDelivery (TrackingInfo trackingInfo, ContactInfo sender, ContactInfo destination){

    }
    public void creatTrackingInfo(ContactInfo sender,ContactInfo destination){

    }
}
