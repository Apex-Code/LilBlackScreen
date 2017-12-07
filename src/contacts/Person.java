package contacts;

// id,first,last,nick,email,street,city,state,zip,phone
public class Person {
    private String firstName, lastName, address, phone;
    private String city, state, email, nickname, street, zipCode;

    Person(String firstName, String lastName, String nickname,
           String email, String address, String street, String city,
           String state, String zipCode, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.street = street;
        this.state = state;
        this.email = email;
        this.nickname = nickname;
        this.zipCode = zipCode;
        this.phone = phone;
    }


    //#######################START GETTERS/SETTERS###################################
    public String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    void setNickname(String nickname) {
        this.nickname = nickname;
    }

    String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFullName() {
        return this.getFirstName() + " \"" + this.getNickname() + "\" " + this.getLastName();
    }

    public String getFullAddress() {
        return this.getAddress() + " " + this.getStreet() + " " + this.getCity() + ", " +
                this.getState() + " " + this.getZipCode();
    }

//########################END GETTERS AND SETTERS################################

}