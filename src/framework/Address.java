package framework;

public class Address {
    private final String street;
    private final String city;
    private final String zipCode;
    private final State state;
    private final String email;

    public Address(String street, String city, String zipCode, State state, String email) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public State getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }
}
