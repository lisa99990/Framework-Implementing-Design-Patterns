package bank;

import framework.Company;
import framework.Customer;
import framework.Observer;
import framework.Person;

public class CheckingAccount extends BankAccount {
    public CheckingAccount(String accountNumber, Customer customer) {
        super(accountNumber, customer);
        setInterestStrategy(new CheckingInterestStrategy());
    }

}
