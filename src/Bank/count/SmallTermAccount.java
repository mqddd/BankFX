package Bank.count;

public class SmallTermAccount extends SavingAccount{
    private final AccountType accountType = AccountType.SMALL_TERM_ACCOUNT;

    public SmallTermAccount(Person person, long accountNumber, String secondPass) {
        super(person, accountNumber, secondPass);
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }
}
