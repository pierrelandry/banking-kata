package fr.octo;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TransactionLine {

    public static final int DATE_OFFSET = 8;
    public static final int BALANCE_OFFSET = 10;

    String date;
    int deposit;
    int balance;

    public TransactionLine(String date, int deposit, int balance) {
        this.date = date;
        this.deposit = deposit;
        this.balance = balance;
    }

    private String calculatePrintOffsetDeposit() {
        return IntStream.range(0, DATE_OFFSET - String.valueOf(this.deposit).length())
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
        return this.date
                + calculatePrintOffsetDeposit() + "+" + this.deposit
                + calculatePrintOffsetBalance() + this.balance+ "\n";
    }
}