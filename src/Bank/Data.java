package Bank;

import Bank.count.Account;
import Bank.count.Person;
import Bank.otherServices.Check;
import Bank.otherServices.CreditCard;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Long> accountNumbers = new ArrayList<>();
    private static ArrayList<Long> cardNumbers = new ArrayList<>();
    private static ArrayList<Long> checkNumbers = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<CreditCard> cards = new ArrayList<>();
    private static ArrayList<Check> checks = new ArrayList<>();
    private static ArrayList<Person> people = new ArrayList<>();

    public static ArrayList<Long> getAccountNumbers() {
        return accountNumbers;
    }

    public static void setAccountNumbers(ArrayList<Long> accountNumbers) {
        Data.accountNumbers = accountNumbers;
    }

    public static ArrayList<Long> getCardNumbers() {
        return cardNumbers;
    }

    public static void setCardNumbers(ArrayList<Long> cardNumbers) {
        Data.cardNumbers = cardNumbers;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void setAccounts(ArrayList<Account> accounts) {
        Data.accounts = accounts;
    }

    public static ArrayList<Person> getPeople() {
        return people;
    }

    public static void setPeople(ArrayList<Person> people) {
        Data.people = people;
    }

    public static ArrayList<CreditCard> getCards() {
        return cards;
    }

    public static void setCards(ArrayList<CreditCard> cards) {
        Data.cards = cards;
    }

    public static ArrayList<Long> getCheckNumbers() {
        return checkNumbers;
    }

    public static void setCheckNumbers(ArrayList<Long> checkNumbers) {
        Data.checkNumbers = checkNumbers;
    }

    public static ArrayList<Check> getChecks() {
        return checks;
    }

    public static void setChecks(ArrayList<Check> checks) {
        Data.checks = checks;
    }
}
