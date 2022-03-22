package framework;

public class Company extends Customer {
    private int numberOfEmployees;

    public Company(String id, String name, int numberOfEmployees, Address address) {
        super(id, name, address);
        this.numberOfEmployees = numberOfEmployees;
    }
    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }
}
