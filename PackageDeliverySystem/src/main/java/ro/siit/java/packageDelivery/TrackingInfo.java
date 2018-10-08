package ro.siit.java.packageDelivery;

import java.io.*;

public class TrackingInfo implements Serializable{
    private String packageHistory;

    public TrackingInfo() {
        this.packageHistory = packageHistory;
    }

    public void addInfoToHistory(String newInformation,int id) throws IOException {
        File file = new File(id + "package.txt");
        BufferedWriter trackingHistoryFile = new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true));
        trackingHistoryFile.write(newInformation);
        trackingHistoryFile.newLine();
        trackingHistoryFile.close();

        this.packageHistory+=newInformation;
    }
    public String displayTrackingInfo()
    {
        return packageHistory;
    }

    public void createTrackingInfo(ContactInfo sender, ContactInfo destination,int id) throws IOException {
        String firstTrackingInfo = "The sender is : " + sender.getContactName() + " from " + sender.getAddress().getCity() + " The destination is: " + destination             .getContactName() + " from: " + destination.getAddress().getCity();
        BufferedWriter trackingHistoryFile = new BufferedWriter(new FileWriter(id + "package.txt"));
        trackingHistoryFile.write(firstTrackingInfo);
        trackingHistoryFile.newLine();
        trackingHistoryFile.close();

        this.packageHistory = firstTrackingInfo;
    }


}
