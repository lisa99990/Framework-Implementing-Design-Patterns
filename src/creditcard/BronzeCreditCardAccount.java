package creditcard;

import framework.Customer;

import java.time.LocalDate;

public class BronzeCreditCardAccount extends CreditCardAccount {
    public BronzeCreditCardAccount(String accountNumber, Customer customer, LocalDate expirationDate) {
        super(accountNumber, customer, expirationDate);
        balance = 0.0;
        setMinimumPayment(14.0);
        setInterestStrategy(new BronzeInterestStrategy());
        setLimit(1500.0);
    }
}
