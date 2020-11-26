package Bank.count;

public abstract class Account {

    public abstract double getAccountBalance();

    public abstract void setAccountBalance(int accountBalance);

    public abstract void addAccountBalance(double money);

    public abstract long getAccountNumber();

    public abstract String getAccountOwnerName();

    public abstract String getSecondPass();

    public abstract void setSecondPass(String secondPass);

    public abstract void reduceBalance(int money);

    public abstract void increaseBalance(int money);

    public abstract AccountType getAccountType();
}
