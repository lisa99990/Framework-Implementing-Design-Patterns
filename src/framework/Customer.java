package framework;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {
    private String id;
    private String name;
    private Address address;

    private List<Account> accounts;


    public Customer(String id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
        accounts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
