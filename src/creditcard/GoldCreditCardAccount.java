package creditcard;

import framework.Customer;

import java.time.LocalDate;

public class GoldCreditCardAccount extends CreditCardAccount {
    public GoldCreditCardAccount(String creditcardNumber, Customer customer, LocalDate expirationDate) {
        super(creditcardNumber, customer, expirationDate);

        balance= 0.0;
        setMinimumPayment(10.0);
        setInterestStrategy(new GoldInterestStrategy());
        setLimit(3000.0);
        this.addObserver(new CreditCardEmailNotification());
    }
}
