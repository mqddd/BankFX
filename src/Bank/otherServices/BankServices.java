package Bank.otherServices;

import Bank.Data;
import Bank.count.*;

public class BankServices implements BankOperations{

    @Override
    public void deposit(int enterMoney, Person person, int AccountNumber) {
        for (int i = 0 ; i < person.getAccounts().size() ; i++){
            if (person.getAccounts().get(i).getAccountNumber() == AccountNumber){
                person.getAccounts().get(i).addAccountBalance(enterMoney);
            }
        }
    }

    @Override
    public void cardToCard(int yourCardNumber, int money, int itsCardNumber) {
        for (int i = 0; i < Data.getCards().size(); i++) {
            if (yourCardNumber == Data.getCards().get(i).getCardNumber()){
                Data.getCards().get(i).reduceBalance(money);
            }
        }
        for (int i = 0; i < Data.getCards().size(); i++) {
            if (itsCardNumber == Data.getCards().get(i).getCardNumber()){
                Data.getCards().get(i).increaseBalance(money);
            }
        }
    }

    @Override
    public void accountToAccount(int yourAccountNumber, int money, int itsAccountNumber) {
        for (int i = 0; i < Data.getAccounts().size(); i++) {
            if (yourAccountNumber == Data.getAccounts().get(i).getAccountNumber()){
                Data.getAccounts().get(i).reduceBalance(money);
            }
        }
        for (int i = 0; i < Data.getAccounts().size(); i++) {
            if (itsAccountNumber == Data.getAccounts().get(i).getAccountNumber()){
                Data.getAccounts().get(i).increaseBalance(money);
            }
        }
    }

    @Override
    public double withdraw(Person person, int AccountNumber) {
        for (int i = 0 ; i < person.getAccounts().size() ; i++){
            if (person.getAccounts().get(i).getAccountNumber() == AccountNumber){
                return person.getAccounts().get(i).getAccountBalance();
            }
        }
        return 0;
    }

    @Override
    public void changeFirstPass(Person person, int AccountNumber, String newPass) {
        for (int i = 0 ; i < person.getAccounts().size() ; i++) {
            if(person.getAccounts().get(i).getAccountNumber() == AccountNumber){
                person.getAccounts().get(i).setSecondPass(newPass);
            }
        }
    }

    @Override
    public Check createNewCheck(Person person, long checkNumber, int checkBalance) {
        return new Check(person, person.createRandomCheckNumber(), checkBalance);
    }

    @Override
    public void payCheck() {

    }

    @Override
    public CreditCard createNewCreditCard(long cardNumber, AccountType accountType, String secondPass) {
        return new CreditCard(cardNumber, accountType, secondPass);
    }

    @Override
    public boolean getLoan(int accountNumber) {
        for (int i = 0; i < Data.getAccounts().size(); i++) {
            if (Data.getAccounts().get(i).getAccountNumber() == accountNumber){
                if (Data.getAccounts().get(i).getAccountBalance() >= 1000){
                    return true;
                }
                else return false;
            } else
                return false;
        }
        return false;
    }

    @Override
    public void payLoan(Person person, int accountNumber, int money) {
        for (int i = 0; i < person.getAccounts().size(); i++) {
            if (accountNumber == person.getAccounts().get(i).getAccountNumber()){
                person.getAccounts().get(i).reduceBalance(money);
            }
        }
    }

    @Override
    public Account createNewAccount(Person person, AccountType accountType, String secondPass){
        switch (accountType){
            case BORROW_ACCOUNT:
                return new BorrowAccount(person, person.createRandomAccountNumber(), secondPass);
            case CURRENT_ACCOUNT:
                return new CurrentAccount(person, person.createRandomAccountNumber(), secondPass);
            case SAVING_ACCOUNT:
                return new SavingAccount(person, person.createRandomAccountNumber(), secondPass);
            case SMALL_TERM_ACCOUNT:
                return new SmallTermAccount(person, person.createRandomAccountNumber(), secondPass);
            case LONG_TERM_ACCOUNT:
                return new LongTermAccount(person, person.createRandomAccountNumber(), secondPass);
            default:
                return null;
        }
    }
}
