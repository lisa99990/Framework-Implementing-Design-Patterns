package bank;

import framework.InterestStrategy;

/**
 * @author Mohammad Aiub Khan
 */
@SuppressWarnings("unused")
public class SavingInterestStrategy extends InterestStrategy {
    public SavingInterestStrategy() {
        super(3);
    }

    public SavingInterestStrategy(double interestRate) {
        super(interestRate);
    }

    @Override
    public Double getInterest(Double balance) {
        return balance * (super.getInterestRate() / 100);
    }
}
