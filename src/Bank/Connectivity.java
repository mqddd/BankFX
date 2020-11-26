package Bank;

import Bank.count.Account;
import Bank.count.AccountType;
import Bank.count.Person;
import Bank.otherServices.Check;
import Bank.otherServices.CreditCard;
import jdk.jshell.PersistentSnippet;

import java.sql.*;
import java.util.ArrayList;

public class Connectivity {
    private final static String URL = "jdbc:mysql://localhost:3306/bank_project";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    private static Connection connection;
    private static Statement statement;
    private Bill bill;
    {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connectivity(){}

    public Connectivity(Bill bill){
        this.bill = bill;
    }

    public void createTableForPerson(){
        String sql = "CREATE TABLE persons " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " name TEXT, " +
                    " last_name TEXT, " +
                    " first_pass TEXT " +
                    " )";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableForAccounts(){
        String sql = "CREATE TABLE accounts " +
                "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                " account_number TEXT," +
                " account_type TEXT, " +
                " account_owner TEXT, " +
                " second_pass TEXT, " +
                " account_balance INTEGER, " +
                " person_id INTEGER )";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableForCards(){
        String sql = "CREATE TABLE cards " +
                "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                " card_number TEXT, " +
                " account_type TEXT, " +
                " owner TEXT, " +
                " second_pass TEXT, " +
                " card_balance INTEGER, " +
                " person_id INTEGER )";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableForChecks(){
        String sql = "CREATE TABLE checks " +
                "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                " check_number TEXT, " +
                " check_balance INTEGER, " +
                " owner TEXT, " +
                " person_id INTEGER )";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPerson(Person person){
        String person_name = person.getPersonName();
        String person_last_name = person.getPersonLastName();
        String person_first_pass = person.getFirstPass();

        String sql = "INSERT INTO persons (name, last_name, first_pass) VALUES ('" + person_name + "', '" + person_last_name + "', '" + person_first_pass + "')";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isLoginInfoTrue(String username, String pass){
        String sql = "SELECT name, first_pass FROM persons";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (username.equals(resultSet.getString("name")) && pass.equals(resultSet.getString("first_pass"))){
                    return true;
                }
                else continue;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addNewAccount(Account account, Person person, int accountBalance){
        String accountType = String.valueOf(account.getAccountType());
        String accountNumber = String.valueOf(person.createRandomAccountNumber());
        String sql = "INSERT INTO accounts (account_number, account_type, account_owner, second_pass, account_balance, person_id) VALUES ('" + accountNumber +
                        "', '" + accountType +
                        "', '" + person.getPersonName() +
                        "', '" + account.getSecondPass() +
                        "', " + accountBalance +
                        ", " + getPersonId(person) + ")";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPersonId(Person person){
        String sql = "SELECT id, name FROM persons";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (person.getPersonName().equals(resultSet.getString("name")))
                    return resultSet.getInt("id");
                else continue;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean searchAccountNumber(String number){
        String sql = "SELECT account_number FROM accounts";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (number.equals(resultSet.getString("account_number")))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isMyAccount(String number, Person person){
        String sql = "SELECT account_number FROM accounts WHERE person_id =" + getPersonId(person);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
                if (number.equals(resultSet.getString("account_number")))
                    return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getMoneyFromAccount(String accountNumber){
        String sql = "SELECT account_balance FROM accounts WHERE account_number =" + accountNumber;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
                return resultSet.getInt("account_balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return 0;
    }

    public void setMoneyInAccount(int money, String accountNumber){
        String sql = "UPDATE accounts SET account_balance =" + (getMoneyFromAccount(accountNumber) + money) + " WHERE account_number =" + accountNumber;
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reduceMoneyFromAccount(int money, String accountNumber){
        String sql = "UPDATE accounts SET account_balance =" + (getMoneyFromAccount(accountNumber) - money) + " WHERE account_number =" + accountNumber;
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isSuitableAccount(String accountNumber, Person person){
        String sql = "SELECT account_number, account_type FROM accounts WHERE person_id ="+ getPersonId(person);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (accountNumber.equals(resultSet.getString("account_number"))){
                    if (resultSet.getString("account_type").equals("CURRENT_ACCOUNT") || resultSet.getString("account_type").equals("BORROW_ACCOUNT")){
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addNewCard(CreditCard card, Person person, String accountNumber, int cardBalance){
        String cardNumber = String.valueOf(card.getCardNumber());
        String secondPass = card.getSecondPass();
        String sql = "INSERT INTO cards (card_number, account_type, owner, second_pass, card_balance, person_id) VALUES ('" + cardNumber +
                "', '" + getAccountTypeForCard(accountNumber).toString() +
                "', '" + person.getPersonName() +
                "', '" + secondPass +
                "', " + cardBalance +
                ", " + getPersonId(person) +
                ")";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AccountType getAccountTypeForCard(String accountNumber){
        String sql = "SELECT account_type FROM accounts WHERE account_number =" + accountNumber;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (resultSet.getString("account_type").equals("CURRENT_ACCOUNT"))
                    return AccountType.CURRENT_ACCOUNT;
                else return AccountType.BORROW_ACCOUNT;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isAccountSuitableForCheck(String accountNumber, Person person){
        String sql = "SELECT account_number, account_type FROM accounts WHERE person_id ="+ getPersonId(person);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                if (accountNumber.equals(resultSet.getString("account_number"))){
                    if (resultSet.getString("account_type").equals("CURRENT_ACCOUNT")){
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getAccountBalance(String accountNumber){
        String sql = "SELECT account_balance FROM accounts WHERE account_number='" + accountNumber + "'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                return resultSet.getInt("account_balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void createNewCheck(Check check, Person person, String checkAmount, String accountNumber) throws InvalidCheck {
        String checkNumber = String.valueOf(check.getCheckNumber());
        if (getAccountBalance(accountNumber) > Integer.parseInt(checkAmount)){
            String sql = "INSERT INTO checks (check_number, check_balance, owner, person_id) VALUES ('" + checkNumber +
                    "', " + check.getCheckBalance() +
                    ", '" + check.getPerson().getPersonName() +
                    "', " + getPersonId(person) + ") ";
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new InvalidCheck(person);
        }
    }

    public String[] updateProfileAccounts(Person person){
        String sql = "SELECT account_type FROM accounts WHERE person_id =" + getPersonId(person);
        String[] accounts = new String[10];
        int j = 0;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            for (int i = 0; resultSet.next() ; i++) {
                accounts[i] = resultSet.getString("account_type");
                j = i;
            }
            for (int i = j + 1; i < 10; i++) {
                accounts[i] = "";
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] updateProfileCards(Person person){
        String sql = "SELECT card_number FROM cards WHERE person_id =" + getPersonId(person);
        String[] cards = new String[10];
        int j = 0;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            for (int i = 0; resultSet.next() ; i++) {
                cards[i] = resultSet.getString("card_number");
                j = i;
            }
            for (int i = j + 1; i < 10; i++) {
                cards[i] = "";
            }
            return cards;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] updateProfileChecks(Person person){
        String sql = "SELECT check_number FROM checks WHERE person_id =" + getPersonId(person);
        String[] checks = new String[10];
        int j = 0;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            for (int i = 0; resultSet.next() ; i++) {
                checks[i] = resultSet.getString("check_number");
                j = i;
            }
            for (int i = j + 1; i < 10; i++) {
                checks[i] = "";
            }
            return checks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void payBill(String accountNumber, Person person) throws InterruptedException {
        synchronized (this){
            String sql = "SELECT account_number, account_balance FROM accounts WHERE person_id =" + getPersonId(person);
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    if (resultSet.getString("account_number").equals(accountNumber)){
                        while (!(resultSet.getInt("account_balance") >= bill.getPay())){
                            wait();
                        }
                        bill.setPayed(true);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void payingBillIsReady(String accountNumber, Person person){
        synchronized (this){
            String sql = "SELECT account_number, account_balance FROM accounts WHERE person_id =" + getPersonId(person);
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    if (resultSet.getString("account_number").equals(accountNumber)){
                        if (resultSet.getInt("account_balance") >= bill.getPay()){
                            bill.setPayed(true);
                            notify();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void accountToAccount(String firstAccount, String secondAccount, int money){
        reduceMoneyFromAccount(money, firstAccount);
        setMoneyInAccount(money, secondAccount);
    }

}
