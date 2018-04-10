package fr.octo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Account {
    private Transactions transactions;
    private int balance;
    private static final int INIT_BALANCE = 0;
    private DateProvider dateProvider;

    Account(DateProvider dateProvider) {
        this.balance = INIT_BALANCE;
        this.dateProvider = dateProvider;
        this.transactions = new Transactions();
    }

    void deposit(int deposit) {

        LocalDate localDate = dateProvider.getNow();

        String date = formatDate(localDate);

        this.balance = this.balance + deposit;
        transactions.addTransactions(date, deposit, this.balance);

    }

    void withdraw(int withdraw) throws Exception {
        if (this.getBalance() < withdraw) {
            throw new Exception("Not enough money!");
        } else {
            LocalDate localDate = dateProvider.getNow();

            String date = formatDate(localDate);
            this.balance = this.balance - withdraw;
            transactions.addTransactions(date, -1 * withdraw, this.balance);
        }
    }

    String printStatement() {
        return transactions.toString();
    }

    int getBalance() {
        return this.balance;
    }



    String formatDate(LocalDate localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return localDate.format(formatter);
    }


}
