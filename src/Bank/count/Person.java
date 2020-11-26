package Bank.count;

import Bank.Data;
import Bank.otherServices.BankServices;
import Bank.otherServices.Check;
import Bank.otherServices.CreditCard;

import java.util.ArrayList;

public class Person {
    private final String personName;
    private final String personLastName;
    private String firstPass;
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<CreditCard> cards = new ArrayList<>();
    private ArrayList<Check> checks = new ArrayList<>();
    private BankServices bankServices = new BankServices();

    public Person(String name, String lastName, String firstPass){
        this.personName = name;
        this.personLastName = lastName;
        this.firstPass = firstPass;
    }

    public long createRandomAccountNumber(){
        long num;
        boolean flag = false;
        while (true) {
            num = (long)(Math.random() * Math.pow(10, 16));
            for (int i = 0; i < Data.getAccountNumbers().size(); i++) {
                if (num == Data.getAccountNumbers().get(i)) {
                    flag = true;
                }
            }
            if (flag == false){
                break;
            }
        }
        return num;
    }

    public long createRandomCardNumber(){
        long num;
        boolean flag = false;
        while (true) {
            num = (long)(Math.random() * Math.pow(10, 16));
            for (int i = 0; i < Data.getCardNumbers().size(); i++) {
                if (num == Data.getCardNumbers().get(i)) {
                    flag = true;
                }
            }
            if (flag == false){
                break;
            }
        }
        return num;
    }

    public Long createRandomCheckNumber(){
        long num;
        boolean flag = false;
        while (true) {
            num = (long)(Math.random() * Math.pow(10, 16));
            for (int i = 0; i < Data.getCheckNumbers().size(); i++) {
                if (num == Data.getCheckNumbers().get(i)) {
                    flag = true;
                }
            }
            if (flag == false){
                break;
            }
        }
        return num;
    }

    public void addAccount(AccountType accountType, String secondPass){
        this.accounts.add(this.bankServices.createNewAccount(this, accountType, secondPass));
    }

    public void addCreditCard(AccountType accountType, String secondPass) throws Exception{
        if (accountType == AccountType.CURRENT_ACCOUNT || accountType == AccountType.BORROW_ACCOUNT)
            this.cards.add(this.bankServices.createNewCreditCard(this.createRandomCardNumber(), accountType, secondPass));
        else throw new Exception("AccountType is not valid");
    }

    public void addCheck(int checkBalance){
        this.checks.add(this.bankServices.createNewCheck(this, this.createRandomCheckNumber(), checkBalance));
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<CreditCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CreditCard> cards) {
        this.cards = cards;
    }

    public ArrayList<Check> getChecks() {
        return checks;
    }

    public void setChecks(ArrayList<Check> checks) {
        this.checks = checks;
    }

    public String getFirstPass() {
        return firstPass;
    }

    public void setFirstPass(String firstPass) {
        this.firstPass = firstPass;
    }
}
