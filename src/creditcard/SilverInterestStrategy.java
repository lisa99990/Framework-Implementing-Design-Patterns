package creditcard;

import framework.InterestStrategy;

/**
 * @author Mohammad Aiub Khan
 */

@SuppressWarnings("unused")
public class SilverInterestStrategy extends InterestStrategy {
    public SilverInterestStrategy() {
        super(8);
    }

    public SilverInterestStrategy(double interestRate) {
        super(interestRate);
    }

    @Override
    public Double getInterest(Double balance) {
        return balance * (super.getInterestRate() / 100);
    }
}
