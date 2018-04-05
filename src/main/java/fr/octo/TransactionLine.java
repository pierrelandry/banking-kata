package fr.octo;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TransactionLine {

    public static final int DATE_OFFSET = 8;
    public static final int BALANCE_OFFSET = 10;

    String date;
    int amount;
    int balance;

    public TransactionLine(String date, int amount, int balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    private String calculatePrintOffsetAmount() {
        return IntStream.range(0, DATE_OFFSET - String.valueOf(this.amount).length())
                .mapToObj(i -> " ")
                .collect(Collectors.joining(""));
    }

    private String calculatePrintOffsetBalance() {
        return IntStream.range(0, BALANCE_OFFSET - String.valueOf(this.balance).length())
                .mapToObj(i -> " ")
                .collect(Collectors.joining(""));
    }

    @Override
    public String toString() {
        String transactionLine;

        if (this.amount >= 0) {
            transactionLine = this.date
                    + calculatePrintOffsetAmount() + "+" + this.amount
                    + calculatePrintOffsetBalance() + this.balance + "\n";
        } else {
            transactionLine = this.date
                    + calculatePrintOffsetAmount() + " " + this.amount
                    + calculatePrintOffsetBalance() + this.balance + "\n";
        }

        return transactionLine;
    }
}