package contacts;

import java.util.Arrays;

// id,first,last,nick,email,street,city,state,zip,phone
public class Person {
    private String firstName, lastName, address, phone;
    private String city, state, email, nickname, street, zipCode;
    private int id;

//    **********States Array and Regex **********

    private static final String[] statelist = new String[]{"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"};
    private static final String zipRegex = "^[0-9]{5}(?:-[0-9]{4})?$";

    Person(){}


    //#######################START GETTERS###################################
    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getFullName() {
        return this.getFirstName() + " \"" + this.getNickname() + "\" " + this.getLastName();
    }

    public String getFullAddress() {
        return this.getAddress() + " " + this.getStreet() + " " + this.getCity() + ", " +
                this.getState() + " " + this.getZipCode();
    }

    public int getId(){
        return id;
    }

//    *****Start Setters*****

    public boolean setId(int id){
        this.id = id;
        return true;
    }

    boolean setFirstName(String firstName) {
        if(firstName.length() > 15 || firstName.trim().length() == 0) {
            return false;
        }
        this.firstName = firstName;
        return true;
    }

    boolean setLastName(String lastName) {
        if(lastName.length() > 15 || lastName.trim().length() == 0) {
            return false;
        }
        this.lastName = lastName;
        return true;
    }

    boolean setNickname(String nickname) {
        if(nickname.length() > 15) {
            return true;
        }
        this.nickname = nickname;
        return true;
    }


    boolean setStreet(String street) {
        if(street.length() > 40){
            return false;
        }
        this.street = street;
        return true;
    }

    public boolean setCity(String city){
        if(city.length() > 23 || city.trim().length() == 0) {
         return false;
        }
        this.city = city;
        return true;
    }

    public boolean setState(String state){
        String caps = state.toUpperCase();
        if(caps.length() != 2 || !Arrays.asList(statelist).contains(caps)){
            return false;
        }
        this.state = state;
        return true;
    }

    boolean setPhone(String phone){
        String phoneRegexStr = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
        if(!phone.matches(phoneRegexStr)){
            return false;
        }
        this.phone = phone;
        return false;
    }

    boolean setEmail(String email){
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if(!email.matches(emailRegex)){
            return false;
        }
        this.email = email;
        return true;
    }

    public boolean setZipCode(String zipCode) {
        if(!zipCode.matches(zipRegex)){
            return false;
        }
        this.zipCode = zipCode;
        return true;
    }

//########################END SETTERS################################

}