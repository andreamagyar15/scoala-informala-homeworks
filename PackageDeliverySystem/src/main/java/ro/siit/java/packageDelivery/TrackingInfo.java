package ro.siit.java.packageDelivery;

public class TrackingInfo{
    private String packageHistory;

    public TrackingInfo() {
        this.packageHistory = packageHistory;
    }

    public void addInfoToHistory(String newInformation) {
        this.packageHistory+=newInformation;
    }
    public String displayTrackingInfo()
    {
        return packageHistory;
    }

    public void createTrackingInfo(ContactInfo sender, ContactInfo destination) {
        this.packageHistory="The sender is : " + sender.getContactName() + " from " + sender.getAddress().getCity() + " The destination is: " + destination         .getContactName() + " from: " + destination.getAddress().getCity();
    }


}
