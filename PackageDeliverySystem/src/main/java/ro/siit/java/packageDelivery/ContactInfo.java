/*
Create Contact object with name, address and phone number
Validate the information
 */

package ro.siit.java.packageDelivery;

import java.io.Serializable;

public class ContactInfo implements Serializable {
    private String contactName;
    private Address address;
    private String phoneNumber;

    public ContactInfo(String contactName, Address address, String phoneNumber) {
        this.contactName = contactName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        if(this.contactName==null||this.contactName.isEmpty()){
            throw new IllegalArgumentException("Contact name null or empty");
        }
        if(this.phoneNumber==null || this.phoneNumber.isEmpty()){
            throw  new IllegalArgumentException("Phone number is null or empty");
        }
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

    @Override
    public String toString() {
        return "ContactInfo{" +
                "contactName='" + contactName + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
