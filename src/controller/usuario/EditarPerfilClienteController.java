package controller.usuario;

import controller.LoginController;
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
import model.Supermercado;
import model.Usuario;
import principal.Principal;
import static principal.Principal.root;

public class EditarPerfilClienteController implements Initializable {

    @FXML
    private TextField nome, cpf, email, nomeUsuario;
    @FXML
    private PasswordField senha;

    @FXML
    private ImageView imagem = new ImageView();

    @FXML
    private Label erroNome, erroNomeUsuario, erroSenha, erroEmail, erroCpf;

    Usuario cliente = LoginController.uN;

    // Declaração do selecionador de arquivo
    FileChooser fc = new FileChooser();
    // Declaração e inicialização do arquivo onde irá receber o arquivo selecionado
    File arquivoImagem = new File(cliente.getUrlImagem());

    ;

    // Metodo que seleciona uma imagem em algum diretório do computador
    @FXML
    private void adicionarImagem(ActionEvent event) {
        fc = new FileChooser();
        arquivoImagem = fc.showOpenDialog(null);

        if (arquivoImagem == null) {
            arquivoImagem = new File(cliente.getUrlImagem());
            return;
        }

        try {
            BufferedImage bi = ImageIO.read(arquivoImagem);
            Image image = SwingFXUtils.toFXImage(bi, null);
            imagem.setImage(image);
            imagem.setFitHeight(100);
            imagem.setFitWidth(100);
        } catch (IOException ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void confirmar(ActionEvent event) throws IOException {
        cliente.setNome(nome.getText());
        cliente.setUserName(nomeUsuario.getText());
        cliente.setCpf(cpf.getText());
        cliente.setEmail(email.getText());
        cliente.setSenha(senha.getText());
        if (arquivoImagem != null) {
            cliente.setImagem(arquivoImagem);
        }

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Perfil editado com sucesso!");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        // Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @FXML
    public void verificaNome(KeyEvent event) {
        if (nome.getText().equals("")) {
            erroNome.setVisible(true);
            nome.setStyle("-fx-border-color: red;"
                    + "-fx-background-radius: 50px;"
                    + "-fx-border-radius: 50px;");
        } else {
            erroNome.setVisible(false);
            nome.setStyle("-fx-border-color: #00FF9A;"
                    + "-fx-background-radius: 50px;"
                    + "-fx-border-radius: 50px;");
        }
    }

    @FXML
    public void verificaNomeUsuario(KeyEvent event) {
        for (Usuario u : Supermercado.getUsers()) {
            if (u.getUserName().equals(nomeUsuario.getText())) {
                erroNomeUsuario.setVisible(true);
                erroNomeUsuario.setText("Nome de Usuário já existe");
                nomeUsuario.setStyle("-fx-border-color: red;"
                        + "-fx-background-radius: 50px;"
                        + "-fx-border-radius: 50px;");
                return;
            } else if (nomeUsuario.getText().equals("")) {
                erroNomeUsuario.setVisible(true);
                erroNomeUsuario.setText("Campo obrigatório");
                nomeUsuario.setStyle("-fx-border-color: red;"
                        + "-fx-background-radius: 50px;"
                        + "-fx-border-radius: 50px;");
                return;
            } else {
                erroNomeUsuario.setVisible(false);
                nomeUsuario.setStyle("-fx-border-color: #00FF9A;"
                        + "-fx-background-radius: 50px;"
                        + "-fx-border-radius: 50px;");
            }
        }
    }

    @FXML
    public void verificaCpf(KeyEvent event) {
        for (Usuario u : Supermercado.getUsers()) {
            if (u.getCpf() == null) {
                return;
            }
            if (u.getCpf().equals(cpf.getText())) {
                erroCpf.setVisible(true);
                erroCpf.setText("CPF já existe");
                cpf.setStyle("-fx-border-color: red;"
                        + "-fx-background-radius: 50px;"
                        + "-fx-border-radius: 50px;");
                return;
            } else {
                erroCpf.setVisible(false);
                cpf.setStyle("-fx-border-color: #00FF9A;"
                        + "-fx-background-radius: 50px;"
                        + "-fx-border-radius: 50px;");
            }
        }

        if (cpf.getLength() > 11 || cpf.getLength() < 11) {
            erroCpf.setVisible(true);
            erroCpf.setText("CPF inválido");
            cpf.setStyle("-fx-border-color: red;"
                    + "-fx-background-radius: 50px;"
                    + "-fx-border-radius: 50px;");
        } else {
            erroCpf.setVisible(false);
            cpf.setStyle("-fx-border-color: #00FF9A;"
                    + "-fx-background-radius: 50px;"
                    + "-fx-border-radius: 50px;");
        }
    }

    @FXML
    public void verificaEmail(KeyEvent event) {
        for (Usuario u : Supermercado.getUsers()) {
            if (u.getEmail() == null) {
                return;
            }
            if (u.getEmail().equals(email.getText())) {
                erroEmail.setVisible(true);
                erroEmail.setText("Email já cadastrado");
                email.setStyle("-fx-border-color: red;"
                        + "-fx-background-radius: 50px;"
                        + "-fx-border-radius: 50px;");
                return;
            } else {
                erroNomeUsuario.setVisible(false);
                email.setStyle("-fx-border-color: #00FF9A;"
                        + "-fx-background-radius: 50px;"
                        + "-fx-border-radius: 50px;");
            }
        }

    }

    @FXML
    public void verificaSenha(KeyEvent event) {
        if (senha.getText().equals("")) {
            senha.setStyle("-fx-border-color: red;"
                    + "-fx-background-radius: 50px;"
                    + "-fx-border-radius: 50px;");
            return;
        } else {
            erroSenha.setVisible(false);
            senha.setStyle("-fx-border-color: #00FF9A;"
                    + "-fx-background-radius: 50px;"
                    + "-fx-border-radius: 50px;");
        }
    }

    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        // Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nome.setText(cliente.getNome());
        nomeUsuario.setText(cliente.getUserName());
        cpf.setText(cliente.getCpf());
        email.setText(cliente.getEmail());
        imagem.setImage(cliente.getImagem().getImage());
    }
}
