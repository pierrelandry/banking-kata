package fr.octo;

public class Account {
    private Transactions transactions;
    private int balance;
    private static final int INIT_BALANCE = 0;

    Account() {
        this.balance = INIT_BALANCE;
        this.transactions = new Transactions();
    }

    void deposit(int deposit) {
        String date = "01.01.2018";
        this.balance = this.balance + deposit;
        transactions.addTransactions(date, deposit, this.balance);

    }

    public void withdraw(int withdraw) {

    }

    String printStatement() {
        return transactions.toString();
    }

    int getBalance() {
        return this.balance;
    }
}
