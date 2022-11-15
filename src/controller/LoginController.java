package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    public static User uN;

    public void acceptLogin(String type, String userName) {
        for (User user : Principal.supermarketData.getUsers()) {
            if (userName.equals(user.getUsername())) {
                uN = user;
            }
        }
        switch (type) {
            case "admin":
                try {
                    StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));
                    StageController.openScreen();
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            case "cliente":
                try {
                    StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));
                    StageController.openScreen();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            case "entregador":
                try {
                    StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaEntregador.fxml"));
                    StageController.openScreen();
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            default:
                break;
        }
    }

    @FXML
    public void enter() throws IOException, InterruptedException {
        String usernameText = username.getText();
        String passwordText = password.getText();

        if (!validFields()) {
            return;
        }

        for (User user : Principal.supermarketData.getUsers()) {
            if (user.getUsername().equals(usernameText)) {
                if (user.getPassword().equals(passwordText)) {
                    acceptLogin(user.getType(), user.getUsername());
                    return;
                } else {
                    alert.setText("Senha incorreta");
                    clearPassword();
                    return;
                }
            } else {
                alert.setText("Usuário não existe");
                clearUsername();
            }
        }
    }

    @FXML
    public void enterKey(KeyEvent event) throws IOException, InterruptedException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            enter();
        }
    }

    public boolean validFields() {
        String usernameText = username.getText();
        String passwordText = password.getText();

        if (usernameText == "") {
            alert.setText("Informe o nome de usuário");
            clearUsername();
            return false;
        }

        if (passwordText == "") {
            alert.setText("Informe a senha");
            clearUsername();
            return false;
        }

        return true;
    }

    @FXML
    public void createAccount(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/CriarConta.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void forgotPassword(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/RedefinirSenha.fxml"));
        StageController.openScreen();
    }

    public void clearUsername() {
        username.setText("");
    }

    public void clearPassword() {
        password.setText("");
    }

    @FXML
    private void clearAlert(KeyEvent event) {
        alert.setText("");
    }

    @FXML
    private void closeApp(ActionEvent event) {
        StageController.stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}