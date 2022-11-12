package controller;

import principal.Principal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Supermercado;
import model.Usuario;
import static principal.Principal.root;

public class LoginController implements Initializable {

    public static Usuario uN;

    //Campo de texto para colocar o nome do usuario
    @FXML
    private TextField userName;
    //Campo de Senha para colocar a senha
    @FXML
    private PasswordField senha;
    //Rotulo para colocar texto que indica erro login
    @FXML
    private Label erros;

    //Metodo que loga no conta do cliente ou administrador, dependendo do tipo recebido
    public void loginAceito(String tipo, String userName) {
        for(Usuario u : Supermercado.getUsers()){
            if(userName.equals(u.getUserName())){
                uN = u;
            }
        }
        switch (tipo) {
            //caso seja tipo admin, loga na tela do admnistrador
            case "admin":
                try {
                    Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));
                    Scene cena1 = new Scene(root);
                    Principal.palco.setScene(cena1);
                    Principal.palco.show();
                    //Colocar palco no centro da tela
                    Principal.palco.centerOnScreen();
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            //caso seja tipo cliente, loga na tela do cliente
            case "cliente":

                try {
                    Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));
                    Scene cena2 = new Scene(root);
                    Principal.palco.setScene(cena2);
                    Principal.palco.setTitle(userName);
                    Principal.palco.show();
                    //Colocar palco no centro da tela
                    Principal.palco.centerOnScreen();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            case "entregador":
                try {
                    Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaEntregador.fxml"));
                    Scene cena3 = new Scene(root);
                    Principal.palco.setScene(cena3);
                    Principal.palco.show();
                    //Colocar palco no centro da tela
                    Principal.palco.centerOnScreen();
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            default:
                break;
        }

    }

    //Metodo que confirma o login quando o usuário clica no botão 'entrar'
    @FXML
    public void entrar() throws IOException, InterruptedException {
        String u, s;
        u = userName.getText();
        s = senha.getText();
        for (Usuario user : Supermercado.getUsers()) {
            if (user.getUserName().equals(u)) {
                if (user.getSenha().equals(s)) {
                    loginAceito(user.getTipo(), user.getUserName());
                    return;
                } else {
                    erros.setText("Senha incorreta");
                    limpar();
                    return;
                }
            } else {
                erros.setText("Usuário não existe");
                limpar();
            }
        }
    }

    //Metodo que confirma o login quando o usuário pressiona a tecla ENTER
    @FXML
    public void entrarKey(KeyEvent event) throws IOException, InterruptedException {
        if (event.getCode().equals(KeyCode.ENTER) && !"".equals(userName.getText()) && !"".equals(senha.getText())) {
            String u, s;
            u = userName.getText();
            s = senha.getText();
            for (Usuario user : Supermercado.getUsers()) {
                if (user.getUserName().equals(u)) {
                    if (user.getSenha().equals(s)) {
                        loginAceito(user.getTipo(), user.getUserName());
                        return;
                    } else {
                        erros.setText("Senha incorreta");
                        limpar();
                        return;
                    }
                } else {
                    erros.setText("Usuário não existe");
                    limpar();
                }
            }
        }

    }

    //Metodo que abre a opção de criar uma nova conta, quando o usuario clica no botão 'Criar conta'
    @FXML
    public void criarConta(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/CriarConta.fxml"));
        Scene cena2 = new Scene(root);
        Principal.palco.setScene(cena2);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @FXML
    public void esqueceuSenha(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/RedefinirSenha.fxml"));
        Scene cena2 = new Scene(root);
        Principal.palco.setScene(cena2);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    //Metodo que limpa as caixas de texto da tela
    public void limpar() {
        senha.setText("");
        userName.setText("");
    }

    //Limpa o rotulo de erro da tela
    @FXML
    private void limparCampo(KeyEvent event) {
        erros.setText("");
    }

    //Fecha o palco do programa, assim encerrando a execussão
    @FXML
    private void sair(ActionEvent event) {
        Principal.palco.close();
    }

    //Metodo inicial da tela
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}