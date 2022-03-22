package bank;


import framework.InterestStrategy;

/**
 * @author Mohammad Aiub Khan
 */

@SuppressWarnings("unused")
public class CheckingInterestStrategy extends InterestStrategy {

    public CheckingInterestStrategy() {
        super(6);
    }

    public CheckingInterestStrategy(double interestRate) {
        super(interestRate);
    }


    @Override
    public Double getInterest(Double balance) {
        return balance * (super.getInterestRate() / 100);
    }

}
