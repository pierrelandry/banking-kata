package fr.octo;

import java.util.ArrayList;
import java.util.List;

public class Transactions {
    static final String TRANSACTIONS_HEADER = "Date         Amount   Balance\n";
    List<TransactionLine> transactionLines;

    Transactions () {
        this.transactionLines = new ArrayList<>();
    }

    void addTransactions(String date, int deposit, int balance) {
        TransactionLine transactionLine = new TransactionLine(date, deposit, balance);
        transactionLines.add(transactionLine);
    }

    @Override
    public String toString() {
        String transactions = "\n" + TRANSACTIONS_HEADER;

        for(TransactionLine line : transactionLines) {
            transactions += line.toString();
        }

        return transactions;
    }
}
