package framework;

@SuppressWarnings("unused")
public abstract class Promotion extends InterestStrategy {
    InterestStrategy interestStrategy;

    public Promotion(double interestRate) {
        super(interestRate);
    }

    public void setInterestStrategy(InterestStrategy interestStrategy) {
        this.interestStrategy = interestStrategy;
    }

    @Override
    public Double getInterest(Double balance) {
        return interestRate + interestStrategy.getInterestRate();
    }
}
