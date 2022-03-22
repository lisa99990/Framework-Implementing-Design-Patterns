package bank;

import framework.Customer;

@SuppressWarnings("unused")
public class SavingAccount extends BankAccount {
    public SavingAccount(String accountNumber, Customer customer) {
        super(accountNumber, customer);
        setInterestStrategy(new SavingInterestStrategy());
    }
}


