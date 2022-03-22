package bank;

import framework.*;

public class CompanyEmailNotification implements Notification {


    @Override
    public void update(Subject subject, Object arg) {
        Account account = (Account) subject;
        Double amount = (Double) arg;

        Customer company = account.getCustomer();
        String email = company.getAddress().getEmail();

        StringBuilder message = new StringBuilder();
        if (account.getBalance() + amount < 0  || amount == null) {
            message.append("There is no enough balance to make the withdraw");
            sendEmail(email, message.toString());
        } else {
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
