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

    void withdraw(int withdraw) throws Exception{
            if (this.getBalance() < withdraw) {
                throw new Exception("Not enough money!");
            } else {
                String date = "01.01.2018";
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
}
