package Bank.otherServices;

import Bank.count.AccountType;

public class CreditCard {
    private long cardNumber;
    private AccountType accountType;
    private int cardBalance;
    private String secondPass;
    public CreditCard(long cardNumber, AccountType accountType, String secondPass){
        this.cardNumber = cardNumber;
        this.accountType = accountType;
        this.cardBalance = 0;
        this.secondPass = secondPass;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public int getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(int cardBalance) {
        this.cardBalance = cardBalance;
    }

    public void reduceBalance(int money){
        this.cardBalance -= money;
    }

    public void increaseBalance(int money){
        this.cardBalance += money;
    }

    public String getSecondPass() {
        return secondPass;
    }

    public void setSecondPass(String secondPass) {
        this.secondPass = secondPass;
    }
}
