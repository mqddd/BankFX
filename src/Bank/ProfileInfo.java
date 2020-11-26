package Bank;

import java.io.Serializable;

public class ProfileInfo implements Serializable {
    private String name;
    private String pass;
    private String accountTypes;
    private String cardNumbers;
    private String checkNumbers;

    public ProfileInfo(String name, String pass){
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(String accountTypes) {
        this.accountTypes = accountTypes;
    }

    public String getCardNumbers() {
        return cardNumbers;
    }

    public void setCardNumbers(String cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

    public String getCheckNumbers() {
        return checkNumbers;
    }

    public void setCheckNumbers(String checkNumbers) {
        this.checkNumbers = checkNumbers;
    }
}
