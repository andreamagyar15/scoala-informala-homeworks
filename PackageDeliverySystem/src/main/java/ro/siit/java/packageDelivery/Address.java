/**
* Creates the Address object with country, city, street and zipcode
    country/city/street/zipcode may not be null
 @throws IllegalArgumentException if any of the arguments are null
 */

package ro.siit.java.packageDelivery;

public class Address {
    private String country;
    private String city;
    private String street;
    private String zipCode;
    public Address(String country,String city, String street,String zipCode) throws IllegalArgumentException{
        this.country=country;
        this.city=city;
        this.street=street;
        this.zipCode=zipCode;
        if(this.country==null|| this.country.isEmpty() ){
            throw new IllegalArgumentException("Country field null or empty");
        }
        if(this.city==null|| this.city.isEmpty() ){
            throw new IllegalArgumentException("City field null or empty");
        }
        if(this.street==null|| this.street.isEmpty()){
            throw new IllegalArgumentException("Street field null or empty");
        }
        if(this.zipCode==null || this.zipCode.isEmpty()){
            throw new IllegalArgumentException("Zipcode field null or empty");
        }
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
