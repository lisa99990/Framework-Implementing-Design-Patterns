package Test;

import bank.SavingInterestStrategy;
import framework.InterestStrategy;

/**
 * @author Mohammad Aiub Khan
 */

public class Test1 {
    public static void main(String[] args) {
        InterestStrategy si = new SavingInterestStrategy(5);
        System.out.println(si.getInterest(100.00));
    }
}
