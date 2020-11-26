package Bank;

import Bank.count.Person;

public class InvalidCheck extends Exception {
    Person person;

    public InvalidCheck(Person person){
        this.person = person;
    }
    public String toString(){
        return "dear " + person.getPersonName() + " your account balance is low !";
    }

}
