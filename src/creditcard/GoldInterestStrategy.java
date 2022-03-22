package creditcard;

import framework.InterestStrategy;

/**
 * @author Mohammad Aiub Khan
 */

@SuppressWarnings("unused")
public class GoldInterestStrategy extends InterestStrategy {
    public GoldInterestStrategy() {
        super(6);
    }

    public GoldInterestStrategy(double interestRate) {
        super(interestRate);
    }

    @Override
    public Double getInterest(Double balance) {
        return balance * (super.getInterestRate() / 100);
    }
}
