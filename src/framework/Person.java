package framework;

import java.time.LocalDate;

public class Person extends  Customer{
    private LocalDate birthdate;
    public Person(String customerId, String name, LocalDate birthdate, Address address) {
        super(customerId, name, address);
        this.birthdate = birthdate;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}
