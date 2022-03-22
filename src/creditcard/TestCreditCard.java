package creditcard;


import framework.*;

import java.time.LocalDate;

public class TestCreditCard {
    public static void main(String[] args) {

        AccountDatabaseFactoryImplementation factory = new AccountDatabaseFactoryImplementation();
        AccountServiceImplementation service = (AccountServiceImplementation) factory.createAccountService(AccountDatabaseType.IN_MEMORY);

        Address address = new Address("1000 N", "Fairfield", "52557", State.IA, "redwan@gmil.com");
        Customer ridwan = new Person("0001", "Ridwan Mohammed", LocalDate.of(1997, 1, 1), address);

        CreditCardAccount ridwanGold = new GoldCreditCardAccount("123456789", ridwan,LocalDate.now().plusYears(5));
        service.createAccount(ridwanGold);
        service.deposit("123456789", 500.0);
        service.deduct("123456789", 450.0);
        service.deduct("123456789", 4500.0);

        service.addInterestToAllAccount();
        System.out.println(service.getAccount("123456789").getBalance());
        ridwanGold.getMonthlyReport();
    }
}
