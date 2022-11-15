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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.Cliente;
import model.User;
import principal.Principal;

public class CriarContaController implements Initializable {

    @FXML
    private TextField nome, email, nomeUsuario, cpf;
    @FXML
    private PasswordField senha;
    @FXML
    private Label erroNome, erroEmail, erroNomeUsuario, erroCpf, erroSenha;
    @FXML
    private ImageView imagem = new ImageView();

    // Declaração do selecionador de arquivo
    FileChooser fc = new FileChooser();
    // Declaração e inicialização do arquivo onde irá receber o arquivo selecionado
    File arquivoImagem = new File("src/imagens/usuario.png");

    // Metodo que seleciona uma imagem em algum diretório do computador
    @FXML
    private void adicionarImagem(ActionEvent event) {
        fc = new FileChooser();
        arquivoImagem = fc.showOpenDialog(null);

        if (arquivoImagem == null) {
            arquivoImagem = new File("src/imagens/usuario.png");
            return;
        }

        try {
            BufferedImage bi = ImageIO.read(arquivoImagem);
            Image image = SwingFXUtils.toFXImage(bi, null);
            imagem.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void confirmar(ActionEvent event) {
        KeyEvent evento = null;
        verificaNome(evento);
        verificaNomeUsuario(evento);
        verificaEmail(evento);
        verificaCpf(evento);
        verificaSenha(evento);
        String n, e, u, se, c;
        n = nome.getText();
        c = cpf.getText();
        e = email.getText();
        u = nomeUsuario.getText();
        se = senha.getText();
        if (erroNome.isVisible() && erroEmail.isVisible() && erroNomeUsuario.isVisible() && erroCpf.isVisible()
                && erroSenha.isVisible()) {
            verificaNome(evento);
            verificaNomeUsuario(evento);
            verificaEmail(evento);
            verificaCpf(evento);
            verificaSenha(evento);
        } else {
            new Cliente(n, u, se, c, e, arquivoImagem);

            Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
            confirmacao.setTitle("Confirmação");
            confirmacao.setHeaderText("");
            confirmacao.setContentText("Conta cadastrada com sucesso!");
            confirmacao.setResult(ButtonType.OK);
            confirmacao.showAndWait();

            try {
                sair(event);
            } catch (IOException ex) {
                Logger.getLogger(CriarContaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void sair(ActionEvent event) throws IOException {
        Principal.palco.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        Principal.palco.centerOnScreen();
    }

    @FXML
    public void verificaNome(KeyEvent event) {
        // se esse campo estiver vazio, ira sinalizar o erro ate que digite algo
        if (nome.getText().equals("")) {
            erroNome.setVisible(true);
            nome.setStyle("-fx-background-color: #FFFFFF;\n" +
                    "-fx-border-color: #FF0000;");
        } else {
            erroNome.setVisible(false);
            nome.setStyle("-fx-background-color: #FFFFFF;\n" +
                    "-fx-border-color: #00FF9A;");
        }
    }

    @FXML
    public void verificaNomeUsuario(KeyEvent event) {
        for (User u : Principal.supermarketData.getUsers()) {
            if (u.getUsername().equals(nomeUsuario.getText())) {
                erroNomeUsuario.setVisible(true);
                erroNomeUsuario.setText("Nome de Usuário já existe");
                nomeUsuario.setStyle("-fx-background-color: #FFFFFF;\n" +
                        "-fx-border-color: #FF0000;");
                return;
            } else if (nomeUsuario.getText().equals("")) {
                erroNomeUsuario.setVisible(true);
                erroNomeUsuario.setText("Campo obrigatório");
                nomeUsuario.setStyle("-fx-background-color: #FFFFFF;\n" +
                        "-fx-border-color: #FF0000;");
                return;
            } else {
                erroNomeUsuario.setVisible(false);
                nomeUsuario.setStyle("-fx-background-color: #FFFFFF;\n" +
                        "-fx-border-color: #00FF9A;");
            }
        }
    }

    @FXML
    public void verificaCpf(KeyEvent event) {
        for (Cliente u : Principal.supermarketData.getClients()) {
            if (u.getCpf() == null) {
                return;
            }
            if (u.getCpf().equals(cpf.getText())) {
                erroCpf.setVisible(true);
                erroCpf.setText("CPF já existe");
                cpf.setStyle("-fx-background-color: #FFFFFF;\n" +
                        "-fx-border-color: #FF0000;");
                return;
            } else {
                erroCpf.setVisible(false);
                cpf.setStyle("-fx-background-color: #FFFFFF;\n" +
                        "-fx-border-color: #00FF9A;");
            }
        }

        if (cpf.getLength() > 11 || cpf.getLength() < 11) {
            erroCpf.setVisible(true);
            erroCpf.setText("CPF inválido");
            cpf.setStyle("-fx-background-color: #FFFFFF;\n" +
                    "-fx-border-color: #FF0000;");
        } else {
            erroCpf.setVisible(false);
            cpf.setStyle("-fx-background-color: #FFFFFF;\n" +
                    "-fx-border-color: #00FF9A;");
        }
    }

    @FXML
    public void verificaEmail(KeyEvent event) {
        for (Cliente c : Principal.supermarketData.getClients()) {
            if (c.getEmail() == null) {
                return;
            }
            if (c.getEmail().equals(email.getText())) {
                erroEmail.setVisible(true);
                erroEmail.setText("Email já cadastrado");
                email.setStyle("-fx-background-color: #FFFFFF;\n" +
                        "-fx-border-color: #FF0000;");
                return;
            } else {
                erroNomeUsuario.setVisible(false);
                erroEmail.setText("");
                email.setStyle("-fx-background-color: #FFFFFF;\n" +
                        "-fx-border-color: #00FF9A;");
            }
        }
    }

    @FXML
    public void verificaSenha(KeyEvent event) {
        if (senha.getText().equals("")) {
            senha.setStyle("-fx-background-color: #FFFFFF;\n" +
                    "-fx-border-color: #FF0000;");
            return;
        } else {
            erroNomeUsuario.setVisible(false);
            senha.setStyle("-fx-background-color: #FFFFFF;\n" +
                    "-fx-border-color: #00FF9A;");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
