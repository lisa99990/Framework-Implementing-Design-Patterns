package creditcard;

import framework.Report;
import framework.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreditCardReport implements Report {
    private LocalDate from;
    private LocalDate to;
    private CreditCardAccount account;

    public CreditCardReport(CreditCardAccount account, LocalDate from, LocalDate to) {
        this.account = account;
        this.from = from;
        this.to = to;
    }

    @Override
    public List<String> generateReport() {
        List<String> reports = new ArrayList<>();

        for (Transaction t : account.getAllTransactions()) {
            if (t.getDate().isBefore(to) && t.getDate().isAfter(from)) {
                reports.add(t.toString());
            }
        }
        return reports;
    }
}
