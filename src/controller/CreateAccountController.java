package controller;

import controller.supermercado.CadastroController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.Cliente;
import principal.Principal;

public class CreateAccountController implements Initializable {

    @FXML
    private TextField usernameInput, emailInput, nameInput, CPFInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label alertName, alertEmail, alertUsername, alertCPF, alertPassword;
    @FXML
    private ImageView profileImage = new ImageView();

    private FileChooser fc = new FileChooser();
    private File imageFile = new File("src/imagens/usuario.png");

    @FXML
    private void uploadImage(ActionEvent event) {
        fc = new FileChooser();
        imageFile = fc.showOpenDialog(null);

        if (imageFile == null) {
            imageFile = new File("src/imagens/usuario.png");
            return;
        }

        try {
            BufferedImage bi = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bi, null);
            profileImage.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void onCreate(ActionEvent event) throws Exception {
        if (!verifyFields()) {
            return;
        }

        String nameText, emailText, usernameText, passwordText, cpfText;

        nameText = nameInput.getText();
        cpfText = CPFInput.getText();
        emailText = emailInput.getText();
        usernameText = usernameInput.getText();
        passwordText = passwordInput.getText();

        new Cliente(nameText, usernameText, passwordText, cpfText, emailText, imageFile);

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Conta cadastrada com sucesso!");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        back(event);
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        StageController.openScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearAlerts();
    }

    private void clearAlerts() {
        alertName.setText("");
        alertUsername.setText("");
        alertEmail.setText("");
        alertCPF.setText("");
        alertPassword.setText("");
    }

    private boolean verifyFields() {
        clearAlerts();

        String nameText, emailText, usernameText, passwordText, CPFText;

        nameText = nameInput.getText().trim();
        CPFText = CPFInput.getText().trim();
        emailText = emailInput.getText().trim();
        usernameText = usernameInput.getText().trim();
        passwordText = passwordInput.getText().trim();

        for (Cliente client : Principal.supermarketData.getClients()) {
            if (client.getUsername().equals(usernameText)) {
                alertUsername.setText("Nome de usuário já está em uso");
                return false;
            }

            if (client.getEmail().equals(emailText)) {
                alertEmail.setText("Email já está em uso");
                return false;
            }

            if (client.getCpf().equals(CPFText)) {
                alertCPF.setText("CPF já está em uso");
                return false;
            }
        }

        if (usernameText.equals("")) {
            alertUsername.setText("Campo obrigatório");
            return false;
        }

        if (nameText.equals("")) {
            alertName.setText("Campo obrigatório");
            return false;
        }

        if (CPFText.equals("") || CPFText.length() < 11) {
            alertCPF.setText("CPF inválido");
            return false;
        }

        if (emailText.equals("") || !emailText.contains("@")) {
            alertEmail.setText("Email inválido");
            return false;
        }

        if (passwordText.equals("") || passwordText.length() < 6) {
            alertPassword.setText("Senha deve conter no mínimo 6 caracteres");
            return false;
        }

        return true;
    }
}
