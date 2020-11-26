package Bank.count;

public class LongTermAccount extends SavingAccount {
    private AccountType accountType = AccountType.LONG_TERM_ACCOUNT;

    public LongTermAccount(Person person, long accountNumber, String secondPass) {
        super(person, accountNumber, secondPass);
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }
}
