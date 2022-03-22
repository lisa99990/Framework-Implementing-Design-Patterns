package bank;

import framework.*;

import java.time.LocalDate;

public class TestApp {
    public static void main(String[] args) {
        AccountDatabaseFactoryImplementation factory = new AccountDatabaseFactoryImplementation();
        AccountServiceImplementation service = (AccountServiceImplementation) factory.createAccountService(AccountDatabaseType.IN_MEMORY);

        Address address = new Address("1000 N", "Fairfield", "52557", State.IA, "redwan@gmil.com");
        Customer ridwan = new Person("0001", "Ridwan Mohammed", LocalDate.of(1997, 1, 1), address);
        BankAccount savingAccount = new SavingAccount("0001", ridwan);
        BankAccount checkingAccount = new CheckingAccount("0003", ridwan);
        service.createAccount(savingAccount);
        service.createAccount(checkingAccount);

        service.deposit("0001", 5000.0);
        service.deposit("0001", 100.0);
        service.deduct("0001", 500.0);
        service.deduct("0001", 5000.0);

        service.deposit("0003", 5430.0);
        service.deposit("0003", 143.0);
        service.deduct("0003", 543.0);
        service.deduct("0003", 5430.0);
        System.out.println(service.getAccount("0001").getBalance());
        service.addInterestToAllAccount();
        System.out.println(service.getAccount("0001").getBalance());
        String[] a = {"0001"};
        service.generateReport(a, LocalDate.of(2010, 10, 10), LocalDate.of(2033, 10, 10)).stream().forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        Customer google = new Company("0002", "Google Inc.", 10000, address);
        BankAccount saving2 = new SavingAccount("0002", google);
        service.createAccount(saving2);
        service.deposit("0002", 5000.0);
        service.deposit("0002", 100.0);
        service.generateReport().forEach(System.out::println);


    }
}
