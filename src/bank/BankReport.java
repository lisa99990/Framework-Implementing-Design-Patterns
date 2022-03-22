package bank;

import framework.Report;
import framework.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankReport implements Report {

    private LocalDate from;
    private LocalDate to;
    private BankAccount account;

    public BankReport(BankAccount account, LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
        this.account = account;
    }

    @Override
    public List<String> generateReport() {
        ArrayList<String> reports = new ArrayList<>();
        for (Transaction t : account.getAllTransactions()) {
            if (t.getDate().isBefore(to) && t.getDate().isAfter(from)) {
                reports.add(t.toString());
            }
        }
        return reports;
    }
}


