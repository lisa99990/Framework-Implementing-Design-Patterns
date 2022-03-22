package bank;

import framework.*;

public class PersonalEmailNotification implements Notification {


    @Override
    public void update(Subject subject, Object arg) {
        Account account = (Account) subject;
        Double amount = (Double) arg;

        Customer accountOwner = account.getCustomer();
        String name = accountOwner.getName();
        String accountNumber = account.getAccountNumber();
        String email = accountOwner.getAddress().getEmail();
        StringBuilder message = new StringBuilder();


        if (account.getBalance() - amount < 0 || amount == null) {
            message.append("Hello, " + name + "! ");
            message.append("Someone tried to overdraft from you account with account number: " + accountNumber);

            sendEmail(email, message.toString());

        } else if (amount >= 500.0 || amount <= -500) {
            Transaction lastTransaction = account.getAllTransactions().get(account.getAllTransactions().size() - 1);
            message.append("Hello, " + name + "! ");
            message.append("An amount of " + lastTransaction.getAmount());
            message.append(" has been " + lastTransaction.getDescription());
            message.append(" on " + lastTransaction.getDate());

            sendEmail(email, message.toString());
        }
    }

    public void sendEmail(String to, String message) {
        System.out.println("EMAIL -\t Address:" + to + "\t Message: " + message);
    }


}
