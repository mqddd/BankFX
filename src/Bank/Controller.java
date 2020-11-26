package Bank;

import Bank.count.AccountType;
import Bank.count.Person;
import Bank.otherServices.BankServices;
import Bank.otherServices.Check;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //create new account pane
    public ToggleGroup toggleGroup = new ToggleGroup();
    public JFXRadioButton current_account;
    public JFXRadioButton saving_account;
    public JFXRadioButton borrow_account;
    public JFXRadioButton long_account;
    public JFXRadioButton small_account;
    public JFXTextField primary_balance;
    public JFXButton confirmPane11;
    public Label invalidPane11;
    public JFXPasswordField second_pass;
    public Label validPane11;
    //put money pane
    public Label infoPane01;
    public JFXTextField accountNumberPane01;
    public JFXTextField amountPane01;
    public Label invalidPane01;
    public JFXButton confirmPane01;
    //get money pane
    public JFXTextField accountNumberPane04;
    public JFXTextField amountPane04;
    public JFXButton confirmPane04;
    public Label invalidPane04;
    public Label infoPane04;
    //get card pane
    public JFXTextField accountNumberPane08;
    public Label invalidPane08;
    public JFXButton confirmPane08;
    public Label validPane08;
    public JFXTextField primary_balancePane08;
    public JFXPasswordField second_passPane08;
    //get check pane
    public JFXTextField accountNumberPane06;
    public JFXButton confirmPane06;
    public Label invalidPane06;
    public JFXTextField checkAmountPane06;
    public Label validPane06;
    //profile pane
    public Label proName;
    public Label proPass;
    public Label proAccount;
    public Label proCard;
    public Label proCheck;
    public JFXButton updatePro;
    public JFXButton savePro;
    public Label saveLabelPro;
    public Label loadProfile;
    //pay bill pane
    public JFXTextField billCode;
    public JFXTextField bill_payment;
    public JFXTextField accountNumberPane12;
    public JFXButton confirmPane12;
    public Label invalidPane12;
    public Label validPane12;
    //account to account pane
    public JFXTextField secondAccountPane02;
    public JFXTextField firstAccountPane02;
    public Label invalidPane02;
    public JFXButton confirmPane02;
    public Label validPane02;
    public JFXTextField moneyPane02;


    //main variables
    private Connectivity connectivity = new Connectivity();
    private Person person = null;
    private ProfileInfo profileInfo = null;
    private BankServices bankServices = new BankServices();
    private Bill bill;

    //login page variables
    public JFXTextField userName;
    public JFXPasswordField password;
    public JFXButton confirm_btn;
    public Label invalid_label;
    //main page menu variables
    public AnchorPane second_scene;
    public JFXButton btn_01;
    public JFXButton btn_02;
    public JFXButton btn_03;
    public JFXButton btn_04;
    public JFXButton btn_05;
    public JFXButton btn_06;
    public JFXButton btn_07;
    public JFXButton btn_08;
    public JFXButton btn_09;
    public JFXButton btn_10;
    public JFXButton btn_00;
    public JFXButton btn_11;
    public JFXButton btn12;

    public Pane pane01;
    public Pane pane02;
    public Pane pane03;
    public Pane pane04;
    public Pane pane05;
    public Pane pane06;
    public Pane pane07;
    public Pane pane08;
    public Pane pane09;
    public Pane pane10;
    public Pane pane00;
    public Pane pane11;
    public Pane pane12;
    //main page panes
    public Pane putMoneyPane;
    public Pane cardToCardPane;
    public Pane accountToAccountPane;
    public Pane getMoneyPane;
    public Pane changePassPane;
    public Pane getChecksPane;
    public Pane getCardPane;
    public Pane getLoanPane;
    public Pane reachCheckPane;
    public Pane payLoanPane;
    public Pane profilePane;
    public Pane createAccountPane;
    public Pane payBillPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        current_account.setToggleGroup(toggleGroup);
        borrow_account.setToggleGroup(toggleGroup);
        saving_account.setToggleGroup(toggleGroup);
        long_account.setToggleGroup(toggleGroup);
        small_account.setToggleGroup(toggleGroup);
    }

    //login page
    public void onAction(ActionEvent actionEvent) {
        if (userName.getText().isBlank() || password.getText().isBlank()){
            invalid_label.setVisible(true);
        } else if (connectivity.isLoginInfoTrue(userName.getText(), password.getText())){
            person = new Person(userName.getText(), userName.getText(), password.getText());
            profileInfo = new ProfileInfo(userName.getText(), password.getText());
            String allAccounts = "";
            String allCards = "";
            String allChecks = "";
            for (int i = 0; i < connectivity.updateProfileAccounts(person).length; i++) {
                allAccounts += connectivity.updateProfileAccounts(person)[i];
                if (!connectivity.updateProfileAccounts(person)[i].equals(""))
                    allAccounts += ", ";
            }
            for (int i = 0; i < connectivity.updateProfileCards(person).length; i++) {
                allCards += connectivity.updateProfileCards(person)[i];
                if (!connectivity.updateProfileCards(person)[i].equals(""))
                    allCards += ", ";
            }
            for (int i = 0; i < connectivity.updateProfileChecks(person).length; i++) {
                allChecks += connectivity.updateProfileChecks(person)[i];
                if (!connectivity.updateProfileChecks(person)[i].equals(""))
                    allChecks += ", ";
            }
            proAccount.setText(allAccounts);
            proCard.setText(allCards);
            proCheck.setText(allChecks);
            proName.setText(person.getPersonName());
            proPass.setText(person.getFirstPass());

            profileInfo.setAccountTypes(allAccounts);
            profileInfo.setCardNumbers(allCards);
            profileInfo.setCheckNumbers(allChecks);

            try {
                loadPro();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            invalid_label.setVisible(false);
            second_scene.toFront();
        } else {
            invalid_label.setVisible(true);
        }

    }

    public void confirmBtnOnMouseEntered(MouseEvent mouseEvent) {
        confirm_btn.setStyle("-fx-background-color: #C2185B");
        confirm_btn.setTextFill(Color.WHITE);
    }

    public void confirmBtnOnMouseExited(MouseEvent mouseEvent) {
        confirm_btn.setStyle("-fx-background-color: #ffffff");
        confirm_btn.setTextFill(Color.valueOf("#880E4F"));
    }

    //main page menu buttons on mouse enter or exit
    public void btn01OnMouseEntered(MouseEvent mouseEvent) {
        btn_01.setStyle("-fx-background-color: #C51162");
    }

    public void btn01OnMouseExited(MouseEvent mouseEvent) {
        btn_01.setStyle("-fx-background-color: #880E4F");
    }

    public void btn02OnMouseEntered(MouseEvent mouseEvent) {
        btn_02.setStyle("-fx-background-color: #C51162");
    }

    public void btn02OnMouseExited(MouseEvent mouseEvent) {
        btn_02.setStyle("-fx-background-color: #880E4F");
    }

    public void btn03OnMouseEntered(MouseEvent mouseEvent) {
        btn_03.setStyle("-fx-background-color: #C51162");
    }

    public void btn03OnMouseExited(MouseEvent mouseEvent) {
        btn_03.setStyle("-fx-background-color: #880E4F");
    }

    public void btn04OnMouseEntered(MouseEvent mouseEvent) {
        btn_04.setStyle("-fx-background-color: #C51162");
    }

    public void btn04OnMouseExited(MouseEvent mouseEvent) {
        btn_04.setStyle("-fx-background-color: #880E4F");
    }

    public void btn05OnMouseEntered(MouseEvent mouseEvent) {
        btn_05.setStyle("-fx-background-color: #C51162");
    }

    public void btn05OnMouseExited(MouseEvent mouseEvent) {
        btn_05.setStyle("-fx-background-color: #880E4F");
    }

    public void btn06OnMouseEntered(MouseEvent mouseEvent) {
        btn_06.setStyle("-fx-background-color: #C51162");
    }

    public void btn06OnMouseExited(MouseEvent mouseEvent) {
        btn_06.setStyle("-fx-background-color: #880E4F");
    }

    public void btn07OnMouseEntered(MouseEvent mouseEvent) {
        btn_07.setStyle("-fx-background-color: #C51162");
    }

    public void btn07OnMouseExited(MouseEvent mouseEvent) {
        btn_07.setStyle("-fx-background-color: #880E4F");
    }

    public void btn08OnMouseEntered(MouseEvent mouseEvent) {
        btn_08.setStyle("-fx-background-color: #C51162");
    }

    public void btn08OnMouseExited(MouseEvent mouseEvent) {
        btn_08.setStyle("-fx-background-color: #880E4F");
    }

    public void btn09OnMouseEntered(MouseEvent mouseEvent) {
        btn_09.setStyle("-fx-background-color: #C51162");
    }

    public void btn09OnMouseExited(MouseEvent mouseEvent) {
        btn_09.setStyle("-fx-background-color: #880E4F");
    }

    public void btn10OnMouseEntered(MouseEvent mouseEvent) {
        btn_10.setStyle("-fx-background-color: #C51162");
    }

    public void btn10OnMouseExited(MouseEvent mouseEvent) {
        btn_10.setStyle("-fx-background-color: #880E4F");
    }

    public void btn00OnMouseEntered(MouseEvent mouseEvent) {
        btn_00.setStyle("-fx-background-color: #C51162");
    }

    public void btn00OnMouseExited(MouseEvent mouseEvent) {
        btn_00.setStyle("-fx-background-color: #880E4F");
    }

    public void btn11OnMouseEntered(MouseEvent mouseEvent) {
        btn_11.setStyle("-fx-background-color: #C51162");
    }

    public void btn11OnMouseExited(MouseEvent mouseEvent) {
        btn_11.setStyle("-fx-background-color: #880E4F");
    }

    public void btn12OnMouseEntered(MouseEvent mouseEvent) {
        btn12.setStyle("-fx-background-color: #C51162");
    }

    public void btn12OnMouseExited(MouseEvent mouseEvent) {
        btn12.setStyle("-fx-background-color: #880E4F");
    }

    //main page menu buttons on action
    public void btn01OnAction(ActionEvent actionEvent) {
        putMoneyPane.toFront();
        pane01.setVisible(true);
        pane00.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        invalidPane01.setVisible(false);
        amountPane01.setText("");
        accountNumberPane01.setText("");
        infoPane01.setVisible(false);
        validPane12.setVisible(false);
    }

    public void btn02OnAction(ActionEvent actionEvent) {
        cardToCardPane.toFront();
        pane02.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        validPane12.setVisible(false);
    }

    public void btn03OnAction(ActionEvent actionEvent) {
        accountToAccountPane.toFront();
        pane03.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        invalidPane02.setVisible(false);
        validPane02.setVisible(false);
        firstAccountPane02.setText("");
        secondAccountPane02.setText("");
        moneyPane02.setText("");
        validPane12.setVisible(false);
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//               // something for checking timer
//            }
//        });
//        thread.start();
    }

    public void btn04OnAction(ActionEvent actionEvent) {
        getMoneyPane.toFront();
        pane04.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        invalidPane04.setVisible(false);
        infoPane04.setVisible(false);
        accountNumberPane04.setText("");
        amountPane04.setText("");
        validPane12.setVisible(false);
    }

    public void btn05OnAction(ActionEvent actionEvent) {
        changePassPane.toFront();
        pane05.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        validPane12.setVisible(false);
    }

    public void btn06OnAction(ActionEvent actionEvent) {
        getChecksPane.toFront();
        pane06.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        validPane06.setVisible(false);
        invalidPane06.setVisible(false);
        accountNumberPane06.setText("");
        checkAmountPane06.setText("");
        validPane12.setVisible(false);
    }

    public void btn07OnAction(ActionEvent actionEvent) {
        reachCheckPane.toFront();
        pane07.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        validPane12.setVisible(false);
    }

    public void btn08OnAction(ActionEvent actionEvent) {
        getCardPane.toFront();
        pane08.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        validPane08.setVisible(false);
        invalidPane08.setVisible(false);
        second_passPane08.setText("");
        accountNumberPane08.setText("");
        primary_balancePane08.setText("");
        validPane12.setVisible(false);
    }

    public void btn09OnAction(ActionEvent actionEvent) {
        getLoanPane.toFront();
        pane09.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        validPane12.setVisible(false);
    }

    public void btn10OnAction(ActionEvent actionEvent) {
        payLoanPane.toFront();
        pane10.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        validPane12.setVisible(false);
    }

    public void btn00OnAction(ActionEvent actionEvent) {
        profilePane.toFront();
        pane00.setVisible(true);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane11.setVisible(false);
        pane12.setVisible(false);
        saveLabelPro.setVisible(false);
        loadProfile.setVisible(false);
        validPane12.setVisible(false);
    }

    public void btn11OnAction(ActionEvent actionEvent) {
        createAccountPane.toFront();
        pane11.setVisible(true);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
        pane12.setVisible(false);
        validPane11.setVisible(false);
        invalidPane11.setVisible(false);
        primary_balance.setText("");
        second_pass.setText("");
        toggleGroup.selectToggle(null);
        validPane12.setVisible(false);
    }

    public void btn12OnAction(ActionEvent actionEvent) {
        payBillPane.toFront();
        pane12.setVisible(true);
        pane11.setVisible(false);
        pane00.setVisible(false);
        pane01.setVisible(false);
        pane02.setVisible(false);
        pane03.setVisible(false);
        pane04.setVisible(false);
        pane05.setVisible(false);
        pane06.setVisible(false);
        pane07.setVisible(false);
        pane08.setVisible(false);
        pane09.setVisible(false);
        pane10.setVisible(false);
    }

    //create new account pane
    public void cPane11Action(ActionEvent actionEvent) {
        if (isValidNumber(primary_balance) && isAccountTypeValid() && !second_pass.getText().isBlank()) {
            connectivity.addNewAccount(bankServices.createNewAccount(person, getAccountType(), second_pass.getText()), person, Integer.parseInt(primary_balance.getText()));
            validPane11.setVisible(true);
            invalidPane11.setVisible(false);
            primary_balance.setText("");
            second_pass.setText("");
            toggleGroup.selectToggle(null);
        }
        else {
            invalidPane11.setVisible(true);
            validPane11.setVisible(false);
        }
    }

    public AccountType getAccountType(){
        if (toggleGroup.getToggles().get(0).equals(toggleGroup.getSelectedToggle()))
            return AccountType.CURRENT_ACCOUNT;
        else if (toggleGroup.getToggles().get(1).equals(toggleGroup.getSelectedToggle()))
            return AccountType.BORROW_ACCOUNT;
        else if (toggleGroup.getToggles().get(2).equals(toggleGroup.getSelectedToggle()))
            return AccountType.SAVING_ACCOUNT;
        else if (toggleGroup.getToggles().get(3).equals(toggleGroup.getSelectedToggle()))
            return AccountType.LONG_TERM_ACCOUNT;
        else return AccountType.SMALL_TERM_ACCOUNT;
    }

    public boolean isValidNumber(JFXTextField jfxTextField){
        try{
            Integer.parseInt(jfxTextField.getText());
            if (!jfxTextField.getText().isBlank())
                return true;
            else return false;
        } catch (Exception e){
            return false;
        }
    }

    public boolean isAccountTypeValid(){
        for (int i = 0; i < 5; i++) {
            if (toggleGroup.getToggles().get(i).isSelected()){
                return true;
            }
        }
        return false;
    }

    //put money pane
    public void cPane01Action(ActionEvent actionEvent) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isAccountNumberValid(accountNumberPane01) && isValidNumber(amountPane01) && isMyAccountValid(accountNumberPane01)){
                    connectivity.setMoneyInAccount(Integer.parseInt(amountPane01.getText()), accountNumberPane01.getText());
                    Connectivity connectivity1 = new Connectivity(bill);
                    connectivity1.payingBillIsReady(accountNumberPane01.getText(), person);
                    invalidPane01.setVisible(false);
                    infoPane01.setVisible(true);
                    accountNumberPane01.setText("");
                    amountPane01.setText("");
                    cPane12Action(actionEvent);
                } else {
                    invalidPane01.setVisible(true);
                    infoPane01.setVisible(false);
                }
            }
        });
        thread.start();
    }

    public boolean isAccountNumberValid(JFXTextField jfxTextField){
        if (connectivity.searchAccountNumber(jfxTextField.getText()))
            return true;
        return false;
    }

    public boolean isMyAccountValid(JFXTextField jfxTextField){
        if (connectivity.isMyAccount(jfxTextField.getText(), person))
            return true;
        return false;
    }

    //get money pane
    public void cPane04Action(ActionEvent actionEvent) {
        if (isAccountNumberValid(accountNumberPane04) && isValidNumber(amountPane04) && isMyAccountValid(accountNumberPane04)){
            connectivity.reduceMoneyFromAccount(Integer.parseInt(amountPane04.getText()), accountNumberPane04.getText());
            invalidPane04.setVisible(false);
            infoPane04.setVisible(true);
            accountNumberPane04.setText("");
            amountPane04.setText("");
        } else {
            invalidPane04.setVisible(true);
            infoPane04.setVisible(false);
        }
    }

    //get card pane
    public void cPane08Action(ActionEvent actionEvent) {
        if (isAccountSuitableForCard(accountNumberPane08) && isValidNumber(primary_balancePane08) && !second_passPane08.getText().isBlank()){
            connectivity.addNewCard(bankServices.createNewCreditCard(person.createRandomCardNumber(), connectivity.getAccountTypeForCard(accountNumberPane08.getText()), second_passPane08.getText()), person, accountNumberPane08.getText(), Integer.parseInt(primary_balancePane08.getText()));
            invalidPane08.setVisible(false);
            validPane08.setVisible(true);
            accountNumberPane08.setText("");
        } else {
            invalidPane08.setVisible(true);
            validPane08.setVisible(false);
        }
    }

    public boolean isAccountSuitableForCard(JFXTextField jfxTextField){
        if (connectivity.isSuitableAccount(jfxTextField.getText(), person))
            return true;
        return false;
    }

    //get check pane
    public void cPane06Action(ActionEvent actionEvent) {
        if (connectivity.isAccountSuitableForCheck(accountNumberPane06.getText(), person) && isValidNumber(checkAmountPane06)){
            try {
                connectivity.createNewCheck(new Check(person, person.createRandomCheckNumber(), Integer.parseInt(checkAmountPane06.getText())), person, checkAmountPane06.getText(), accountNumberPane06.getText());
                validPane06.setVisible(true);
                invalidPane06.setVisible(false);
            } catch (InvalidCheck invalidCheck) {
                System.out.println(invalidCheck.toString());
                invalidPane06.setVisible(true);
            }
            accountNumberPane06.setText("");
            checkAmountPane06.setText("");
        } else {
            invalidPane06.setVisible(true);
            validPane06.setVisible(false);
        }
    }

    //profile pane
    public void updatePro(ActionEvent actionEvent) {
        String updatedAccounts = "";
        String updatedCards = "";
        String updatedChecks = "";
        for (int i = 0; i < connectivity.updateProfileAccounts(person).length; i++) {
            updatedAccounts += connectivity.updateProfileAccounts(person)[i];
            if (!connectivity.updateProfileAccounts(person)[i].equals(""))
                updatedAccounts += ", ";
        }
        for (int i = 0; i < connectivity.updateProfileCards(person).length; i++) {
            updatedCards += connectivity.updateProfileCards(person)[i];
            if (!connectivity.updateProfileCards(person)[i].equals(""))
                updatedCards += ", ";
        }
        for (int i = 0; i < connectivity.updateProfileChecks(person).length; i++) {
            updatedChecks += connectivity.updateProfileChecks(person)[i];
            if (!connectivity.updateProfileChecks(person)[i].equals(""))
                updatedChecks += ", ";
        }
        proAccount.setText(updatedAccounts);
        proCard.setText(updatedCards);
        proCheck.setText(updatedChecks);

        loadProfile.setVisible(true);
    }


    public void savePro(ActionEvent actionEvent) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("info.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(profileInfo);
        outputStream.close();
        fileOutputStream.close();
        saveLabelPro.setVisible(true);
    }

    public void loadPro() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("info.txt");
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        ProfileInfo newProfileInfo = (ProfileInfo) inputStream.readObject();
        inputStream.close();
        fileInputStream.close();
        loadProfile.setVisible(true);
    }

    //pay bill pane
    public void cPane12Action(ActionEvent actionEvent) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isMyAccountValid(accountNumberPane12) && isValidNumber(bill_payment)){
                    try {
                        bill = new Bill(Integer.parseInt(bill_payment.getText()), billCode.getText());
                        Connectivity connectivity1 = new Connectivity(bill);
                        connectivity1.payBill(accountNumberPane12.getText(), person);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (bill.getPayed())
                        validPane12.setVisible(true);
                } else {
                    invalidPane12.setVisible(true);
                    validPane12.setVisible(false);
                }
            }
        });
        thread.start();
    }

    //account to account pane
    public void cPane02Action(ActionEvent actionEvent) {
        if (isMyAccountValid(firstAccountPane02) && isAccountNumberValid(secondAccountPane02) && isValidNumber(moneyPane02)){
             connectivity.accountToAccount(firstAccountPane02.getText(), secondAccountPane02.getText(), Integer.parseInt(moneyPane02.getText()));
             invalidPane02.setVisible(false);
             validPane02.setVisible(true);
             firstAccountPane02.setText("");
             secondAccountPane02.setText("");
             moneyPane02.setText("");
        } else {
            invalidPane02.setVisible(true);
            validPane02.setVisible(false);
        }
    }
}
