package creditcard;

import framework.Customer;

import java.time.LocalDate;

public class SilverCreditCardAccount extends CreditCardAccount {
    public SilverCreditCardAccount(String accountNumber, Customer customer, LocalDate expirationDate) {
        super(accountNumber, customer, expirationDate);
        balance=0.0;
        setMinimumPayment(12.0);
        setInterestStrategy(new SilverInterestStrategy());
        setLimit(2000.00);
    }
}
