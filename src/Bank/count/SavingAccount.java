package Bank.count;

public class SavingAccount extends Account {
    private long accountNumber;
    private String accountOwnerName;
    private int accountBalance;
    private String secondPass;

    private final AccountType accountType = AccountType.SAVING_ACCOUNT;
    private int interest;

    public SavingAccount(Person person, long number, String secondPass) {
        this.accountNumber = number;
        this.accountOwnerName = person.getPersonName() + " " + person.getPersonLastName();
        this.accountBalance = 0;
        this.secondPass = secondPass;
    }

    @Override
    public double getAccountBalance() {
        return this.accountBalance;
    }

    @Override
    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public void addAccountBalance(double money) {
        this.accountBalance += money;
    }

    @Override
    public long getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public String getAccountOwnerName() {
        return this.accountOwnerName;
    }

    @Override
    public String getSecondPass() {
        return this.secondPass;
    }

    @Override
    public void setSecondPass(String secondPass) {
        this.secondPass = secondPass;
    }

    @Override
    public void reduceBalance(int money) {
        this.accountBalance -= money;
    }

    @Override
    public void increaseBalance(int money) {
        this.accountBalance += money;
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }
}
