package creditcard;

import framework.*;

public class CreditCardEmailNotification implements Notification {


    @Override
    public void update(Subject subject, Object arg) {
        Account account = (Account) subject;
        Double amount = (Double) arg;

        Customer company = account.getCustomer();
        String email = company.getAddress().getEmail();

        StringBuilder message = new StringBuilder();
        if (amount >= 400 || amount == null) {
            message.append("There is a transaction on you account ");

            Transaction lastTransaction = account.getAllTransactions().get(account.getAllTransactions().size() - 1);
            message.append("Account number: " + account.getAccountNumber());
            message.append(" " + lastTransaction.getDescription());
            message.append(" amount: " + lastTransaction.getAmount());
            message.append(" on " + lastTransaction.getDate());
            sendEmail(email, message.toString());
        }

    }

    public void sendEmail(String to, String message) {
        System.out.println("EMAIL -\tAddress:" + to + "\t Message: " + message);
    }

}