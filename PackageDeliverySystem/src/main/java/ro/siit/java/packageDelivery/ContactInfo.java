/*
Create Contact object with name, address and phone number
Validate the information
 */

package ro.siit.java.packageDelivery;

public class ContactInfo {
    private String contactName;
    private Address address;
    private String phoneNumber;

    public ContactInfo(String contactName, Address address, String phoneNumber) {
        this.contactName = contactName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean validateAddress(Address  address){
     return false;
    }
}
