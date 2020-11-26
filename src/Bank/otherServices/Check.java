package Bank.otherServices;

import Bank.count.Person;

public class Check {
    private long checkNumber;
    private Person person;
    private int checkBalance;
    public Check(Person person, long checkNumber, int checkBalance){
        this.person = person;
        this.checkNumber = checkNumber;
        this.checkBalance = checkBalance;
    }

    public long getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(long checkNumber) {
        this.checkNumber = checkNumber;
    }

    public int getCheckBalance() {
        return checkBalance;
    }

    public void setCheckBalance(int checkBalance) {
        this.checkBalance = checkBalance;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
