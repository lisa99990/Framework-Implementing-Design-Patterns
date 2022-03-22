package creditcard;

import framework.InterestStrategy;

/**
 * @author Mohammad Aiub Khan
 */
@SuppressWarnings("unused")
public class BronzeInterestStrategy extends InterestStrategy {
    public BronzeInterestStrategy() {
        super(10);
    }

    public BronzeInterestStrategy(double interestRate) {
        super(interestRate);
    }

    @Override
    public Double getInterest(Double balance) {
        return balance * (super.getInterestRate() / 100);
    }
}
