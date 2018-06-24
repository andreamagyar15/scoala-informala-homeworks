/*
Create the Adress object with country, city, street and zipcode
 */

package ro.siit.java.packageDelivery;

public class Address {
    private String country;
    private String city;
    private String street;
    private String zipCode;
    public Address(String country,String city, String street,String zipCode){
        this.country=country;
        this.city=city;
        this.street=street;
        this.zipCode=zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }
}
