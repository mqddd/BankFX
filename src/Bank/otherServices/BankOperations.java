package Bank.otherServices;

import Bank.count.Account;
import Bank.count.AccountType;
import Bank.count.Person;

public interface BankOperations {
    void deposit(int enterMoney, Person person, int AccountNumber);
    void cardToCard(int yourCardNumber, int money, int itsCardNumber);
    void accountToAccount(int yourAccountNumber, int money, int itsAccountNumber);
    double withdraw(Person person, int AccountNumber);
    void changeFirstPass(Person person, int AccountNumber, String newPass);
    Check createNewCheck(Person person, long checkNumber, int checkBalance);
    void payCheck();
    CreditCard createNewCreditCard(long cardNumber, AccountType accountType, String secondPass);
    boolean getLoan(int accountNumber);
    void payLoan(Person person, int accountNumber, int money);
    //other services
    Account createNewAccount(Person person, AccountType accountType, String secondPass);

}
