package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.User;
import principal.Principal;

public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label alert;
    @FXML
    private CheckBox stayConnectedSelection;

    @FXML
    public void enter() throws IOException, InterruptedException {
        String usernameText = username.getText();
        String passwordText = password.getText();

        if (!validFields())
            return;

        for (User user : Principal.supermarketData.getUsers()) {
            if (user.getUsername().equals(usernameText) && user.getPassword().equals(passwordText)) {
                boolean stayConnected = stayConnectedSelection.isSelected();
                acceptLogin(user, stayConnected);
                return;
            }

            alert.setText("Usuário ou senha inválida");
            clearUsername();
        }
    }

    @FXML
    public void enterKey(KeyEvent event) throws IOException, InterruptedException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            enter();
        }
    }

    @FXML
    public void createAccount(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/CreateAccount.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void forgotPassword(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/RedefinirSenha.fxml"));
        StageController.openScreen();
    }

    @FXML
    private void clearAlert(KeyEvent event) throws Exception {
        if (!alert.getText().equals("")) {
            TimeUnit.SECONDS.sleep(1);
            alert.setText("");
        }
    }

    @FXML
    private void closeApp(ActionEvent event) {
        StageController.stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Principal.supermarketData.logUser(null, true);
    }

    private void clearUsername() {
        username.setText("");
        password.setText("");
    }

    private boolean validFields() {
        String usernameText = username.getText().trim();
        String passwordText = password.getText().trim();

        if (usernameText.equals("")) {
            alert.setText("Informe o nome de usuário");
            return false;
        }

        if (passwordText.equals("")) {
            alert.setText("Informe a senha");
            return false;
        }

        return true;
    }

    private void acceptLogin(User user, boolean stayConnected) {
        Principal.supermarketData.logUser(user, stayConnected);

        System.out.println(user.getName());
        System.out.println(user.getType());

        try {
            if (user.getType().equals(User.TYPE_ADMIN))
                StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));

            if (user.getType().equals(User.TYPE_CLIENT))
                StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));

            if (user.getType().equals(User.TYPE_DELIVERY_PEOPLE))
                StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaEntregador.fxml"));

            StageController.openScreen();
        } catch (Exception e) {
            alert.setText("Erro ao conectar usuário");
        }
    }

}